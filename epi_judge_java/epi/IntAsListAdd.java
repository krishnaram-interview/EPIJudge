package epi;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IntAsListAdd {
  private static int cnt = 0;

  @EpiTest(testDataFile = "int_as_list_add.tsv")
  public static ListNode<Integer> addTwoNumbers(ListNode<Integer> L1, ListNode<Integer> L2) {
    cnt++;
    if (cnt == 45) {
      System.out.println();
    }

    // TODO - you fill in here.

    // O(n) time and O(1) space. Much better than book solution since no new nodes are created except for last carry bit if that exists.
    ListNode<Integer> result = new ListNode<>(0, null);

    int carry = 0;
    ListNode<Integer> prev1 = null;
    ListNode<Integer> prev2 = null;
    result.next = L1;

    while (L1 != null && L2 != null) {
      int sum = L1.data + L2.data + carry;
      carry = sum / 10;

      L1.data = sum % 10;

      prev1 = L1;
      prev2 = L2;
      L1 = L1.next;
      L2 = L2.next;
    }

    ListNode<Integer> last = prev1;
    if (L2 != null) {
      if (prev1 != null) {
        prev1.next = L2;
      }

      while (L2 != null) {
        int sum = L2.data + carry;
        carry = sum / 10;

        L2.data = sum % 10;
        last = L2;
        L2 = L2.next;
      }

    } else if (L1 != null) {
      if (prev2 != null) {
        prev2.next = L1;
      }

      while (L1 != null) {
        int sum = L1.data + carry;
        carry = sum / 10;

        L1.data = sum % 10;
        last = L1;
        L1 = L1.next;
      }
    }

    if (carry > 0) {
      last.next = new ListNode<>(carry, null);
    }

    return result.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsListAdd.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
