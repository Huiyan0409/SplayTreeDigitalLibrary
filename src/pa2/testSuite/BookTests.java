package pa2.testSuite;

import pa2.SPL_DIGITAL_LIB.Book;

import static org.junit.Assert.*;
import org.junit.Test;

public class BookTests {

	@Test
	public void testBookISBN() {
		Book book1 = new Book("Example Title", "Example Author", 12345678);
		assertTrue(book1.ISBN == 12345678);
	}
	
	@Test
	public void testBookToString() {
		Book book1 = new Book("Example Title", "Example Author", 12345678);
		String expectedOutput = "Example Title, Example Author, 12345678";
		String output = book1.toString();
		assertTrue(output.equals(expectedOutput));
	}
	
}