package epi;

import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
public class LowestCommonAncestor {
  private static class Status {
    BinaryTreeNode<Integer> node;
    int val;

    public Status(BinaryTreeNode<Integer> node, int val) {
      this.node = node;
      this.val = val;
    }
  }

  public static BinaryTreeNode<Integer> lca(BinaryTreeNode<Integer> tree,
                                            BinaryTreeNode<Integer> node0,
                                            BinaryTreeNode<Integer> node1) {
    // TODO - you fill in here.
    //return bruteForceLca(tree, node0, node1);
    return LCHelper(tree, node0, node1).node;

  }

  private static Status LCHelper(BinaryTreeNode<Integer> tree,
                                 BinaryTreeNode<Integer> node0,
                                 BinaryTreeNode<Integer> node1) {
    if (tree == null) {
      return new Status(null, 0);
    }

    Status ls = LCHelper(tree.left, node0, node1);
    if (ls.val == 2) {
      return ls;
    }

    Status rs = LCHelper(tree.right, node0, node1);
    if (rs.val == 2) {
      return rs;
    }

    int val = (tree == node0 ? 1 : 0) + (tree == node1 ? 1 : 0) + ls.val + rs.val;
    return new Status(tree, val);

  }

  private static BinaryTreeNode<Integer> bruteForceLca(BinaryTreeNode<Integer> tree, BinaryTreeNode<Integer> node0,
                                                       BinaryTreeNode<Integer> node1) {
    if (tree == null) {
      return null;
    }

    if (tree == node0 && tree == node1) {
      return tree;
    }

    boolean isNode0InLeft = isPresent(tree.left, node0);
    boolean isNode0InRight = isPresent(tree.right, node0);
    boolean isNode1InLeft = isPresent(tree.left, node1);
    boolean isNode1InRight = isPresent(tree.right, node1);

    if ((tree == node0 && (isNode1InRight || isNode1InLeft)) || (tree == node1 && (isNode0InRight || isNode0InLeft)) || (isNode0InLeft && isNode1InRight) || (isNode1InLeft && isNode0InRight)) {
      return tree;
    }

    if (isNode0InLeft && isNode1InLeft) {
      return bruteForceLca(tree.left, node0, node1);
    } else {
      return bruteForceLca(tree.right, node0, node1);
    }
  }

  private static boolean isPresent(BinaryTreeNode<Integer> tree, BinaryTreeNode<Integer> node) {
    if (tree == null) {
      return false;
    }

    if (tree == node) {
      return true;
    }

    return isPresent(tree.left, node) || isPresent(tree.right, node);
  }


  @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
  public static int lcaWrapper(TimedExecutor executor,
                               BinaryTreeNode<Integer> tree, Integer key0,
                               Integer key1) throws Exception {
    BinaryTreeNode<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
    BinaryTreeNode<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

    BinaryTreeNode<Integer> result =
        executor.run(() -> lca(tree, node0, node1));

    if (result == null) {
      throw new TestFailure("Result can not be null");
    }
    return result.data;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LowestCommonAncestor.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
