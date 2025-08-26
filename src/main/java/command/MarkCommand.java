package command;

import exception.JobeException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class MarkCommand extends Command {
    private int index;
    
    public MarkCommand(int index) {
        this.index = index;
    }
    
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            if (this.index > taskList.size()) {
                throw new JobeException("OOPS!!!! You are trying to mark a task which does not exist!");
            }
            
            taskList.getTask(this.index).setDone();
            storage.saveTasks(taskList);
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
