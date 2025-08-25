package command;

import exception.JobeException;
import task.TaskList;
import ui.Ui;

public class MarkCommand extends Command {
    private int index;
    
    public MarkCommand(int index) {
        this.index = index;
    }
    
    @Override
    public void execute(TaskList taskList, Ui ui) {
        try {
            if (this.index > taskList.size()) {
                throw new JobeException("OOPS!!!! You are trying to mark a task which does not exist!");
            }
            
            taskList.getTask(this.index).setDone();
            System.out.println("Nice! I've marked this task as done:");
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
