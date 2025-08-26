import command.Command;
import parser.Parser;
import storage.Storage;
import task.*; // to remove *
import ui.Ui;
import exception.*; // to remove *
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class Jobe {
    private TaskList taskList;
    private boolean isExit = false;
    private Ui ui;
    private Storage storage;
    
    public Jobe() {
        this.taskList = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage(taskList);
    }
    
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
    
    public static void main(String[] args) {
        Jobe jobe = new Jobe();
        jobe.run();
    }
}
