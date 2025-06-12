package utils;

import enums.LAT_ABC;

import java.util.Objects;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

import static utils.CYR_ABC.getIntLower;
import static utils.CYR_ABC.getIntUpper;


public class CryptAlgorithm {

    static final int[] LATINIC_LOWER = LAT_ABC.LATINIC.get();
    static final int[] LATINIC_UPPER = LAT_ABC.LATINIC_UPPER.get();

    static final int[] CYRILIC_LOWER = CYR_ABC.get_CYRILIC_LOWER();
    static final int[] CYRILIC_UPPER = CYR_ABC.get_CYRILIC_UPPER();


    public static String crypt(String inputString, int shift){
        if (Objects.isNull(inputString) || Objects.isNull(shift)|| inputString.isEmpty()) return null;

        IntFunction<String> mapper = value -> {
            return String.valueOf((char) checkAbcUpperLowerRange(value, shift));
        };

        if (inputString.length() <= 1_000){ // если строка короткая то многопоток не применять тк создание планировщика потоков не перекрывает выгоды многопоточки
            return inputString.chars()
                    .mapToObj(mapper)
                    .collect(Collectors.joining());
        }

        return inputString.chars()// многопоточка
                .parallel()
                .mapToObj(mapper)
                .collect(Collectors.joining());
    }


    public static int checkAbcUpperLowerRange(int charUnicodeNum, int shift){
        if ((LATINIC_LOWER[0] <= charUnicodeNum) && (charUnicodeNum <= LATINIC_LOWER[LATINIC_LOWER.length - 1])){
            return shiftChar(charUnicodeNum, shift, LATINIC_LOWER);
        } else {
            if ((LATINIC_UPPER[0] <= charUnicodeNum) && (charUnicodeNum <= LATINIC_UPPER[LATINIC_UPPER.length - 1])){
                return shiftChar(charUnicodeNum, shift, LATINIC_UPPER);
            } else {
                int intLower = getIntLower((char) charUnicodeNum);
                if (intLower != 0){
                    return shiftChar(intLower, shift, CYRILIC_LOWER);
                } else {
                    int intUpper = getIntUpper((char) charUnicodeNum);
                    if (intUpper != 0){
                        return shiftChar(intUpper, shift, CYRILIC_UPPER);
                    } else {
                        return charUnicodeNum;
                    }
                }
            }
        }
    }


    public static int shiftChar(int charUnicodeNum, int shift, int[] AbcArray){
        if (charUnicodeNum + shift <= AbcArray[AbcArray.length - 1]){
            return charUnicodeNum + shift;
        } else {
            int delta = (charUnicodeNum + shift) - AbcArray[AbcArray.length - 1];
            int ost = delta % AbcArray.length;
            return AbcArray[ost - 1];
        }
    }


    public static void main(String[] args) {
        String abc = crypt("Мир", 5);
        System.out.println(abc);

        /*
Input: "Привет Мир", shift: 5
Output: "Хумёзй Рну"


         */

    }





}
