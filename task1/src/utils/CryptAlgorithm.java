package utils;

import enums.ABC;

import java.util.Objects;
import java.util.function.IntFunction;
import java.util.stream.Collectors;


public class CryptAlgorithm {

    static int[] LATINIC_LOWER = ABC.LATINIC.get().chars().toArray();
    static int[] LATINIC_UPPER = ABC.LATINIC.get().toUpperCase().chars().toArray();

    static int[] CYRILIC_LOWER = ABC.CYRILIC.get().chars().toArray();
    static int[] CYRILIC_UPPER = ABC.CYRILIC.get().toUpperCase().chars().toArray();


    public static String crypt(String inputString, int shift){
        if (Objects.isNull(inputString) || Objects.isNull(shift)|| inputString.isEmpty()) return null;

        IntFunction<String> mapper = value -> {
            if (checkAbcUpperLowerRange(value)){
                return String.valueOf(shiftChar(value, shift));
            } else {
                return String.valueOf(value);
            }
        };

        if (inputString.length() <= 1_000){ // если строка короткая то многопоток не применять тк создание планировщика потоков не перекрывает выгоды многопоточки
            return inputString.chars()
                    .mapToObj(mapper)
                    .collect(Collectors.joining());
        }

        return inputString.chars()
                .parallel()
                .mapToObj(mapper)
                .collect(Collectors.joining());
    }

    public static boolean checkAbcUpperLowerRange(int charUnicodeNum){
        if ((LATINIC_LOWER[0] <= charUnicodeNum) && (charUnicodeNum <= LATINIC_LOWER[LATINIC_LOWER.length - 1])){
            return true;
        } else {
            if ((LATINIC_UPPER[0] <= charUnicodeNum) && (charUnicodeNum <= LATINIC_UPPER[LATINIC_UPPER.length - 1])){
                return true;
            } else {
                if ((CYRILIC_LOWER[0] <= charUnicodeNum) && (charUnicodeNum <= CYRILIC_LOWER[CYRILIC_LOWER.length - 1])){
                    return true;
                } else {
                    if ((CYRILIC_UPPER[0] <= charUnicodeNum) && (charUnicodeNum <= CYRILIC_UPPER[CYRILIC_UPPER.length - 1])){
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
    }

    public static int shiftChar(int charUnicodeNum, int shift){

    }




}
