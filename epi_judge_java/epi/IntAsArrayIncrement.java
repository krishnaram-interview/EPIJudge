package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class IntAsArrayIncrement {
  @EpiTest(testDataFile = "int_as_array_increment.tsv")
  public static List<Integer> plusOne(List<Integer> A) {
    // TODO - you fill in here.
    int q = 1;
    for (int i=A.size()-1; i>=0; i--) {
      Integer digit = A.get(i);
      digit += q;

      q = digit / 10;
      int r = digit % 10;

      A.set(i, r);
    }

    if (q != 0) {
      A.add(0, q);
    }
    return A;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsArrayIncrement.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
