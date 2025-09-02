package jobe.command;

import jobe.exception.JobeException;
import jobe.storage.Storage;
import jobe.task.Task;
import jobe.task.TaskList;
import jobe.ui.Ui;

/**
 * Represents a command to instruct jobe.Jobe to exit when user inputs "delete".
 */
public class DeleteCommand extends Command {
    private int index;
    
    public DeleteCommand(int index) {
        this.index = index;
    }
    
    /**
     * Searches for tasks corresponding to the user's input index, and removes it from jobe.task list.
     * Saves tasks to user's local file and prints confirmation messages to the user.
     *
     * @param taskList Current user's jobe.task list for new tasks to be added.
     * @param ui Ui object used to display messages to the user.
     * @param storage Storage object to save tasks to user's local file.
     * @throws JobeException If user is trying to delete a jobe.task which does not exist.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JobeException {
        if (this.index > taskList.size()) {
            throw new JobeException("OOPS!!!! You are trying to delete a jobe.task which does not exist!");
        }
        
        Task task = taskList.getTask(this.index);
        taskList.removeTask(this.index);
        storage.saveTasks(taskList);
        System.out.println("Noted. I've removed this jobe.task:");
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
