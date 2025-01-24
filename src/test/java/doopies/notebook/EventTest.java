package doopies.notebook;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventTest {

    @Test
    void testEventCreation() {
        Event event = new Event("meeting", "24/1/2025 1400", "24/1/2025 1600");
        assertEquals("[E][ ] meeting (from: Jan 24 2025, 02:00 pm to: Jan 24 2025, 04:00 pm)", event.toString());
    }

    @Test
    void testMarkEvent() {
        Event event = new Event("meeting", "24/1/2025 1400", "24/1/2025 1600");
        Event markedEvent = event.mark();

        assertNotSame(event, markedEvent);
        assertTrue(markedEvent.isDone());
        assertEquals("[E][X] meeting (from: Jan 24 2025, 02:00 pm to: Jan 24 2025, 04:00 pm)", markedEvent.toString());
    }

    @Test
    void testUnmarkEvent() {
        Event event = new Event("meeting", "24/1/2025 1400", "24/1/2025 1600");
        Event markedEvent = event.mark();
        Event unmarkedEvent = markedEvent.unmark();

        assertNotSame(markedEvent, unmarkedEvent);
        assertFalse(unmarkedEvent.isDone());
        assertEquals("[E][ ] meeting (from: Jan 24 2025, 02:00 pm to: Jan 24 2025, 04:00 pm)", unmarkedEvent.toString());
    }

    @Test
    void testInvalidDateFormat() {
        Event event = new Event("meeting", "invalid start", "invalid end");
        assertEquals("[E][ ] meeting (from: invalid start to: invalid end)", event.toString());
    }
}

