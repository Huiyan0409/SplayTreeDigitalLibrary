package pa2.testSuite;

import pa2.SPL_DIGITAL_LIB.*;
import pa2.SPL_DIGITAL_LIB.SplayTreeUtils;

import static org.junit.Assert.*;

import org.junit.Test;

public class SplayTreeUtilsTests{

	@Test
	public void testZig() {
		Book book1 = new Book("A Book", "Ellis", 12345678);
		Book book2 = new Book("Another Book", "Micaela", 87654312);
		Book book3 = new Book("A Third Book", "Shuai", 90871234);
		Book book4 = new Book("A Fourth Book", "Ben", 89502312);
		Book book5 = new Book("A Fifth Book", "Shanshan", 83029539);
		Book book6 = new Book("A Sixth Book", "Andre", 84920452);

		SplayTreeNode<Book> node1 = new SplayTreeNode<Book>(book1);
		SplayTreeNode<Book> node2 = new SplayTreeNode<Book>(book2);
		SplayTreeNode<Book> node3 = new SplayTreeNode<Book>(book3);
		SplayTreeNode<Book> node4 = new SplayTreeNode<Book>(book4);
		SplayTreeNode<Book> node5 = new SplayTreeNode<Book>(book5);
		SplayTreeNode<Book> node6 = new SplayTreeNode<Book>(book6);

		node1.left = node2;
		node2.parent = node1;
		node1.right = node3;
		node3.parent = node1;

		node2.left = node4;
		node4.parent = node2;
		node2.right = node5;
		node5.parent = node2;

		node3.left = node6;
		node6.parent = node3;

		SplayTreeUtils.zig(node2);

		assertTrue(node2.parent == null &&
			node2.left == node4 &&
			node2.right == node1 &&
			node4.parent == node2 &&
			node4.left == null &&
			node4.right == null &&
			node1.parent == node2 &&
			node1.left == node5 &&
			node1.right == node3 &&
			node5.parent == node1 &&
			node5.left == null &&
			node5.right == null &&
			node3.parent == node1 &&
			node3.left == node6 &&
			node3.right == null &&
			node6.parent == node3 &&
			node6.left == null &&
			node6.right == null);
	}
	
}