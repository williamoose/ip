package jobe;

import jobe.command.Command;
import jobe.exception.JobeException;
import jobe.parser.Parser;
import jobe.storage.Storage;
import jobe.task.TaskList;
import jobe.ui.Ui;

/**
 * Main class for the Jobe Application.
 */
public class Jobe {
    private TaskList taskList;
    private boolean isExit = false;
    private Ui ui;
    private Storage storage;
    
    /**
     * Initialises a Jobe object.
     */
    public Jobe() {
        this.taskList = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage(taskList);
    }
    
    /**
     * Runs the main application. Continues reading user inputs until isExit is set to true.
     */
    public void run() {
        this.ui.sayHello();
        
        while (!this.isExit) {
            try {
                String input = this.ui.readMessage();
                
                if (input.isBlank()) {
                    throw new JobeException("OOPS!!!! You forgot to type something!");
                }
                
                Command c = Parser.parse(input);
                c.execute(this.taskList, this.ui, this.storage);
                this.isExit = c.isExit();
            } catch (JobeException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage() + " Try again later!");
            }
        }
    }
    
    /**
     * Generates a response for the user's chat message.
     *
     * @param input user's input.
     * @return String representation of response.
     */
    public String getResponse(String input) {
        return "Jobe heard: " + input;
    }
    
    /**
     * Entry point of the Jobe application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Jobe jobe = new Jobe();
        jobe.run();
    }
}
