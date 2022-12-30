package others;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class ThreeSum {
    public static void main(String[] args) {
       threeSum(new int[]{-1,0,1,2,-1,-4});
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        Set<Triplet> triplets = new HashSet<>();
        for (int i=0; i<nums.length; i++) {
            for (int j=i+1; j<nums.length-2; j++) {
                for (int k=j+1; k<nums.length-1; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        triplets.add(new Triplet(nums[i], nums[j], nums[k]));
                    }
                }
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        triplets.forEach(t -> {
            result.add(t.triplet);
        });
        return result;
    }

    public static class Triplet {
        public List<Integer> triplet = new ArrayList<>();

        public Triplet(Integer i, Integer j, Integer k) {
            triplet.add(i);
            triplet.add(j);
            triplet.add(k);
            Collections.sort(triplet);
        }

        @Override
        public int hashCode() {
            return Objects.hash(triplet);
        }

        @Override
        public boolean equals(Object otherTrip) {
            Triplet other = (Triplet) otherTrip;
            return triplet.get(0).equals(other.triplet.get(0)) &&
                    triplet.get(1).equals(other.triplet.get(1)) &&
                    triplet.get(2).equals(other.triplet.get(2));
        }
    }
}
