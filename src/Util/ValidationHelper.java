package Util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * A class to validate given data
 * <p>
 * This class provides a way to convert a string into an integer or a double
 * and check whether the given data is valid
 * <p>
 * Also, it provides a way to check if an integer
 * or a character is in the given range
 *
 * @author Phil O'Connell (pxo4@psu.edu )
 * @author Mahfuzur Rahman (mvr5854@psu.edu)
 */
public class ValidationHelper {
    private final static Logger logger = LogManager.getRootLogger();

    public static Double tryParseDouble(String text) {
        try {
            return Double.parseDouble(text);
        } catch (Exception e) {
            logger.debug("Invalid input: " + text);
            return null;
        }
    }

    public static Integer tryParseInt(String text) {
        try {
            return Integer.parseInt(text);
        } catch (Exception e) {
            logger.debug("Invalid input: " + text);
            return null;
        }
    }

    /**
     * Validate a given character is in range
     * <p>
     * Determine if the given character is in an allowed range
     *
     * @param ch    The character being validated
     * @param range The string holding valid characters
     * @return true if the character is in range
     */
    public static boolean isCharInRange(char ch, String range) {
        if (range == null) {
            return false;
        }
        return (range.indexOf(ch) >= 0);
    }

    /**
     * Checks that given string is "Y" or "N"
     *
     * @param str string to be checked
     * @return true if string is "Y" or "N"
     */
    public static boolean isValidYorN(String str) {
        return ("N".equals(str) || "Y".equals(str));
    }

    /**
     * Validate a given integer is in the range
     * <p>
     * This method allows the user to provide an integer
     * and a range of numbers
     * <p>
     * Determine if the given integer is in the allowed range
     *
     * @param value The integer that the user wants to check
     * @param start The starting value of the range
     * @param end The ending value of the range
     * @return true if the integer is in the range
     *
     * @author Phil O'Connell (pxo4@psu.edu )
     * @author Mahfuzur Rahman (mvr5854@psu.edu)
     */
    public static boolean isIntInRange(int value, int start, int end) {
        if (value < start || value > end) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isStringNullOrEmpty(String str) {
        return ((str == null) || (str.isEmpty()));
    }

}
