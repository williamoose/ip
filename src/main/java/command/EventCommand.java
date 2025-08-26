package command;

import exception.JobeException;
import storage.Storage;
import task.DeadlineTask;
import task.EventTask;
import task.Task;
import task.TaskList;
import ui.Ui;

public class EventCommand extends Command {
    private String taskDescription;
    private String fromTime;
    private String toTime;
    
    public EventCommand(String taskDescription, String fromTime, String toTime) {
        this.taskDescription = taskDescription;
        this.fromTime = fromTime;
        this.toTime = toTime;
    }
    
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JobeException {
        Task task = new EventTask(this.taskDescription, this.fromTime, this.toTime);
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