package utils;

import enums.Messages;

import java.util.Scanner;

public class CheckConsoleMessage {

    public static Integer readCorrectIntegerConsole(Scanner scanner, String regex, Messages messagesFatal) {
        String inputString;
        while (true) {
            inputString = scanner.next();
            if (!Regex.findedByRegex(regex, inputString)) {
                System.out.println(messagesFatal.get());
            } else break;
        }
        return Integer.valueOf(inputString);
    }

}
