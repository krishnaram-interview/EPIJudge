package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Anagrams {
  @EpiTest(testDataFile = "anagrams.tsv")

  public static List<List<String>> findAnagrams(List<String> dictionary) {
    // TODO - you fill in here.
    Map<String, List<String>> anagrams = new HashMap<>();

    dictionary.forEach(v -> {
      char[] vArr = v.toCharArray();
      Arrays.sort(vArr);
      String sortedVal = Arrays.toString(vArr);

      if (anagrams.containsKey(sortedVal)) {
        anagrams.get(sortedVal).add(v);
      } else {
        anagrams.put(sortedVal, new ArrayList<>(List.of(v)));
      }
    });

    Map<String, List<String>> validAnagrams =
            anagrams.entrySet().stream().filter(a -> a.getValue().size() > 1).collect(Collectors.toMap(Map.Entry::getKey,
            Map.Entry::getValue));
    return new ArrayList<>(validAnagrams.values());
  }

  @EpiTestComparator
  public static boolean comp(List<List<String>> expected,
                             List<List<String>> result) {
    if (result == null) {
      return false;
    }
    for (List<String> l : expected) {
      Collections.sort(l);
    }
    expected.sort(new LexicographicalListComparator<>());
    for (List<String> l : result) {
      Collections.sort(l);
    }
    result.sort(new LexicographicalListComparator<>());
    return expected.equals(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Anagrams.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
