package pa2.SPL_DIGITAL_LIB;

/**
 * This class contains utilities for SplayTreeNodes
 * @author Huiyan Zhang
 * nicolezhang@brandeis.edu
 */
public class SplayTreeUtils {

    /**
     * Compare data of two nodes by author or ISBN deciding by mode
     * @param nodeData, data of node which is going to be compared
     * @param currData, data of current node
     * @param mode, an integer supposed to equal to 0 or 1
     * @return an integer represents the result of comparing
     * The running time is O(1)
     */
    public static <T> int compare(T nodeData, T currData, int mode) {
		if(nodeData==null || currData==null){
			return 0;
		}
		if(nodeData instanceof Book && currData instanceof Book){
			if(mode == 0) {//if mode is 0, comparing by author
				return ((Book)nodeData).author.compareTo(((Book)currData).author);
			} else if(mode == 1) {//if mode is 1, comparing by ISBN
				return  new Long(((Book)nodeData).ISBN).compareTo(new Long(((Book)currData).ISBN));
			} else {
				System.out.println("Unable to compare due to the incorrect mode");
				return 0;
			}
		}else {
			return 0;
		}
    }

    /**
     * Compare the search item with the data of current node by author or ISBN
     * @param searchitem, the item that the user wants to search in the splay tree
     * @param curr, data of node which is going to be compared with
     * @param mode, an integer supposed to equal to 0 or 1
     * @return an integer represents the result of comparing after comparing two data of nodes
     * The running time is O(1)
     */
	public static <T> int compare(String searchitem, T curr, int mode) {

		Book nodeData = null;

		if(mode == 0) {//if mode is 0, comparing by author
			nodeData = new Book("",searchitem,0);//set the comparing data
		} else if(mode == 1) {//if mode is 1, comparing by ISBN
			nodeData = new Book("","",Long.parseLong(searchitem));
		} else {
			System.out.println("Unable to compare due to the incorrect mode");
			return 0;
		}
		return compare(nodeData,curr,mode);
	}
    
	/**
	 * Left rotation of splay tree
	 * @param node, the splayTreeNode that needs to be rotated
	 * The running time is O(1)
	 */
	public static<T> void zig(SplayTreeNode<T> node) {
		if(node == null || node.parent == null || node.parent.left != node) {
			System.out.println("Unable to zig rotate!");
			return;
		}
		SplayTreeNode<T> beforeParent = node.parent;
		SplayTreeNode<T> subParent = beforeParent.parent;
		node.parent = subParent;
		beforeParent.left = node.right;
		if(node.right != null) {
			node.right.parent = beforeParent;
		}
		node.right = beforeParent;
		beforeParent.parent = node;
		if(subParent != null) {  
			if (subParent.right == beforeParent) {
				subParent.right = node;
			} else {
				subParent.left = node;
			}
		}
	}

	/**
	 * Right rotation of splay tree
	 * @param node, the splayTreeNode that needs to be rotated
	 * The running time is O(1)
	 */
	public static<T> void zag(SplayTreeNode<T> node) {
		if(node == null || node.parent == null || node.parent.right != node) {
			System.out.println("Unable to zag rotate!");
			return;
		}
		SplayTreeNode<T> beforeParent = node.parent;
		SplayTreeNode<T> subParent = beforeParent.parent;
		node.parent = subParent;
		beforeParent.right = node.left;
		if(node.left != null) {
			node.left.parent = beforeParent;
		}
		node.left = beforeParent;
		beforeParent.parent = node;
		if(subParent != null) { 
			if (subParent.right == beforeParent) {
				subParent.right = node;
			} else {
				subParent.left = node;
			}
		}
	}


	/**
	 * Double rotation for left child of left child or right child of right child
	 * zig-zig/zag-zag
	 * @param node, the splayTreeNode that needs to be rotated
	 * The running time is O(1)
	 */
	public static<T> void zig_zig(SplayTreeNode<T> node) {
		if(node==node.parent.right&&node.parent==node.parent.parent.right) { // right child of right child
			zag(node.parent);
			zag(node);
		}else if(node==node.parent.left&&node.parent==node.parent.parent.left){ // left child or left child
			zig(node.parent);
			zig(node);
		}else{
			System.out.println("Unable to zig_zig!");
			return;
		}
	}

	/**
	 * Double rotation for left child of right child or right child of left child
	 * zig-zag/zag-zig
	 * @param node, the splayTreeNode that needs to be rotated
	 * The running time is O(1)
	 */
	public static<T> void zig_zag(SplayTreeNode<T> node) {
		if(node==node.parent.right&&node.parent==node.parent.parent.left) { // right child of left child
			zag(node);
			zig(node);
		}else if(node==node.parent.left&&node.parent==node.parent.parent.right) { // left child of right child
			zig(node);
			zag(node);
		}else{
			System.out.println("Unable to zig_zag!");
			return;
		}
	}
	/**
	 * splay the new node to the root of the splay tree
	 * @param node, the splaytreenode that needs to be splayed up
	 * The running time is O(1)
	 */
	private static<T> void splay(SplayTreeNode<T> node) {
		if(node != null){
			while(node.parent != null){
				if (rotateUp(node)) return;
			}
		}
	}

	/**
	 * Insert a splaytreenode to the splay tree
	 * @param root, the root of the splay tree
	 * @param node, the node that needs to be inserted
	 * The running time is O(logn)
	 */
	public static<T> void insert(SplayTreeNode<T> root, SplayTreeNode<T> node, int mode) {
		SplayTreeNode<T> current = root;
		if(current == null){
			System.out.println("root is null!");
			return;
		}else{
			int result = 0;
			SplayTreeNode<T> parent = current;
			while(current != null){
				parent = current;
				result = compare(node.data, current.data, mode);
				if(result > 0){
					current = current.right;
				}else{
					current = current.left;
				}
			}
			if(result > 0){
				parent.right = node;
			}else{
				parent.left = node;
			}
			node.parent = parent;
			splay(node);  //splay the node to the root after insertion
		}
	}

	/**
	 * Insert a splaytreenode to the splay tree
	 * @param root, the root of the splay tree
	 * @param node, the node that needs to be inserted
	 * The running time is O(logn)
	 */
	public static<T> void insertForNoSpay(SplayTreeNode<T> root, SplayTreeNode<T> node, int mode) {
		SplayTreeNode<T> current = root;
		if(current == null){
			System.out.println("root is null!");
			return;
		}else{
			int result = 0;
			SplayTreeNode<T> parent = current;
			while(current != null){
				parent = current;
				result = compare(node.data, current.data, mode);
				if(result > 0){
					current = current.right;
				}else{
					current = current.left;
				}
			}
			if(result > 0){
				parent.right = node;
			}else{
				parent.left = node;
			}
			node.parent = parent;
		}
	}

	/**
	 * Delete a splaytreenode from a splay tree
	 * @param root, the root node of the splay tree
	 * @param node, the node that is going to be deleted
	 * The running time is O(logn)
	 */
	public static<T> SplayTreeNode<T> delete(SplayTreeNode<T> root, SplayTreeNode<T> node, int mode) {
		//finding out the node that is going to be deleted
        SplayTreeNode<T> del = search(root,node.data,mode);
		if(del == null){
			System.out.println("node not found!");
			return null;
		}else{
			//splay the node that is going to be deleted to be root node
			splay(del);
			root = del;
			if(root.left == null && root.right == null) {
				return null;
			}
			//finding the max of left subtree
			SplayTreeNode frontNodeOfRoot = frontOfNode(root);
			splayToRootLeft(root,frontNodeOfRoot);
			//remove currently root and splay the max of left subtree to the root
            if(root.left!=null) {
                root.left.right = root.right;
                if (root.right != null) {
                    root.right.parent = root.left;
                }
                root = root.left;
            }else{
                root=root.right;
            }
			root.parent.left = root.parent.right = null;
			root.parent = null;
		}
		return root;
	}

	/**
	 * Rotate the max node in the left subtree to the root of left subtree
	 * @param root, the root of the splay tree
	 * @param node, the splaytreenode that needs to be rotated
	 * The running time is O(logn)
	 */
	public static<T> void splayToRootLeft(SplayTreeNode<T> root, SplayTreeNode<T> node) {
		if(node != null){
			while(node.parent != root && node.parent != null){
				if (rotateUp(node)) return;
			}
		}
	}

	/**
	 * Get the right most node in the left subtree which is the max of left subtree
	 * @param node, the node that is going to be deleted from the splay tree
	 * @return the max node of the left subtree
	 * The running time is O(logn)
	 */
	public static<T> SplayTreeNode<T> frontOfNode(SplayTreeNode<T> node){
		if(node == null||node.left==null){
			return null;
		}else{
			SplayTreeNode<T> current = node.left;
			while(current.right != null){
				current = current.right;
			}
			return current;
		}
	}
	
	/**
	 * search data from the splay tree when the data is entered as a string
	 * @param root, the root of the splay tree
	 * @param searchitem, the data that wants to be found out
	 * @param mode, an integer equals to 0 or 1 deciding searching by author or ISBN
	 * @return the node that is found in the splay tree
	 * The running time is O(logn)
	 */
	public static<T> SplayTreeNode<T> search(SplayTreeNode<T> root, String searchitem, int mode){
		Book nodeData = null;

		if(mode == 0) {//search by author if mode is 0
			nodeData = new Book("",searchitem,0);//build up the node element for searching
		} else if(mode == 1) {//search by ISBn if mode is 1
			nodeData = new Book("","",Long.parseLong(searchitem));
		}
		return search(root,(T)nodeData,mode);
	}

	/**
	 * search a splaytreenode from a splay tree
	 * @param root, the root of the splay tree
	 * @param searchitemData, the data of the splaytreenode that wants to be found out in the splay tree
	 * @param mode, an integer equals to 0 or 1 deciding searching by author or ISBN
	 * @return the node that is found in the splay tree or the last access node if the expected node cannot be found in the splay tree
	 * The running time is O(logn)
	 */
	public static<T> SplayTreeNode<T> search(SplayTreeNode<T> root, T searchitemData, int mode){
		SplayTreeNode<T> current = root;
		SplayTreeNode<T> subcurrent = current;
		if(current == null){
			return null;
		}else{
			int result = 0;
			while(current != null){
				result = compare(searchitemData,current.data,mode);
				if(result > 0){
						subcurrent = current;
						current = current.right;
				}else if(result < 0){
						subcurrent = current;
						current = current.left;
				}else{
					splay(current);
					return current;
				}
			}
			splay(subcurrent);
			return subcurrent;
		}
	}

	/**
	 * Rotate up the splay tree node 
	 * @param node,the node that needs to be rotated up
	 * @return false if rotate successfully, true if not
	 * The running time is O(1)
	 */
	private static<T> boolean rotateUp(SplayTreeNode<T> node) {
		if(node == node.parent.left){
			if(node.parent.parent == null){
				zig(node);
			}else if(node.parent.parent != null && node.parent == node.parent.parent.left){ //zig-zig
				zig_zig(node);
			}else if(node.parent.parent != null && node.parent == node.parent.parent.right){
				zig_zag(node);
			}
		}else if(node == node.parent.right){
			if(node.parent.parent == null){
				zag(node);
			}else if(node.parent.parent != null && node.parent == node.parent.parent.right){ //zag-zag
				zig_zig(node);
			}else if(node.parent.parent != null && node.parent == node.parent.parent.left){
				zig_zag(node);
			}
		}else{
			System.out.println("splay error!node is not its parent's child");
			return true;
		}
		return false;
	}
}