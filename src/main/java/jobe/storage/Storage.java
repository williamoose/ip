package jobe.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import jobe.exception.JobeException;
import jobe.parser.Parser;
import jobe.task.Task;
import jobe.task.TaskList;

/**
 * Storage class for file validation, creation, loading and saving.
 */
public class Storage {
    private File file;
    public static final String TASKS_FILE_PATH_STRING = "./data/jobe.txt";
    public static final Path TASKS_FILE_PATH = Paths.get(TASKS_FILE_PATH_STRING);
    
    /**
     * Constructor to load tasks into file if file exists.
     * <p>
     * Else, if parent directory does not exist, one is created.
     * Similarly, if file does not exist one is created.
     *
     * @param taskList Task list containing user's current tasks.
     */
    public Storage(TaskList taskList) {
        this.file = new File(TASKS_FILE_PATH_STRING);
        
        File parentDirectory = file.getParentFile();
        if (parentDirectory != null && !parentDirectory.exists()) {
            if (parentDirectory.mkdirs()) {
                System.out.println("Parent directory 'data' successfully created!");
            } else {
                System.out.println("Failed to create parent directory 'data'.");
            }
        }
        
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("File 'jobe.txt' containing tasks successfully created!");
                } else {
                    System.out.println("File 'jobe.txt' could not be created");
                }
            } catch (IOException ioe) {
                System.out.println("Creation of file has failed with error: " + ioe.getMessage());
            }
        }
        
        this.loadTasks(taskList);
    }
    
    /**
     * Saves user's jobe.task list into a file with the corresponding file path.
     *
     * @param taskList Task list containing user's tasks.
     */
    public void saveTasks(TaskList taskList) {
        try {
            Files.writeString(TASKS_FILE_PATH, taskList.convertToFileFormat());
        } catch (IOException e) {
            System.out.println("OOPS!!!! File failed to save.");
        }
    }
    
    /**
     * Loads user's saved tasks from file into jobe.task list.
     *
     * @param taskList Task list containing user's tasks.
     */
    public void loadTasks(TaskList taskList) {
        try {
            List<String> lines = Files.readAllLines(TASKS_FILE_PATH);
            for (String line: lines) {
                Task task = Parser.parseTask(line);
                taskList.addTask(task);
                System.out.println(task);
            }
        } catch (IOException e) {
            System.out.println("Failed to read from file. Please try again!");
        } catch (JobeException je) {
            System.out.println(je.getMessage());
        }
    }
}
