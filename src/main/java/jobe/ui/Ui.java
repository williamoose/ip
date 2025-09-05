package jobe.ui;

import java.util.List;
import java.util.Scanner;

import jobe.task.Task;
import jobe.task.TaskList;

/**
 * Represents a Ui class for displaying messages to user.
 */
public class Ui {
    private Scanner sc;
    private String response;
    
    public Ui() {
        this.sc = new Scanner(System.in);
    }
    
    /**
     * Prints out messages to greet the user upon launching Jobe.
     */
    public void showHelloResponse() {
        this.response = "Hello! I'm Jobe. What can I do for you?";
    }
    
    public void showDeadlineResponse(Task newTask, TaskList taskList) {
        this.response = "Got it. I've added this task:\n"
                + newTask.toString()
                + "\n"
                + "Now you have " + taskList.size() + " tasks in the list";
    }
    
    public void showDeleteResponse(Task newTask, TaskList taskList) {
        this.response = "Noted. I've removed this task:\n"
                + newTask.toString()
                + "\n"
                + "Now you have " + taskList.size() + " tasks in the list";
    }
    
    public void showEventResponse(Task newTask, TaskList taskList) {
        this.response = "Got it. I've added this task:\n"
                + newTask.toString()
                + "\n"
                + "Now you have " + taskList.size() + " tasks in the list";
    }
    
    public void showFindResponse(List<Task> taskList) {
        int count = 1;
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        for (Task task : taskList) {
            sb.append(count).append(". ").append(task).append("\n");
            count++;
        }
        this.response = sb.toString();
    }
    
    public void showListResponse(TaskList taskList) {
        this.response = taskList.toString();
    }
    
    public void showMarkResponse(Task task) {
        this.response = "Nice! I've marked this task as done:\n" + task;
    }
    
    public void showTodoResponse(Task newTask, TaskList taskList) {
        this.response = "Got it. I've added this task:\n"
                + newTask.toString()
                + "\n"
                + "Now you have " + taskList.size() + " tasks in the list";
    }
    
    public void showUnmarkResponse(Task task) {
        this.response =  "OK, I've marked this task as not done yet:" + task;
    }
    
    /**
     * @deprecated Method is part of the old CLI version.
     * Reads user's input using a scanner object.
     *
     * @return A string representing user's input.
     */
    @Deprecated
    public String readMessage() {
        return sc.nextLine();
    }
    
    /**
     * Prints out messages to say bye to user upon exiting Jobe.
     */
    @Deprecated
    public void sayBye() {
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }
    
    public String getResponse() {
        return this.response;
    }
}
