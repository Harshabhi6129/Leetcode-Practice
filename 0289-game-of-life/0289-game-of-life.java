class Solution {
    public void gameOfLife(int[][] board) {
        int m = board.length, n = board[0].length;
        
        int[][] dirs = {
            {-1,-1}, {-1,0}, {-1,1},
            {0,-1},         {0,1},
            {1,-1}, {1,0}, {1,1}
        };
        
        // Step 1: Apply rules with encoding
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                
                int liveNeighbors = 0;
                
                for (int[] d : dirs) {
                    int x = i + d[0], y = j + d[1];
                    
                    if (x >= 0 && x < m && y >= 0 && y < n 
                        && Math.abs(board[x][y]) == 1) {
                        liveNeighbors++;
                    }
                }
                
                if (board[i][j] == 1) {
                    if (liveNeighbors < 2 || liveNeighbors > 3) {
                        board[i][j] = -1; // live -> dead
                    }
                } else {
                    if (liveNeighbors == 3) {
                        board[i][j] = 2; // dead -> live
                    }
                }
            }
        }
        
        // Step 2: Finalize states
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] > 0) board[i][j] = 1;
                else board[i][j] = 0;
            }
        }
    }
}