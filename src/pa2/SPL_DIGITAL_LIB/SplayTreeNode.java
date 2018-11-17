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
		String leftStr = "NULL";//设置默认值
		String rightStr = "NULL";
		if(this.left != null) {
			leftStr = this.left.data.toString();
		}
		if(this.right != null) {
			rightStr = this.right.data.toString();
		}
		return this.data.toString()+"\nLEFT    |    "+leftStr+"\nRIGHT   |    "+rightStr;
	}
}