/**
 * Test of CS 143 Assignment 6 by Martin Hock (Version of 11:55 PM 5/26/2017)
 * 
 * You may only use this code as a student of Martin Hock, CS 143 Spring 2017.
 */
public class BinaryTreeTest {

	public static <T extends Comparable<T>> int reachScore(BinaryTree<T> tree, String name, T first, T second,
			T correct, int score) {
		if (first instanceof String) {
			System.out.println("Testing " + name + ".reachesBoth(\"" + first + "\", \"" + second + "\")");
		} else {
			System.out.println("Testing " + name + ".reachesBoth(" + first + ", " + second + ")");
		}
		T rb = tree.reachesBoth(first, second);
		if (correct == rb || (correct != null && correct.equals(rb))) {
			System.out.println("Got correct value of " + rb);
			return score;
		} else {
			System.out.println("Expected " + correct + " got " + rb);
			return 0;
		}
	}

	public static <T extends Comparable<T>> int rightmostLowestScore(BinaryTree<T> tree, String name, T correct,
			int score) {
		System.out.println("Testing " + name + ".findRightmostLowest()");
		T rb = tree.findRightmostLowest();
		if (correct == rb || (correct != null && correct.equals(rb))) {
			System.out.println("Got correct value of " + rb);
			return score;
		} else {
			System.out.println("Expected " + correct + " got " + rb);
			return 0;
		}
	}

	public static <T extends Comparable<T>> int kthLargestScore(BinaryTree<T> tree, String name, int k, T correct,
			int score) {
		System.out.println("Testing " + name + ".findKthLargest(" + k + ")");
		T rb = tree.findKthLargest(k);
		if (correct == rb || (correct != null && correct.equals(rb))) {
			System.out.println("Got correct value of " + rb);
			return score;
		} else {
			System.out.println("Expected " + correct + " got " + rb);
			return 0;
		}
	}

	public static void main(String[] args) {
		int reachesBothScore = 0, findRightmostLowestScore = 0, findKthLargestScore = 0;
		try {
			String[] letters = { "M", "G", "N", "D", "H", "B", "F" };
			BinaryTree<String> letterTree = new BinaryTree<>();
			for (String name : letters) {
				letterTree.add(name);
			}
			BinaryTree<String> emptyTree = new BinaryTree<>();
			BinaryTree<String> simpleTree = new BinaryTree<>();
			simpleTree.add("Hello");
			letterTree.print();
			reachesBothScore += reachScore(letterTree, "letterTree", "B", "H", "G", 2);
			letterTree.print();
			reachesBothScore += reachScore(letterTree, "letterTree", "F", "N", "M", 2);
			letterTree.print();
			reachesBothScore += reachScore(letterTree, "letterTree", "B", "D", "D", 2);
			letterTree.print();
			reachesBothScore += reachScore(letterTree, "letterTree", "X", "M", null, 2);
			letterTree.print();
			reachesBothScore += reachScore(letterTree, "letterTree", "N", "N", "N", 2);
			letterTree.print();
			reachesBothScore += reachScore(emptyTree, "emptyTree", "a", "b", null, 2);
			findRightmostLowestScore += rightmostLowestScore(letterTree, "letterTree", "F", 2);
			findRightmostLowestScore += rightmostLowestScore(simpleTree, "simpleTree", "Hello", 2);
			findKthLargestScore += kthLargestScore(letterTree, "letterTree", 1, "D", 2);
			findKthLargestScore += kthLargestScore(letterTree, "letterTree", 4, "H", 2);
			findKthLargestScore += kthLargestScore(letterTree, "letterTree", -1, null, 2);
			findKthLargestScore += kthLargestScore(letterTree, "letterTree", 7, null, 2);
			letterTree.add("P");
			findRightmostLowestScore += rightmostLowestScore(letterTree, "letterTree", "F", 2);
			letterTree.add("O");
			findRightmostLowestScore += rightmostLowestScore(letterTree, "letterTree", "O", 2);

			BinaryTree<Integer> intTree = new BinaryTree<>();
			int[] rightLow = { 1, -56981709, -113963419, -95375583, -95375583, -105278602, -86690766, -86690766,
					-96593785, -96593785 };
			Integer[] kthLarge = { null, 2081208021, 47078692, -652853260, -1078997883, -1277215666, -1410985124,
					-1524948544, -1590615071, -1667402819 };
			for (int i = 1, j = 0; j <= 1000; i += 101 * 101 * 101 * 101, j++) {
				intTree.add(i);
				if (j <= 900 && j % 100 == 0) {
					findRightmostLowestScore += rightmostLowestScore(intTree, j + " element containing intTree",
							rightLow[j / 100], 2);
					findKthLargestScore += kthLargestScore(intTree, j + " element containing intTree", 100,
							kthLarge[j / 100], 2);
				}
			}
			// If you have trouble, you may want to take a look at the tree!
			// intTree.print();
			reachesBothScore += reachScore(intTree, "intTree", 460883892, 356823491, 416241605, 2);
			reachesBothScore += reachScore(intTree, "intTree", -2102232259, -2110917076, -2109698874, 2);
			reachesBothScore += reachScore(intTree, "intTree", 2146874548, -2140626133, 1, 2);
			reachesBothScore += reachScore(intTree, "intTree", -1958559782, -2034129328, -2005638473, 2);
			reachesBothScore += reachScore(intTree, "intTree", 2128286712, 94157383, 104060402, 2);
			reachesBothScore += reachScore(intTree, "intTree", -276223732, -1770245018, -1693457270, 2);
			reachesBothScore += reachScore(intTree, "intTree", 1816105509, 1116173557, 1144664412, 2);
			reachesBothScore += reachScore(intTree, "intTree", 3, 4, null, 2);
			findRightmostLowestScore += rightmostLowestScore(intTree, "intTree", -96593785, 2);
			findKthLargestScore += kthLargestScore(intTree, "intTree", 700, 851071045, 2);
			findKthLargestScore += kthLargestScore(intTree, "intTree", 600, 434829441, 2);
			findKthLargestScore += kthLargestScore(intTree, "intTree", 500, 18587837, 2);
			findKthLargestScore += kthLargestScore(intTree, "intTree", 400, -408774988, 2);
			findKthLargestScore += kthLargestScore(intTree, "intTree", 300, -843604428, 2);
			findKthLargestScore += kthLargestScore(intTree, "intTree", 1000, 2146874548, 2);
			findKthLargestScore += kthLargestScore(intTree, "intTree", 0, -2140626133, 2);
		} finally {
			System.out.println("reachesBoth score " + reachesBothScore + " / 28");
			System.out.println("findRightmostLowest score " + findRightmostLowestScore + " / 30");
			System.out.println("findKthLargest score " + findKthLargestScore + " / 42");
			System.out.println(
					"Total: " + (reachesBothScore + findRightmostLowestScore + findKthLargestScore) + " / 100");
			System.out.println("Extra credit not tested. Score may be affected by academic honesty policy.");
		}
	}

}
