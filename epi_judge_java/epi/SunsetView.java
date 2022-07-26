package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class SunsetView {

  public static List<Integer> examineBuildingsWithSunset(Iterator<Integer> sequence) {
    // TODO - you fill in here.
    // O(n) time and O(n) space
    // return bruteForce(sequence);

    // O(n) time and still O(n) space
    return solUsingStack(sequence);
  }

  private static List<Integer> solUsingStack(Iterator<Integer> sequence) {
    if (sequence == null || !sequence.hasNext()) {
      return new ArrayList<>();
    }

    Deque<Integer[]> buildings = new LinkedList<>();
    int i = -1;
    while (sequence.hasNext()) {
      Integer height = sequence.next();
      i++;
      if (!buildings.isEmpty() && height >= buildings.peek()[1]) {
        while (!buildings.isEmpty() && buildings.peek()[1] <= height) {
          buildings.pop();
        }
      }
      buildings.push(new Integer[]{i, height});
    }

    return buildings.stream().map(v -> v[0]).collect(Collectors.toList());
  }

  private static List<Integer> bruteForce(Iterator<Integer> sequence) {
    List<Integer> result = new ArrayList<>();
    if (sequence == null || !sequence.hasNext()) {
      return result;
    }

    List<Integer> buildings = new ArrayList<>();
    while (sequence.hasNext()) {
      buildings.add(sequence.next());
    }

    int maxHeight = -1;
    for (int i = buildings.size()-1; i >= 0; i--) {
      Integer height = buildings.get(i);
      if (height > maxHeight) {
        result.add(i);
        maxHeight = height;
      }
    }

    return result;
  }

  @EpiTest(testDataFile = "sunset_view.tsv")
  public static List<Integer>
  examineBuildingsWithSunsetWrapper(List<Integer> sequence) {
    return examineBuildingsWithSunset(sequence.iterator());
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SunsetView.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
