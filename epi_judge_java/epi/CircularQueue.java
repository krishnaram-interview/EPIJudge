package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.List;
public class CircularQueue {

  public static class Queue {
    private static int RESIZE_FACTOR = 2;
    private Integer[] queue;
    private int size = 0;
    private int front = 0;
    private int rear = 0;

    public Queue(int capacity) {
      queue = new Integer[capacity];
    }

    public void enqueue(Integer x) {
      // TODO - you fill in here.

      if (size == queue.length) {
        int newCapacity = size * 2;
        Integer[] newQueue = new Integer[newCapacity];
        int f = front;
        for (int j = 0; j < size; j++) {
          newQueue[j] = queue[f];
          f = (f+1) % size;
        }
        front = 0;
        rear = size;
        queue = newQueue;
      }

      queue[rear] = x;
      rear = (rear + 1) % queue.length;
      size++;
    }

    public Integer dequeue() {
      // TODO - you fill in here.

      if (size <= 0) {
        throw new RuntimeException("Queue is empty");
      }

      Integer val = queue[front];
      queue[front] = null;
      front = (front + 1) % queue.length;
      size--;
      return val;
    }

    public int size() {
      // TODO - you fill in here.
      return size;
    }

    @Override
    public String toString() {
      // TODO - you fill in here.
      StringBuilder sb = new StringBuilder();
      if (size > 0) {
        for (int i=front; i<size; i++, i=i%size) {
          if (i == size-1) {
            sb.append(queue[i]);
          } else {
            sb.append(queue[i]).append("->");
          }
        }
      }
      return sb.toString();
    }
  }

  @EpiUserType(ctorParams = {String.class, int.class})
  public static class QueueOp {
    public String op;
    public int arg;

    public QueueOp(String op, int arg) {
      this.op = op;
      this.arg = arg;
    }

    @Override
    public String toString() {
      return op;
    }
  }

  @EpiTest(testDataFile = "circular_queue.tsv")
  public static void queueTester(List<QueueOp> ops) throws TestFailure {
    Queue q = new Queue(1);
    int opIdx = 0;
    for (QueueOp op : ops) {
      switch (op.op) {
      case "Queue":
        q = new Queue(op.arg);
        break;
      case "enqueue":
        q.enqueue(op.arg);
        break;
      case "dequeue":
        int result = q.dequeue();
        if (result != op.arg) {
          throw new TestFailure()
              .withProperty(TestFailure.PropertyName.STATE, q)
              .withProperty(TestFailure.PropertyName.COMMAND, op)
              .withMismatchInfo(opIdx, op.arg, result);
        }
        break;
      case "size":
        int s = q.size();
        if (s != op.arg) {
          throw new TestFailure()
              .withProperty(TestFailure.PropertyName.STATE, q)
              .withProperty(TestFailure.PropertyName.COMMAND, op)
              .withMismatchInfo(opIdx, op.arg, s);
        }
        break;
      }
      opIdx++;
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "CircularQueue.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
