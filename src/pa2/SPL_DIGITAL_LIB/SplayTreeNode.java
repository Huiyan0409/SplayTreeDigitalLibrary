package pa2.SPL_DIGITAL_LIB;

public class SplayTreeNode<T>{
	public T data;
	public SplayTreeNode<T> left;
	public SplayTreeNode<T> right;
	public SplayTreeNode<T> parent;
	
	public SplayTreeNode(){
	}
	
	public SplayTreeNode(T data) {
		this.data = data;
	}
	
	public String toString() {
		if(this.left == null) {
			this.left.data = null;
		}
		if(this.right == null) {
			this.right.data = null;
		}
		return this.data.toString()+"\nLEFT    |    "+this.left.data.toString()+"\nRIGHT   |    "+this.right.data.toString();
	}
}