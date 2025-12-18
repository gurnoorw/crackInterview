package com.company;

import java.util.*;
public class Solution {
    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    int dp[][][] ;
    public int isScramble(final String a, final String b) {
        if (a.length() != b.length()) {
            return 0; // False
        }

        int n = a.length();
        dp = new int[n + 1][n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        return isScrambleHelper(0, 0, n - 1, a, b) ? 1 : 0; // True or False
    }

    private boolean isScrambleHelper(int startA, int startB, int length, String a, String b) {
        if (a.substring(startA, startA + length).equals(b.substring(startB, startB + length))) {
            return true;
        }

        if (dp[startA][startB][length] != -1) {
            return dp[startA][startB][length] == 1; // Convert from boolean to 1/0 for dp
        }

        boolean possible = false;
        for (int i = 1; i < length; i++) {
            possible |= (isScrambleHelper(startA, startB, i, a.substring(startA, startA + i), b.substring(startB, startB + i)) &&
                    isScrambleHelper(startA + i, startA + i, length - i, a.substring(startA + i, startA + length), b.substring(startB + i, startB + length)));
            possible |= (isScrambleHelper(startA, startB + length - i, i, a.substring(startA, startA + i), b.substring(startB + length - i, startB + length)) &&
                    isScrambleHelper(startA + i, startB, length - i, a.substring(startA + i, startA + length), b.substring(startB, startB + length - i)));
        }

        dp[startA][startB][length] = possible ? 1 : 0;
        return possible;
    }
}