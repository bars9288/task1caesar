import enums.Messages;
import utils.CryptAlgorithm;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static utils.CheckConsoleMessage.readCorrectIntegerConsole;
import static utils.FileUtils.printLargeFileContents;
import static utils.Regex.*;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println(Messages.CHOOSE_LANGUAGE.get());
        Integer numLanguage = readCorrectIntegerConsole(scanner, "^[012]{1}$", Messages.CHOOSE_LANGUAGE_TRY_AGAIN);
        if (numLanguage.equals(0)) {
            System.out.println(Messages.FINISHED);
        } else {
            Integer numSource = selectNumSource(numLanguage);
            if (numSource.equals(0)) {
                System.out.println(Messages.FINISHED);
            } else {
                Integer numTask = selectNumAction(numLanguage);
                if (numSource.equals(0)) {
                    System.out.println(Messages.FINISHED);
                }
                createTask(numLanguage, numSource, numTask);
            }
        }
    }

    public static int selectNumSource(int numLanguage) {
        if (numLanguage == 1) {
            System.out.println(Messages.CHOOSE_TASK_SOURCE_ENG.get());
            return readCorrectIntegerConsole(scanner, "^[012]{1}$", Messages.CHOOSE_LANGUAGE_TRY_AGAIN);
        } else if (numLanguage == 2) {
            System.out.println(Messages.CHOOSE_TASK_SOURCE_RUS.get());
            return readCorrectIntegerConsole(scanner, "^[012]{1}$", Messages.CHOOSE_LANGUAGE_TRY_AGAIN);
        }
        return -1;
    }


    public static int selectNumAction(int numLanguage) {
        if (numLanguage == 1) {
            System.out.println(Messages.CHOOSE_TASK_ACTION_ENG.get());
            return readCorrectIntegerConsole(scanner, "^[012]{1}$", Messages.CHOOSE_LANGUAGE_TRY_AGAIN);
        } else if (numLanguage == 2) {
            System.out.println(Messages.CHOOSE_TASK_ACTION_RUS.get());
            return readCorrectIntegerConsole(scanner, "^[012]{1}$", Messages.CHOOSE_LANGUAGE_TRY_AGAIN);
        }
        return -1;
    }

    public static void createTask(int numLanguage, int numSource, int numTask) {
        if (numLanguage == 1) { // English
            if (numSource == 1) { // Console input
                if (numTask == 1) { // Cript
                    criptingTask(Messages.ENTER_CONSOLE_STRING_SHIFT_ENG, Messages.ENTER_CONSOLE_STRING_NULL_ENG, Messages.ENTER_CONSOLE_SHIFT_NULL_ENG, Messages.GET_15_DECRYPT_ENG, true);
                } else if (numTask == 2) { // DeCript
                    criptingTask(Messages.ENTER_CONSOLE_STRING_SHIFT_ENG, Messages.ENTER_CONSOLE_STRING_NULL_ENG, Messages.ENTER_CONSOLE_SHIFT_NULL_ENG, Messages.GET_15_DECRYPT_ENG, false);
                }
            } else if (numSource == 2) { // File input
                if (numTask == 1) { // Cript
                    criptingTaskFile(Messages.ENTER_CONSOLE_STRING_SHIFT_FILE_ENG, Messages.ENTER_CONSOLE_404_FILE_ENG, true);
                } else if (numTask == 2) { // DeCript
                    criptingTaskFile(Messages.ENTER_CONSOLE_STRING_SHIFT_FILE_ENG, Messages.ENTER_CONSOLE_404_FILE_ENG, false);
                }
            }
        } else if (numLanguage == 2) { // Russian
            if (numSource == 1) { // Console input
                if (numTask == 1) { // Cript
                    criptingTask(Messages.ENTER_CONSOLE_STRING_SHIFT_RUS, Messages.ENTER_CONSOLE_STRING_NULL_RUS, Messages.ENTER_CONSOLE_SHIFT_NULL_RUS, Messages.GET_15_DECRYPT_RUS, true);
                } else if (numTask == 2) { // DeCript
                    criptingTask(Messages.ENTER_CONSOLE_STRING_SHIFT_RUS, Messages.ENTER_CONSOLE_STRING_NULL_RUS, Messages.ENTER_CONSOLE_SHIFT_NULL_RUS, Messages.GET_15_DECRYPT_RUS, false);
                }
            } else if (numSource == 2) { // File input
                if (numTask == 1) { // Cript
                    criptingTaskFile(Messages.ENTER_CONSOLE_STRING_SHIFT_FILE_RUS, Messages.ENTER_CONSOLE_404_FILE_RUS, true);
                } else if (numTask == 2) { // DeCript
                    criptingTaskFile(Messages.ENTER_CONSOLE_STRING_SHIFT_FILE_RUS, Messages.ENTER_CONSOLE_404_FILE_RUS, false);
                }
            }
        }
    }

    public static void criptingTask(Messages first, Messages notFoundString, Messages notFoundInt, Messages get15exapmles, boolean cript) {
        System.out.println(first.get());
        scanner.nextLine();  // не баг а фича - что бы скинуть перенос строки от прошлого nextInt
        String next = scanner.nextLine();
        if (!findedByRegex("^[\\\"].*[\\\"]", next)) {
            System.out.println(notFoundString.get());
            return;
        }
        if (!findedByRegex("[\\d]+$", next)) {
            if (!cript) {
                System.out.println(get15exapmles.get());
                for (int inputShift = -1; inputShift >= -20; inputShift--) {
                    String inputData = findStringByRegex("^[\\\"].*[\\\"]", next);
                    String result = CryptAlgorithm.crypt(inputData, inputShift);
                    System.out.println(inputShift + " - " + result);
                }
                return;
            }
            System.out.println(notFoundInt.get());
            return;
        }
        String inputData = findStringByRegex("^[\\\"].*[\\\"]", next);
        int inputShift = Integer.parseInt(Objects.requireNonNull(findStringByRegex("[\\d]+$", next)));
        String result = CryptAlgorithm.crypt(inputData, (cript) ? inputShift : -inputShift);
        System.out.println(result);
    }

    public static void criptingTaskFile(Messages firstFile, Messages notFoundFileDigit, boolean cript) {
        System.out.println(firstFile.get());
        scanner.nextLine(); // не баг а фича - что бы скинуть перенос строки от прошлого nextInt
        String next = scanner.nextLine();
        if (!findedByRegex("^.*[.][\\w\\d]+", next)) {
            System.out.println(notFoundFileDigit.get());
            return;
        }
        if (!findedByRegex("[\\d]+$", next)) {
            System.out.println(notFoundFileDigit.get());
            return;
        }
        String inputData = findStringByRegex("^.*[.][\\w\\d]+", next);
        int inputShift = Integer.parseInt(Objects.requireNonNull(findStringByRegex("[\\d]+$", next)));
        List<String> strings = Collections.emptyList();
        try {
            strings = printLargeFileContents(inputData);
        } catch (IOException e) {
            System.out.println(notFoundFileDigit.get());
        }
        strings.forEach(s -> {
            String crypt = CryptAlgorithm.crypt(s, (cript) ? inputShift : -inputShift);
            System.out.println(crypt);
        });
    }
}

