package stringutils;

import java.util.Arrays;

public class StringUtils {
    
    // Parse user's input and return the task description without the command
    public static String removeFirstWord(String[] input) {
        String[] splitString = Arrays.copyOfRange(input, 1, input.length);
        return String.join(" ", splitString).trim();
    }
    
    public static String splitStringAndRemoveFirstWord(String input) {
        String[] deadlineSplit = input.split(" ");
        String[] deadlineSliced = Arrays.copyOfRange(deadlineSplit, 1, deadlineSplit.length);
        return String.join(" ", deadlineSliced).trim();
    }
    
}
