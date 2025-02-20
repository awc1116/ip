package doopies.notebook;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    void testDeadlineCreation() {
        Deadline deadline = new Deadline("return book", "31/1/2025 2359");
        assertEquals("[D][ ] return book (by: 31 Jan 2025, 11:59 pm)", deadline.toString());
    }

    @Test
    void testMarkDeadline() {
        Deadline deadline = new Deadline("return book", "31/1/2025 2359");
        Deadline markedDeadline = deadline.mark();

        assertNotSame(deadline, markedDeadline);
        assertTrue(markedDeadline.isDone());
        assertEquals("[D][X] return book (by: 31 Jan 2025, 11:59 pm)", markedDeadline.toString());
    }

    @Test
    void testUnmarkDeadline() {
        Deadline deadline = new Deadline("return book", "31/1/2025 2359");
        Deadline markedDeadline = deadline.mark();
        Deadline unmarkedDeadline = markedDeadline.unmark();

        assertNotSame(markedDeadline, unmarkedDeadline);
        assertFalse(unmarkedDeadline.isDone());
        assertEquals("[D][ ] return book (by: 31 Jan 2025, 11:59 pm)", unmarkedDeadline.toString());
    }

    @Test
    void testInvalidDateFormat() {
        Deadline deadline = new Deadline("return book", "invalid date");
        assertEquals("[D][ ] return book (by: invalid date)", deadline.toString());
    }
}

