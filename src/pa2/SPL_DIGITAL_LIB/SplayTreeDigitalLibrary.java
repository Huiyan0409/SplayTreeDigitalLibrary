package pa2.SPL_DIGITAL_LIB;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Scanner;

public class SplayTreeDigitalLibrary{
	
	public static SplayTreeNode<Book> authorTree;
	public static SplayTreeNode<Book> ISBNTree;
	public static SplayTreeNode<Book> borrowTree;
	 
	public String main(String[] args) {
		String output = "";
		Scanner sc = new Scanner(System.in);
		String welcome = "Welcome to the SPLTREE_DIGITAL_LIBRARY.";
		String loading = "Loading library ...";
		output = output + welcome + loading;
		loadData();
		String question = "Please enter ‘author’ to search by author name, ‘ISBN’ to search by reference ISBN, ‘popular’ to see the top books, ‘return’ to return a book, or ‘exit’ to leave the program:";
		output = output + question;
		String answer = sc.next();
		while(!answer.equalsIgnoreCase("exit")) {
			if(answer.equalsIgnoreCase("author")) {
				System.out.println("You have selected Search by Author. Please enter the author name: ");
				String authorname = sc.next();
				authorSearch(authorname);
			} else if(answer.equalsIgnoreCase("isbn")) {
				System.out.println("You have selected Search by ISBN. Please enter the ISBN: ");
				String ISBNInput = sc.next();
				try {
					long isbn = Long.parseLong(ISBNInput);
					isbnSearch(isbn);
				} catch(Exception e){
					System.out.println("Unvalid ISBN Input");
				}
			} else if (answer.equalsIgnoreCase("popular")) {
				popular();
			} else if (answer.equalsIgnoreCase("return")) {
				System.out.println("Please enter the author for the book you are returning: ");
				String bookauthor = sc.next();
				returnBook(bookauthor);
			} else {
				System.out.println("Unvalid request!");
			}
			System.out.println(question);
			answer = sc.next();
		}
		return output;// You MUST return all output to the console here
	}
	
	public static void loadData() {
		File authorLeading = new File("spltreedigi_lib_auth.txt");
		File isbnLeading = new File("spltreedigi_lib_isbn.txt");
		File borrowLeading = new File("spltreedigi_lib_borrowed.txt");
		if(!authorLeading.exists() && !isbnLeading.exists() && !borrowLeading.exists()) {
			try {
				authorLeading.createNewFile();
				isbnLeading.createNewFile();
				borrowLeading.createNewFile();
			}catch(Exception e){
				e.printStackTrace();
			}
			readFromOrigin();
		} else {
			readFile(authorLeading,authorTree,0);
			readFile(isbnLeading,ISBNTree,1);
			readFile(borrowLeading,borrowTree,0);
		}
		System.out.println("DONE");
		System.out.println("");
	}
	
	public static void readFile(File file, SplayTreeNode<Book> root, int mode) {
		try {
			Scanner sc = new Scanner(file);
			while(sc.hasNext()) {
				String tab = " ";
				char tabb = tab.charAt(0);
				int i = 1;
				String line = sc.nextLine();
				int titleIndex = line.indexOf("  ");
				String title = line.substring(0, titleIndex);
				int authorIndex = line.indexOf("  ",titleIndex+1);
				String author = line.substring(titleIndex+1, authorIndex);
				while(author.charAt(0) == tabb) {
					author = author.substring(i, authorIndex);
					i++;
				}
				String ISBNString = line.substring(authorIndex+1);
				i=1;
				while(ISBNString.charAt(0) == tabb) {
					ISBNString = ISBNString.substring(authorIndex+1+i);
					i++;
				}
				long ISBN = Long.parseLong(ISBNString);
				Book newBook = new Book(title, author, ISBN);
				SplayTreeNode<Book> newBookNode = new SplayTreeNode<Book>(newBook);
				if(root == null) {
					root = newBookNode;
				}else{
					SplayTreeUtils.insert(root, newBookNode, mode);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void readFromOrigin() {
		try {
			Scanner sc = new Scanner(new File("spltree_digi_lib_baselib.txt"));
			Writer writeAuthorTree = new FileWriter("spltreedigi_lib_auth.txt");
			Writer writeISBNTree = new FileWriter("spltreedigi_lib_isbn.txt");
			while(sc.hasNext()) {
				String tab = " ";
				char tabb = tab.charAt(0);
				int i = 1;
				String line = sc.nextLine();
				int titleIndex = line.indexOf("  ");
				String title = line.substring(0, titleIndex);
				int authorIndex = line.indexOf("  ",titleIndex+1);
				String author = line.substring(titleIndex+1, authorIndex);
				while(author.charAt(0) == tabb) {
					author = author.substring(i, authorIndex);
					i++;
				}
				String ISBNString = line.substring(authorIndex+1);
				i=1;
				while(ISBNString.charAt(0) == tabb) {
					ISBNString = ISBNString.substring(authorIndex+1+i);
					i++;
				}
				long ISBN = Long.parseLong(ISBNString);
				Book newBook = new Book(title, author, ISBN);
				SplayTreeNode<Book> newBookNode = new SplayTreeNode<Book>(newBook);
				buildAuthorTree(newBookNode);
				buildISBNTree(newBookNode);
				writeAuthorTree.write(newBookNode.toString()+"\n");
				writeISBNTree.write(newBookNode.toString()+"\n");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void buildAuthorTree(SplayTreeNode<Book> newBook) {
		if(authorTree == null) {
			authorTree = newBook;
		} else {
			SplayTreeUtils.insert(authorTree, newBook, 0);
		}	
	}
	
	public static void buildISBNTree(SplayTreeNode<Book> newBook) {
		if(ISBNTree == null) {
			ISBNTree = newBook;
		} else {
			SplayTreeUtils.insert(ISBNTree, newBook, 1);
		}
	}
	
	
	public static void authorSearch(String authorName) {
		Scanner input = new Scanner(System.in);
		SplayTreeNode<Book> curr = new SplayTreeNode<Book>();
		curr = SplayTreeUtils.search(authorTree,authorName,0);
		if(curr == null) {
			System.out.println("Sorry, no books were found with your search term.");
			return;
		}
		System.out.println("The following entry matched your search term: "+curr.data.toString());
		System.out.println("Would you like to borrow this book?(y/n)");
		String answer = input.next();
		while(!answer.equalsIgnoreCase("y") || !answer.equalsIgnoreCase("n")) {
			System.out.println("Would you like to borrow this book?(y/n)");
			answer = input.next();
		}
		if(answer.equalsIgnoreCase("y")) {
			SplayTreeUtils.delete(authorTree, curr, 0);
			SplayTreeUtils.delete(ISBNTree, curr, 1);
			SplayTreeUtils.insert(borrowTree, curr, 0);
		}		
	}
	
	public static void isbnSearch(long isbn) {
		Scanner input = new Scanner(System.in);
		SplayTreeNode<Book> curr = new SplayTreeNode<Book>();
		curr = SplayTreeUtils.search(authorTree,String.valueOf(isbn),1);
		if(curr == null) {
			System.out.println("Sorry, no books were found with your search term.");
			return;
		}
		System.out.println("The following entry matched your search term: "+curr.data.toString());
		System.out.println("Would you like to borrow this book?(y/n)");
		String answer = input.next();
		while(!answer.equalsIgnoreCase("y") || !answer.equalsIgnoreCase("n")) {
			System.out.println("Would you like to borrow this book?(y/n)");
			answer = input.next();
		}
		if(answer.equalsIgnoreCase("y")) {
			SplayTreeUtils.delete(authorTree, curr, 0);
			SplayTreeUtils.delete(ISBNTree, curr, 1);
			SplayTreeUtils.insert(borrowTree, curr, 0);
		}		
	}
	
	public static void popular() {
		System.out.println(authorTree.toString());
		System.out.println("");
		System.out.println(ISBNTree.toString());
	}
	
	public static void returnBook(String authorName) {
		SplayTreeNode<Book> curr = new SplayTreeNode<Book>();
		curr = SplayTreeUtils.search(borrowTree,authorName,0);
		if(curr == null) {
			System.out.println("Sorry, no books were borrowed with that author");
			return;
		}
		System.out.println("Thank you for returning this book");
		SplayTreeUtils.delete(borrowTree, curr, 0);
		SplayTreeUtils.insert(ISBNTree, curr, 1);
		SplayTreeUtils.insert(authorTree, curr, 0);
	}
	
	public static SplayTreeNode<Book> authorSplayTree(){
		return authorTree;
	}
	
	public static SplayTreeNode<Book> isbnSplayTree(){
		return ISBNTree;
	}
	
	public static SplayTreeNode<Book> borrowedSplayTree(){
		return borrowTree;
	}

}
