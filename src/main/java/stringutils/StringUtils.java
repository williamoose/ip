package stringutils;

import java.util.Arrays;

public class StringUtils {
    
    // Parse user's input and return the task description without the command
    public static String removeCommandWord(String[] input) {
        String[] splitString = Arrays.copyOfRange(input, 1, input.length);
        return String.join(" ", splitString);
    }
    
}
