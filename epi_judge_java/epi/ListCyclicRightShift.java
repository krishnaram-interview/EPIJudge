package epi;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ListCyclicRightShift {
  private static int cnt = 0;

  @EpiTest(testDataFile = "list_cyclic_right_shift.tsv")
  public static ListNode<Integer> cyclicallyRightShiftList(ListNode<Integer> L, int k) {
    // TODO - you fill in here.
    cnt++;
    if (cnt == 40) {
      System.out.println();
    }

    //return sol1(L, k);

    if (L == null || k <= 0 || L.next == null) {
      return L;
    }

    int l = 0;
    ListNode<Integer> tail = L;
    ListNode<Integer> head = L;
    while (head != null) {
      tail = head;
      head = head.next;
      l++;
    }
    int z = k % l;
    if (z == 0) {
      return L;
    }

    tail.next = L;
    z = l - z;
    ListNode<Integer> newTail = L;
    while (--z > 0) {
      newTail = newTail.next;
    }
    head = newTail.next;
    newTail.next = null;
    return head;
  }

  //Slow
  private static ListNode<Integer> sol1(ListNode<Integer> L, int k) {
    if (L == null || L.next == null || k <= 0) {
      return L;
    }

    ListNode<Integer> s1 = L;
    ListNode<Integer> s2 = L;

    while (k-- > 0) {
      s2 = s2.next;
      if (s2 == null) {
        s2 = L;
      }
    }

    ListNode<Integer> prev = null;
    while (s2 != null) {
      prev = s1;
      s1 = s1.next;
      if (s1 == null) {
        prev = null;
        s1 = L;
      }
      s2 = s2.next;
    }

    ListNode<Integer> head = s1;

    while (s1 != null && s1.next != null) {
      s1 = s1.next;
    }
    s1.next = L;

    if (prev != null) {
      prev.next = null;
    }

    return head;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ListCyclicRightShift.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
