import enums.Messages;

import java.util.Scanner;

import static utils.CheckConsoleMessage.readCorrectIntegerConsole;

public class Main {

   static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println(Messages.CHOOSE_LANGUAGE.get());
        Integer numLanguage = readCorrectIntegerConsole(scanner, "^[012]{1}$", Messages.CHOOSE_LANGUAGE_TRY_AGAIN);
        if (numLanguage.equals(0)){
            System.out.println(Messages.FINISHED);
        } else {
            if (numLanguage.equals(1)){
              //  Integer numLanguage = readCorrectIntegerConsole(scanner, "^[012]{1}$", Messages.CHOOSE_LANGUAGE_TRY_AGAIN);
            } else if (numLanguage.equals(2)){
               // Integer numLanguage = readCorrectIntegerConsole(scanner, "^[012]{1}$", Messages.CHOOSE_LANGUAGE_TRY_AGAIN);
            }
        }
    }
}

