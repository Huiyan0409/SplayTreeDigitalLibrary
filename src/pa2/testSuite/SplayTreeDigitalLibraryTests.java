package pa2.testSuite;

import org.junit.Test;
import pa2.SPL_DIGITAL_LIB.SplayTreeDigitalLibrary;

import java.io.File;

import static org.junit.Assert.assertTrue;

public class SplayTreeDigitalLibraryTests {

	@Test
	public void testPopular() {
		SplayTreeDigitalLibrary digilib = new SplayTreeDigitalLibrary();
		String[] args = {"author", "Keith Cooper & Linda Torczon", "n", "isbn", "9780262640688", "n", "exit"};
		digilib.main(args);

		SplayTreeDigitalLibrary digilib2 = new SplayTreeDigitalLibrary();
		String[] args2 = {"popular", "exit"};
		String output = digilib2.main(args2);

		String expectedOutput = "Welcome to the SPLTREE_DIGITAL_LIBRARY.\nLoading library...  DONE.\n\nPlease enter ‘author’ to search by author name, ‘ISBN’ to search by reference ISBN, ‘popular’ to see the top books, ‘return’ to return a book, or ‘exit’ to leave the program: popular\nEngineering: A Compiler, 2nd Edition, Keith Cooper & Linda Torczon, 9780120884780\nThe Elements of Computing Systems: Building a Modern Computer from First Principles, Noam Nisan & Shimon Schocken, 9780262640688\n\nPlease enter ‘author’ to search by author name, ‘ISBN’ to search by reference ISBN, ‘popular’ to see the top books, ‘return’ to return a book, or ‘exit’ to leave the program: exit";

		assertTrue(output.equals(expectedOutput));
	}

	@Test
	public void  testMain(){
		SplayTreeDigitalLibrary digilib = new SplayTreeDigitalLibrary();
		digilib.main(null);
	}

	@Test
	public void testReadFromOriginForFirstReader(){

		File authorLeading = new File("spltreedigi_lib_auth.txt");
		File isbnLeading = new File("spltreedigi_lib_isbn.txt");
		File borrowLeading = new File("spltreedigi_lib_borrowed.txt");
		authorLeading.delete();
		isbnLeading.delete();
		borrowLeading.delete();
		new SplayTreeDigitalLibrary().readFromOrigin();
	}

	
}