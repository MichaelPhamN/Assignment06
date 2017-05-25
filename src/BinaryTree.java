import java.util.Arrays;

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
		
		public Node<T> researchsBoth(T a, T b){
			return null;
		}
		
		
		
		public Node<T> findRightmostLowest() {
			//find All Node in depth
			int countLeft = 0;
			
			return null;
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

	/**
	 * Returns the data value of the node that can reach both a and b in the
	 * least number of steps. If the tree doesn't contain both a and b, return
	 * null.
	 * 
	 * @param a
	 * @param b
	 * @return data value
	 */
	public Node<T> reachesBoth(T a, T b) {
		// Tree is empty
		if(root == null)
			return null;
		
		// Root is parent of the Other Child Nodes
		if(root.data == a || root.data == b)
			return root;
		
		return root.researchsBoth(a,b);
	}

	/**
	 * Among all the nodes which are farthest from the root, find the one which
	 * is farthest to the right.
	 * 
	 * @return data value of said node
	 */
	public T findRightmostLowest() {
		if(root == null){
			return null;
		}
		
		
		// TODO: Implement.
		return null;
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
		// TODO: Implement.
		return null;
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
		
	}
}
