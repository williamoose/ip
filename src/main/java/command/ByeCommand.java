package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to instruct Jobe to exit when user inputs "bye".
 */
public class ByeCommand extends Command {
    
    /**
     * Executes ByeCommand. Calls Ui class to display goodbye message to user.
     *
     * @param taskList Current user's task list for new tasks to be added.
     * @param ui Ui object used to display messages to the user.
     * @param storage Storage object to save tasks to user's local file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.sayBye();
    }
    
    /**
     * Sets isExit to true.
     *
     * @return boolean true.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
