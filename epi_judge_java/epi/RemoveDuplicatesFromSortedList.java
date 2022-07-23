package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class RemoveDuplicatesFromSortedList {
  @EpiTest(testDataFile = "remove_duplicates_from_sorted_list.tsv")

  public static ListNode<Integer> removeDuplicates(ListNode<Integer> L) {
    // TODO - you fill in here.

    if (L == null) {
      return L;
    }

    ListNode<Integer> cur = L;
    ListNode<Integer> next = L.next;

    while (next != null) {
      if (cur.data.equals(next.data)) {
        cur.next = next.next;
        next = next.next;
      } else {
        cur = next;
        next = cur.next;
      }
    }

    return L;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RemoveDuplicatesFromSortedList.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
