package task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    
    private List<Task> listOfTasks;
    private int numOfTasks;
    
    public TaskList() {
        this.listOfTasks = new ArrayList<>();
    }
    
    /**
     * Adds the input task into the list of tasks. Increments the total number of tasks.
     *
     * @param task A Task object.
     */
    public void addTask(Task task) {
        this.listOfTasks.add(task);
        this.numOfTasks++;
    }
    
    public Task getTask(int index) {
        return this.listOfTasks.get(index);
    }
    
    /**
     * Removes the input task from the list of tasks. Decrements the total number of tasks.
     *
     * @param index index corresponding to the task object.
     */
    public void removeTask(int index) {
        this.listOfTasks.remove(index);
        this.numOfTasks--;
    }
    
    /**
     * Converts task into stream format.
     *
     * @return A stream containing Task objects.
     */
    public Stream<Task> toStream() {
        return this.listOfTasks.stream();
    }
    
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Here are the tasks in your list:\n");
        int count = 1;
        for (Task task : listOfTasks) {
            str.append(count).append(". ").append(task).append("\n");
            count++;
        }
        return str.toString();
    }
    
    /**
     * Returns number of tasks.
     *
     * @return Number of tasks.
     */
    public int size() {
        return this.numOfTasks;
    }
    
    /**
     * Converts task list and individual files into a savable format for saving to a file.
     *
     * @return a String representation of all tasks.
     */
    public String convertToFileFormat() {
        return this.toStream()
                .map(Task::convertToFileFormat)
                .collect(Collectors.joining("\n"));
    }
}
