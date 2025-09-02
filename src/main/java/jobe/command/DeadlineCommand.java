package jobe.command;

import jobe.exception.JobeException;
import jobe.storage.Storage;
import jobe.task.DeadlineTask;
import jobe.task.Task;
import jobe.task.TaskList;
import jobe.ui.Ui;

/**
 * Represents a command to instruct jobe.Jobe to exit when user inputs "deadline".
 */
public class DeadlineCommand extends Command {
    private String taskDescription;
    private String deadline;
    
    /**
     * Initialises a DeadlineCommand object.
     *
     * @param taskDescription String description of jobe.task.
     * @param deadline Deadline of jobe.task.
     */
    public DeadlineCommand(String taskDescription, String deadline) {
        this.taskDescription = taskDescription;
        this.deadline = deadline;
    }
    
    /**
     * Creates new deadline jobe.task, adds this created jobe.task to user's jobe.task list,
     * saves tasks to user's local file and prints confirmation messages to the user.
     *
     * @param taskList Current user's jobe.task list for new tasks to be added.
     * @param ui Ui object used to display messages to the user.
     * @param storage Storage object to save tasks to user's local file.
     * @throws JobeException If DeadlineTask class throws an exception when new jobe.task is created.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JobeException {
        Task task = new DeadlineTask(this.taskDescription, this.deadline);
        taskList.addTask(task);
        storage.saveTasks(taskList);
        System.out.println("Got it. I've added this jobe.task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list");
    }
    
    /**
     * Sets isExit to false.
     *
     * @return boolean false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
