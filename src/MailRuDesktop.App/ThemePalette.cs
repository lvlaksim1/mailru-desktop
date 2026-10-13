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
            "Активные булавки закреплённых писем", "#C62832", "#C62832"),
        new("AppUnreadTextBrush", "Текст", "Непрочитанные письма",
            "Отправители и темы непрочитанных писем", "#8FB5E2", "#355D8D")
    ];

    // All UI colors are derived from eight tones of a SINGLE user-supplied
    // primary color. Semantic brushes remain stable for the existing XAML.
    // Success/warning/danger and pinned/flagged status use fixed colors.
    public const string DefaultPrimaryColor = "#426B9C";

    public static IReadOnlyList<ThemeColorRole> EditableRoles { get; } =
        Roles.Where(role => role.Key == "AppAccentBrush").ToArray();

    public static IReadOnlyDictionary<string, string> Defaults(bool dark) =>
        Generate(dark, DefaultPrimaryColor);

    public static bool TryNormalize(string? value, out string normalized)
    {
        normalized = "";
        if (value is null) return false;
        var text = value.Trim().TrimStart('#');
        if (text.Length != 6 ||
            !int.TryParse(text, NumberStyles.HexNumber,
                CultureInfo.InvariantCulture, out _))
            return false;
        normalized = "#" + text.ToUpperInvariant();
        return true;
    }

    // Accept the former entry point, but ignore every legacy per-role setting.
    // Older stored dictionaries remain untouched on disk for safe migration.
    public static Dictionary<string, string> Merge(
        bool dark, IReadOnlyDictionary<string, string>? overrides)
    {
        var primary = overrides is not null &&
                      overrides.TryGetValue("AppAccentBrush", out var selected) &&
                      TryNormalize(selected, out var normalized)
            ? normalized : DefaultPrimaryColor;
        return Generate(dark, primary);
    }

    public static Dictionary<string, string> Generate(bool dark, string primary)
    {
        if (!TryNormalize(primary, out var baseHex))
            throw new ArgumentException("Основной цвет должен иметь вид #RRGGBB.", nameof(primary));

        // Exactly eight shared tones; the end points are tinted rather than
        // neutral so changing the primary hue affects backgrounds and text.
        var tones = new[]
        {
            Blend(baseHex, "#FFFFFF", .985),
            Blend(baseHex, "#FFFFFF", .90),
            Blend(baseHex, "#FFFFFF", .76),
            Blend(baseHex, "#FFFFFF", .58),
            Blend(baseHex, "#FFFFFF", .28),
            Blend(baseHex, "#070A10", .10),
            Blend(baseHex, "#070A10", .66),
            Blend(baseHex, "#070A10", .86)
        };
        var index = dark
            ? new Dictionary<string, int>(StringComparer.Ordinal)
            {
                ["AppWindowBrush"] = 7, ["AppPanelBrush"] = 6,
                ["AppDialogBrush"] = 6, ["AppReaderBrush"] = 7,
                ["AppInputBrush"] = 6, ["AppControlBrush"] = 6,
                ["AppControlHoverBrush"] = 5, ["AppControlPressedBrush"] = 4,
                ["AppBorderBrush"] = 5, ["AppFocusBrush"] = 4,
                ["AppTextBrush"] = 0, ["AppMutedTextBrush"] = 3,
                ["AppDisabledTextBrush"] = 3, ["AppAccentTextBrush"] = 7,
                ["AppLinkBrush"] = 3, ["AppAccentBrush"] = 4,
                ["AppSelectionBrush"] = 5, ["AppSelectionTextBrush"] = 0,
                ["AppScrollTrackBrush"] = 7, ["AppScrollThumbBrush"] = 4,
                ["AppScrollArrowBrush"] = 3, ["AppUnreadTextBrush"] = 4
            }
            : new Dictionary<string, int>(StringComparer.Ordinal)
            {
                ["AppWindowBrush"] = 0, ["AppPanelBrush"] = 1,
                ["AppDialogBrush"] = 1, ["AppReaderBrush"] = 0,
                ["AppInputBrush"] = 0, ["AppControlBrush"] = 2,
                ["AppControlHoverBrush"] = 3, ["AppControlPressedBrush"] = 4,
                ["AppBorderBrush"] = 3, ["AppFocusBrush"] = 5,
                ["AppTextBrush"] = 7, ["AppMutedTextBrush"] = 5,
                ["AppDisabledTextBrush"] = 5, ["AppAccentTextBrush"] = 0,
                ["AppLinkBrush"] = 5, ["AppAccentBrush"] = 5,
                ["AppSelectionBrush"] = 2, ["AppSelectionTextBrush"] = 7,
                ["AppScrollTrackBrush"] = 1, ["AppScrollThumbBrush"] = 4,
                ["AppScrollArrowBrush"] = 5, ["AppUnreadTextBrush"] = 5
            };

        var colors = index.ToDictionary(
            pair => pair.Key, pair => tones[pair.Value], StringComparer.Ordinal);
        colors["AppSuccessBrush"] = "#208A61";
        colors["AppWarningBrush"] = "#D99A18";
        colors["AppDangerBrush"] = "#C62832";
        colors["AppPinBrush"] = "#C62832";
        colors["AppStarBrush"] = "#D49E00";
        if (colors.Count != Roles.Count)
            throw new InvalidOperationException("Не все цветовые роли назначены.");
        return colors;
    }

    private static string Blend(string first, string second, double otherWeight)
    {
        var channels = new int[3];
        for (var channel = 0; channel < 3; channel++)
        {
            var offset = 1 + channel * 2;
            var start = Convert.ToInt32(first.Substring(offset, 2), 16);
            var target = Convert.ToInt32(second.Substring(offset, 2), 16);
            channels[channel] = (int)Math.Round(
                start * (1 - otherWeight) + target * otherWeight);
        }
        return $"#{channels[0]:X2}{channels[1]:X2}{channels[2]:X2}";
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
