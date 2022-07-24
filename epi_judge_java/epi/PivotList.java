package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PivotList {

  public static ListNode<Integer> listPivoting(ListNode<Integer> l, int x) {
    // TODO - you fill in here.

    // O(n) time and O(1) space. The best solution
    if (l == null || l.next == null) {
      return l;
    }

    ListNode<Integer> greaterDummyHead = new ListNode<>(0, null);
    ListNode<Integer> equalDummyHead = new ListNode<>(0, null);
    ListNode<Integer> lessDummyHead = new ListNode<>(0, null);

    Map<Integer, ListNode<Integer>> result = new HashMap<>();
    result.put(0, greaterDummyHead);
    result.put(1, equalDummyHead);
    result.put(2, lessDummyHead);

    while (l != null) {
      if (l.data > x) {
        result.get(0).next = l;
        result.put(0, l);
      } else if (l.data == x) {
        result.get(1).next = l;
        result.put(1, l);
      } else {
        result.get(2).next = l;
        result.put(2, l);
      }
      l = l.next;
    }

    result.get(0).next = null;
    result.get(1).next = greaterDummyHead.next;
    result.get(2).next = equalDummyHead.next;

    return lessDummyHead.next;
  }

  public static List<Integer> linkedToList(ListNode<Integer> l) {
    List<Integer> v = new ArrayList<>();
    while (l != null) {
      v.add(l.data);
      l = l.next;
    }
    return v;
  }

  @EpiTest(testDataFile = "pivot_list.tsv")
  public static void listPivotingWrapper(TimedExecutor executor,
                                         ListNode<Integer> l, int x)
      throws Exception {
    List<Integer> original = linkedToList(l);

    final ListNode<Integer> finalL = l;
    l = executor.run(() -> listPivoting(finalL, x));

    List<Integer> pivoted = linkedToList(l);

    int mode = -1;
    for (Integer i : pivoted) {
      switch (mode) {
      case -1:
        if (i == x) {
          mode = 0;
        } else if (i > x) {
          mode = 1;
        }
        break;
      case 0:
        if (i < x) {
          throw new TestFailure("List is not pivoted");
        } else if (i > x) {
          mode = 1;
        }
        break;
      case 1:
        if (i <= x) {
          throw new TestFailure("List is not pivoted");
        }
      }
    }

    Collections.sort(original);
    Collections.sort(pivoted);
    if (!original.equals(pivoted))
      throw new TestFailure("Result list contains different values");
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PivotList.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
