import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Jobe {
  private Scanner scanner;
  private List<String> taskList;
  boolean isExit = false;

  public Jobe() {
    scanner = new Scanner(System.in);
    taskList = new ArrayList<>();
  }

  public void run() {
    System.out.println("Hello! I'm Jobe.");
    System.out.println("What can I do for you?");

  while (!this.isExit) {
    String input = this.scanner.nextLine();
    
    switch (input) {
      case "bye":
        System.out.println("Bye. Hope to see you again soon!");
        this.isExit = true;
        break;
      case "list":
        this.printList();
        break;
      default:
        this.taskList.add(input);
        System.out.println("added: " + input);
        break;
      }
    }
  }

  public void printList() {
    int count = 1;
    for (String task : taskList) {
      System.out.println(count + ". " + task);
      count++;
    }
  }

  public static void main(String[] args) {
    Jobe jobe = new Jobe();
    jobe.run();
  }
}
