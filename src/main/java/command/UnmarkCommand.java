package command;

import exception.JobeException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to instruct Jobe to exit when user inputs "unmark".
 */
public class UnmarkCommand extends Command {
    private int index;
    
    public UnmarkCommand(int index) {
        this.index = index;
    }
    
    /**
     * Unmarks a task corresponding to user's input index,
     * saves tasks to user's local file and prints confirmation messages to the user.
     *
     * @param taskList Current user's task list for new tasks to be added.
     * @param ui Ui object used to display messages to the user.
     * @param storage Storage object to save tasks to user's local file.
     * @throws JobeException If user is trying to unmark a task which does not exist.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JobeException {
        if (this.index > taskList.size()) {
            throw new JobeException("OOPS!!!! You are trying to unmark a task which does not exist!");
        }
        
        taskList.getTask(this.index).setUndone();
        storage.saveTasks(taskList);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(taskList.getTask(this.index));
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
