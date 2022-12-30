package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsTreeBalanced {
  private static class BalanceStatus {
    private final boolean isBalanced;
    private final int height;

    public BalanceStatus(boolean isBalanced, int height) {
      this.isBalanced = isBalanced;
      this.height = height;
    }

    public boolean isBalanced() {
      return isBalanced;
    }

    public int getHeight() {
      return height;
    }
  }

  @EpiTest(testDataFile = "is_tree_balanced.tsv")
  public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
    // TODO - you fill in here.
    return checkBalance(tree).isBalanced();
  }

  private static BalanceStatus checkBalance(BinaryTreeNode<Integer> root) {
    if (root == null) {
      return new BalanceStatus(true, -1);
    }

    BalanceStatus l = checkBalance(root.left);
    if (!l.isBalanced()) {
      return l;
    }
    BalanceStatus r = checkBalance(root.right);
    if (!r.isBalanced()) {
      return r;
    }

    if (Math.abs(l.getHeight()- r.getHeight()) <= 1) {
      return new BalanceStatus(true, Math.max(l.getHeight(), r.getHeight()) + 1);
    } else {
      return new BalanceStatus(false, Math.max(l.getHeight(), r.getHeight()) + 1);
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeBalanced.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
