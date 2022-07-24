package epi;
import java.util.Deque;
import java.util.LinkedList;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class EvaluateRpn {
  @EpiTest(testDataFile = "evaluate_rpn.tsv")

  public static int eval(String expression) {
    // TODO - you fill in here.

    // O(n) time and O(1) space
    int len = expression.length();
    if (len == 0) {
      return 0;
    }

    String[] expressionArray = expression.split(",");

    Deque<Integer> stack = new LinkedList<>();
    for (int i=0; i<expressionArray.length; i++) {
      String val = expressionArray[i];

      switch (val) {
        case "+":
          int v2 = stack.pop();
          int v1 = stack.pop();
          stack.push(v1 + v2);
          break;
        case "-":
          v2 = stack.pop();
          v1 = stack.pop();
          stack.push(v1 - v2);
          break;
        case "/":
          v2 = stack.pop();
          v1 = stack.pop();
          stack.push(v1 / v2);
          break;
        case "*":
          v2 = stack.pop();
          v1 = stack.pop();
          stack.push(v1 * v2);
          break;
        default:
          stack.push(Integer.parseInt(val));
          break;
      }
    }

    return stack.pop();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EvaluateRpn.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
