package jobe.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jobe.exception.JobeException;
import jobe.storage.Storage;
import jobe.task.Task;
import jobe.task.TaskList;

public class ListCommandTest {
    
    private ListCommand cmd;
    private TaskList taskList;
    private UiStub uiStub;
    private StorageStub storageStub;
    
    @BeforeEach
    public void setUp(){
        cmd = new ListCommand();
        taskList = new TaskList();
        uiStub = new UiStub();
        storageStub = new StorageStub();
    }
    
    @Test
    public void testExecute_nonEmptyTaskList() throws JobeException {
        taskList.addTask(new Task("read book"));
        cmd.execute(taskList, uiStub, storageStub);
        assertTrue(uiStub.isResponseCalled);
    }
    
    @Test
    public void testExecute_emptyTaskList_throwsException() {
        JobeException exception = assertThrows(JobeException.class,
                () -> cmd.execute(taskList, uiStub, storageStub)
        );
        assertEquals("OOPS!!!! You have nothing in your list!", exception.getMessage());
    }
}
