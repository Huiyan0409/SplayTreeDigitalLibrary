package pa2.SPL_DIGITAL_LIB;

import java.util.Comparator;

public class SplayTreeUtils {

    
    public static <T> int compare(T nodeData, T currData, int mode) {

		if(nodeData==null || currData==null){
			return 0;
		}
		if(nodeData instanceof Book && currData instanceof Book){
			if(mode == 0) {
				return ((Book)nodeData).author.compareTo(((Book)currData).author);
			} else if(mode == 1) {
				return (int)(((Book)nodeData).ISBN-((Book)currData).ISBN);
			} else {
				System.out.println("Unable to compare due to the incorrect mode");
				return 0;
			}
		}else {
			return 0;
		}
    }


	public static <T> int compare(String searchitem, T curr, int mode) {

		Book nodeData = null;

		if(mode == 0) {
			nodeData = new Book("",searchitem,0);//构建比较数据信息
		} else if(mode == 1) {
			nodeData = new Book("","",Long.parseLong(searchitem));
		} else {
			System.out.println("Unable to compare due to the incorrect mode");
			return 0;
		}
		return compare(nodeData,curr,mode);
	}
    
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
		if(subParent != null) {  //为null则node的parent已被置为null,无需操作
			if (subParent.right == beforeParent) {
				subParent.right = node;
			} else {
				subParent.left = node;
			}
		}
	}

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
		if(subParent != null) { //为null则node的parent已被置为null,无需操作
			if (subParent.right == beforeParent) {
				subParent.right = node;
			} else {
				subParent.left = node;
			}
		}
	}


	/**
	 * 同一边连续两次旋转
	 * @param node
	 */
	public static<T> void zig_zig(SplayTreeNode<T> node) {
		if(node==node.parent.right&&node.parent==node.parent.parent.right) { // '\' 型
			zag(node.parent);
			zag(node);
		}else if(node==node.parent.left&&node.parent==node.parent.parent.left){ // '/' 型
			zig(node.parent);
			zig(node);
		}else{
			System.out.println("Unable to zig_zig!");
			return;
		}
	}

	/**
	 * z字形两次旋转
	 * @param node
	 */
	public static<T> void zig_zag(SplayTreeNode<T> node) {
		if(node==node.parent.right&&node.parent==node.parent.parent.left) { // '<' 型
			zag(node);
			zig(node);
		}else if(node==node.parent.left&&node.parent==node.parent.parent.right) { // '>' 型
			zig(node);
			zag(node);
		}else{
			System.out.println("Unable to zig_zag!");
			return;
		}
	}
	/**
	 * 添加节点后对树的改变（将新添加的节点旋转为根节点）
	 * @param node
	 */
	private static<T> void splay(SplayTreeNode<T> node) {//首先要明确一点，该方法只会在根不为空的判断里面出现
		if(node != null){
			while(node.parent != null){
				if (rotateUp(node)) return;
			}
		}
	}

	/**
	 * 添加节点
	 * @param root
	 * @param node
	 *
	 * 添加节点：跟二叉排序树一样添加节点，不过与二叉排序树添加节点不同的是伸展树添加节点后，
	 * 会把新添加的节点旋转到根节点位置。
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
			splay(node);  //旋到root
		}
	}

	/**
	 * 删除节点
	 * @param root
	 * @param node
	 * 删除节点：从SplayTree中找出要删除的节点，然后将该节点旋转为根节点，然后再把此时的根节点的左子树中
	 * 的最大值节点(前驱)旋转为根节点的左子节点（这样根节点的左子节点的子节点只会有左子树，因为它最大），紧接着把根节点
	 * 的右节点当做根节点的左子节点的右子节点，最后在 删除根节点（也就是要删除的节点）。
	 */
	public static<T> SplayTreeNode<T> delete(SplayTreeNode<T> root, SplayTreeNode<T> node, int mode) {
		//找到要删除的节点
		SplayTreeNode<T> del = search(root,node.data,mode);
		if(del == null){
			System.out.println("node not found!");
			return null;
		}else{
			//把要删除的节点旋转为根节点
			splay(del);
			root = del;
			//找到此时根节点的前驱
			SplayTreeNode frontNodeOfRoot = frontOfNode(root);
			//把跟的前驱旋转为根节点的左子节点
			splayToRootLeft(root,frontNodeOfRoot);
			//去除root，并将左子节点变为root
			root.left.right = root.right;
			if(root.right != null){
				root.right.parent = root.left;
			}
			root = root.left;
			root.parent.left = root.parent.right = null;
			root.parent = null;
		}
		return  root;
	}

	/**
	 * 把根的前驱旋转为根节点的左子节点
	 * @param root
	 * @param node
	 */
	public static<T> void splayToRootLeft(SplayTreeNode<T> root, SplayTreeNode<T> node) {
		if(node != null){
			while(node.parent != root && node.parent != null){
				if (rotateUp(node)) return;
			}
		}
	}

	/**
	 *找到节点的前驱
	 */
	public static<T> SplayTreeNode<T> frontOfNode(SplayTreeNode<T> node){
		if(node == null){
			return null;
		}else{
			SplayTreeNode<T> current = node.left;
			while(current != null){
				current = current.right;
			}
			return current;
		}
	}

	public static<T> SplayTreeNode<T> search(SplayTreeNode<T> root, String searchitem, int mode){
		Book nodeData = null;

		if(mode == 0) {
			nodeData = new Book("",searchitem,0);//构建比较数据信息
		} else if(mode == 1) {
			nodeData = new Book("","",Long.parseLong(searchitem));
		}
		return search(root,(T)nodeData,mode);

	}

	public static<T> SplayTreeNode<T> search(SplayTreeNode<T> root, T searchitemData, int mode){
		SplayTreeNode<T> current = root;
		if(current == null){
			return null;
		}else{
			int result = 0;
			while(current != null){
				result = compare(searchitemData,current.data,mode);
				if(result > 0){
					current = current.right;
				}else if(result < 0){
					current = current.left;
				}else{
					return current;
				}
			}
			return null;
		}
	}


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