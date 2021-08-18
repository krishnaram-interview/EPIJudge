package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeBalanced {
  public static class BalanceStatusWithHeight {
    public boolean isBalanced;
    public int height;

    public BalanceStatusWithHeight(boolean status, int height) {
      this.isBalanced = status;
      this.height = height;
    }
  }

  @EpiTest(testDataFile = "is_tree_balanced.tsv")
  public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
    // TODO - you fill in here.
    return checkBalance(tree).isBalanced;
  }

  private static BalanceStatusWithHeight checkBalance(BinaryTreeNode<Integer> root) {
    if (root == null) {
      return new BalanceStatusWithHeight(true, -1);
    }

    BalanceStatusWithHeight leftResult = checkBalance(root.left);
    if (!leftResult.isBalanced) {
      return leftResult;
    }

    BalanceStatusWithHeight rightResult = checkBalance(root.right);
    if (!rightResult.isBalanced) {
      return rightResult;
    }

    int height = Math.max(leftResult.height, rightResult.height) + 1;
    boolean isBalanced = Math.abs(leftResult.height - rightResult.height) <= 1;
    return new BalanceStatusWithHeight(isBalanced, height);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeBalanced.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
