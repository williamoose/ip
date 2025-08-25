package parser;

import command.*;
import exception.JobeException;
import stringutils.StringUtils;

public class Parser {
    
    public static Command parse(String input) throws JobeException {
        String[] splitString = input.split(" ");
        CommandType command = CommandType.stringToCommand(splitString[0]);
        
        switch (command) {
        case BYE:
            return new ByeCommand();
        case LIST:
            return new ListCommand();
        case MARK:
            try {
                int index = Integer.parseInt(splitString[1]) - 1;
                return new MarkCommand(index);
            } catch (NumberFormatException e) {
                throw new JobeException("OOPS!!!! The index must be a number!");
            }
        case UNMARK:
            try {
                int index = Integer.parseInt(splitString[1]) - 1;
                return new UnmarkCommand(index);
            } catch (NumberFormatException e) {
                throw new JobeException("OOPS!!!! The index must be a number!");
            }
        case TODO: {
            String taskDescription = StringUtils.removeCommandWord(splitString);
            
            if (taskDescription.isBlank()) {
                throw new JobeException("OOPS!!!! The description of a todo cannot be empty!");
            }
            
            return new TodoCommand(taskDescription);
        }
        case DEADLINE: {
            String[] taskDescription = StringUtils.removeCommandWord(splitString).split("/");
            
            if (taskDescription[0].isBlank()) {
                throw new JobeException("OOPS!!!! The description of a deadline task cannot be empty!");
            }
            
            if (taskDescription.length < 2) {
                throw new JobeException("OOPS!!!! You forgot to specify the deadline!");
            }
            
            return new DeadlineCommand(taskDescription[0], taskDescription[1]);
        }
        case EVENT: {
            String[] taskDescription = StringUtils.removeCommandWord(splitString).split("/");
            
            if (taskDescription[0].isBlank()) {
                throw new JobeException("OOPS!!!! The description of an event task cannot be empty!");
            }
            
            if (taskDescription.length < 2) {
                throw new JobeException("OOPS!!!! You forgot to specify the START date/time!");
            }
            
            if (taskDescription.length < 3) {
                throw new JobeException("OOPS!!!! You forgot to specify the END date/time!");
            }
            
            return new EventCommand(taskDescription[0], taskDescription[1], taskDescription[2]);
        }
        case DELETE:
            try {
                int index = Integer.parseInt(splitString[1]) - 1;
                return new DeleteCommand(index);
            } catch (NumberFormatException e) {
                throw new JobeException("OOPS!!!! The index must be a number!");
            }
        default:
            throw new JobeException("OOPS!!!! I'm Sorry, but I am not sure what you mean.");
        }
    }
}

