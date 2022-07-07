package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class IntersectSortedArrays {
  @EpiTest(testDataFile = "intersect_sorted_arrays.tsv")

  public static List<Integer> intersectTwoSortedArrays(List<Integer> A,
                                                       List<Integer> B) {
    // TODO - you fill in here.

    //return bruteForce(A, B);
    // return solution1(A, B);
    return linearTimeSolution(A, B);
  }

  // O(n*m)
  private static List<Integer> bruteForce(List<Integer> A, List<Integer> B) {
    List<Integer> result = new ArrayList<>();
    for (int i1 = 0; i1 < A.size(); i1++) {
      if (i1 == 0 || !A.get(i1).equals(A.get(i1-1))) {
        for (int i2 = 0; i2 < B.size(); i2++) {
          if (Objects.equals(A.get(i1), B.get(i2))) {
            result.add(A.get(i1));
            break;
          }
        }
      }
    }
    return result;
  }

  // O(n*logm)
  private static List<Integer> solution1(List<Integer> A, List<Integer> B) {
    List<Integer> result = new ArrayList<>();
    List<Integer> a1;
    List<Integer> a2;
    if (A.size() <= B.size()) {
      a1 = A;
      a2 = B;
    } else {
      a1 = B;
      a2 = A;
    }

    for (int i=0; i<a1.size(); i++) {
      if (i == 0 || !a1.get(i).equals(a1.get(i-1))) {
        if(Collections.binarySearch(a2, a1.get(i)) >= 0) {
          result.add(a1.get(i));
        }
      }
    }

    return result;
  }

  // O(m+n)
  private static List<Integer> linearTimeSolution(List<Integer> A, List<Integer> B) {
    int i = 0;
    int j = 0;
    List<Integer> result = new ArrayList<>();

    while (i < A.size() && j < B.size()) {
      if (A.get(i) < B.get(j)) {
        i++;
      } else if (B.get(j) < A.get(i)) {
        j++;
      } else {
        if (A.get(i).equals(B.get(j)) && (i == 0 || !A.get(i).equals(A.get(i-1)))) {
          result.add(A.get(i));
        }
        i++;
        j++;
      }
    }
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntersectSortedArrays.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
