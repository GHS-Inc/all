package org.example;

import java.util.*;

/**
 * Given a collection of intervals, merge all intervals that overlap.
 * Input:
 * int[][] intervals = {
 *     {1, 3},
 *     {2, 6},
 *     {8, 10},
 *     {15, 18}
 * };
 *
 *
 * Output:
 * [1, 6]
 * [8, 10]
 * [15, 18]
 */
public class MergeOverlap {

        public static int[][] merge(int[][] intervals) {

            if (intervals == null || intervals.length <= 1) {
                return intervals;
            }

            // Step 1: Sort intervals by start time
            Arrays.sort(intervals,
                    Comparator.comparingInt(a -> a[0]));

            List<int[]> result = new ArrayList<>();

            // Step 2: Start with the first interval
            int[] current = intervals[0];

            for (int i = 1; i < intervals.length; i++) {

                int[] next = intervals[i];

                // Step 3: Check whether intervals overlap
                if (next[0] <= current[1]) {

                    // Merge by extending the end
                    current[1] = Math.max(
                            current[1], next[1]);

                } else {

                    // No overlap: save current interval
                    result.add(current);
                    current = next;
                }
            }

            // Step 4: Add the last interval
            result.add(current);

            return result.toArray(new int[result.size()][]);
        }

        public static void main(String[] args) {

            int[][] intervals = {
                    {1, 3},
                    {2, 6},
                    {8, 10},
                    {15, 18}
            };

            int[][] merged = merge(intervals);

            for (int[] interval : merged) {
                System.out.println(
                        Arrays.toString(interval));
            }
        }
    }


