import parser.Parser;
import task.*;
import ui.Ui;
import exception.*;

public class Jobe {
  private TaskList taskList;
  private boolean isExit = false;
  
  public Jobe() {
    this.taskList = new TaskList();
  }
  
  public void run() {
    Ui.sayHello();
    
    while (!this.isExit) {
      try {
        String input = Ui.readMessage();
        
        if (input.isBlank()) {
          throw new JobeException("OOPS!!!! You forgot to type something!");
        }
        
        String[] splitString = input.split(" ");
        Command command = Command.stringToCommand(splitString[0]);
        
        switch (command) {
          case BYE:
            Ui.sayBye();
            this.isExit = true;
            break;
          
          case LIST:
            try {
              if (this.taskList.size() <= 0) {
                throw new JobeException("OOPS!!!! You have nothing in your list!");
              }
              
              System.out.println(this.taskList.toString());
            } catch (JobeException e) {
              System.out.println(e.getMessage());
            }
            break;
          
          case MARK: {
            try {
              int index = Integer.parseInt(splitString[1]) - 1;
              
              if (index > this.taskList.size()) {
                throw new JobeException(
                  "OOPS!!!! You are trying to mark a task which does not exist!"
                );
              }
              
              this.taskList.getTask(index).setDone();
              System.out.println("Nice! I've marked this task as done:");
              System.out.println(taskList.getTask(index));
            } catch (JobeException e) {
              System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
              throw new JobeException("OOPS!!!! The index must be a number!");
            }
            break;
          }
          
          case UNMARK: {
            try {
              int index = Integer.parseInt(splitString[1]) - 1;
              
              if (index > this.taskList.size()) {
                throw new JobeException(
                  "OOPS!!!! You are trying to unmark a task which does not exist!"
                );
              }
              
              this.taskList.getTask(index).setUndone();
              System.out.println("OK, I've marked this task as not done yet:");
              System.out.println(taskList.getTask(index));
            } catch (JobeException e) {
              System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
              throw new JobeException("OOPS!!!! The index must be a number!");
            }
            break;
          }
          
          case TODO: {
            try {
              String taskDescription = Parser.removeCommandWord(splitString);
              
              if (taskDescription.isBlank()) {
                throw new JobeException("OOPS!!!! The description of a todo cannot be empty!");
              }
              
              Task task = new TodoTask(taskDescription);
              this.taskList.addTask(task);
              System.out.println("Got it. I've added this task:");
              System.out.println(task.toString());
              System.out.println("Now you have " + this.taskList.size() + " tasks in the list");
            } catch (JobeException e) {
              System.out.println(e.getMessage());
            }
            break;
          }
          
          case DEADLINE: {
            try {
              String[] splitStringBySlash = Parser.removeCommandWord(splitString).split("/");
              
              if (splitStringBySlash[0].isBlank()) {
                throw new JobeException(
                  "OOPS!!!! The description of a deadline task cannot be empty!"
                );
              }
              
              if (splitStringBySlash.length < 2) {
                throw new JobeException("OOPS!!!! You forgot to specify the deadline!");
              }
              
              Task task = new DeadlineTask(splitStringBySlash[0], splitStringBySlash[1]);
              this.taskList.addTask(task);
              System.out.println("Got it. I've added this task:");
              System.out.println(task.toString());
              System.out.println("Now you have " + this.taskList.size() + " tasks in the list");
            } catch (JobeException e) {
              System.out.println(e.getMessage());
            }
            break;
          }
          
          case EVENT: {
            String[] splitStringBySlash = Parser.removeCommandWord(splitString).split("/");
            
            if (splitStringBySlash[0].isBlank()) {
              throw new JobeException(
                "OOPS!!!! The description of an event task cannot be empty!"
              );
            }
            
            if (splitStringBySlash.length < 2) {
              throw new JobeException("OOPS!!!! You forgot to specify the start date/time!");
            }
            
            if (splitStringBySlash.length < 3) {
              throw new JobeException("OOPS!!!! You forgot to specify the end date/time!");
            }
            
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
          
          case DELETE: {
            try {
              int index = Integer.parseInt(splitString[1]) - 1;
              
              if (index > this.taskList.size()) {
                throw new JobeException(
                  "OOPS!!!! You are trying to delete a task which does not exist!"
                );
              }
              
              Task task = this.taskList.getTask(index);
              this.taskList.removeTask(index);
              System.out.println("Noted. I've removed this task:");
              System.out.println(task.toString());
              System.out.println("Now you have " + this.taskList.size() + " tasks in the list");
            } catch (JobeException e) {
              System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
              throw new JobeException("OOPS!!!! The index must be a number!");
            }
            break;
          }
          
          default:
            throw new JobeException(
              "OOPS!!!! I'm Sorry, but I am not sure what you mean."
            );
        }
      } catch (JobeException e) {
        System.out.println(e.getMessage());
      } catch (Exception e) {
        System.out.println("Something went wrong... Try again later!");
      }
    }
  }
  
  public static void main(String[] args) {
    Jobe jobe = new Jobe();
    jobe.run();
  }
}
