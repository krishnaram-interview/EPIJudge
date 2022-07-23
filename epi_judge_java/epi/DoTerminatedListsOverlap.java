package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
public class DoTerminatedListsOverlap {

  public static ListNode<Integer> overlappingNoCycleLists(ListNode<Integer> l0, ListNode<Integer> l1) {
    // TODO - you fill in here.
    ListNode<Integer> head0 = new ListNode<>(0, l0);
    ListNode<Integer> tail0 = null;
    int size0 = 0;
    while(head0.next != null) {
      head0 = head0.next;
      size0++;
    }
    tail0 = head0;

    ListNode<Integer> head1 = new ListNode<>(0, l1);
    ListNode<Integer> tail1 = null;
    int size1 = 0;
    while(head1.next != null) {
      head1 = head1.next;
      size1++;
    }
    tail1 = head1;

    if (tail0 == tail1) {
      if (size0 > size1) {
        int diff = size0 - size1;
        while(diff-- > 0) {
          l0 = l0.next;
        }
      } else {
        int diff = size1 - size0;
        while (diff-- > 0) {
          l1 = l1.next;
        }
      }

      while (l0 != l1) {
        l0 = l0.next;
        l1 = l1.next;
      }

      return l0;
    } else {
      return null;
    }
  }

  @EpiTest(testDataFile = "do_terminated_lists_overlap.tsv")
  public static void
  overlappingNoCycleListsWrapper(TimedExecutor executor, ListNode<Integer> l0,
                                 ListNode<Integer> l1, ListNode<Integer> common)
      throws Exception {
    if (common != null) {
      if (l0 != null) {
        ListNode<Integer> i = l0;
        while (i.next != null) {
          i = i.next;
        }
        i.next = common;
      } else {
        l0 = common;
      }

      if (l1 != null) {
        ListNode<Integer> i = l1;
        while (i.next != null) {
          i = i.next;
        }
        i.next = common;
      } else {
        l1 = common;
      }
    }

    final ListNode<Integer> finalL0 = l0;
    final ListNode<Integer> finalL1 = l1;
    ListNode<Integer> result =
        executor.run(() -> overlappingNoCycleLists(finalL0, finalL1));

    if (result != common) {
      throw new TestFailure("Invalid result");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DoTerminatedListsOverlap.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
