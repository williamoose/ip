package command;

import exception.JobeException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.util.List;

public class FindCommand extends Command {
    private String keyword;
    
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JobeException {
        System.out.println("Here are the matching tasks in your list:");
        List<Task> list = taskList.toStream()
          .filter(task -> task.getTaskDescription().contains(this.keyword))
          .toList();
        int count = 1;
        for (Task task: list) {
            System.out.println(count + ". " + task.toString());
            count += 1;
        }
    }
    
    @Override
    public boolean isExit() {
        return false;
    }
}
