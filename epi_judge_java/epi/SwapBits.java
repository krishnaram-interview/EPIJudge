package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SwapBits {
  @EpiTest(testDataFile = "swap_bits.tsv")
  public static long swapBits(long x, int i, int j) {
    // TODO - you fill in here.
    return bookSolution(x, i, j);
    //return mySolution(x, i, j);
  }

  // O(1) time, still faster than my solution.
  private static long bookSolution(long x, int i, int j) {
    if ((x >> i & 1) != (x >> j & 1)) {
      x = x ^ ((1L << i) | (1L << j));
    }
    return x;
  }

  // O(1) time, still slower than book solution and takes some auxiliary space.
  private static long mySolution(long x, int i, int j) {
    long a = (long) Math.pow(2, i);
    long b = (long) Math.pow(2, j);

    boolean isIthBitSet = (x & a) == a;
    boolean isJThBitSet = (x & b) == b;

    if (isIthBitSet && !isJThBitSet) {
      x = x & ~a;
      x = x + b;
    } else if (!isIthBitSet && isJThBitSet) {
      x = x + a;
      x = x & ~b;
    }
    return x;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SwapBits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
