package task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
  
  private List<Task> listOfTasks;
  private int numOfTasks;
  
  public TaskList() {
    this.listOfTasks = new ArrayList<>();
  }
  
  public void addTask(Task task) {
    this.listOfTasks.add(task);
    this.numOfTasks++;
  }
  
  public Task getTask(int index) {
    return this.listOfTasks.get(index);
  }
  
  public void removeTask(int index) {
    this.listOfTasks.remove(index);
    this.numOfTasks--;
  }
  
  public String toString() {
    StringBuilder str = new StringBuilder("Here are the tasks in your list:\n");
    int count = 1;
    for (Task task : listOfTasks) {
      str.append(count).append(". ").append(task).append("\n");
      count++;
    }
    
    return str.toString();
  }
  
  public int size() {
    return this.numOfTasks;
  }
}
