package pa2.SPL_DIGITAL_LIB;

public class Book {
	public String title;
	public String author;
	public long ISBN;
	
	public Book(String title, String author, long ISBN) {
		this.title = title;
		this.author = author;
		this.ISBN = ISBN;
	}
	
	public String toString() {
		return this.title+", "+this.author+", "+this.ISBN;
	}
}
