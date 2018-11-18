package pa2.SPL_DIGITAL_LIB;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Scanner;

public class SplayTreeDigitalLibrary{
	
	public static SplayTreeNode<Book> authorTree;
	public static SplayTreeNode<Book> ISBNTree;
	public static SplayTreeNode<Book> borrowTree;
	public static File authorLeading = new File("spltreedigi_lib_auth.txt");
	public static File isbnLeading = new File("spltreedigi_lib_isbn.txt");
	public static File borrowLeading = new File("spltreedigi_lib_borrowed.txt");
	private String[] mainArgs = null;
	private int mainArgsIndex = 0;
	private Scanner sc = null;
	private StringBuffer output = null;
	

	public String getNextAnswer(){
		if(mainArgs==null||(mainArgsIndex>=mainArgs.length)){
			return sc.next();
		}else{
			String argStr = mainArgs[mainArgsIndex];
			mainArgsIndex++;
			return argStr;
		}
	}

	 
	public   String main(String[] args) {
		this.mainArgs = args;
		this.sc = new Scanner(System.in);
		output = new StringBuffer();

		String welcome = "Welcome to the SPLTREE_DIGITAL_LIBRARY.\n";
		String loading = "Loading library ...";
		System.out.print(welcome);
		System.out.print(loading);
		output.append(welcome).append(loading);

		loadData();
		String done = "  DONE.\n\n";
		System.out.print(done);
		output.append(done);

		String question = "Please enter ‘author’ to search by author name, ‘ISBN’ to search by reference ISBN, ‘popular’ to see the top books, ‘return’ to return a book, or ‘exit’ to leave the program:";
		System.out.print(question);
		output.append(question) ;

		String answer = getNextAnswer();

		String responseStr = "";
		while(!answer.equalsIgnoreCase("exit")) {
			output.append(answer).append("\n") ;
			if(answer.equalsIgnoreCase("author")) {
				responseStr = "You have selected Search by Author. Please enter the author name: ";
				System.out.print(responseStr);
				output.append(responseStr) ;
				String authorname = getNextAnswer();
				output.append(authorname).append("\n") ;
				authorSearch(authorname);
			} else if(answer.equalsIgnoreCase("isbn")) {
				responseStr="You have selected Search by ISBN. Please enter the ISBN: ";
				System.out.print(responseStr);
				output.append(responseStr);
				String ISBNInput = getNextAnswer();
				output.append(ISBNInput).append("\n") ;
				try {
					long isbn = Long.parseLong(ISBNInput);
					isbnSearch(isbn);
				} catch(Exception e){
					System.out.println("Unvalid ISBN Input");
				}
			} else if (answer.equalsIgnoreCase("popular")) {
				popular();
			} else if (answer.equalsIgnoreCase("return")) {
				responseStr="Please enter the author for the book you are returning: ";
				System.out.print(responseStr);
				String bookauthor = getNextAnswer();
				output.append(bookauthor).append("\n") ;
				returnBook(bookauthor);
			} else {
				System.out.println("Unvalid request!");
			}
			System.out.print(question);
			output.append(question) ;
			answer = getNextAnswer();
		}
		output.append(answer) ;
		sc.close();
		return output.toString();// You MUST return all output to the console here
	}
	
	public  void loadData() {
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
			if(authorTree == null && ISBNTree == null) {
				readFromOrigin();
			}
		}

	}
	
		
	public  void authorSearch(String authorName) {

		String responseStr = "";
		SplayTreeNode<Book> curr = new SplayTreeNode<Book>();
		curr = SplayTreeUtils.search(authorTree,authorName,0);
		if(curr == null) {
			responseStr="Sorry, no books were found with your search term.\n";
			System.out.print(responseStr);
			output.append(responseStr);
			return;
		}
		responseStr = "The following entry matched your search term: "+curr.data.toString()+"\n";
		System.out.print(responseStr);
		output.append(responseStr);

		responseStr = "Would you like to borrow this book?(y/n)";
		System.out.print(responseStr);
		output.append(responseStr);

		String answer = getNextAnswer();
		output.append(answer).append("\n");
		while(!answer.equalsIgnoreCase("y") || !answer.equalsIgnoreCase("n")) {
			responseStr = "Would you like to borrow this book?(y/n)";
			System.out.print(responseStr);
			output.append(responseStr);
			answer = getNextAnswer();
			output.append(answer).append("\n");
		}
		if(answer.equalsIgnoreCase("y")) {
			SplayTreeUtils.delete(authorTree, curr, 0);
			SplayTreeUtils.delete(ISBNTree, curr, 1);
			SplayTreeUtils.insert(borrowTree, curr, 0);
			writeToFile(authorLeading, authorTree);
			writeToFile(isbnLeading, ISBNTree);
			writeToFile(borrowLeading, borrowTree);
		}		
	}
	
	public  void isbnSearch(long isbn) {
		String responseStr = "";
		SplayTreeNode<Book> curr = new SplayTreeNode<Book>();
		String isbnStr = String.valueOf(isbn);
		curr = SplayTreeUtils.search(authorTree,isbnStr,1);
		if(curr == null) {
			responseStr="Sorry, no books were found with your search term.\n";
			System.out.print(responseStr);
			output.append(responseStr);
			return;
		}
		responseStr = "The following entry matched your search term: "+curr.data.toString()+"\n";
		System.out.print(responseStr);
		output.append(responseStr);

		responseStr = "Would you like to borrow this book?(y/n)";
		System.out.print(responseStr);
		output.append(responseStr);

		String answer = getNextAnswer();
		output.append(answer).append("\n");
		while(!answer.equalsIgnoreCase("y") || !answer.equalsIgnoreCase("n")) {
			responseStr = "Would you like to borrow this book?(y/n)";
			System.out.print(responseStr);
			output.append(responseStr);
			answer = getNextAnswer();
			output.append(answer).append("\n");
		}
		if(answer.equalsIgnoreCase("y")) {
			SplayTreeUtils.delete(authorTree, curr, 0);
			SplayTreeUtils.delete(ISBNTree, curr, 1);
			SplayTreeUtils.insert(borrowTree, curr, 0);
			writeToFile(authorLeading, authorTree);
			writeToFile(isbnLeading, ISBNTree);
			writeToFile(borrowLeading, borrowTree);
		}		
	}
	
	public  void popular() {
		System.out.println(authorTree.data.toString());
		output.append(authorTree.data.toString()).append("\n");
		System.out.println(ISBNTree.data.toString());
		System.out.println("");
		output.append(authorTree.data.toString()).append("\n\n");

	}
	
	public  void returnBook(String authorName) {
		String responseStr = "";
		SplayTreeNode<Book> curr = new SplayTreeNode<Book>();
		curr = SplayTreeUtils.search(borrowTree,authorName,0);
		if(curr == null) {
			responseStr="Sorry, no books were borrowed with that author.\n";
			System.out.print(responseStr);
			output.append(responseStr);
			return;
		}
		responseStr="Thank you for returning this book.";
		System.out.print(responseStr);
		output.append(responseStr);
		SplayTreeUtils.delete(borrowTree, curr, 0);
		SplayTreeUtils.insert(ISBNTree, curr, 1);
		SplayTreeUtils.insert(authorTree, curr, 0);
		writeToFile(authorLeading, authorTree);
		writeToFile(isbnLeading, ISBNTree);
		writeToFile(borrowLeading, borrowTree);
	}
	
	public  SplayTreeNode<Book> authorSplayTree(){
		return authorTree;
	}
	
	public  SplayTreeNode<Book> isbnSplayTree(){
		return ISBNTree;
	}
	
	public  SplayTreeNode<Book> borrowedSplayTree(){
		return borrowTree;
	}
	
	public  void readFile(File file, SplayTreeNode<Book> root, int mode) {
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
	
	public  void readFromOrigin() {
		try {
			Scanner sc = new Scanner(new File("./src/pa2/SPL_DIGITAL_LIB/spltree_digi_lib_baselib.txt"));
			Writer writeAuthorTree = new FileWriter("spltreedigi_lib_auth.txt");
			Writer writeISBNTree = new FileWriter("spltreedigi_lib_isbn.txt");
			while(sc.hasNext()) {
				String tab = "\t";
				String line ="";
				try {
					line = sc.nextLine();
					int titleIndex = line.indexOf(tab);
					String title = line.substring(0, titleIndex);
					int authorIndex = line.indexOf(tab,titleIndex+1);
					String author = line.substring(titleIndex+1, authorIndex);

					String ISBNString = line.substring(authorIndex+1);
					long ISBN = Long.parseLong(ISBNString);
					Book newBook = new Book(title, author, ISBN);
					SplayTreeNode<Book> newBookNode = new SplayTreeNode<Book>(newBook);
				buildAuthorTree(newBookNode);
				buildISBNTree(newBookNode);
//				writeAuthorTree.write(newBookNode.toString()+"\n");
//				writeISBNTree.write(newBookNode.toString()+"\n");
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Invalid record format:"+line);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public  void buildAuthorTree(SplayTreeNode<Book> newBook) {
		if(authorTree == null) {
			authorTree = newBook;
		} else {
			SplayTreeUtils.insert(authorTree, newBook, 0);
		}	
	}
	
	public  void buildISBNTree(SplayTreeNode<Book> newBook) {
		if(ISBNTree == null) {
			ISBNTree = newBook;
		} else {
			SplayTreeUtils.insert(ISBNTree, newBook, 1);
		}
	}
	
	public  void writeToFile(File a, SplayTreeNode<Book> root){
		try {
			FileWriter writer = new FileWriter(a);
			if(root == null) {
				return;
			}
			writer.write(root.toString()+"\n");
			writeToFile(a,root.left);
			writeToFile(a,root.right);
		} catch(Exception e) {
			
		}
	}
}
