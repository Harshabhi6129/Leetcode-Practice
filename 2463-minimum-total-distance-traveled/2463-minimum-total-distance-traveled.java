import java.util.*;

class Solution {
    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        Collections.sort(robot);
        Arrays.sort(factory, (a, b) -> a[0] - b[0]);

        int m = robot.size();
        int n = factory.length;

        long[][] dp = new long[m + 1][n + 1];

        for (long[] row : dp) Arrays.fill(row, Long.MAX_VALUE);
        dp[0][0] = 0;

        for (int j = 1; j <= n; j++) {
            int pos = factory[j - 1][0];
            int limit = factory[j - 1][1];

            for (int i = 0; i <= m; i++) {
                dp[i][j] = dp[i][j - 1]; // skip this factory

                long cost = 0;

                // assign k robots to this factory
                for (int k = 1; k <= limit && k <= i; k++) {
                    cost += Math.abs(robot.get(i - k) - pos);

                    if (dp[i - k][j - 1] != Long.MAX_VALUE) {
                        dp[i][j] = Math.min(dp[i][j],
                                dp[i - k][j - 1] + cost);
                    }
                }
            }
        }

        return dp[m][n];
    }
}