package pa2.SPL_DIGITAL_LIB;

/**
 * This is a generic class implementing SplayTreeNode
 * @author Huiyan Zhang
 * nicolezhang@brandeis.edu
 * 
 * @param <T>
 */
public class SplayTreeNode<T>{
	public T data; //value of the node
	public SplayTreeNode<T> left; //left node
	public SplayTreeNode<T> right; //right node
	public SplayTreeNode<T> parent; //parent node
	
	/**
	 * Constructor
	 */
	public SplayTreeNode(){
	}
	
	/**
	 * Constructor
	 * @param data, value of the node
	 */
	public SplayTreeNode(T data) {
		this.data = data;
	}
	
	/**
	 * toString method return the data of the original node, its left node and right node
	 * The running time is O(1)
	 */
	public String toString() {
		String leftStr = "NULL";//set null as default value
		String rightStr = "NULL";//set null as default value
		if(this.left != null) {
			leftStr = this.left.data.toString();
		}
		if(this.right != null) {
			rightStr = this.right.data.toString();
		}
		return this.data.toString()+"\nLEFT    |    "+leftStr+"\nRIGHT   |    "+rightStr;
	}
}
