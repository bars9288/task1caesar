package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

    public static boolean findedByRegex(String regex, String inputString){

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputString);

        return (matcher.find());
    }

    public static String findStringByRegex(String regex, String inputString){

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputString);

        if (matcher.find()){
            return matcher.group();
        }
        return null;
    }
}
