package epi;

import java.util.HashMap;
import java.util.Map;

import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
public class LowestCommonAncestorWithParent {

  public static BinaryTree<Integer> lca(BinaryTree<Integer> node0,
                                        BinaryTree<Integer> node1) {
    // TODO - you fill in here.
    // return null;
    Map<BinaryTree<Integer>, BinaryTree<Integer>> n0l = new HashMap<>();

    while (node0 != null) {
      n0l.put(node0, node0);
      node0 = node0.parent;
    }

    BinaryTree<Integer> result = null;
    while (node1 != null) {
      if (n0l.containsKey(node1)) {
        result = node1;
        break;
      }
      node1 = node1.parent;
    }

    return result;
  }

  @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
  public static int lcaWrapper(TimedExecutor executor, BinaryTree<Integer> tree,
                               Integer key0, Integer key1) throws Exception {
    BinaryTree<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
    BinaryTree<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

    BinaryTree<Integer> result = executor.run(() -> lca(node0, node1));

    if (result == null) {
      throw new TestFailure("Result can not be null");
    }
    return result.data;
  }

  public static void main(String[] args) {
    System.exit(
            GenericTest
                    .runFromAnnotations(args, "LowestCommonAncestorWithParent.java",
                            new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
