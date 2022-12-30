package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class AdvanceByOffsets {
  @EpiTest(testDataFile = "advance_by_offsets.tsv")
  public static boolean canReachEnd(List<Integer> maxAdvanceSteps) {
    // TODO - you fill in here.
    // return true;
    //return dpSolution(maxAdvanceSteps);
    return bestSol(maxAdvanceSteps);
  }

  // O(n) solution
  private static boolean bestSol(List<Integer> maxAdvanceSteps) {
    int furthestReachSoFar = 0;
    List<Integer> reach = new ArrayList<>();
    for(int i=0; i<maxAdvanceSteps.size()-1; i++) {
      if(i<=furthestReachSoFar) {
        furthestReachSoFar = Math.max(i+maxAdvanceSteps.get(i), furthestReachSoFar);
        reach.add(furthestReachSoFar);
      } else {
        break;
      }
    }
    return furthestReachSoFar >= maxAdvanceSteps.size()-1;
  }

  // O(n^2) solution based on Dynamic Programming
  private static boolean dpSolution(List<Integer> maxAdvanceSteps) {
    if (maxAdvanceSteps.size() == 0) {
      return false;
    }

    List<Boolean> dpTable = new ArrayList<>(Collections.nCopies(maxAdvanceSteps.size(), Boolean.FALSE));
    dpTable.set(0, true);

    for(int i=1; i<maxAdvanceSteps.size(); i++) {
      for (int j=0; j<i; j++) {
        if (maxAdvanceSteps.get(j) >= (i-j) && dpTable.get(j)) {
          dpTable.set(i, true);
          break;
        }
      }
    }
    return dpTable.get(maxAdvanceSteps.size()-1);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "AdvanceByOffsets.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
