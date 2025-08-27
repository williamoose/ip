package command;

import exception.JobeException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

public class DeleteCommand extends Command {
    private int index;
    
    public DeleteCommand(int index) {
        this.index = index;
    }
    
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            if (this.index > taskList.size()) {
                throw new JobeException("OOPS!!!! You are trying to delete a task which does not exist!");
            }
            
            Task task = taskList.getTask(this.index);
            taskList.removeTask(this.index);
            storage.saveTasks(taskList);
            System.out.println("Noted. I've removed this task:");
            System.out.println(task.toString());
            System.out.println("Now you have " + taskList.size() + " tasks in the list");
        } catch (JobeException e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public boolean isExit() {
        return false;
    }
}
