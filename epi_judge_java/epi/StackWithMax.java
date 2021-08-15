package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class StackWithMax {
  public static class StackElement {
    public Integer element;
    public Integer count;

    public StackElement(Integer element, Integer count) {
      this.element = element;
      this.count = count;
    }
  }

  // Best case approach is when the max value is tracked during push and pop, right from the beginning.
  // With Time Complexity -> O(1) Space Complexity -> O(n)
  public static class Stack {
    private Deque<Integer> stack = new LinkedList<>();
    private Deque<StackElement> auxStack = new LinkedList<>();

    public boolean empty() {
      // TODO - you fill in here.
      return stack.isEmpty();
    }

    public Integer max() {
      // TODO - you fill in here.
      return auxStack.peek() != null ? auxStack.peek().element : null;
    }

    public Integer pop() {
      // TODO - you fill in here.
      Integer x = stack.pop();
      if (!auxStack.isEmpty() && x.equals(auxStack.peek().element)) {
        if (auxStack.peek().count > 1) {
          auxStack.peek().count--;
        } else {
          auxStack.pop();
        }
      }
      return x;
    }

    public void push(Integer x) {
      // TODO - you fill in here.
      if (auxStack.isEmpty()) {
        auxStack.push(new StackElement(x, 1));
      } else if (x > auxStack.peek().element) {
        auxStack.push(new StackElement(x, 1));
      } else if (x.equals(auxStack.peek().element)) {
        auxStack.peek().count++;
      }
      stack.push(x);
    }
  }

//  // Brute force approach: Takes O(n) time to find max value in worst case and O(1) -> Space complexity
//  public static class Stack {
//    private Deque<Integer> stack = new LinkedList<>();
//
//    public boolean empty() {
//      // TODO - you fill in here.
//      return stack.isEmpty();
//    }
//
//    public Integer max() {
//      // TODO - you fill in here.
//      Iterator<Integer> stackItr = stack.iterator();
//      Integer max = null;
//      while (stackItr.hasNext()) {
//        Integer val = stackItr.next();
//        if (max == null) {
//          max = val;
//        } else if (val > max) {
//          max = val;
//        }
//      }
//      return max;
//    }
//
//    public Integer pop() {
//      // TODO - you fill in here.
//      return stack.pop();
//    }
//
//    public void push(Integer x) {
//      // TODO - you fill in here.
//     stack.push(x);
//    }
//  }

  @EpiUserType(ctorParams = {String.class, int.class})
  public static class StackOp {
    public String op;
    public int arg;

    public StackOp(String op, int arg) {
      this.op = op;
      this.arg = arg;
    }
  }

  @EpiTest(testDataFile = "stack_with_max.tsv")
  public static void stackTester(List<StackOp> ops) throws TestFailure {
    try {
      Stack s = new Stack();
      int result;
      for (StackOp op : ops) {
        switch (op.op) {
        case "Stack":
          s = new Stack();
          break;
        case "push":
          s.push(op.arg);
          break;
        case "pop":
          result = s.pop();
          if (result != op.arg) {
            throw new TestFailure("Pop: expected " + String.valueOf(op.arg) +
                                  ", got " + String.valueOf(result));
          }
          break;
        case "max":
          result = s.max();
          if (result != op.arg) {
            throw new TestFailure("Max: expected " + String.valueOf(op.arg) +
                                  ", got " + String.valueOf(result));
          }
          break;
        case "empty":
          result = s.empty() ? 1 : 0;
          if (result != op.arg) {
            throw new TestFailure("Empty: expected " + String.valueOf(op.arg) +
                                  ", got " + String.valueOf(s));
          }
          break;
        default:
          throw new RuntimeException("Unsupported stack operation: " + op.op);
        }
      }
    } catch (NoSuchElementException e) {
      throw new TestFailure("Unexpected NoSuchElement exception");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "StackWithMax.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
