import java.util.Arrays;

//import BinaryTree.Node;

/**
 * A binary search tree for Comparable objects such as Strings, Integers, etc.
 * For each node n, all nodes to the left have data which is less than n.data
 * and all nodes to the right have data which is greater than n.data.
 *
 * @param <T>
 */
public class BinaryTree<T extends Comparable<T>> {
	private static class Node<T extends Comparable<T>> {
		public T data;
		public Node<T> left, right;
		public static int count = 0;
		public void add(T d) {
			int comp = d.compareTo(data);
			if (comp == 0)
				return; // Already in tree
			if (comp < 0) {
				if (left == null) {
					left = new Node<>();
					left.data = d;
				} else {
					left.add(d);
				}
			} else {
				// Greater than
				if (right == null) {
					right = new Node<>();
					right.data = d;
				} else {
					right.add(d);
				}
			}
		}

		public boolean contains(T d) {
			int comp = d.compareTo(data);
			if (comp == 0)
				return true; // Already in tree
			if (comp < 0) {
				if (left == null) {
					return false; // Not in the tree
				} else {
					return left.contains(d);
				}
			} else {
				if (right == null) {
					return false; // Not in the tree
				} else {
					return right.contains(d);
				}
			}

		}

		public void print(int indent) {
			if (right != null)
				right.print(indent + 1);
			char[] spaces = new char[indent * 2];
			Arrays.fill(spaces, ' ');
			System.out.println(new String(spaces) + data);
			if (left != null)
				left.print(indent + 1);
		}

		/**
		 * The number of nodes of this subtree.
		 * @return Number of nodes
		 */
		public int size() {
			// We know there is a node here
			int total = 1;
			// This node may have left children
			if (left != null)
				total = total + left.size();
			// This node may have right children
			if (right != null)
				total = total + right.size();
			// The total size of the tree from this point...
			return total;
		}

		/**
		 * Delete this node.
		 * 
		 * @return The new root of this subtree (null if this node had no
		 *         children, also known as a leaf)
		 */
		public Node<T> deleteNode() {
			if (left == null)
				return right;
			if (right == null)
				return left;
			Node<T> successor = right;
			if (successor.left == null) {
				// Case 1: no left child of immediate successor
				right = right.right;
			} else {
				// Case 2: loop until we find leftmost child
				Node<T> successorParent = null;
				while (successor.left != null) {
					successorParent = successor;
					successor = successor.left;
				}
				successorParent.left = successor.right;
			}
			// Replace this data with successor data
			data = successor.data;
			return this;
		}

		/**
		 * Deletes the node containing d if it exists.
		 * 
		 * @param d
		 * @return A valid BinaryTree that doesn't have d in it but does have
		 *         everything else.
		 */
		public Node<T> delete(T d) {			
			int comp = d.compareTo(data);
			if (comp == 0)
				return deleteNode();
			if (comp < 0) {
				// If d exists, it's to the left
				if (left != null)
					left = left.delete(d);
				return this;
			} else {
				if (right != null)
					right = right.delete(d);
				return this;
			}
		}
		
		public int depth() {			
			int leftDepth = 0;
			if (left != null) leftDepth = left.depth();
			int rightDepth = 0;
			if (right != null) rightDepth = right.depth();
			return 1 + Math.max(leftDepth, rightDepth);
		}
		
		
		public boolean searchNode(T d){
			if(data == null)
				return false;
			int comp = d.compareTo(data);
			if(comp == 0){
				return true;
			}
			
			if(comp < 0){
				if(left != null)
					return left.searchNode(d);
			}else{
				if(right != null)
					return right.searchNode(d);
			}			
			return false;
		}
		
		public T reachesBoth(T a, T b){						
			int comp1 = a.compareTo(data);
			int comp2 = b.compareTo(data);	
			
			if(comp1 == 0 || comp2 == 0)
				return data;
			
			T left_data = null;
			if(left != null){
				left_data = left.reachesBoth(a, b);
			}
			
			T right_data = null;
			if(right != null){
				right_data = right.reachesBoth(a, b);
			}			
			
			if (left_data!=null && right_data!=null){
				return data;	
			}			
			return (left_data != null) ? left_data : right_data;			
		}
		
		public T findRightmostLowest(int height, int depth){
			if(left == null && right == null){
				if(depth == height){
					return data;
				}
				return null;
			}
			T right_data = null;						
			if(right != null)				
				right_data = right.findRightmostLowest(height, depth+1);			
				
			if(left!=null)
				if(right_data == null){
					right_data = left.findRightmostLowest(height, depth+1);
				}		
			return right_data;
		}		
		
		public T findKthLargest(int k, int size) {			
			T right_data = null;						
			if(right != null){				
				right_data = right.findKthLargest(k, size);
				size = size - 1;
			}
			System.out.println(size);
			System.out.println(data);
			if(left == null && right == null){				
				if(size == k){
					return data;
				}
				return null;
			}
				
			if(left!=null){
				if(size == k){
					return data;
				}
				if(right_data == null){
					size = size - 1;
					right_data = left.findKthLargest(k, size);
				}
			}		
			return right_data;
		}
	}

	private Node<T> root;

	public BinaryTree() {
		root = null;
	}

	/**
	 * Adds data to the tree if it didn't already contain it.
	 * 
	 * @param data
	 */
	public void add(T data) {
		if (root == null) {
			root = new Node<>();
			root.data = data;
		} else {
			root.add(data);
		}
	}

	/**
	 * Returns true if the tree contains data, false otherwise
	 * 
	 * @param data
	 *            Does the tree contain this?
	 * @return true if it does
	 */
	public boolean contains(T data) {
		if (root == null)
			return false;
		return root.contains(data);
	}

	/**
	 * Prints out a representation of the tree (rotate your head 90 degrees
	 * left)
	 */
	public void print() {
		if (root != null)
			root.print(0);
	}

	/**
	 * Gets the number of nodes of the tree in O(n) time.
	 * 
	 * @return number of nodes
	 */
	public int size() {
		if (root == null)
			return 0;
		return root.size();
	}

	/**
	 * Delete the node containing data from the tree, if it exists.
	 * 
	 * @param data
	 */
	public void delete(T data) {
		root = root.delete(data);
	}
	
	public int depth() {
		if (root == null) return 0;
		return root.depth();
	}

	/**
	 * Returns the data value of the node that can reach both a and b in the
	 * least number of steps. If the tree doesn't contain both a and b, return
	 * null.
	 * 
	 * @param a
	 * @param b
	 * @return data value
	 */
	public T reachesBoth(T a, T b) {
		if(root == null)
			return null;
		
		if(root.searchNode(a) == false || root.searchNode(b) == false)
			return null;
		
		return root.reachesBoth(a, b);
	}
	
	/**
	 * Search a Node existed in a tree	
	 * @param d
	 * @return true, false
	 */
	public boolean searchNode(T d){
		if(root == null)
			return false;
		return root.searchNode(d);
	}

	/**
	 * Among all the nodes which are farthest from the root, find the one which
	 * is farthest to the right.
	 * 
	 * @return data value of said node
	 */
	public T findRightmostLowest() {
		int height = root.depth();
		
		if(root == null){
			return null;
		}
		
		return root.findRightmostLowest(height - 1, 0);
	}

	/**
	 * Return the kth largest element according to the Comparable sorted order
	 * of the tree. The leftmost node has index 0 and the rightmost node has
	 * index size() - 1.
	 * 
	 * @param k
	 *            index
	 * @return element, or null if k is out of range.
	 */
	public T findKthLargest(int k) {
		int size = root.size();		
		if(k > root.size() || k < 0)
			return null;
		if(root == null)
			return null;
		
		return root.findKthLargest(k,size - 1);
	}

	/**
	 * EXTRA CREDIT: Balance the tree. The new root should be the
	 * findKthLargest(size()/2) node. Recursively, the root of each subtree
	 * should also be the size/2-largest node (indexed from 0) of that subtree.
	 * This method should not call new and should execute in O(n log n) time for
	 * full credit.
	 */
	public void balance() {
		// TODO: Implement for extra credit.
	}
	
	public static void main(String[] args){
		String[] ex1 = { "M", "G", "N", "D", "H", "B", "F", "P", "O" };
		BinaryTree<String> tree = new BinaryTree<>();
		for (String name : ex1) {
			tree.add(name);
		}
//		tree.print();
		System.out.println(tree.findKthLargest(0));
//		System.out.println(tree.findKthLargest(1));
//		System.out.println(tree.findKthLargest(2));
//		System.out.println(tree.findKthLargest(3));
//		System.out.println(tree.findKthLargest(4));
//		System.out.println(tree.findKthLargest(5));
//		System.out.println(tree.findKthLargest(6));
//		System.out.println(tree.findKthLargest(7));
//		System.out.println(tree.findKthLargest(8));
//		tree.print();		
//		
//		tree.print();		
//		
//		tree.print();		
//		
//		tree.print();		
		
		
	}
}
