package doopies.notebook;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    void testToDoCreation() {
        ToDo todo = new ToDo("read book");
        assertEquals("[T][ ] read book", todo.toString());
    }

    @Test
    void testMarkToDo() {
        ToDo todo = new ToDo("read book");
        ToDo markedToDo = todo.mark();

        assertNotSame(todo, markedToDo);
        assertTrue(markedToDo.isDone());
        assertEquals("[T][X] read book", markedToDo.toString());
    }

    @Test
    void testUnmarkToDo() {
        ToDo todo = new ToDo("read book");
        ToDo markedToDo = todo.mark();
        ToDo unmarkedToDo = markedToDo.unmark();

        assertNotSame(markedToDo, unmarkedToDo);
        assertFalse(unmarkedToDo.isDone());
        assertEquals("[T][ ] read book", unmarkedToDo.toString());
    }
}

