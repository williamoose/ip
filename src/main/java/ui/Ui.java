package ui;

import java.util.Scanner;

public class Ui {
    private Scanner sc;
    
    public Ui() {
        this.sc = new Scanner(System.in);
    }
    
    public void sayHello() {
        System.out.println("Hello! I'm Jobe.");
        System.out.println("What can I do for you?");
    }
    
    public String readMessage() {
        return sc.nextLine();
    }
    
    public void sayBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
