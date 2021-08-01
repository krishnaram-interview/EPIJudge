package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
public class DutchNationalFlag {
  public enum Color { RED, WHITE, BLUE }

  public static void dutchFlagPartition(int pivotIndex, List<Color> A) {
    // TODO - you fill in here.
    //solution1(pivotIndex, A);
    solve(pivotIndex, A);
  }

  // Best solution based on book.
  // O(n) -< time complexity, O(1) -> Space complexity
  private static void solve(int pivotIndex, List<Color> A) {
    int l = 0;
    int e = 0;
    int g = A.size();
    Color pivot = A.get(pivotIndex);

    while(e < g) {
      if (A.get(e).ordinal() < pivot.ordinal()) {
        Collections.swap(A, l++, e++);
      } else if (A.get(e).ordinal() == pivot.ordinal()) {
        e++;
      } else {
        Collections.swap(A, e, --g);
      }
    }
  }

  // My original solution. Little complicated unnecessarily
  // O(n) -> Time and O(1) -> space
  private static void solution1(int pivotIndex, List<Color> A) {
    Color pivot = A.get(pivotIndex);
    int l = 0;
    int r = A.size() - 1;
    int m = l;

    while (l <= r) {
      if (A.get(l).ordinal() < pivot.ordinal()) {
       if (m != -1) {
         Color temp = A.get(l);
         A.set(l, A.get(m));
         A.set(m, temp);
         if (m+1 < A.size() && A.get(m+1).ordinal() == pivot.ordinal()) {
           m += 1;
         } else{
           m = -1;
         }
       }
       l++;
      } else if (A.get(l).ordinal() == pivot.ordinal()) {
        if (m == -1) {
          m = l;
        }
        l++;
      } else {
        Color temp = A.get(r);
        A.set(r, A.get(l));
        A.set(l, temp);
        r--;
      }
    }
  }

  @EpiTest(testDataFile = "dutch_national_flag.tsv")
  public static void dutchFlagPartitionWrapper(TimedExecutor executor,
                                               List<Integer> A, int pivotIdx)
      throws Exception {
    List<Color> colors = new ArrayList<>();
    int[] count = new int[3];

    Color[] C = Color.values();
    for (int i = 0; i < A.size(); i++) {
      count[A.get(i)]++;
      colors.add(C[A.get(i)]);
    }

    Color pivot = colors.get(pivotIdx);
    executor.run(() -> dutchFlagPartition(pivotIdx, colors));

    int i = 0;
    while (i < colors.size() && colors.get(i).ordinal() < pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    while (i < colors.size() && colors.get(i).ordinal() == pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    while (i < colors.size() && colors.get(i).ordinal() > pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    if (i != colors.size()) {
      throw new TestFailure("Not partitioned after " + Integer.toString(i) +
                            "th element");
    } else if (count[0] != 0 || count[1] != 0 || count[2] != 0) {
      throw new TestFailure("Some elements are missing from original array");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DutchNationalFlag.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
