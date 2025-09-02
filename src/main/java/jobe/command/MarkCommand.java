package jobe.command;

import jobe.exception.JobeException;
import jobe.storage.Storage;
import jobe.task.TaskList;
import jobe.ui.Ui;

/**
 * Represents a command to instruct jobe.Jobe to exit when user inputs "mark".
 */
public class MarkCommand extends Command {
    private int index;
    
    public MarkCommand(int index) {
        this.index = index;
    }
    
    /**
     * Marks a jobe.task corresponding to user's input index,
     * saves tasks to user's local file and prints confirmation messages to the user.
     *
     * @param taskList Current user's jobe.task list for new tasks to be added.
     * @param ui Ui object used to display messages to the user.
     * @param storage Storage object to save tasks to user's local file.
     * @throws JobeException If user is trying to mark a jobe.task which does not exist.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JobeException {
        if (this.index > taskList.size()) {
            throw new JobeException("OOPS!!!! You are trying to mark a jobe.task which does not exist!");
        }
        
        taskList.getTask(this.index).setDone();
        storage.saveTasks(taskList);
        System.out.println("Nice! I've marked this jobe.task as done:");
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
