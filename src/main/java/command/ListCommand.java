package command;

import exception.JobeException;
import task.TaskList;
import ui.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui) {
        try {
            if (taskList.size() <= 0) {
                throw new JobeException("OOPS!!!! You have nothing in your list!");
            }
            
            System.out.println(taskList.toString());
        } catch (JobeException e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public boolean isExit() {
        return false;
    }
}
