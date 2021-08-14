package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsStringPalindromic {
  @EpiTest(testDataFile = "is_string_palindromic.tsv")

  public static boolean isPalindromic(String s) {
    // TODO - you fill in here.
    int i = 0;
    int j = s.length() - 1;
    boolean isPalindrome = true;
    while(i < j) {
      if (s.charAt(i) != s.charAt(j)) {
        isPalindrome = false;
        break;
      }
      i++;
      j--;
    }
    return isPalindrome;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsStringPalindromic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
