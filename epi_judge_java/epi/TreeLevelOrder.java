package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
public class TreeLevelOrder {
  @EpiTest(testDataFile = "tree_level_order.tsv")

  public static List<List<Integer>> binaryTreeDepthOrder(BinaryTreeNode<Integer> tree) {
    // TODO - you fill in here.

    // O(n) time and O(m=max num of node at any level) space
    List<List<Integer>> result = new ArrayList<>();

    if (tree == null) {
      return result;
    }

    Deque<BinaryTreeNode<Integer>> q = new LinkedList<>();
    q.add(tree);

    do {
      List<Integer> l = new ArrayList<>();
      List<BinaryTreeNode<Integer>> children = new ArrayList<>();
      while (!q.isEmpty()) {
        BinaryTreeNode<Integer> sibling = q.remove();
        l.add(sibling.data);
        if (sibling.left != null)
          children.add(sibling.left);
        if (sibling.right != null)
          children.add(sibling.right);
      }
      result.add(l);
      if (!children.isEmpty()) {
        q.addAll(children);
      }
    } while (!q.isEmpty());

    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeLevelOrder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
