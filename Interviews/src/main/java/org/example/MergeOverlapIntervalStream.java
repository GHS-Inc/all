package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.lang.Integer.max;
//https://www.baeldung.com/java-collection-merge-overlapping-intervals

public class MergeOverlapIntervalStream {

    public static void main(String[] args) {
        List<Interval> intervals = Arrays.asList(
                new Interval(3, 5),
                new Interval(13, 20),
                new Interval(11, 15),
                new Interval(15, 16),
                new Interval(1, 3),
                new Interval(4, 5),
                new Interval(16, 17)
        );
        System.out.println("doMerge(intervals) = " + doMerge(intervals));
        doMerge(intervals).stream().forEach(System.out::println);
    }
    static List<Interval> doMerge(List<Interval> intervals) {
        intervals.sort(Comparator.comparingInt(interval -> interval.start)); // {1,3}, 3 5, 4 5,       11 15, 13 30, 15 16, 16 17
        ArrayList<Interval> merged = new ArrayList<>();
        merged.add(intervals.get(0));  // {1, 3}

        intervals.forEach(interval -> {
            Interval lastMerged = merged.get(merged.size() - 1);
            if (interval.start <= lastMerged.end){
                lastMerged.setEnd(max(interval.end, lastMerged.end));
            } else {
                merged.add(interval);
            }
        });

        return merged;
    }
    static class Interval{
        int start;
        int end;

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public Interval() {
        }

        @Override
        public String toString() {
            return "Interval{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }
}
