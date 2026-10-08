using System.Globalization;

namespace MailRuDesktop.App;

/// <summary>
/// Immutable mapping from UI elements/states to semantic roles.
/// Only HEX values are editable; users cannot remap a role to other controls.
/// Every theme exposes precisely the same 26 roles.
/// </summary>
internal sealed record ThemeColorRole(
    string Key,
    string Group,
    string Label,
    string AppliesTo,
    string DarkDefault,
    string LightDefault);

internal static class ThemePalette
{
    public static IReadOnlyList<ThemeColorRole> Roles { get; } =
    [
        new("AppWindowBrush", "Фоны", "Фон окна, панелей и диалогов",
            "Главное окно и свободные области приложения", "#17191D", "#FFFFFF"),
        new("AppPanelBrush", "Фоны", "Фон панелей",
            "Боковые панели, папки, списки, контейнеры и меню", "#1E2228", "#F7F9FC"),
        new("AppDialogBrush", "Фоны", "Фон диалогов",
            "Диалоговые окна, всплывающие панели и область выбора цвета", "#22272E", "#FFFFFF"),
        new("AppReaderBrush", "Фоны", "Фон письма",
            "Область HTML-письма и отдельное окно просмотра", "#17191D", "#FFFFFF"),
        new("AppInputBrush", "Фоны", "Фон полей ввода",
            "Поля ввода, поиск, редактор ответа и выбор дат", "#252A31", "#FFFFFF"),
        new("AppControlBrush", "Фоны", "Фон кнопок и полей ввода",
            "Обычные кнопки, переключатели и закрытые списки", "#252A31", "#F3F5F7"),

        new("AppControlHoverBrush", "Взаимодействие", "Наведение",
            "Строки и кнопки под указателем мыши", "#303640", "#E8EFF8"),
        new("AppControlPressedBrush", "Взаимодействие", "Фон нажатой кнопки",
            "Заливка обычных и навигационных кнопок при удержании мыши (IsPressed)", "#39414C", "#D8E5F4"),
        new("AppBorderBrush", "Взаимодействие", "Границы",
            "Рамки панелей, полей, таблиц и разделителей", "#353B45", "#D6DCE5"),
        new("AppFocusBrush", "Взаимодействие", "Контур фокуса",
            "Обводка элемента при управлении с клавиатуры", "#86BEFF", "#1769BC"),

        new("AppTextBrush", "Текст", "Основной текст",
            "Названия писем, получатели, заголовки и текст полей", "#F4F6F8", "#202124"),
        new("AppMutedTextBrush", "Текст", "Дополнительный текст",
            "Даты, фрагменты писем, пояснения и метаданные", "#AAB2BD", "#616B78"),
        new("AppDisabledTextBrush", "Текст", "Неактивный текст",
            "Недоступные команды и подсказки в отключённых элементах", "#7D8794", "#7A8491"),
        new("AppAccentTextBrush", "Текст", "Текст основных кнопок",
            "Текст и галочки поверх основного цвета действий", "#102235", "#FFFFFF"),
        new("AppLinkBrush", "Текст", "Ссылки",
            "Адреса и ссылки в просмотрщике HTML-писем", "#6CB6FF", "#0B57D0"),

        new("AppAccentBrush", "Выделение", "Основной цвет действий",
            "Главная кнопка отправки, флажки, активные команды", "#5AA7FF", "#0D6EFD"),
        new("AppSelectionBrush", "Выделение", "Фон выделения",
            "Выбранные письма, аккаунты и выделенные строки", "#263E5F", "#DCEBFA"),
        new("AppSelectionTextBrush", "Выделение", "Текст выделения",
            "Текст внутри выделенных строк и текст выделенного фрагмента", "#FFFFFF", "#202124"),

        new("AppScrollTrackBrush", "Прокрутка", "Дорожка",
            "Вертикальные и горизонтальные дорожки прокрутки", "#1A1D22", "#F2F4F7"),
        new("AppScrollThumbBrush", "Прокрутка", "Ползунок",
            "Ползунки всех вертикальных и горизонтальных полос прокрутки", "#3A4049", "#AEB6C2"),
        new("AppScrollArrowBrush", "Прокрутка", "Стрелки",
            "Стрелки полос прокрутки и их пиктограммы", "#5F6874", "#667283"),

        new("AppSuccessBrush", "Состояния", "Успех",
            "Успешное выполнение, положительные отметки состояния", "#55C695", "#18794E"),
        new("AppWarningBrush", "Состояния", "Предупреждение",
            "Предупреждения, важные уведомления и состояния внимания", "#E9BC62", "#946200"),
        new("AppDangerBrush", "Состояния", "Ошибка",
            "Ошибки, опасные и необратимые действия", "#E5484D", "#C62832"),

        new("AppStarBrush", "Отметки", "Избранное",
            "Активные звёздочки писем", "#FFD54A", "#D49E00"),
        new("AppPinBrush", "Отметки", "Закрепление",
            "Активные булавки закреплённых писем", "#E5484D", "#C62832")
    ];

    // The visual resource names remain stable for older XAML and saved theme
    // files. These keys now share one editable setting for similar elements.
    // Previously saved colors are carried forward without deleting user data.
    public static IReadOnlyDictionary<string, string> UnifiedRoleKeys { get; } =
        new Dictionary<string, string>(StringComparer.Ordinal)
        {
            ["AppPanelBrush"] = "AppWindowBrush",
            ["AppDialogBrush"] = "AppWindowBrush",
            ["AppInputBrush"] = "AppControlBrush",
            ["AppDisabledTextBrush"] = "AppMutedTextBrush",
            ["AppScrollArrowBrush"] = "AppScrollThumbBrush",
            ["AppSelectionTextBrush"] = "AppTextBrush"
        };

    public static IReadOnlyList<ThemeColorRole> EditableRoles { get; } =
        Roles.Where(role => !UnifiedRoleKeys.ContainsKey(role.Key)).ToArray();

    private static readonly Dictionary<string, ThemeColorRole> ByKey =
        Roles.ToDictionary(role => role.Key, StringComparer.Ordinal);

    public static IReadOnlyDictionary<string, string> Defaults(bool dark) =>
        Roles.ToDictionary(r => r.Key, r => dark ? r.DarkDefault : r.LightDefault,
            StringComparer.Ordinal);

    public static bool TryNormalize(string? value, out string normalized)
    {
        normalized = "";
        if (value is null)
            return false;

        var text = value.Trim().TrimStart('#');
        if (text.Length != 6 ||
            !int.TryParse(text, NumberStyles.HexNumber, CultureInfo.InvariantCulture,
                out _))
            return false;

        normalized = "#" + text.ToUpperInvariant();
        return true;
    }

    public static Dictionary<string, string> Merge(
        bool dark, IReadOnlyDictionary<string, string>? overrides)
    {
        var colors = Defaults(dark).ToDictionary(p => p.Key, p => p.Value, StringComparer.Ordinal);
        if (overrides is null)
        {
            foreach (var (alias, canonical) in UnifiedRoleKeys)
                colors[alias] = colors[canonical];
            return colors;
        }

        foreach (var (key, value) in overrides)
        {
            if (ByKey.ContainsKey(key) && TryNormalize(value, out var hex))
                colors[key] = hex;
        }

        // A migrated setting is chosen deterministically. Explicitly edited
        // canonical values take precedence over any older alias overrides.
        foreach (var (alias, canonical) in UnifiedRoleKeys)
        {
            if (overrides is not null &&
                !overrides.ContainsKey(canonical) &&
                overrides.TryGetValue(alias, out var previous) &&
                TryNormalize(previous, out var normalized))
                colors[canonical] = normalized;
            colors[alias] = colors[canonical];
        }

        return colors;
    }

    public static double Contrast(string first, string second)
    {
        static double Luminance(string hex)
        {
            static double Channel(int value)
            {
                var v = value / 255.0;
                return v <= 0.04045 ? v / 12.92 : Math.Pow((v + 0.055) / 1.055, 2.4);
            }
            return .2126 * Channel(Convert.ToInt32(hex.Substring(1, 2), 16)) +
                   .7152 * Channel(Convert.ToInt32(hex.Substring(3, 2), 16)) +
                   .0722 * Channel(Convert.ToInt32(hex.Substring(5, 2), 16));
        }

        var a = Luminance(first);
        var b = Luminance(second);
        return (Math.Max(a, b) + .05) / (Math.Min(a, b) + .05);
    }
}
