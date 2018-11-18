package pa2.testSuite;

import org.junit.Test;
import pa2.SPL_DIGITAL_LIB.Book;

import static org.junit.Assert.assertTrue;

/**
 * This class tests the Book class
 * @author Huiyan Zhang
 * nicolezhang@brandeis.edu
 */
public class BookTests {

	@Test
	/**
	 * Test the correctness of ISBN for a entered book
	 */
	public void testBookISBN() {
		Book book1 = new Book("Example Title", "Example Author", 12345678);
		assertTrue(book1.ISBN == 12345678);
	}
	
	@Test
	/**
	 * Test the toString method
	 */
	public void testBookToString() {
		Book book1 = new Book("Example Title", "Example Author", 12345678);
		String expectedOutput = "Example Title, Example Author, 12345678";
		String output = book1.toString();
		assertTrue(output.equals(expectedOutput));
	}
	
	@Test
	/**
	 * Test the correctness of book title for a entered book
	 */
	public void testBookTitle() {
		Book book1 = new Book("Example Title", "Example Author", 12345678);
		assertTrue(book1.title.equals("Example Title"));
	}
	
	@Test
	/**
	 * Test the correctness of book author for a entered book
	 */
	public void testBookAuthor() {
		Book book1 = new Book("Example Title", "Example Author", 12345678);
		assertTrue(book1.author.equals("Example Author"));
	}
	
}