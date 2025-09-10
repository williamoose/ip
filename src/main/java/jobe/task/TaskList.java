package jobe.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jobe.exception.JobeException;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    
    private List<Task> listOfTasks;
    private int numOfTasks;
    
    public TaskList() {
        this.listOfTasks = new ArrayList<>();
    }
    
    public Task markTask(int index) throws JobeException {
        if (index > this.size()) {
            throw new JobeException("OOPS!!!! You are trying to mark a task which does not exist!");
        }
        
        Task task = this.getTask(index);
        task.setDone();
        return task;
    }
    
    public Task unmarkTask(int index) throws JobeException {
        if (index > this.size()) {
            throw new JobeException("OOPS!!!! You are trying to unmark a task which does not exist!");
        }
        
        Task task = this.getTask(index);
        task.setUndone();
        return task;
    }
    
    public Task createTodoTask(String taskDescription) {
        Task task = new TodoTask(taskDescription);
        this.addTask(task);
        return task;
    }
    
    public Task createDeadlineTask(String taskDescription, String deadline) throws JobeException {
        Task task = new DeadlineTask(taskDescription, deadline);
        this.addTask(task);
        return task;
    }
    
    public Task createEventTask(String taskDescription, String startDate, String endDate) throws JobeException {
        Task task = new EventTask(taskDescription, startDate, endDate);
        this.addTask(task);
        return task;
    }
    
    public Task deleteTask(int index) throws JobeException {
        if (index > this.size()) {
            throw new JobeException("OOPS!!!! You are trying to delete a task which does not exist!");
        }
        
        Task task = this.getTask(index);
        this.removeTask(index);
        return task;
    }
    
    public List<Task> findTask(String keyword) throws JobeException {
        List<Task> list = this.toStream()
          .filter(task -> task.getTaskDescription().contains(keyword))
          .toList();
        
        if (list.isEmpty()) {
            throw new JobeException("OOPS!!!! There are no matching tasks in your list!");
        }
        
        return list;
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
