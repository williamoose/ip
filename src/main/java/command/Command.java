package command;

import exception.JobeException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public abstract class Command {
    
    public abstract void execute(TaskList tasklist, Ui ui, Storage storage) throws JobeException;
    
    public abstract boolean isExit();
    
}
