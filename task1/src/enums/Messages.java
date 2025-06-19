package enums;

public enum Messages {
    FINISHED("The program has stopped"),

    CHOOSE_LANGUAGE("Select your language, if English press 1; Выберите Ваш язык, если Русский нажмите 2"),
    CHOOSE_LANGUAGE_TRY_AGAIN("enter 1 or 2 (exit 0); Введите 1 или 2 (выйти 0)"),

    CHOOSE_TASK_SOURCE_RUS("Выберите операцию 1-Чтение из консоли 2-чтение из файла (0 - выйти)"),
    CHOOSE_TASK_SOURCE_ENG("Select operation 1-Read from console 2-Read from file (0 - exit)"),

    CHOOSE_TASK_ACTION_RUS("Выберите операцию 1-Шифрование 2-Дешифровка (0 - выйти)"),
    CHOOSE_TASK_ACTION_ENG("Select operation 1-Encryption 2-Decryption (0 - exit)"),

    ENTER_CONSOLE_STRING_SHIFT_RUS("Введите исходные данные: в кавычках исходный текст и число смещения (пример:  \"Hello World\", shift: 3)"),
    ENTER_CONSOLE_STRING_SHIFT_ENG("Enter the source data: in quotes the source text and the offset number (example: \"Hello World\", shift: 3)"),

    ENTER_CONSOLE_STRING_SHIFT_FILE_RUS("Введите абсолютный путь к файлу и смещение через пробел"),
    ENTER_CONSOLE_STRING_SHIFT_FILE_ENG("Enter the absolute path to the file and shift with space"),

    ENTER_CONSOLE_404_FILE_RUS("Файл не найден (адрес не распознан) ИЛИ не распознано число смещения"),
    ENTER_CONSOLE_404_FILE_ENG("File not found (path not recognized) OR shift number  not recognized"),

    ENTER_CONSOLE_STRING_NULL_RUS("Не распознан текст"),
    ENTER_CONSOLE_SHIFT_NULL_RUS("Не распознано число"),
    ENTER_CONSOLE_STRING_NULL_ENG("Text not recognized"),
    ENTER_CONSOLE_SHIFT_NULL_ENG("Number not recognized"),

    GET_15_DECRYPT_RUS("Не указано число смещения. Указаны ближайшие 20 вариантов"),
    GET_15_DECRYPT_ENG("Undefined offset value. Specified visitors 20 variants"),
    A("A");
    private String message;

    public String get() {
        return message;
    }

    Messages(String message) {
        this.message = message;
    }
}
