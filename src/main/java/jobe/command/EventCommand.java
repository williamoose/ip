package jobe.command;

import jobe.exception.JobeException;
import jobe.storage.Storage;
import jobe.task.Task;
import jobe.task.TaskList;
import jobe.ui.Ui;

/**
 * Represents a command to instruct Jobe to exit when user inputs "event".
 */
public class EventCommand extends Command {
    private String taskDescription;
    private String startDate;
    private String endDate;
    
    /**
     * Initialises an EventCommand object.
     *
     * @param args User input without command word.
     * @throws JobeException if user forgets to input task description, start, or end date.
     */
    public EventCommand(String args) throws JobeException {
        String[] taskDescription = args.split("/", 2);
        
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
        
        this.taskDescription = taskDescription[0];
        this.startDate = dates[0];
        this.endDate = dates[1];
    }
    
    /**
     * Creates new event task, adds this created task to user's task list,
     * saves tasks to user's local file and prints confirmation messages to the user.
     *
     * @param taskList Current user's task list for new tasks to be added.
     * @param ui Ui object used to display messages to the user.
     * @param storage Storage object to save tasks to user's local file.
     * @throws JobeException If EventTask class throws an exception when new task is created.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JobeException {
        Task task = taskList.createEventTask(this.taskDescription, this.startDate, this.endDate);
        storage.saveTasks(taskList);
        ui.showEventResponse(task, taskList);
    }
    
    /**
     * Sets isExit to false.
     *
     * @return boolean false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
