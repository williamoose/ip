package jobe.command;

import jobe.storage.Storage;
import jobe.task.Task;
import jobe.task.TaskList;
import jobe.task.TodoTask;
import jobe.ui.Ui;

/**
 * Represents a command to instruct Jobe to exit when user inputs "todo".
 */
public class TodoCommand extends Command {
    private String taskDescription;
    
    public TodoCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }
    
    /**
     * Creates new to do task, adds this created task to tasklist,
     * saves tasks to user's local file and prints confirmation messages to the user.
     *
     * @param taskList Current user's task list for new tasks to be added.
     * @param ui Ui object used to display messages to the user.
     * @param storage Storage object to save tasks to user's local file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = new TodoTask(this.taskDescription);
        taskList.addTask(task);
        storage.saveTasks(taskList);
        ui.showTodoResponse(task, taskList);
    }
    
    /**
     * Sets isExit to false.
     *
     * @return boolean false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
