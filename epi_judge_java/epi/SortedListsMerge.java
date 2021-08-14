package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SortedListsMerge {
  @EpiTest(testDataFile = "sorted_lists_merge.tsv")

  //@include
  public static ListNode<Integer> mergeTwoSortedLists(ListNode<Integer> L1,
                                                      ListNode<Integer> L2) {
    // TODO - you fill in here.

    // Both solutions have same time and space complexity.
    //return mySolution(L1, L2);
    return booksSolution(L1, L2);
  }

  // Brute force
  // Time -> O(n1+n2), Space -> O(1)
  private static ListNode<Integer> mySolution(ListNode<Integer> l1, ListNode<Integer> l2) {
    if (l1 == null && l2 == null) {
      return null;
    } else if (l1 == null) {
      return l2;
    } else if (l2 == null) {
      return l1;
    } else {
      ListNode<Integer> start;

      if (l1.data <= l2.data) {
        start = l1;
        l1 = l1.next;
      } else {
        start = l2;
        l2 = l2.next;
      }

      ListNode<Integer> curNode = start;
      while(l1 != null && l2 != null) {
        if (l1.data <= l2.data) {
          curNode.next = l1;
          l1 = l1.next;
        } else {
          curNode.next = l2;
          l2 = l2.next;
        }
        curNode = curNode.next;
      }

      if (l1 == null) {
        curNode.next = l2;
      } else {
        curNode.next = l1;
      }
      return start;
    }
  }

  private static ListNode<Integer> booksSolution(ListNode<Integer> l1, ListNode<Integer> l2) {
    ListNode<Integer> head = new ListNode<>(0, null);
    ListNode<Integer> current = head;

    while(l1 != null && l2 != null) {
      if (l1.data <= l2.data) {
        current.next = l1;
        l1 = l1.next;
      } else {
        current.next = l2;
        l2 = l2.next;
      }
      current = current.next;
    }

    current.next = l1 == null ? l2 : l1;
    return head.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedListsMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
