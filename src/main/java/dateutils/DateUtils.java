package dateutils;

import exception.JobeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {
    
    public static final DateTimeFormatter INPUT_DATE_TIME_FORMATTER = DateTimeFormatter
            .ofPattern("d/MM/yyyy HHmm");
    public static final DateTimeFormatter OUTPUT_DATE_TIME_FORMATTER = DateTimeFormatter
            .ofPattern("MMM d yyyy, HH:mm");
    
    public static String convertToDateTime(String input) throws JobeException {
        try {
            LocalDateTime date = LocalDateTime.parse(input, INPUT_DATE_TIME_FORMATTER);
            return date.format(OUTPUT_DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new JobeException(
              "OOPS!!!! Failed to parse DateTime, please format your Date and Time as dd/mm/yyyy hhmm."
            );
        }
    }
}
