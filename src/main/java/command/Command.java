package command;

import exception.JobeException;
import task.TaskList;
import ui.Ui;

public abstract class Command {
    
    public abstract void execute(TaskList tasklist, Ui ui) throws JobeException;
    
    public abstract boolean isExit();
    
}
