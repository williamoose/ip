package jobe.parser;

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
        
        assert command != null : "Command type should never be null";
        
        String args = splitString.length > 1 ? splitString[1] : "";
        
        switch (command) {
        case LIST:
            return new ListCommand();
        case MARK:
            return new MarkCommand(args);
        case UNMARK:
            return new UnmarkCommand(args);
        case TODO:
            return new TodoCommand(args);
        case DEADLINE:
            return new DeadlineCommand(args);
        case EVENT:
            return new EventCommand(args);
        case DELETE:
            return new DeleteCommand(args);
        case FIND:
            return new FindCommand(args);
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
            String startDate = splitString[3];
            String endDate = splitString[4];
            return new EventTask(taskDescription, startDate, endDate, isDone);
        case T:
            return new TodoTask(taskDescription, isDone);
        default:
            throw new JobeException("OOPS!!!! Task parsing failed");
        }
    }
}

