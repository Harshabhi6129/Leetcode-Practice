class Solution {
    public boolean areSimilar(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        int shift = k % n;

        if (shift == 0) return true;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int original = mat[i][j];
                int shifted;
                if (i % 2 == 0) { // left shift: element at j comes from j+shift
                    shifted = mat[i][(j + shift) % n];
                } else {           // right shift: element at j comes from j-shift
                    shifted = mat[i][(j - shift + n) % n];
                }
                if (original != shifted) return false;
            }
        }
        return true;
    }
}