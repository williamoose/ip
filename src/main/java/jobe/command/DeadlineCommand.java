package jobe.command;

import jobe.Jobe;
import jobe.exception.JobeException;
import jobe.storage.Storage;
import jobe.stringutils.StringUtils;
import jobe.task.DeadlineTask;
import jobe.task.Task;
import jobe.task.TaskList;
import jobe.ui.Ui;

/**
 * Represents a command to instruct Jobe to exit when user inputs "deadline".
 */
public class DeadlineCommand extends Command {
    private String taskDescription;
    private String deadline;
    
    /**
     * Initialises a DeadlineCommand object.
     *
     * @param args User input without command word.
     * @throws JobeException if user forgets to input task description or deadline.
     */
    public DeadlineCommand(String args) throws JobeException {
        String[] taskDescription = args.split("/", 2);
        
        if (taskDescription[0].isBlank()) {
            throw new JobeException("OOPS!!!! The description of a deadline task cannot be empty!");
        }
        
        if (taskDescription.length < 2) {
            throw new JobeException("OOPS!!!! You forgot to specify the deadline!");
        }
        
        this.taskDescription = taskDescription[0];
        this.deadline = taskDescription[1];
    }
    
    /**
     * Creates new deadline task, adds this created task to user's task list,
     * saves tasks to user's local file and prints confirmation messages to the user.
     *
     * @param taskList Current user's task list for new tasks to be added.
     * @param ui Ui object used to display messages to the user.
     * @param storage Storage object to save tasks to user's local file.
     * @throws JobeException If DeadlineTask class throws an exception when new task is created.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JobeException {
        Task task = taskList.createDeadlineTask(this.taskDescription, this.deadline);
        storage.saveTasks(taskList);
        ui.showDeadlineResponse(task, taskList);
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
