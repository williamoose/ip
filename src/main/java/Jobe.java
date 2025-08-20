import Task.*;

public class Jobe {
  private Ui ui;
  private TaskList taskList;
  private boolean isExit = false;

  public Jobe() {
    this.ui = new Ui();
    this.taskList = new TaskList();
  }

  public void run() {
    ui.sayHello();

    while (!this.isExit) {
      String input = ui.readMessage();
      String[] splitString = input.split(" ");
      
      switch (splitString[0]) {
        case "bye":
          ui.sayBye();
          this.isExit = true;
          break;
          
        case "list":
          System.out.println(this.taskList.toString());
          break;
          
        case "mark": {
          int index = Integer.parseInt(splitString[1]) - 1;
          taskList.getTask(index).setDone();
          System.out.println("Nice! I've marked this task as done:");
          System.out.println(taskList.getTask(index));
          break;
        }
        
        case "unmark": {
          int index = Integer.parseInt(splitString[1]) - 1;
          taskList.getTask(index).setUndone();
          System.out.println("OK, I've marked this task as not done yet:");
          System.out.println(taskList.getTask(index));
          break;
        }
        
        case "todo": {
          String taskDescription = Parser.removeCommandWord(splitString);
          Task task = new TodoTask(taskDescription);
          this.taskList.addTask(task);
          System.out.println("Got it. I've added this task:");
          System.out.println(task.toString());
          System.out.println("Now you have " + this.taskList.size() + " tasks in the list");
          break;
        }
        
        case "deadline": {
          String[] splitStringBySlash = Parser.removeCommandWord(splitString).split("/");
          Task task = new DeadlineTask(splitStringBySlash[0], splitStringBySlash[1]);
          this.taskList.addTask(task);
          System.out.println("Got it. I've added this task:");
          System.out.println(task.toString());
          System.out.println("Now you have " + this.taskList.size() + " tasks in the list");
          break;
        }
        
        case "event": {
          String[] splitStringBySlash = Parser.removeCommandWord(splitString).split("/");
          Task task = new EventTask(
            splitStringBySlash[0],
            splitStringBySlash[1],
            splitStringBySlash[2]
          );
          this.taskList.addTask(task);
          System.out.println("Got it. I've added this task:");
          System.out.println(task.toString());
          System.out.println("Now you have " + this.taskList.size() + " tasks in the list");
          break;
        }
        
        default:
          this.taskList.addTask(new Task(input));
          System.out.println("added: " + input);
          break;
      }
    }
  }

  public static void main(String[] args) {
    Jobe jobe = new Jobe();
    jobe.run();
  }
}
