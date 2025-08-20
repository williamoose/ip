import java.util.Arrays;

public class Parser {

  // Parse user's input and return the task description without the command
  public static String removeCommandWord(String[] input) {
    String[] parsedString = Arrays.copyOfRange(input, 1, input.length);
    return String.join(" ", parsedString);
  }
}
