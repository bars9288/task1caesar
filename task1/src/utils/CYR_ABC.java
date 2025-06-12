package utils;

import java.util.HashMap;
import java.util.Objects;

class CYR_ABC {
    static HashMap<Character, Integer> CYRILIC_LOWER = new HashMap<>();
    static HashMap<Character, Integer> CYRILIC_UPPER = new HashMap<>();

    static void init(){
        int num = 66_000;
        for (int charNum : "абвгде".chars().toArray()){
            CYRILIC_LOWER.put((char) charNum, num);
            num++;
        }
        CYRILIC_LOWER.put('ё', num);
        num++;
        for (int charNum : "жзийклмнопрстуфхцчшщъыьэюя".chars().toArray()){
            CYRILIC_LOWER.put((char) charNum, num);
            num++;
        }

        num = 67_000;
        for (int charNum : "абвгде".toUpperCase().chars().toArray()){
            CYRILIC_UPPER.put((char) charNum, num);
            num++;
        }
        CYRILIC_UPPER.put('Ё', num);
        num++;
        for (int charNum : "жзийклмнопрстуфхцчшщъыьэюя".toUpperCase().chars().toArray()){
            CYRILIC_UPPER.put((char) charNum, num);
            num++;
        }
    }

    static int[] get_CYRILIC_LOWER(){
        CYR_ABC.init();
        return CYRILIC_LOWER.values().stream().sorted(Integer::compareTo).mapToInt(Integer::intValue).toArray();
    }

    static int[] get_CYRILIC_UPPER(){
        return CYRILIC_UPPER.values().stream().sorted(Integer::compareTo).mapToInt(Integer::intValue).toArray();
    }

    static int getIntLower(char varChar){
       return Objects.nonNull(CYRILIC_LOWER.get(varChar)) ? CYRILIC_LOWER.get(varChar) : 0;
    }

    static int getIntUpper(char varChar){
        return Objects.nonNull(CYRILIC_UPPER.get(varChar)) ? CYRILIC_UPPER.get(varChar) : 0;
    }
}