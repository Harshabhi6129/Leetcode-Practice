class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        long total = 0;
        for (int[] row : grid)
            for (int val : row)
                total += val;

        // Try horizontal cuts (between rows)
        long prefix = 0;
        for (int i = 0; i < m - 1; i++) {
            for (int val : grid[i])
                prefix += val;
            if (2 * prefix == total)
                return true;
        }

        // Try vertical cuts (between columns)
        prefix = 0;
        for (int j = 0; j < n - 1; j++) {
            for (int i = 0; i < m; i++)
                prefix += grid[i][j];
            if (2 * prefix == total)
                return true;
        }

        return false;
    }
}