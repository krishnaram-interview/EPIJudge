package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ReverseSublist {
  @EpiTest(testDataFile = "reverse_sublist.tsv")

  public static ListNode<Integer> reverseSublist(ListNode<Integer> L, int start, int finish) {
    // TODO - you fill in here.

    if (start != finish) {
      ListNode<Integer> cur = L;
      ListNode<Integer> prev = null;

      for (int i=1; i<start; i++) {
        prev = cur;
        cur = cur.next;
      }

      ListNode<Integer> rHead = null;
      ListNode<Integer> rTail = cur;
      while(start++ <= finish) {
        ListNode<Integer> next = cur.next;
        cur.next = rHead;
        rHead = cur;
        cur = next;
      }

      if (prev == null) {
        L = rHead;
      } else {
        prev.next = rHead;
      }

      rTail.next = cur;
    }

    return L;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseSublist.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
