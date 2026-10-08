#!/usr/bin/env python3
"""Generate or validate the complete MailRu Desktop UI element and window catalog.

Run from repository root:
    python tools/ui_registry.py --write
    python tools/ui_registry.py --check

An element is a declared XAML node or a WPF control created in C#.
The catalog is a generated snapshot: no separate manual list of controls
that can silently diverge from the actual application.
"""
from __future__ import annotations

import argparse
import json
import re
import sys
import xml.etree.ElementTree as ET
from collections import defaultdict
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
SOURCE = ROOT / "src" / "MailRuDesktop.App"
OUTPUT = ROOT / "docs" / "ui-registry.json"
X_NS = "{http://schemas.microsoft.com/winfx/2006/xaml}"
ATTR_LAYOUT = {
    "Width", "Height", "MinWidth", "MaxWidth", "MinHeight", "MaxHeight",
    "Margin", "Padding", "HorizontalAlignment", "VerticalAlignment",
    "Grid.Row", "Grid.Column", "Grid.RowSpan", "Grid.ColumnSpan",
    "DockPanel.Dock", "Orientation", "ResizeMode", "WindowStartupLocation",
    "Top", "Left", "ColumnDefinitions", "RowDefinitions", "Visibility",
    "HorizontalContentAlignment", "VerticalContentAlignment",
}
ATTR_TEXT = {"FontSize", "FontFamily", "FontWeight", "FontStyle", "TextAlignment"}
ATTR_COLOR = {
    "Background", "Foreground", "BorderBrush", "Fill", "Stroke", "Color",
    "CaretBrush", "SelectionBrush", "SelectionTextBrush",
}
WPF_RUNTIME_TYPES = {
    "Window", "Button", "CheckBox", "TextBox", "RichTextBox", "Border",
    "Grid", "StackPanel", "DockPanel", "Canvas", "TextBlock", "ComboBox",
    "ListBox", "ListBoxItem", "RadioButton", "Slider", "Thumb", "Image",
    "Rectangle", "Ellipse", "ScrollViewer", "Expander", "GroupBox",
    "Popup", "MenuItem", "ContextMenu", "DatePicker", "Separator",
    "WrapPanel", "UniformGrid", "GridSplitter", "TabControl", "TabItem",
    "FlowDocument", "Paragraph", "Run", "Hyperlink", "ToolTip",
}
CONTROL_CTOR = re.compile(
    r"\bnew\s+(?:System\.Windows\.Controls\.)?"
    r"(" + "|".join(sorted(WPF_RUNTIME_TYPES, key=len, reverse=True)) + r")"
    r"\s*(?:\{|\()"
)
WINDOW_CLASS = re.compile(
    r"\bclass\s+(\w+)\s*:\s*(?:System\.Windows\.)?Window\b"
)


def local_name(name: str) -> str:
    return name.split("}", 1)[-1].split(":", 1)[-1]


def source_files(extension: str):
    return sorted(
        path for path in SOURCE.rglob("*" + extension)
        if "obj" not in path.parts and "bin" not in path.parts
    )


def attr_map(node: ET.Element, keys: set[str]) -> dict[str, str]:
    return {
        local_name(name): value
        for name, value in node.attrib.items()
        if local_name(name) in keys
    }


def summarize_xml(source: str, xml: str, owner: str, output: list[dict]) -> None:
    try:
        root = ET.fromstring(xml)
    except ET.ParseError as ex:
        raise ValueError(f"Invalid UI markup {source}: {ex}") from ex

    def walk(node: ET.Element, parent: str, sibling_position: int) -> None:
        tag = local_name(node.tag)
        name = node.attrib.get(X_NS + "Name") or node.attrib.get("Name")
        identity = name or f"{tag}[{sibling_position}]"
        ident = parent + "/" + identity if parent else owner + "/" + source + "/" + identity
        # The root is a Window/ResourceDictionary; named descendants are
        # indexed by their stable names, anonymous nodes by tree position.
        styles = {}
        if tag in {"Style", "ControlTemplate", "DataTemplate"}:
            styles = {
                "TargetType": node.attrib.get("TargetType", ""),
                "Key": node.attrib.get(X_NS + "Key", ""),
            }
        if tag == "Trigger" or tag.endswith("Trigger"):
            styles = attr_map(node, {"Property", "Value", "SourceName"})

        entry = {
            "id": ident,
            "window": owner,
            "source": source,
            "type": tag,
            "parent": parent or None,
            "name": name,
            "location": attr_map(node, ATTR_LAYOUT),
            "color_roles_and_values": attr_map(node, ATTR_COLOR),
            "typography": attr_map(node, ATTR_TEXT),
            "style": node.attrib.get("Style") or node.attrib.get(X_NS + "Key"),
            "states": styles or None,
        }
        output.append(entry)
        for i, child in enumerate(node):
            walk(child, ident, i)

    walk(root, "", 0)


def scan() -> dict:
    windows: dict[str, dict] = {}
    elements: list[dict] = []
    relocated_workspace_window: str | None = None

    style_sources = []
    for file in source_files(".xaml"):
        relative = file.relative_to(ROOT).as_posix()
        root = ET.parse(file).getroot()
        if local_name(root.tag) == "Window":
            cls = root.attrib.get(X_NS + "Class", file.stem)
            owner = cls.rsplit(".", 1)[-1]
            windows[owner] = {
                "id": owner, "source": relative, "declared_as": "XAML",
                "geometry": attr_map(root, ATTR_LAYOUT),
                "typography": attr_map(root, ATTR_TEXT),
                "colors": attr_map(root, ATTR_COLOR),
            }
        else:
            # Application.xaml and every ResourceDictionary define shared
            # colors, font sizes, styles and interactive visual states.
            owner = "ApplicationStyles"
            style_sources.append(relative)
        summarize_xml(relative, file.read_text(encoding="utf-8"), owner, elements)

    for file in source_files(".cs"):
        relative = file.relative_to(ROOT).as_posix()
        code = file.read_text(encoding="utf-8-sig")
        active_runtime_window: str | None = None
        for match in WINDOW_CLASS.finditer(code):
            cls = match.group(1)
            if cls not in windows:
                windows[cls] = {
                    "id": cls, "source": relative, "declared_as": "C#",
                    "geometry": {}, "typography": {}, "colors": {},
                }
        for match in re.finditer(r'"""(.*?)"""', code, flags=re.DOTALL):
            xml = match.group(1).strip()
            if xml.startswith("<ResourceDictionary") or xml.startswith("<DataTemplate"):
                summarize_xml(relative + "#generated-xaml:" + str(match.start()),
                              xml, "MainWindow", elements)

        for match in CONTROL_CTOR.finditer(code):
            kind = match.group(1)
            line = code.count("\n", 0, match.start()) + 1
            window = (file.name.split(".", 1)[0]
                      if file.name.startswith(("MainWindow.", "MessageWindow."))
                      else file.stem)
            if window == "AppDialog":
                windows.setdefault("AppDialog", {
                    "id": "AppDialog", "source": relative,
                    "declared_as": "C# dynamic dialogs",
                    "geometry": {}, "typography": {}, "colors": {},
                })
            snippet = code[match.end():match.end() + 350]
            inline = {}
            for prop in ATTR_LAYOUT | ATTR_TEXT | ATTR_COLOR:
                found = re.search(
                    r"\b" + re.escape(prop) + r"\s*=\s*"
                    r"([^,\n;}]+)", snippet)
                if found:
                    inline[prop] = found.group(1).strip()[:100]
            # Every instantiated Window is a real window, even when it is
            # created inside a MainWindow partial class rather than through
            # its own XAML or subclass. Register that window independently.
            if kind == "Window" and window != "AppDialog":
                runtime_id = f"{file.stem}/window@{line}"
                windows[runtime_id] = {
                    "id": runtime_id, "source": relative,
                    "declared_as": "C# runtime Window",
                    "geometry": {
                        k: v for k, v in inline.items() if k in ATTR_LAYOUT
                    },
                    "typography": {
                        k: v for k, v in inline.items() if k in ATTR_TEXT
                    },
                    "colors": {
                        k: v for k, v in inline.items() if k in ATTR_COLOR
                    },
                }
                active_runtime_window = runtime_id
                if "Content = ComposeWorkspace" in code[match.start():match.start() + 1200]:
                    relocated_workspace_window = runtime_id

            # Controls constructed after an inline window are children of
            # that runtime window rather than phantom MainWindow elements.
            if active_runtime_window and window != "AppDialog":
                window = active_runtime_window

            elements.append({
                "id": f"{window}/runtime/{relative}:{line}:{kind}",
                "window": window, "source": relative, "line": line,
                "type": kind, "parent": f"{window}/runtime",
                "name": None, "location": {
                    k: v for k, v in inline.items() if k in ATTR_LAYOUT
                }, "color_roles_and_values": {
                    k: v for k, v in inline.items() if k in ATTR_COLOR
                }, "typography": {
                    k: v for k, v in inline.items() if k in ATTR_TEXT
                }, "style": None, "states": None,
            })
    # ComposeWorkspace is declared in the MainWindow XAML namescope but
    # reparented into an independently owned compose window at runtime.
    # Preserve its design-time parent while recording the actual window host.
    if relocated_workspace_window:
        for entry in elements:
            if (entry["source"] == "src/MailRuDesktop.App/MainWindow.xaml"
                    and "ComposeWorkspace" in entry["id"]):
                entry["runtime_host_window"] = relocated_workspace_window

    # A shared resource is recorded once, along with windows and templates.
    # If source declares a Window that isn't in XAML, it is still registered.
    windows = dict(sorted(windows.items()))
    elements.sort(key=lambda e: e["id"])
    ids = [element["id"] for element in elements]
    if len(ids) != len(set(ids)):
        duplicates = sorted({x for x in ids if ids.count(x) > 1})
        raise ValueError("Duplicate UI ids: " + ", ".join(duplicates[:10]))

    palette_text = (SOURCE / "ThemePalette.cs").read_text(encoding="utf-8-sig")
    declared_roles = set(re.findall(r'new\("(App\w+Brush)"', palette_text))
    used_roles = set()
    for file in source_files(".xaml") + source_files(".cs"):
        code = file.read_text(encoding="utf-8-sig")
        used_roles.update(re.findall(
            r'\{(?:DynamicResource|StaticResource)\s+(App\w+Brush)\}', code))
    unresolved = sorted(used_roles - declared_roles)
    if unresolved:
        raise ValueError("Unknown palette color roles: " + ", ".join(unresolved))

    counts = defaultdict(int)
    for element in elements:
        counts[element["window"]] += 1
    for owner, row in windows.items():
        row["element_count"] = counts.get(owner, 0)

    return {
        "schema": 2,
        "generator": "tools/ui_registry.py",
        "color_reference_audit": {
            "declared_roles": len(declared_roles),
            "used_roles": sorted(used_roles),
            "unresolved_roles": unresolved,
        },
        "source_of_truth": "Application source: XAML + WPF controls instantiated in C#",
        "rules": {
            "user_can_change_role_values": True,
            "user_can_reassign_role_membership": False,
            "styles_source": style_sources,
            "all_windows_must_be_declared": True,
            "unregistered_elements_fail_ci": True,
            "changing_props_requires_registry_regeneration": True,
        },
        "windows": windows,
        "elements": elements,
    }


def main() -> int:
    parser = argparse.ArgumentParser()
    mode = parser.add_mutually_exclusive_group(required=True)
    mode.add_argument("--write", action="store_true")
    mode.add_argument("--check", action="store_true")
    args = parser.parse_args()

    current = scan()
    rendered = json.dumps(current, ensure_ascii=False, sort_keys=True, indent=2) + "\n"
    if args.write:
        OUTPUT.parent.mkdir(parents=True, exist_ok=True)
        OUTPUT.write_text(rendered, encoding="utf-8")
        print(f"Generated {OUTPUT.relative_to(ROOT)}: "
              f"{len(current['windows'])} windows, "
              f"{len(current['elements'])} UI elements")
        return 0

    if not OUTPUT.exists():
        print("Missing docs/ui-registry.json; run python tools/ui_registry.py --write",
              file=sys.stderr)
        return 1

    existing = OUTPUT.read_text(encoding="utf-8")
    if existing != rendered:
        print("UI element or window definitions changed without regenerating "
              "docs/ui-registry.json. Run python tools/ui_registry.py --write.",
              file=sys.stderr)
        return 1
    print(f"UI catalog verified: {len(current['windows'])} windows, "
          f"{len(current['elements'])} elements")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
