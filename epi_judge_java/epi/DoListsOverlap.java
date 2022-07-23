package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.plaf.ListUI;

public class DoListsOverlap {
  public static ListNode<Integer> overlappingLists(ListNode<Integer> l0, ListNode<Integer> l1) {
    ListNode<Integer> a0 = getCycleNode(l0);
    ListNode<Integer> a1 = getCycleNode(l1);

    if (a0 == null && a1 == null) {
      return getIntersectingNodeWithoutCycle(l0, l1);
    } else if ((a0 == null && a1 != null) || (a0 != null && a1 == null)) {
      return null;
    } else if (a0 != null && a1 != null){
      if (a0.equals(a1)) {
        return getIntersectingNodeWithCycle(l0, l1, a0);
      } else {
        ListNode<Integer> temp = a0;
        do {
          temp = temp.next;
        } while(temp != a0 && temp != a1);

        return temp == a1 ? a1 : null;
      }
    } else {
      return null;
    }
  }

  private static ListNode<Integer> getIntersectingNodeWithCycle(ListNode<Integer> l0, ListNode<Integer> l1, ListNode<Integer> cycleNode) {
    if (l0 == null || l1 == null) {
      return null;
    }

    int distUptoCycleNode0 = 0;
    ListNode<Integer> head0 = l0;
    while (head0 != cycleNode) {
      head0 = head0.next;
      distUptoCycleNode0++;
    }

    int distUptoCycleNode1 = 0;
    ListNode<Integer> head1 = l1;
    while (head1 != cycleNode) {
      head1 = head1.next;
      distUptoCycleNode1++;
    }

    head0 = l0;
    head1 = l1;
    if (distUptoCycleNode0 > distUptoCycleNode1) {
      int diff = distUptoCycleNode0 - distUptoCycleNode1;
      while (diff-- > 0) {
        head0 = head0.next;
      }
    } else {
      int diff = distUptoCycleNode1 - distUptoCycleNode0;
      while (diff-- > 0) {
        head1 = head1.next;
      }
    }

    while (head0 != head1) {
      head0 = head0.next;
      head1 = head1.next;
    }
    return head0;
  }

  private static ListNode<Integer> getIntersectingNodeWithoutCycle(ListNode<Integer> l0, ListNode<Integer> l1) {
    if (l0 == null || l1 == null) {
      return null;
    }

    int len0 = 0;
    ListNode<Integer> head0 = l0;
    while(head0.next != null) {
      head0 = head0.next;
      len0++;
    }

    int len1 = 0;
    ListNode<Integer> head1 = l1;
    while(head1.next != null) {
      head1 = head1.next;
      len1++;
    }

    if (head0 == head1) {
      int diff = 0;
      head0 = l0;
      head1 = l1;
      if (len0 > len1) {
        diff = len0 - len1;
        while (diff-- > 0) {
          head0 = head0.next;
        }
      } else {
        diff = len1 - len0;
        while (diff-- > 0) {
          head1 = head1.next;
        }
      }

      while (head0 != head1) {
        head0 = head0.next;
        head1 = head1.next;
      }
      return head1;
    } else {
      return null;
    }
  }

  private static ListNode<Integer> getCycleNode(ListNode<Integer> l) {
    if (l == null) {
      return null;
    }
    ListNode<Integer> fast = l.next;
    ListNode<Integer> slow = l;

    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;

      if (slow == fast) {
        int cycleLen = 0;

        fast = fast.next;
        cycleLen++;
        while (slow != fast) {
          fast = fast.next;
          cycleLen++;
        }

        ListNode<Integer> start = l;
        ListNode<Integer> advStart = l;
        while(cycleLen-- > 0) {
          advStart = advStart.next;
        }

        while (start != advStart) {
          start = start.next;
          advStart = advStart.next;
        }

        return advStart;
      }
    }
    return null;
  }

  @EpiTest(testDataFile = "do_lists_overlap.tsv")
  public static void
  overlappingListsWrapper(TimedExecutor executor, ListNode<Integer> l0,
                          ListNode<Integer> l1, ListNode<Integer> common,
                          int cycle0, int cycle1) throws Exception {
    if (common != null) {
      if (l0 == null) {
        l0 = common;
      } else {
        ListNode<Integer> it = l0;
        while (it.next != null) {
          it = it.next;
        }
        it.next = common;
      }

      if (l1 == null) {
        l1 = common;
      } else {
        ListNode<Integer> it = l1;
        while (it.next != null) {
          it = it.next;
        }
        it.next = common;
      }
    }

    if (cycle0 != -1 && l0 != null) {
      ListNode<Integer> last = l0;
      while (last.next != null) {
        last = last.next;
      }
      ListNode<Integer> it = l0;
      while (cycle0-- > 0) {
        if (it == null) {
          throw new RuntimeException("Invalid input data");
        }
        it = it.next;
      }
      last.next = it;
    }

    if (cycle1 != -1 && l1 != null) {
      ListNode<Integer> last = l1;
      while (last.next != null) {
        last = last.next;
      }
      ListNode<Integer> it = l1;
      while (cycle1-- > 0) {
        if (it == null) {
          throw new RuntimeException("Invalid input data");
        }
        it = it.next;
      }
      last.next = it;
    }

    Set<Integer> commonNodes = new HashSet<>();
    ListNode<Integer> it = common;
    while (it != null && !commonNodes.contains(it.data)) {
      commonNodes.add(it.data);
      it = it.next;
    }

    final ListNode<Integer> finalL0 = l0;
    final ListNode<Integer> finalL1 = l1;
    ListNode<Integer> result =
        executor.run(() -> overlappingLists(finalL0, finalL1));

    if (!((commonNodes.isEmpty() && result == null) ||
          (result != null && commonNodes.contains(result.data)))) {
      throw new TestFailure("Invalid result");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DoListsOverlap.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
