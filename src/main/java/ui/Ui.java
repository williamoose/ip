package ui;

import java.util.Scanner;

public class Ui {
  private static Scanner sc = new Scanner(System.in);
  
  public static void sayHello() {
    System.out.println("Hello! I'm Jobe.");
    System.out.println("What can I do for you?");
  }
  
  public static String readMessage() {
    return sc.nextLine();
  }
  
  public static void sayBye() {
    System.out.println("Bye. Hope to see you again soon!");
  }
}
