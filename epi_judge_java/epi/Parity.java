package epi;
import java.util.HashMap;
import java.util.Map;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import static java.lang.Math.pow;

public class Parity {
  @EpiTest(testDataFile = "parity.tsv")
  public static short parity(long x) {
    // TODO - you fill in here.
    // return bruteForce(x);

    // return findParity(x);

    // Map<Integer, Short> precomputedParity = getPrecomputedParity();
    // return findParity(x, precomputedParity);

    return findParityXor(x);
  }

  // O(num of bits)
  private static short bruteForce(long x) {
    short numOfOnes = 0;
    while (x != 0) {
      numOfOnes += x & 1;
      x >>>=  1;
    }
    return numOfOnes % 2 == 0 ? (short)0: (short)1;
/*  Another method
    short result = 0;
    while (x != 0) {
      result ^= (x & 1);
      x >>>= 1;
    }
    return result;*/
  }

  // O(k)
  private static short findParity(long x) {
    short numOfOnes = 0;
    while(x != 0) {
      x &= (x - 1);
      numOfOnes += 1;
    }
    return numOfOnes % 2 == 0 ? (short)0: (short)1;
  }

  private static Map<Integer, Short> getPrecomputedParity() {
    Map<Integer, Short> precomputedParity = new HashMap<>();
    for(int i=0;i<pow(2, 16); i++) {
      precomputedParity.put(i, findParity(i));
    }
    return precomputedParity;
  }

  // O(n/L) , L = 4, n = 64
  private static short findParity(long x, Map<Integer, Short> precomputedParity) {
    short result = 0;
    final int byte_size = 16;
    final int mask = 0xFFFF;
    result += precomputedParity.get((int) ((x >>> (3 * byte_size)) & mask));
    result += precomputedParity.get((int) ((x >>> (2 * byte_size)) & mask));
    result += precomputedParity.get((int) ((x >>> (1 * byte_size)) & mask));
    result += precomputedParity.get((int) (x & mask));

    return result % 2 == 0 ? (short)0 : (short)1;
  }

  // O(logn)
  private static short findParityXor(long x) {
    x ^= x >>> 32;
    x ^= x >>> 16;
    x ^= x >>> 8;
    x ^= x >>> 4;
    x ^= x >>> 2;
    x ^= x >>> 1;

    return (short) (x & 0x1);
  }


  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Parity.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
