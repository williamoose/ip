import exception.JobeException;

public enum Command {
  BYE,
  LIST,
  MARK,
  UNMARK,
  TODO,
  DEADLINE,
  EVENT,
  DELETE;
  
  public static Command stringToCommand(String input) throws JobeException {
    try {
      return Command.valueOf(input.toUpperCase());
    } catch (IllegalArgumentException e) {
      throw new JobeException("OOPS!!!! I'm Sorry, but I am not sure what you mean.");
    }
  }
}
