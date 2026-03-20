class Solution {
    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int[][] ans = new int[m - k + 1][n - k + 1];
        
        for (int i = 0; i <= m - k; i++) {
            for (int j = 0; j <= n - k; j++) {
                // Collect distinct values using TreeSet (auto-sorted)
                TreeSet<Integer> set = new TreeSet<>();
                for (int di = 0; di < k; di++)
                    for (int dj = 0; dj < k; dj++)
                        set.add(grid[i + di][j + dj]);
                
                // Find min diff between consecutive sorted distinct values
                int minDiff = Integer.MAX_VALUE;
                int prev = Integer.MIN_VALUE;
                for (int val : set) {
                    if (prev != Integer.MIN_VALUE)
                        minDiff = Math.min(minDiff, val - prev);
                    prev = val;
                }
                
                ans[i][j] = (minDiff == Integer.MAX_VALUE) ? 0 : minDiff;
            }
        }
        
        return ans;
    }
}