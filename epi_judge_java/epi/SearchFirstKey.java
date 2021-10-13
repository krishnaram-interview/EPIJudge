package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class SearchFirstKey {
  @EpiTest(testDataFile = "search_first_key.tsv")

  public static int searchFirstOfK(List<Integer> A, int k) {
    // TODO - you fill in here.

    int l = 0;
    int r = A.size() - 1;
    int m = -1;
    int result = -1;
    while (l <= r) {
      m = l + (r - l) / 2;
      if (k < A.get(m)) {
        r = m - 1;
      } else if (k > A.get(m)) {
        l = m + 1;
      } else {
        int mid = m;
        while (mid >= 0 && A.get(mid) == k) {
          mid--;
        }
        result = mid + 1;
        break;
      }
    }
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchFirstKey.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
