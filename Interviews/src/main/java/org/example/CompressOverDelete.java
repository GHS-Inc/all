package org.example;

import java.util.Arrays;
/**
 * Java solution — Dynamic Programming
 * Example:
 * - Input: s = "aaabcccd", k = 2
 * - Output: 4
 * - One optimal result is "aaaccc", compressed as a3c3, with length 4.
 */
public class CompressOverDelete {

    public static int getLengthOfOptimalCompression(
                String s, int k) {

            int n = s.length();

            // dp[i][deleted] = minimum compressed length
            // for suffix starting at i, with deleted deletions.
            int[][] dp = new int[n + 1][k + 1];

            for (int[] row : dp) {
                Arrays.fill(row, 10000);
            }

            Arrays.fill(dp[n], 0);

            for (int i = n - 1; i >= 0; i--) {
                for (int deleted = 0; deleted <= k; deleted++) {

                    // Option 1: delete current character
                    if (deleted > 0) {
                        dp[i][deleted] =
                                dp[i + 1][deleted - 1];
                    }

                    // Option 2: keep current character and
                    // form a group of identical characters.
                    int same = 0;
                    int remove = 0;

                    for (int j = i; j < n; j++) {
                        if (s.charAt(j) == s.charAt(i)) {
                            same++;
                        } else {
                            remove++;
                        }

                        if (remove > deleted) {
                            break;
                        }

                        int encodedLength =
                                1 + (same >= 100 ? 3
                                        : same >= 10 ? 2
                                        : same >= 2 ? 1 : 0);

                        dp[i][deleted] = Math.min(
                                dp[i][deleted],
                                encodedLength
                                        + dp[j + 1][deleted - remove]);
                    }
                }
            }

            return dp[0][k];
        }

        public static void main(String[] args) {
            String s = "aaabcccd";
            int k = 2;

            System.out.println(
                    getLengthOfOptimalCompression(s, k));
        }

}
