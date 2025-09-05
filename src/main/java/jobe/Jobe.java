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
     * @deprecated Method is part of the old CLI version.
     * Runs the main application. Continues reading user inputs until isExit is set to true.
     */
    @Deprecated
    public void run(String input) {
        this.ui.showHelloResponse();
        
        while (!this.isExit) {
            try {
                
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
    
    public String getResponse(String input) {
        try {
            if (input.isBlank()) {
                throw new JobeException("OOPS!!!! You forgot to type something!");
            }
            
            Command c = Parser.parse(input);
            c.execute(this.taskList, this.ui, this.storage);
            return this.ui.getResponse();
        } catch (JobeException e) {
            return e.getMessage();
        } catch (Exception e) {
            return e.getMessage() + " Try again later!";
        }
    }
    
    /**
     * Entry point of the Jobe application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Jobe jobe = new Jobe();
    }
}
