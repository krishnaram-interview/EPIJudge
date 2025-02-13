package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class QueueWithMax {
  private static final int RESIZE_FACTOR = 2;

  private Integer[] queue = new Integer[1];
  private int size;
  private int f;
  private int r;
  private Deque<Integer> deque = new LinkedList<>();

  public void enqueue(Integer x) {
    // TODO - you fill in here.
    if (size == queue.length) {
      Collections.rotate(Arrays.asList(queue), -f);
      f = 0;
      r = size;
      queue = Arrays.copyOf(queue, size * RESIZE_FACTOR);
    }
    queue[r] = x;
    r = (r + 1) % queue.length;
    size++;

    while (!deque.isEmpty()) {
      if (x > deque.getLast()) {
        deque.removeLast();
      } else {
        break;
      }
    }
    deque.addLast(x);
  }

  public Integer dequeue() {
    // TODO - you fill in here.
    if (size <= 0) {
      throw new RuntimeException("Queue is empty");
    }

    int val = queue[f];
    queue[f] = null;
    size--;
    f = (f + 1) % queue.length;

    if (val == deque.getFirst()) {
      deque.removeFirst();
    }

    return val;
  }

  public Integer max() {
    // TODO - you fill in here.
    if (deque.isEmpty()) {
      throw new RuntimeException("Invalid state.");
    }

    return deque.getFirst();
  }

  @EpiUserType(ctorParams = {String.class, int.class})
  public static class QueueOp {
    public String op;
    public int arg;

    public QueueOp(String op, int arg) {
      this.op = op;
      this.arg = arg;
    }
  }

  @EpiTest(testDataFile = "queue_with_max.tsv")
  public static void queueTester(List<QueueOp> ops) throws TestFailure {
    try {
      QueueWithMax q = new QueueWithMax();

      for (QueueOp op : ops) {
        switch (op.op) {
        case "QueueWithMax":
          q = new QueueWithMax();
          break;
        case "enqueue":
          q.enqueue(op.arg);
          break;
        case "dequeue":
          int result = q.dequeue();
          if (result != op.arg) {
            throw new TestFailure("Dequeue: expected " +
                                  String.valueOf(op.arg) + ", got " +
                                  String.valueOf(result));
          }
          break;
        case "max":
          int s = q.max();
          if (s != op.arg) {
            throw new TestFailure("Max: expected " + String.valueOf(op.arg) +
                                  ", got " + String.valueOf(s));
          }
          break;
        }
      }
    } catch (NoSuchElementException e) {
      throw new TestFailure("Unexpected NoSuchElement exception");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "QueueWithMax.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
