package pa2.SPL_DIGITAL_LIB;
/**
 * This class helps implement Book with its title, author and ISBN
 * @author Huiyan Zhang
 * nicolezhang@brandeis.edu
 */
public class Book {
	public String title;
	public String author;
	public long ISBN;
	
	/**
	 * Constructor for book
	 * @param title, the title of book
	 * @param author, the author of book
	 * @param ISBN, the ISBN number of book
	 */
	public Book(String title, String author, long ISBN) {
		this.title = title;
		this.author = author;
		this.ISBN = ISBN;
	}
	
	/**
	 * toString method returns a string about the book
	 * The running time is O(1)
	 */
	public String toString() {
		return this.title+", "+this.author+", "+this.ISBN;
	}
}
