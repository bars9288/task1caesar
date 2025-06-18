package utils;

import enums.LAT_ABC;

import java.util.Objects;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

import static utils.CYR_ABC.*;

public class CryptAlgorithm {

    static final int[] LATINIC_LOWER = LAT_ABC.LATINIC.get();
    static final int[] LATINIC_UPPER = LAT_ABC.LATINIC_UPPER.get();

    static final int[] CYRILIC_LOWER = CYR_ABC.get_CYRILIC_LOWER();
    static final int[] CYRILIC_UPPER = CYR_ABC.get_CYRILIC_UPPER();

    public static String crypt(String inputString, int shift) {
        if (Objects.isNull(inputString) || Objects.isNull(shift) || inputString.isEmpty()) return "";

        IntFunction<String> mapper = value -> {
            return String.valueOf((char) checkAbcUpperLowerRange(value, shift));
        };

        if (inputString.length() <= 1_000) { // если строка короткая то многопоток не применять тк создание планировщика потоков не перекрывает выгоды многопоточки
            return inputString.chars()
                    .mapToObj(mapper)
                    .collect(Collectors.joining());
        }
        return inputString.chars()// многопоточка
                .parallel()
                .mapToObj(mapper)
                .collect(Collectors.joining());
    }


    public static int checkAbcUpperLowerRange(int charUnicodeNum, int shift) {
        if ((LATINIC_LOWER[0] <= charUnicodeNum) && (charUnicodeNum <= LATINIC_LOWER[LATINIC_LOWER.length - 1])) {
            return shiftChar(charUnicodeNum, shift, LATINIC_LOWER, false);
        } else {
            if ((LATINIC_UPPER[0] <= charUnicodeNum) && (charUnicodeNum <= LATINIC_UPPER[LATINIC_UPPER.length - 1])) {
                return shiftChar(charUnicodeNum, shift, LATINIC_UPPER, false);
            } else {
                int intLower = getIntLower((char) charUnicodeNum);
                if (intLower != 0) {
                    return shiftChar(intLower, shift, CYRILIC_LOWER, true);
                } else {
                    int intUpper = getIntUpper((char) charUnicodeNum);
                    if (intUpper != 0) {
                        return shiftChar(intUpper, shift, CYRILIC_UPPER, true);
                    } else {
                        return charUnicodeNum;
                    }
                }
            }
        }
    }


    public static int shiftChar(int charUnicodeNum, int shift, int[] AbcArray, boolean isCirilic) {
        if (charUnicodeNum + shift <= AbcArray[AbcArray.length - 1]) {
            if (!isCirilic) {
                return charUnicodeNum + shift;
            } else {
                return cyrilicGetIntChar(charUnicodeNum + shift);
            }
        } else {
            int delta = (charUnicodeNum + shift) - AbcArray[AbcArray.length - 1];
            int ost = delta % AbcArray.length;
            if (!isCirilic) {
                return AbcArray[ost - 1];
            } else {
                return cyrilicGetIntChar(AbcArray[ost - 1]);
            }
        }
    }

}

