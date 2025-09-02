package command;

import exception.JobeException;
import storage.Storage;
import task.EventTask;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to instruct Jobe to exit when user inputs "event".
 */
public class EventCommand extends Command {
    private String taskDescription;
    private String fromTime;
    private String toTime;
    
    /**
     * Initialises a new EventCommand object.
     *
     * @param taskDescription String description of task.
     * @param fromTime Start date/time of event.
     * @param toTime End date/time of event.
     */
    public EventCommand(String taskDescription, String fromTime, String toTime) {
        this.taskDescription = taskDescription;
        this.fromTime = fromTime;
        this.toTime = toTime;
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
        Task task = new EventTask(this.taskDescription, this.fromTime, this.toTime);
        taskList.addTask(task);
        storage.saveTasks(taskList);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list");
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
