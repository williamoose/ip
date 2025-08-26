package command;

import exception.JobeException;
import storage.Storage;
import task.DeadlineTask;
import task.Task;
import task.TaskList;
import ui.Ui;

public class DeadlineCommand extends Command {
    private String taskDescription;
    private String deadline;
    
    public DeadlineCommand(String taskDescription, String deadline) {
        this.taskDescription = taskDescription;
        this.deadline = deadline;
    }
    
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JobeException {
        Task task = new DeadlineTask(this.taskDescription, this.deadline);
        taskList.addTask(task);
        storage.saveTasks(taskList);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list");
    }
    
    @Override
    public boolean isExit() {
        return false;
    }
}
