package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class ByeCommand extends Command {
    
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.sayBye();
    }
    
    @Override
    public boolean isExit() {
        return true;
    }
}
