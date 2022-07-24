package epi;
import java.util.Deque;
import java.util.LinkedList;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsValidParenthesization {
  @EpiTest(testDataFile = "is_valid_parenthesization.tsv")

  public static boolean isWellFormed(String s) {
    // TODO - you fill in here.
    // O(n) time and O(1) space
    // return bookSol(s);

    // O(n) time and O(1) space
    return mySol(s);
  }


  private static boolean bookSol(String s) {
    Deque<Character> leftBrackets = new LinkedList<>();
    for (int i = 0; i< s.length(); i++) {
      if (s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[') {
        leftBrackets.push(s.charAt(i));
      } else {
        if (leftBrackets.isEmpty()) {
          return false;
        } else {
          if ((s.charAt(i) == ')' && leftBrackets.peek() == '(') ||
                  (s.charAt(i) == '}' && leftBrackets.peek() == '{') ||
                  (s.charAt(i) == ']' && leftBrackets.peek() == '[')
          ) {
            leftBrackets.pop();
          } else {
            return false;
          }
        }
      }
    }
    return leftBrackets.isEmpty();
  }

  private static boolean mySol(String s) {
    Deque<Character> stack = new LinkedList<>();
    boolean isWellFormed = true;
    for (int i = 0; i< s.length() && isWellFormed; i++) {
      char c1 = s.charAt(i);
      switch (c1) {
        case '{':
        case '(':
        case '[':
          stack.push(c1);
          break;
        case '}':
          isWellFormed = checkIfWellFormed(stack, '{');
          break;
        case ')':
          isWellFormed = checkIfWellFormed(stack, '(');
          break;
        case ']':
          isWellFormed = checkIfWellFormed(stack, '[');
          break;
        default:
          break;
      }
    }
    return isWellFormed && stack.isEmpty();
  }

  private static boolean checkIfWellFormed(Deque<Character> stack, Character c) {
    boolean isWellFormed = true;
    if (!stack.isEmpty()) {
      char c2 = stack.pop();
      if (c2 != c) {
        isWellFormed = false;
      }
    } else {
      isWellFormed = false;
    }
    return isWellFormed;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsValidParenthesization.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
