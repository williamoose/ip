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
