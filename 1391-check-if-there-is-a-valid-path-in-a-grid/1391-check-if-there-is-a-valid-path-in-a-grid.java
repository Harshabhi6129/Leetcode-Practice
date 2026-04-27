import java.util.*;

class Solution {

    // directions: 0=up,1=right,2=down,3=left
    int[][] dirs = {{-1,0},{0,1},{1,0},{0,-1}};

    // For each street type, which directions are allowed
    int[][] typeDirs = {
        {},                 // 0 (unused)
        {1,3},              // 1: right, left
        {0,2},              // 2: up, down
        {3,2},              // 3: left, down
        {1,2},              // 4: right, down
        {3,0},              // 5: left, up
        {1,0}               // 6: right, up
    };

    public boolean hasValidPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] vis = new boolean[m][n];

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0,0});
        vis[0][0] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1];

            if (r == m - 1 && c == n - 1) return true;

            int type = grid[r][c];

            for (int d : typeDirs[type]) {
                int nr = r + dirs[d][0];
                int nc = c + dirs[d][1];

                if (nr < 0 || nc < 0 || nr >= m || nc >= n || vis[nr][nc]) continue;

                int nextType = grid[nr][nc];

                // reverse direction
                int rev = (d + 2) % 4;

                // check if neighbor connects back
                for (int nd : typeDirs[nextType]) {
                    if (nd == rev) {
                        vis[nr][nc] = true;
                        q.offer(new int[]{nr, nc});
                        break;
                    }
                }
            }
        }

        return false;
    }
}