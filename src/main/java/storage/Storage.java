package storage;

import exception.JobeException;
import parser.Parser;
import task.Task;
import task.TaskList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Storage {
    private File file;
    private static final String TASKS_FILE_PATH_STRING = "./data/jobe.txt";
    private static final Path TASKS_FILE_PATH = Paths.get(TASKS_FILE_PATH_STRING);
    
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
    
    public void saveTasks(TaskList taskList) {
        try {
            Files.writeString(TASKS_FILE_PATH, taskList.convertToFileFormat());
        } catch (IOException e) {
            System.out.println("OOPS!!!! File failed to save.");
        }
    }
    
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
