package command;

import exception.JobeException;
import task.TaskList;
import ui.Ui;

public class UnmarkCommand extends Command {
    private int index;
    
    public UnmarkCommand(int index) {
        this.index = index;
    }
    
    @Override
    public void execute(TaskList taskList, Ui ui) {
        try {
            if (this.index > taskList.size()) {
                throw new JobeException("OOPS!!!! You are trying to unmark a task which does not exist!");
            }
            
            taskList.getTask(this.index).setUndone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(taskList.getTask(this.index));
        } catch (JobeException e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public boolean isExit() {
        return false;
    }
}
