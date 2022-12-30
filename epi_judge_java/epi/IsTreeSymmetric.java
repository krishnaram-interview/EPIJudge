package epi;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeSymmetric {
  @EpiTest(testDataFile = "is_tree_symmetric.tsv")

  public static boolean isSymmetric(BinaryTreeNode<Integer> tree) {
    // TODO - you fill in here.
    return mySolution(tree);
    //return bestSolution(tree);
  }

  // Time complexity -> O(n) and space complexity -> O(h)
  private static boolean bestSolution(BinaryTreeNode<Integer> root) {
    if (root == null) {
      return true;
    } else {
      return checkSymmetry(root.left, root.right);
    }
  }

  private static boolean checkSymmetry(BinaryTreeNode<Integer> subTree1, BinaryTreeNode<Integer> subTree2) {
    if (subTree1 == null && subTree2 == null) {
      return true;
    } else if (subTree1 != null && subTree2 != null) {
      return subTree1.data.equals(subTree2.data) &&
              checkSymmetry(subTree1.left, subTree2.right) &&
              checkSymmetry(subTree1.right, subTree2.left);
    } else {
      return false;
    }
  }

  // Time complexity -> O(n) and Space complexity -> O(n)
  // This is slow coz, has to perform reverse and must traverse entire tree all the time.
  private static boolean mySolution(BinaryTreeNode<Integer> tree) {
    if (tree == null) {
      return true;
    }
    List<Integer> ld = inOrderData(tree.left);
    List<Integer> rd = inOrderData(tree.right);
    Collections.reverse(rd);
    return ld.equals(rd);
  }

  private static List<Integer> inOrderData(BinaryTreeNode<Integer> root) {
    if (root == null) {
      return new ArrayList<>();
    }

    List<Integer> left = inOrderData(root.left);
    left.add(root.data);
    List<Integer> right = inOrderData(root.right);
    left.addAll(right);
    return left;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeSymmetric.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
