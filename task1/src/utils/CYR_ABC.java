package utils;

import java.util.HashMap;
import java.util.Objects;

class CYR_ABC {
    static HashMap<Character, Integer> CYRILIC_LOWER = new HashMap<>();
    static HashMap<Character, Integer> CYRILIC_UPPER = new HashMap<>();

    // номера специально выбраны такими что бы исключить пересечение с кодом char списка unicode
    static int startNumLowerArray = 66_000;
    static int startNumUpperArray = 67_000;

    /**
     * Метод инициализирует алфавит с применением порядка букв традиционного русского алфавита, где буква "ё" стоит 7-м символом
     * в отличие от UniCode где буква  "ё" стоит после буквы "я" через символ е-операнд(ударение)
     * Т.к. нет явного указания по алфавиту, и для исключнеия ошибки буквы е-операнд(ударение) применен этот метод.
     */
    static void init() {
        int num = startNumLowerArray;
        for (int charNum : "абвгде".chars().toArray()) {
            CYRILIC_LOWER.put((char) charNum, num);
            num++;
        }
        CYRILIC_LOWER.put('ё', num);
        num++;
        for (int charNum : "жзийклмнопрстуфхцчшщъыьэюя".chars().toArray()) {
            CYRILIC_LOWER.put((char) charNum, num);
            num++;
        }

        num = startNumUpperArray;
        for (int charNum : "абвгде".toUpperCase().chars().toArray()) {
            CYRILIC_UPPER.put((char) charNum, num);
            num++;
        }
        CYRILIC_UPPER.put('Ё', num);
        num++;
        for (int charNum : "жзийклмнопрстуфхцчшщъыьэюя".toUpperCase().chars().toArray()) {
            CYRILIC_UPPER.put((char) charNum, num);
            num++;
        }
    }

    static int[] get_CYRILIC_LOWER() {
        CYR_ABC.init();
        return CYRILIC_LOWER.values().stream().sorted(Integer::compareTo).mapToInt(Integer::intValue).toArray();
    }

    static int[] get_CYRILIC_UPPER() {
        return CYRILIC_UPPER.values().stream().sorted(Integer::compareTo).mapToInt(Integer::intValue).toArray();
    }

    static int getIntLower(char varChar) {
        return Objects.nonNull(CYRILIC_LOWER.get(varChar)) ? CYRILIC_LOWER.get(varChar) : 0;
    }

    static int getIntUpper(char varChar) {
        return Objects.nonNull(CYRILIC_UPPER.get(varChar)) ? CYRILIC_UPPER.get(varChar) : 0;
    }


    static int cyrilicGetIntChar(int num) {
        if (startNumLowerArray <= num && num <= startNumLowerArray + 33) {
            return getIntCharLower(num);
        } else if (startNumUpperArray <= num && num <= startNumUpperArray + 33) {
            return getIntCharUpper(num);
        }
        return Integer.MIN_VALUE;
    }

    static int getIntCharLower(int num) {
        return CYRILIC_LOWER.entrySet().stream().filter(ent -> ent.getValue().equals(num)).findFirst().get().getKey().charValue();
    }

    static int getIntCharUpper(int num) {
        return CYRILIC_UPPER.entrySet().stream().filter(ent -> ent.getValue().equals(num)).findFirst().get().getKey().charValue();
    }
}