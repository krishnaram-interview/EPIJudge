package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvenOddListMerge {
  @EpiTest(testDataFile = "even_odd_list_merge.tsv")

  public static ListNode<Integer> evenOddMerge(ListNode<Integer> L) {
    // TODO - you fill in here.
    return mySol(L); // This is fast
    // return boolSol(L);
  }

  private static ListNode<Integer> boolSol(ListNode<Integer> L) {
    int turn = 0;
    Map<Integer, ListNode<Integer>> result = new HashMap<>();
    ListNode<Integer> dummyHeadEven = new ListNode<>(0, null);
    ListNode<Integer> dummyHeadOdd = new ListNode<>(0, null);

    result.put(0, dummyHeadEven);
    result.put(1, dummyHeadOdd);

    while (L != null) {
      result.get(turn).next = L;
      result.put(turn, result.get(turn).next);
      L = L.next;
      turn = turn == 0 ? 1 : 0;
    }

    result.get(1).next = null;
    result.get(0).next = dummyHeadOdd.next;
    return dummyHeadEven.next;
  }

  // The best sol O(n) time and O(1) space
  private static ListNode<Integer> mySol(ListNode<Integer> L) {
    if (L == null || L.next == null) {
      return L;
    }

    ListNode<Integer> cur = L;
    ListNode<Integer> prev = null;
    ListNode<Integer> oddHeadStart = L.next;

    int i = 0;
    while (cur.next != null) {
      prev = cur;
      ListNode<Integer> next = cur.next;
      cur.next = next.next;
      cur = next;
      i++;
    }

    if (i % 2 != 0) {
      prev.next = oddHeadStart;
    } else {
      cur.next = oddHeadStart;
    }

    return L;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EvenOddListMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
