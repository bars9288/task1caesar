package enums;

public enum ABC {
    LATINIC("abcdefghijklmnopqrstuvwxyz"),
    CYRILIC("абвгдеёжзийклмнопрстуфхцчшщъыьэюя");

    String abc;

    ABC(String abcLine) {
        this.abc = abcLine;
    }

    public String get() {
        return abc;
    }
}
