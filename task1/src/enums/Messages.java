package enums;

public enum Messages {
    FINISHED("The program has stopped"),
    CHOOSE_LANGUAGE("Select your language, if English press 1; Выберите Ваш язык, если Русский нажмите 2"),
    CHOOSE_LANGUAGE_TRY_AGAIN("enter 1 or 2 (exit 0); Введите 1 или 2 (выйти 0)"),

    CHOOSE_TASK_E(""),

    A("A");
    private String message;

    public String get() {
        return message;
    }

    Messages(String message) {
        this.message = message;
    }
}
