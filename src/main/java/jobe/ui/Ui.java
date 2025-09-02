package jobe.ui;

import java.util.Scanner;

/**
 * Represents a Ui class for displaying messages to user.
 */
public class Ui {
    private Scanner sc;
    
    public Ui() {
        this.sc = new Scanner(System.in);
    }
    
    /**
     * Prints out messages to greet the user upon launching jobe.Jobe.
     */
    public void sayHello() {
        System.out.println("\n" + "Hello! I'm jobe.Jobe.");
        System.out.println("What can I do for you?");
    }
    
    /**
     * Reads user's input using a scanner object.
     *
     * @return A string representing user's input.
     */
    public String readMessage() {
        return sc.nextLine();
    }
    
    /**
     * Prints out messages to say bye to user upon exiting jobe.Jobe.
     */
    public void sayBye() {
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }
}
