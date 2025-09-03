package jobe.parser;

import jobe.command.ByeCommand;
import jobe.command.Command;
import jobe.command.CommandType;
import jobe.command.DeadlineCommand;
import jobe.command.DeleteCommand;
import jobe.command.EventCommand;
import jobe.command.FindCommand;
import jobe.command.ListCommand;
import jobe.command.MarkCommand;
import jobe.command.TodoCommand;
import jobe.command.UnmarkCommand;
import jobe.exception.JobeException;
import jobe.stringutils.StringUtils;
import jobe.task.DeadlineTask;
import jobe.task.EventTask;
import jobe.task.Task;
import jobe.task.TaskType;
import jobe.task.TodoTask;

/**
 * Parser class to parse user's input into corresponding command classes.
 */
public class Parser {
    
    /**
     * Parses user's input into corresponding commands listed in CommandType enum.
     * Calls the corresponding command class with necessary inputs.
     *
     * @param input User's entire String input.
     * @return a Command object corresponding to user's input.
     * @throws JobeException If individual command classes throws an exception.
     */
    public static Command parse(String input) throws JobeException {
        String[] splitString = input.split(" ", 2);
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
                throw new JobeException("OOPS!!!! The description of a deadline jobe.task cannot be empty!");
            }
            if (taskDescription.length < 2) {
                throw new JobeException("OOPS!!!! You forgot to specify the deadline!");
            }
            System.out.println(taskDescription[1]);
            return new DeadlineCommand(taskDescription[0], taskDescription[1]);
        }
        case EVENT: {
            String[] taskDescription = StringUtils.removeFirstWord(splitString).split("/", 2);
            if (taskDescription[0].isBlank()) {
                throw new JobeException("OOPS!!!! The description of an event jobe.task cannot be empty!");
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
        case FIND:
            if (splitString.length < 2 || splitString[1].isBlank()) {
                throw new JobeException("OOPS!!!! You forgot to enter your keyword!");
            }
            String keywords = splitString[1];
            return new FindCommand(keywords);
        default:
            throw new JobeException("OOPS!!!! I'm Sorry, but I am not sure what you mean.");
        }
    }
    
    /**
     * Parses input when loading files into corresponding tasks.
     *
     * @param input Input from file containing user's tasks.
     * @return a Task object corresponding to input from file.
     * @throws JobeException If failed to parse tasks.
     */
    public static Task parseTask(String input) throws JobeException {
        String[] splitString = input.split(" / ");
        TaskType taskType = TaskType.stringToCommand(splitString[0]);
        // if splitString[1] equals to "[X]" means that it has been marked as done
        // and hence isDone is true.
        boolean isDone = splitString[1].equals("[X]");
        String taskDescription = splitString[2];
        switch (taskType) {
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

