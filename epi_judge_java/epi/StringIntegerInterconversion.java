package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

public class StringIntegerInterconversion {

  // O(n) -> Time, O(1) -> Space
  public static String intToString(int x) {
    // TODO - you fill in here.

    boolean isNumberNegative = x < 0;
    StringBuilder sb = new StringBuilder();

    if (x == 0) {
      sb.append(x);
    } else {
      while (x != 0) {
        sb.append(Math.abs(x % 10));
        x = x / 10;
      }
    }

    if (isNumberNegative) {
      sb.append('-');
    }

    return sb.reverse().toString();
  }

  // O(n) -> Time, O(1) -> Space
  public static int stringToInt(String s) {
    // TODO - you fill in here.

    // Brute force
    //return stringToIntBruteForce(s);

    // This is better solution compared to stringToIntBruteForce() because, the number of multiplication operation by 10 is less.
    // In brute force in every iteration, the number is multiplied by power of 10. While in this solution, the partial is result is kept
    // and multiplied by only 10 each time. So little better than calculating power of 10 each time.
    return stringToIntBestSol(s);
  }

  private static int stringToIntBestSol(String s) {
    int result = 0;
    boolean isNegative = s.charAt(0) == '-';
    boolean isPos = s.charAt(0) == '+';

    for (int i=isNegative || isPos ? 1 : 0; i<s.length(); i++) {
      result = result * 10 + s.charAt(i) - '0';
    }

    return isNegative ? -result : result;
  }

  private static int stringToIntBruteForce(String s) {
    long result = 0;
    boolean isNumberNegative = s.charAt(0) == '-';
    s = (s.charAt(0) == '+' || s.charAt(0) == '-') ? s.substring(1) : s;

    for(int i = 0; i < s.length(); i++) {
      result += Math.pow(10, s.length() - 1 - i)  * (s.charAt(i) - 48);
    }

    return (int) (isNumberNegative ? -result : result);
  }

  @EpiTest(testDataFile = "string_integer_interconversion.tsv")
  public static void wrapper(int x, String s) throws TestFailure {
    if (Integer.parseInt(intToString(x)) != x) {
      throw new TestFailure("Int to string conversion failed");
    }

    if (stringToInt(s) != x) {
      throw new TestFailure("String to int conversion failed");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "StringIntegerInterconversion.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
