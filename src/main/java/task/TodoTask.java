package task;

public class TodoTask extends Task {
    
    public TodoTask(String taskDescription) {
        super(taskDescription);
    }
    
    @Override
    public String toString() {
        return "  [T]" + super.toString();
    }
}
