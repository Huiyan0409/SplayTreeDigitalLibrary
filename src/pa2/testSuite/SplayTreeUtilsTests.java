package pa2.testSuite;

import org.junit.Before;
import org.junit.Test;
import pa2.SPL_DIGITAL_LIB.Book;
import pa2.SPL_DIGITAL_LIB.SplayTreeNode;
import pa2.SPL_DIGITAL_LIB.SplayTreeUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class tests the SplayTreeUtils
 * @author Huiyan Zhanhg
 * nicolezhang@brandeis.edu
 */
public class SplayTreeUtilsTests{

	private SplayTreeNode<Book> node1;
	private SplayTreeNode<Book> node2;
	private SplayTreeNode<Book> node3;
	private SplayTreeNode<Book> node4;
	private SplayTreeNode<Book> node5;
	private SplayTreeNode<Book> node6;

	@Before
	public void ini() {
		Book book1 = new Book("A Book", "Ellis",		    12345678);
		Book book2 = new Book("Another Book", "Micaela",    87654312);
		Book book3 = new Book("A Third Book", "Shuai",      90871234);
		Book book4 = new Book("A Fourth Book", "Ben",       89502312);
		Book book5 = new Book("A Fifth Book", "Shanshan",   83029539);
		Book book6 = new Book("A Sixth Book", "Andre", 		84920452);

		node1 = new SplayTreeNode<Book>(book1);
		node2 = new SplayTreeNode<Book>(book2);
		node3 = new SplayTreeNode<Book>(book3);
		node4 = new SplayTreeNode<Book>(book4);
		node5 = new SplayTreeNode<Book>(book5);
		node6 = new SplayTreeNode<Book>(book6);
	}

	private void iniForZigAndZag() {

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
	}

	@Test
	public void testZig() {
		iniForZigAndZag();
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

	@Test
	public void testZag() {
		iniForZigAndZag();
		SplayTreeUtils.zag(node3);

		assertTrue(
			node2.parent == node1 &&
			node2.left == node4 &&
			node2.right == node5 &&
			node4.parent == node2 &&
			node4.left == null &&
			node4.right == null &&
			node1.parent == node3 &&
			node1.left == node2 &&
			node1.right == node6 &&
			node5.parent == node2 &&
			node5.left == null &&
			node5.right == null &&
			node3.parent == null &&
			node3.left == node1 &&
			node3.right == null &&
			node6.parent == node1 &&
			node6.left == null &&
			node6.right == null);
	}

	@Test
	public void testZig_zig() {
		iniForZigAndZag();
		SplayTreeUtils.zig_zig(node4);

		assertTrue(
			node2.parent == node4 &&
			node2.left == null &&
			node2.right == node1 &&
			node4.parent == null &&
			node4.left == null &&
			node4.right == node2 &&
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

	@Test
	public void testZig_zag() {
		iniForZigAndZag();
		SplayTreeUtils.zig_zag(node5);

		assertTrue(
			node2.parent == node5 &&
			node2.left == node4 &&
			node2.right == null &&
			node4.parent == node2 &&
			node4.left == null &&
			node4.right == null &&
			node1.parent == node5 &&
			node1.left == null &&
			node1.right == node3 &&
			node5.parent == null &&
			node5.left == node2 &&
			node5.right == node1 &&
			node3.parent == node1 &&
			node3.left == node6 &&
			node3.right == null &&
			node6.parent == node3 &&
			node6.left == null &&
			node6.right == null);
	}

	@Test
	public void testInsertByMode() {
		SplayTreeUtils.insert(node1, node2, 1);
		SplayTreeUtils.insert(node2, node3, 1);
		SplayTreeUtils.insert(node3, node4, 1);
		SplayTreeUtils.insert(node4, node5, 1);
		SplayTreeUtils.insert(node5, node6, 1);

		assertTrue(
			node2.parent == node6 &&
			node2.left == null &&
			node2.right == node4 &&
			node4.parent == node2 &&
			node4.left == null &&
			node4.right == node3 &&
			node1.parent == node5 &&
			node1.left == null &&
			node1.right == null &&
			node5.parent == node6&&
			node5.left == node1 &&
			node5.right == null &&
			node3.parent == node4 &&
			node3.left == null &&
			node3.right == null &&
			node6.parent == null &&
			node6.left == node5 &&
			node6.right == node2);
	}

	@Test
	public void testDelete() {
		testInsertByMode();
		SplayTreeUtils.delete(node6, node2, 0);

		assertTrue(
			node2.parent == null &&
			node2.left == null &&
			node2.right == null &&
			node4.parent == node6 &&
			node4.left == null &&
			node4.right == node3 &&
			node1.parent == node5 &&
			node1.left == null &&
			node1.right == null &&
			node5.parent == node6&&
			node5.left == node1 &&
			node5.right == null &&
			node3.parent == node4 &&
			node3.left == null &&
			node3.right == null &&
			node6.parent == null &&
			node6.left == node5 &&
			node6.right == node4);
	}
	
	@Test
	public void testSearch() {
		testInsertByMode();
		assertEquals(node2,SplayTreeUtils.search(node6, "Micaela", 0));
		assertEquals(node3,SplayTreeUtils.search(node2, "90871234", 1));
		assertEquals(node4,SplayTreeUtils.search(node3, "Nicole", 0));
	}

}