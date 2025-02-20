package doopies.util;

import java.time.format.DateTimeFormatter;

/**
 * Represents various date and time formats used in the doopies.userinterface.Doopies application.
 * <p>
 * This enum defines both input formats (for parsing user-provided dates)
 * and an output format (for displaying dates to the user).
 * Each constant wraps a {@link DateTimeFormatter} with a specific pattern.
 * </p>
 */
public enum DateFormat {
    /** Input format: "yyyy-MM-dd HHmm" (e.g., "2025-01-31 2359"). */
    INPUT_FORMAT_1("yyyy-MM-dd HHmm"),

    /** Input format: "dd/MM/yyyy HHmm" (e.g., "31/01/2025 2359"). */
    INPUT_FORMAT_2("dd/MM/yyyy HHmm"),

    /** Input format: "dd/MM/yyyy HH:mm" (e.g., "31/01/2025 23:59"). */
    INPUT_FORMAT_3("dd/MM/yyyy HH:mm"),

    /** Input format: "MMM dd yyyy HH:mm" (e.g., "Jan 31 2025 23:59"). */
    INPUT_FORMAT_4("MMM dd yyyy HH:mm"),

    /** Input format: "MMM dd yyyy HHa" (e.g., "Jan 31 2025 11PM"). */
    INPUT_FORMAT_5("MMM dd yyyy HHa"),

    /** Input format: "d/MM/yyyy HHmm" (e.g., "1/01/2025 2359"). */
    INPUT_FORMAT_6("d/MM/yyyy HHmm"),

    /** Input format: "d/M/yyyy HHmm" (e.g., "1/1/2025 2359"). */
    INPUT_FORMAT_7("d/M/yyyy HHmm"),

    /** Input format: "dd/M/yyyy HHmm" (e.g., "31/1/2025 2359"). */
    INPUT_FORMAT_8("dd/M/yyyy HHmm"),

    /** Output format: "MMM dd yyyy, hh:mm a" (e.g., "Jan 31 2025, 11:59 PM"). */
    OUTPUT_FORMAT("dd MMM yyyy, hh:mm a");

    /** The {@link DateTimeFormatter} associated with the date format pattern. */
    private final DateTimeFormatter formatter;

    /**
     * Constructs a {@code DateFormat} constant with the specified date pattern.
     *
     * @param pattern The pattern used to create the {@link DateTimeFormatter}.
     */
    DateFormat(String pattern) {

        this.formatter = DateTimeFormatter.ofPattern(pattern);
    }

    /**
     * Retrieves the {@link DateTimeFormatter} associated with this date format.
     *
     * @return The {@link DateTimeFormatter} for this date format.
     */
    public DateTimeFormatter getFormatter() {

        return this.formatter;
    }
}
