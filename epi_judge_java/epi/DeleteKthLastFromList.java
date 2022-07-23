package epi;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class DeleteKthLastFromList {
  @EpiTest(testDataFile = "delete_kth_last_from_list.tsv")

  // Assumes L has at least k nodes, deletes the k-th last node in L.
  public static ListNode<Integer> removeKthLast(ListNode<Integer> L, int k) {
    // TODO - you fill in here.

    //return sol1(L, k);
    return sol2(L, k);
  }

  // O(n) time. Best solution since iterates the list only once.
  private static ListNode<Integer> sol2(ListNode<Integer> L, int k) {
    if (L == null || k <= 0) {
      return L;
    }

    ListNode<Integer> itr1 = L;
    ListNode<Integer> itr2 = L;

    while (k-- > 0) {
      itr2 = itr2.next;
    }

    ListNode<Integer> prev = null;
    while (itr2 != null) {
      prev = itr1;
      itr1 = itr1.next;
      itr2 = itr2.next;
    }

    if (prev == null) {
      return L.next;
    } else {
      prev.next = itr1.next;
      return L;
    }
  }

  // Makes two iterations of the List. So not that efficient. O(n) time.
  private static ListNode<Integer> sol1(ListNode<Integer> L, int k) {
    if (L == null) {
      return L;
    }

    int len = 0;
    ListNode<Integer> head = L;
    while (head != null) {
      head = head.next;
      len++;
    }

    if (k == len) {
      return L.next;
    } else {
     ListNode<Integer> node = L;
     for (int i = 1; i< (len- k); i++) {
       node = node.next;
     }
     node.next = node.next.next;
     return L;
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DeleteKthLastFromList.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
