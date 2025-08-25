package command;

import task.Task;
import task.TaskList;
import task.TodoTask;
import ui.Ui;

public class TodoCommand extends Command {
    private String taskDescription;
    
    public TodoCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }
    
    @Override
    public void execute(TaskList taskList, Ui ui) {
        Task task = new TodoTask(this.taskDescription);
        taskList.addTask(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list");
    }
    
    @Override
    public boolean isExit() {
        return false;
    }
}
