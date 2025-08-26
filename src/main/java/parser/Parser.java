package parser;

import command.*;
import exception.JobeException;
import stringutils.StringUtils;
import task.*;

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
            String taskDescription = StringUtils.removeFirstWord(splitString);
            
            if (taskDescription.isBlank()) {
                throw new JobeException("OOPS!!!! The description of a todo cannot be empty!");
            }
            
            return new TodoCommand(taskDescription);
        }
        case DEADLINE: {
            String[] taskDescription = StringUtils.removeFirstWord(splitString).split("/", 2);
            
            if (taskDescription[0].isBlank()) {
                throw new JobeException("OOPS!!!! The description of a deadline task cannot be empty!");
            }
            
            if (taskDescription.length < 2) {
                throw new JobeException("OOPS!!!! You forgot to specify the deadline!");
            }
            
            return new DeadlineCommand(taskDescription[0], taskDescription[1]);
        }
        case EVENT: {
            String[] taskDescription = StringUtils.removeFirstWord(splitString).split("/", 2);
            
            if (taskDescription[0].isBlank()) {
                throw new JobeException("OOPS!!!! The description of an event task cannot be empty!");
            }
            
            if (taskDescription.length < 2) {
                throw new JobeException("OOPS!!!! You forgot to specify the START date/time!");
            }
            
            String[] dates = taskDescription[1].split("/to");
            if (dates.length < 2) {
                throw new JobeException("OOPS!!!! You forgot to specify the END date/time!");
            }
            
            return new EventCommand(taskDescription[0], dates[0], dates[1]);
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
    
    public static Task parseTask(String input) throws JobeException {
        String[] splitString = input.split(" / ");
        TaskType taskType = TaskType.stringToCommand(splitString[0]);
        
        // if splitString[1] equals to "[X]" means that it has been marked as done
        // and hence isDone is true.
        boolean isDone = splitString[1].equals("[X]");
        String taskDescription = splitString[2];
        switch(taskType) {
        case D:
            String deadline = splitString[3];
            return new DeadlineTask(taskDescription, deadline, isDone);
        case E:
            String fromDate = splitString[3];
            String toDate = splitString[4];
            return new EventTask(taskDescription, fromDate, toDate, isDone);
        case T:
            return new TodoTask(taskDescription, isDone);
        default:
            throw new JobeException("OOPS!!!! Task parsing failed");
        }
    }
}

