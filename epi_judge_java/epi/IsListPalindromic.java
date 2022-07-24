package epi;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsListPalindromic {
  private static int cnt = 0;

  @EpiTest(testDataFile = "is_list_palindromic.tsv")
  public static boolean isLinkedListAPalindrome(ListNode<Integer> L) {
    // TODO - you fill in here.
    // return sol1(L);
    // return sol2(L);

    // Fastest Solution
    return sol3(L);
  }

  // O(n) time and O(1) space
  private static boolean sol3(ListNode<Integer> L) {
    if (L == null || L.next == null) {
      return true;
    }

    ListNode<Integer> l1 = L;
    ListNode<Integer> l2 = L;

    ListNode<Integer> sublistHead = null;
    ListNode<Integer> next = null;
    while (l2 != null && l2.next != null) {
      l2 = l2.next.next;

      next = l1.next;
      l1.next = sublistHead;
      sublistHead = l1;
      l1 = next;
    }

    if (l2 != null) {
      if (l1 != null) {
        l1 = l1.next;
      }
    }

    boolean isPalindrome = true;
    while (l1 != null && sublistHead != null) {
      if (!l1.data.equals(sublistHead.data)) {
        isPalindrome = false;
        break;
      }
      l1 = l1.next;
      sublistHead = sublistHead.next;
    }
    return isPalindrome;
  }

  // 2 * O(n) time and O(1) space. Slower due to length calculation prior iteration to check the palindrome
  private static boolean sol2(ListNode<Integer> L) {
    if (L == null || L.next == null) {
      return true;
    }

    int n = L.size();
    int mid = n / 2;

    ListNode<Integer> cur = L;
    ListNode<Integer> head = null;
    while (mid-- > 0) {
      ListNode<Integer> next = cur.next;
      cur.next = head;
      head = cur;
      cur = next;
    }

    if (n % 2 != 0 && cur != null) {
      cur = cur.next;
    }

    boolean isPalindrome = true;
    while (cur != null && head != null) {
      if (!head.data.equals(cur.data)) {
        isPalindrome = false;
        break;
      }
      head = head.next;
      cur = cur.next;
    }

    return isPalindrome;
  }

  // O(n) Time, O(n) space
  private static boolean sol1(ListNode<Integer> L) {
    List<Integer> list = new ArrayList<>();
    ListNode<Integer> cur = L;
    ListNode<Integer> newHead = null;
    while (cur != null) {
      list.add(cur.data);
      ListNode<Integer> next = cur.next;
      cur.next = newHead;
      newHead = cur;
      cur = next;
    }

    boolean isPalindrome = true;
    ListIterator<Integer> listItr = list.listIterator();
    while (newHead != null && listItr.hasNext()) {
      Integer listData = listItr.next();
      if (!newHead.data.equals(listData)) {
        isPalindrome = false;
        break;
      }
      newHead = newHead.next;
    }

    return isPalindrome;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsListPalindromic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
