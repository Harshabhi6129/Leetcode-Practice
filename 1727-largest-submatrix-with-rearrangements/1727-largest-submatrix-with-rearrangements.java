class Solution {
    public int largestSubmatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        
        // Step 1: Build height (histogram) array in-place
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    matrix[i][j] += matrix[i-1][j];
                }
            }
        }
        
        // Step 2: For each row, sort descending and compute best area
        int ans = 0;
        for (int i = 0; i < m; i++) {
            int[] row = matrix[i].clone();
            Arrays.sort(row);  // ascending, we'll read right to left
            
            for (int k = n - 1; k >= 0; k--) {
                if (row[k] == 0) break;
                int width = n - k;  // number of columns used
                ans = Math.max(ans, row[k] * width);
            }
        }
        
        return ans;
    }
}