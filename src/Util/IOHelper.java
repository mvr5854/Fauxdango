package Util;

import Model.Console;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IOHelper {
    private static final Scanner keyboard = Console.keyboard;

    private static final IOHelper INSTANCE = new IOHelper();

    private IOHelper() {

    }

    public static IOHelper getInstance() {
        return INSTANCE;
    }

    public static String readStringFromKeyboard(String prompt) {
        System.out.print(prompt + ": ");

        return keyboard.nextLine();
    }

    public static String readNonBlankStringFromKeyboard(String prompt) {
        String nonblankString;

        while (true) {
            nonblankString = readStringFromKeyboard(prompt);
            if (nonblankString.trim().isEmpty()) {
                System.out.println();
                System.out.println("Cannot be blank");
            } else {
                break;
            }
        }

        return nonblankString;
    }

    /**
     * Checks if a given string matches a specified regular expression.
     * <p>
     * This method compiles the provided regular expression into a pattern
     * and matches it against the given string.
     * </p>
     *
     * @param str   The string to be checked.
     * @param regex The regular expression to match against the string.
     * @return true if the string matches the regular expression, false otherwise.
     */
    public static boolean checkRegex(String str, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    public static String readValidNameFromKeyboard(String prompt) {
        String validName;
        while (true) {
            validName = readNonBlankStringFromKeyboard(prompt).trim();
            if (!checkRegex(validName, "^[A-Z][a-z']*$")) {
                System.out.println("\nName can only contain letters\nand must begin with a capital letter");
            } else {
                break;
            }
        }
        return validName;
    }

    public static String readValidEmailAddressFromKeyboard(String prompt) {
        String validEmailAddress;
        while (true) {
            validEmailAddress = readNonBlankStringFromKeyboard(prompt).trim();
            if (!checkRegex(validEmailAddress, "^[a-z0-9\\.]{1,64}@[a-z0-9\\.]+\\.(com|org|net|edu|gov|co|us|info)$")) {
                System.out.println("\nInvalid email address! try again");
            } else {
                break;
            }
        }
        return validEmailAddress;
    }

    /**
     * Prompt for user input, and convert the input to a double
     * <p>
     * Repeatedly prompt the user for a string input.
     * <p>
     * Parses the string input as a double and returns it to the caller.
     *
     * @param prompt Prompt the user so that they know what to input.
     * @return the converted value of user input
     * @author Phil O'Connell (pxo4@psu.edu )
     * @author Mahfuzur Rahman (mvr5854@psu.edu)
     */
    public static double userInputDouble(String prompt) {
        Double parsedToDouble = null;

        while (parsedToDouble == null) {
            String userInput = readStringFromKeyboard(prompt);
            parsedToDouble = ValidationHelper.tryParseDouble(userInput);
        }

        return parsedToDouble;
    }

    public static <T> void printList(List<T> list) {
        for (T item : list) {
            System.out.println(item.toString());
        }
    }
}
