package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ReverseBits {
  @EpiTest(testDataFile = "reverse_bits.tsv")
  public static long reverseBits(long x) {
    // TODO - you fill in here.
    return bruteForce(x);
  }

  private static long bruteForce(long x) {
    for (int i=0; i<32; i++) {
      x = swapBits(x, i, 63-i);
    }
    return x;
  }

  private static long swapBits(long x, int i, int j) {
    if ((x >> i & 1) != (x >> j & 1)) {
      x = x ^ (1L << i | 1L << j);
    }
    return x;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseBits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
