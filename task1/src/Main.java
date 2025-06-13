import enums.Messages;
import utils.CryptAlgorithm;

import java.util.Objects;
import java.util.Scanner;

import static utils.CheckConsoleMessage.readCorrectIntegerConsole;
import static utils.Regex.*;

public class Main {

   static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println(Messages.CHOOSE_LANGUAGE.get());
        Integer numLanguage = readCorrectIntegerConsole(scanner, "^[012]{1}$", Messages.CHOOSE_LANGUAGE_TRY_AGAIN);
        if (numLanguage.equals(0)){
            System.out.println(Messages.FINISHED);
        } else {
            Integer numSource = selectNumSource(numLanguage);
                if (numSource.equals(0)){
                    System.out.println(Messages.FINISHED);
                } else {
                    Integer numTask = selectNumAction(numLanguage);
                    if (numSource.equals(0)){
                        System.out.println(Messages.FINISHED);
                    }
                    createTask(numLanguage, numSource, numTask);
            }
        }
    }

    public static int selectNumSource(int numLanguage){
        if (numLanguage == 1){
            System.out.println(Messages.CHOOSE_TASK_SOURCE_ENG.get());
            return  readCorrectIntegerConsole(scanner, "^[012]{1}$", Messages.CHOOSE_LANGUAGE_TRY_AGAIN);
        } else if (numLanguage == 2){
            System.out.println(Messages.CHOOSE_TASK_SOURCE_RUS.get());
            return readCorrectIntegerConsole(scanner, "^[012]{1}$", Messages.CHOOSE_LANGUAGE_TRY_AGAIN);
        }
        return -1;
    }


    public static int selectNumAction(int numLanguage){
            if (numLanguage == 1){
                System.out.println(Messages.CHOOSE_TASK_ACTION_ENG.get());
                return  readCorrectIntegerConsole(scanner, "^[012]{1}$", Messages.CHOOSE_LANGUAGE_TRY_AGAIN);
            } else if (numLanguage == 2){
                System.out.println(Messages.CHOOSE_TASK_ACTION_RUS.get());
                return readCorrectIntegerConsole(scanner, "^[012]{1}$", Messages.CHOOSE_LANGUAGE_TRY_AGAIN);
            }
        return -1;
    }

    public static void createTask(int numLanguage, int numSource, int numTask){
        if (numLanguage == 1){
            if (numSource == 1){
                if (numTask == 1){
                    criptingTask(Messages.ENTER_CONSOLE_STRING_SHIFT_ENG, Messages.ENTER_CONSOLE_STRING_NULL_ENG, Messages.ENTER_CONSOLE_SHIFT_NULL_ENG, true);
                } else if (numTask == 2){
                    criptingTask(Messages.ENTER_CONSOLE_STRING_SHIFT_ENG, Messages.ENTER_CONSOLE_STRING_NULL_ENG, Messages.ENTER_CONSOLE_SHIFT_NULL_ENG, false);
                }
            } else if (numSource == 2){
                if (numTask == 1){
                    criptingTaskFile(Messages.ENTER_CONSOLE_STRING_SHIFT_ENG, Messages.ENTER_CONSOLE_STRING_NULL_ENG, Messages.ENTER_CONSOLE_SHIFT_NULL_ENG, true);
                } else if (numTask == 2){
                    criptingTaskFile(Messages.ENTER_CONSOLE_STRING_SHIFT_ENG, Messages.ENTER_CONSOLE_STRING_NULL_ENG, Messages.ENTER_CONSOLE_SHIFT_NULL_ENG, false);
                }
            }
        } else if (numLanguage == 2){
            if (numSource == 1){
                if (numTask == 1){
                    criptingTask(Messages.ENTER_CONSOLE_STRING_SHIFT_RUS, Messages.ENTER_CONSOLE_STRING_NULL_RUS, Messages.ENTER_CONSOLE_SHIFT_NULL_RUS, true);
                } else if (numTask == 2){
                    criptingTask(Messages.ENTER_CONSOLE_STRING_SHIFT_RUS, Messages.ENTER_CONSOLE_STRING_NULL_RUS, Messages.ENTER_CONSOLE_SHIFT_NULL_RUS, false);
                }
            } else if (numSource == 2){
                if (numTask == 1){
                    criptingTaskFile(Messages.ENTER_CONSOLE_STRING_SHIFT_ENG, Messages.ENTER_CONSOLE_STRING_NULL_ENG, Messages.ENTER_CONSOLE_SHIFT_NULL_ENG, true);
                } else if (numTask == 2){
                    criptingTaskFile(Messages.ENTER_CONSOLE_STRING_SHIFT_ENG, Messages.ENTER_CONSOLE_STRING_NULL_ENG, Messages.ENTER_CONSOLE_SHIFT_NULL_ENG, false);
                }
            }
        }


        System.out.println(numLanguage);
        System.out.println(numSource);
        System.out.println(numTask);
    }

    public static void criptingTask(Messages first, Messages notFoundString, Messages notFoundInt, boolean cript){
        System.out.println(first.get());
        String next = scanner.next();
        if (!findedByRegex("^[\\\"].*[\\\"]", next)){
            System.out.println(notFoundString.get());
            return;
        }
        if (!findedByRegex("[\\d]+$", next)){
            System.out.println(notFoundInt.get());
            return;
        }
        String inputData = findStringByRegex("^[\\\"].*[\\\"]", next);
        int inputShift = Integer.parseInt(Objects.requireNonNull(findStringByRegex("[\\d]+$", next)));
        String result = CryptAlgorithm.crypt(inputData, (cript) ? inputShift : -inputShift);
        System.out.println("\"" + result + "\"");
    }

    public static void criptingTaskFile(Messages firstFile, Messages notFoundFile, Messages notFoundInt, boolean cript){
        System.out.println(first.get());
        String next = scanner.next();
        if (!findedByRegex("^[\\\"].*[\\\"]", next)){
            System.out.println(notFoundString.get());
            return;
        }
        if (!findedByRegex("[\\d]+$", next)){
            System.out.println(notFoundInt.get());
            return;
        }
        String inputData = findStringByRegex("^[\\\"].*[\\\"]", next);
        int inputShift = Integer.parseInt(Objects.requireNonNull(findStringByRegex("[\\d]+$", next)));
        String result = CryptAlgorithm.crypt(inputData, (cript) ? inputShift : -inputShift);
        System.out.println("\"" + result + "\"");
    }

}

