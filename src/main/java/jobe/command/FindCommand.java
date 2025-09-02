package jobe.command;

import java.util.List;

import jobe.exception.JobeException;
import jobe.storage.Storage;
import jobe.task.Task;
import jobe.task.TaskList;
import jobe.ui.Ui;

/**
 * Represents a command to instruct jobe.Jobe to exit when user inputs "find".
 */
public class FindCommand extends Command {
    private String keyword;
    
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    
    /**
     * Takes user's jobe.task list and filters out tasks which does not contain the user input.
     * Then it prints out the remaining tasks.
     *
     * @param taskList Current user's jobe.task list for new tasks to be added.
     * @param ui Ui object used to display messages to the user.
     * @param storage Storage object to save tasks to user's local file.
     * @throws JobeException If user has no tasks in the jobe.task list.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JobeException {
        List<Task> list = taskList.toStream()
            .filter(task -> task.getTaskDescription().contains(this.keyword))
            .toList();
        
        if (list.isEmpty()) {
            throw new JobeException("OOPS!!!! There are no matching tasks in your list!");
        }
        
        int count = 1;
        System.out.println("Here are the matching tasks in your list:");
        for (Task task: list) {
            System.out.println(count + ". " + task.toString());
            count += 1;
        }
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
