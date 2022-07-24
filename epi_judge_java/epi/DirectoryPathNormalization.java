package epi;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class DirectoryPathNormalization {
  @EpiTest(testDataFile = "directory_path_normalization.tsv")

  public static String shortestEquivalentPath(String path) {
    // TODO - you fill in here.

    if (path == "") {
      return path;
    }
    String[] pathStr = path.split("/");
    Deque<String> normalizedPathStr = new LinkedList<>();

    if (path.startsWith("/")) {
      normalizedPathStr.push("/");
    }

    for(int i=0; i<pathStr.length; i++) {
      switch (pathStr[i]) {
        case ".":
        case "":
          break;
        case "..":
          if (normalizedPathStr.isEmpty() || normalizedPathStr.peek().equals("..")) {
            normalizedPathStr.push("..");
          } else {
            normalizedPathStr.pop();
          }
          break;
        default:
          normalizedPathStr.push(pathStr[i]);
          break;
      }
    }

    StringBuilder result = new StringBuilder();

    if (!normalizedPathStr.isEmpty()) {
      Iterator<String> stringIterator = normalizedPathStr.descendingIterator();
      String val = stringIterator.next();
      result.append(val);

      while (stringIterator.hasNext()) {
        if (!val.equals("/")) {
          result.append("/");
        }
        val = stringIterator.next();
        result.append(val);
      }
    }

    return result.toString();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DirectoryPathNormalization.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
