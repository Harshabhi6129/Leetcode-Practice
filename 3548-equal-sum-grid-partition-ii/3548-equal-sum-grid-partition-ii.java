class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        long total = 0;
        for (int[] row : grid)
            for (int v : row) total += v;
        return checkH(grid, m, n, total) || checkV(grid, m, n, total);
    }

    private boolean checkH(int[][] grid, int m, int n, long total) {
        Set<Integer> top = new HashSet<>();
        Map<Integer, Integer> bot = new HashMap<>();
        for (int[] row : grid)
            for (int v : row) bot.merge(v, 1, Integer::sum);

        long topSum = 0;
        for (int i = 0; i < m - 1; i++) {
            for (int v : grid[i]) {
                topSum += v;
                top.add(v);
                bot.compute(v, (k, c) -> c == 1 ? null : c - 1);
            }
            long diff = 2 * topSum - total;
            if (diff == 0) return true;

            int tr = i + 1, br = m - i - 1;
            if (diff > 0 && diff <= 100_000) {
                int dv = (int) diff;
                if (tr >= 2 && n >= 2 && top.contains(dv))                          return true;
                if (tr == 1 && n >= 2 && (grid[0][0] == dv || grid[0][n-1] == dv)) return true;
                if (tr >= 2 && n == 1 && (grid[0][0] == dv || grid[i][0]   == dv)) return true;
            } else if (diff < 0 && -diff <= 100_000) {
                int dv = (int) -diff;
                if (br >= 2 && n >= 2 && bot.containsKey(dv))                              return true;
                if (br == 1 && n >= 2 && (grid[i+1][0] == dv || grid[i+1][n-1] == dv))   return true;
                if (br >= 2 && n == 1 && (grid[i+1][0] == dv || grid[m-1][0]    == dv))  return true;
            }
        }
        return false;
    }

    private boolean checkV(int[][] grid, int m, int n, long total) {
        Set<Integer> left = new HashSet<>();
        Map<Integer, Integer> right = new HashMap<>();
        for (int[] row : grid)
            for (int v : row) right.merge(v, 1, Integer::sum);

        long leftSum = 0;
        for (int j = 0; j < n - 1; j++) {
            for (int i = 0; i < m; i++) {
                int v = grid[i][j];
                leftSum += v;
                left.add(v);
                right.compute(v, (k, c) -> c == 1 ? null : c - 1);
            }
            long diff = 2 * leftSum - total;
            if (diff == 0) return true;

            int lc = j + 1, rc = n - j - 1;
            if (diff > 0 && diff <= 100_000) {
                int dv = (int) diff;
                if (m >= 2 && lc >= 2 && left.contains(dv))                        return true;
                if (m == 1 && lc >= 2 && (grid[0][0] == dv || grid[0][j]   == dv)) return true;
                if (m >= 2 && lc == 1 && (grid[0][0] == dv || grid[m-1][0] == dv)) return true;
            } else if (diff < 0 && -diff <= 100_000) {
                int dv = (int) -diff;
                if (m >= 2 && rc >= 2 && right.containsKey(dv))                           return true;
                if (m == 1 && rc >= 2 && (grid[0][j+1] == dv || grid[0][n-1]   == dv))   return true;
                if (m >= 2 && rc == 1 && (grid[0][n-1] == dv || grid[m-1][n-1] == dv))  return true;
            }
        }
        return false;
    }
}