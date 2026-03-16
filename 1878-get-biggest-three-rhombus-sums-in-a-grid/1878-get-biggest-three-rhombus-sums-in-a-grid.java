class Solution {
    public int[] getBiggestThree(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        TreeSet<Integer> top3 = new TreeSet<>();
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int maxR = Math.min(Math.min(i, m - 1 - i), Math.min(j, n - 1 - j));
                
                for (int r = 0; r <= maxR; r++) {
                    int s = 0;
                    
                    if (r == 0) {
                        s = grid[i][j];
                    } else {
                        for (int k = 0; k < r; k++) {
                            s += grid[i - r + k][j + k];  // top → right
                            s += grid[i + k][j + r - k];  // right → bottom
                            s += grid[i + r - k][j - k];  // bottom → left
                            s += grid[i - k][j - r + k];  // left → top
                        }
                    }
                    
                    top3.add(s);
                    if (top3.size() > 3) {
                        top3.pollFirst(); // remove smallest
                    }
                }
            }
        }
        
        // Convert to descending array
        int[] result = new int[top3.size()];
        int idx = top3.size() - 1;
        for (int val : top3) {
            result[idx--] = val;
        }
        return result;
    }
}