package doopies.util;

import java.time.format.DateTimeFormatter;

public enum DateFormat {
    INPUT_FORMAT_1("yyyy-MM-dd HHmm"),
    INPUT_FORMAT_2("dd/MM/yyyy HHmm"),
    INPUT_FORMAT_3("dd/MM/yyyy HH:mm"),
    INPUT_FORMAT_4("MMM dd yyyy HH:mm"),
    INPUT_FORMAT_5("MMM dd yyyy HHa"),
    INPUT_FORMAT_6("d/MM/yyyy HHmm"),
    INPUT_FORMAT_7("d/M/yyyy HHmm"),
    INPUT_FORMAT_8("dd/M/yyyy HHmm"),
    OUTPUT_FORMAT("MMM dd yyyy, hh:mm a");

    private final DateTimeFormatter formatter;

    DateFormat(String pattern) {
        this.formatter = DateTimeFormatter.ofPattern(pattern);
    }

    public DateTimeFormatter getFormatter() {
        return this.formatter;
    }
}
