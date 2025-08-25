package command;

import task.TaskList;
import ui.Ui;

public class ByeCommand extends Command {
    
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.sayBye();
    }
    
    @Override
    public boolean isExit() {
        return true;
    }
}
