package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        int[][] A = {{1, 2, 2, 3, 5},
                {3, 2, 3, 4, 4},
                {2, 4, 5, 3, 1},
                {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4},
        };
        Solution sol = new Solution();
        System.out.println(sol.isScramble("abbbcbaaccacaacc","acaaaccabcabcbcb"));
    }


//    public static void main(String[] args) {
//        int[] nums = {1, 3, 5, 7, 9, 11};
//        SegmentTree segmentTree = new SegmentTree(nums);
//        NumArray numArray = new NumArray(nums);
//
//        // Query the sum of range [1, 3]
//        System.out.println("Sum of range [1, 3]: " + segmentTree.query(1, 3)); // Output: 15
//
//        // Update value at index 1 to 10
//        segmentTree.update(1, 10);
//        System.out.println("Sum of range [1, 3] after update: " + segmentTree.query(1, 3)); // Output: 22
//    }
}
