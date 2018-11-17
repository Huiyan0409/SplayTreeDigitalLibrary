package pa2.testSuite;

import pa2.SPL_DIGITAL_LIB.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class SplayTreeNodeTests {
	
	@Test
	public void testCreateSplayTreeNode() {
		SplayTreeNode<Integer> node = new SplayTreeNode<Integer>();
		node.data = 9;
		assertTrue(node.data == 9);
	}

	@Test
	public void testCreateSplayTreeNodeWithInteger() {
		SplayTreeNode<Integer> node = new SplayTreeNode<Integer>(9);
		assertTrue(node.data == 9);
	}
	
	@Test
	public void testSplayTreeNodeLeft() {
		SplayTreeNode<Integer> node = new SplayTreeNode<Integer>(8);
		SplayTreeNode<Integer> leftnode = new SplayTreeNode<Integer>(7);
		node.left = leftnode;
		assertTrue(node.left == leftnode);
	}

	@Test
	public void testSplayTreeNodeRight() {
		SplayTreeNode<Integer> node = new SplayTreeNode<Integer>(3);
		SplayTreeNode<Integer> rightnode = new SplayTreeNode<Integer>(4);
		node.right = rightnode;
		assertTrue(node.right == rightnode);
	}

	@Test
	public void testSplayTreeNodeParent() {
		SplayTreeNode<Integer> node = new SplayTreeNode<Integer>(6);
		SplayTreeNode<Integer> parentnode = new SplayTreeNode<Integer>(8);
		node.parent = parentnode;
		assertTrue(node.parent == parentnode);
	}

	@Test
	public void testSplayTreeNodeToString() {
		Book book1 = new Book("An Interesting Book", "Antonella", 12345678);
		Book book2 = new Book("Another Book", "Raphael", 87654312);
		Book book3 = new Book("A Third Book", "Allison", 90871234);

		SplayTreeNode<Book> node = new SplayTreeNode<Book>(book1);
		SplayTreeNode<Book> leftnode = new SplayTreeNode<Book>(book2);
		SplayTreeNode<Book> rightnode = new SplayTreeNode<Book>(book3);

		node.left = leftnode;
		node.right = rightnode;

		String expectedOutput = "An Interesting Book, Antonella, 12345678\nLEFT    |    Another Book, Raphael, 87654312\nRIGHT   |    A Third Book, Allison, 90871234";
		String output = node.toString();
		assertTrue(output.equals(expectedOutput));
	}
	

}