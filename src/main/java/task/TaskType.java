package task;

import exception.JobeException;

public enum TaskType {
    D,
    E,
    T;
    
    public static TaskType stringToCommand(String input) throws JobeException {
        try {
            return TaskType.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new JobeException("OOPS!!!! There is an error with TaskType enums");
        }
    }
}
