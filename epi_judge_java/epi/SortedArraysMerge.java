package epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SortedArraysMerge {
    private static class ArrayItem {
        public int val;
        public int arrInd;

        public ArrayItem(int val, int arrInd) {
            this.val = val;
            this.arrInd = arrInd;
        }
    }

    @EpiTest(testDataFile = "sorted_arrays_merge.tsv")
    public static List<Integer> mergeSortedArrays(List<List<Integer>> sortedArrays) {
        // TODO - you fill in here.

        // Time Complexity -> O(n*log(growing * sizeOf(sortedArrays))) and Space complexity of -> O(sizeOf(sortedArrays))
        // Not efficient
        // return mySolution1(sortedArrays);

        return bookSolution(sortedArrays);
    }

    private static List<Integer> bookSolution(List<List<Integer>> sortedArrays) {
        PriorityQueue<ArrayItem> minHeap = new PriorityQueue<>(3, new Comparator<ArrayItem>() {
            @Override
            public int compare(ArrayItem it1, ArrayItem it2) {
                return Integer.compare(it1.val, it2.val);
            }
        });

        List<Integer> result = new ArrayList<>();
        List<Iterator<Integer>> itrList = new ArrayList<>(sortedArrays.size());
        sortedArrays.forEach(arr -> itrList.add(arr.iterator()));
        for (int i=0; i<itrList.size(); i++) {
            if(itrList.get(i).hasNext()) {
                minHeap.add(new ArrayItem(itrList.get(i).next(), i));
            }
        }

        while (!minHeap.isEmpty()) {
            ArrayItem item = minHeap.poll();
            result.add(item.val);

            if(itrList.get(item.arrInd).hasNext()) {
                minHeap.add(new ArrayItem(itrList.get(item.arrInd).next(), item.arrInd));
            }
        }

        return result;
    }

    private static List<Integer> mySolution1(List<List<Integer>> sortedArrays) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        List<Integer> result = new ArrayList<>();

        int maxSize = Collections.max(sortedArrays.stream().map(List::size).collect(Collectors.toList()));
        int ind = 0;
        while (ind < maxSize) {
            for (List<Integer> arr : sortedArrays) {
                if (ind < arr.size()) {
                    minHeap.add(arr.get(ind));
                }
            }
            result.add(minHeap.poll());
            ind++;
        }

        while (minHeap.peek() != null) {
            result.add(minHeap.poll());
        }

        return result;
    }

    public static void main(String[] args) {
        System.exit(GenericTest.runFromAnnotations(args, "SortedArraysMerge.java", new Object() {
        }.getClass().getEnclosingClass()).ordinal());
    }
}
