import java.util.*;

class Solution {
    public String findTheString(int[][] lcp) {
        int n = lcp.length;
        char[] res = new char[n];
        Arrays.fill(res, '#');
        
        char ch = 'a';
        
        // Step 1: Build string
        for (int i = 0; i < n; i++) {
            if (res[i] != '#') continue;
            
            if (ch > 'z') return "";
            
            for (int j = i; j < n; j++) {
                if (lcp[i][j] > 0) {
                    res[j] = ch;
                }
            }
            ch++;
        }
        
        // Step 2: Validate using DP
        int[][] dp = new int[n][n];
        
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (res[i] == res[j]) {
                    if (i == n - 1 || j == n - 1)
                        dp[i][j] = 1;
                    else
                        dp[i][j] = 1 + dp[i + 1][j + 1];
                }
            }
        }
        
        // Compare with given lcp
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] != lcp[i][j]) {
                    return "";
                }
            }
        }
        
        return new String(res);
    }
}