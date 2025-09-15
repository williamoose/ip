package jobe.command;

import jobe.storage.Storage;
import jobe.task.TaskList;

public class StorageStub extends Storage {
    
    public StorageStub() {
        super(new TaskList()); // still needs to call super, but you can ignore the taskList
    }
    
    @Override
    public void saveTasks(TaskList taskList) {
    }
    
    @Override
    public void loadTasks(TaskList taskList) {
    }
}


