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
     * Gets response to user input for Jobe to display.
     *
     * @param input User's input.
     * @return Jobe's response.
     */
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
