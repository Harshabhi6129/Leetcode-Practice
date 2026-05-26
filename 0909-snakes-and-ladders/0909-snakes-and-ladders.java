import java.util.*;

class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n * n + 1];

        queue.offer(1);
        visited[1] = true;

        int moves = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int curr = queue.poll();

                // Reached destination
                if (curr == n * n) {
                    return moves;
                }

                // Try all dice rolls from 1 to 6
                for (int dice = 1; dice <= 6 && curr + dice <= n * n; dice++) {
                    int next = curr + dice;

                    int[] pos = getCoordinates(next, n);
                    int r = pos[0];
                    int c = pos[1];

                    // If snake or ladder exists
                    if (board[r][c] != -1) {
                        next = board[r][c];
                    }

                    if (!visited[next]) {
                        visited[next] = true;
                        queue.offer(next);
                    }
                }
            }

            moves++;
        }

        return -1;
    }

    // Convert square number to board coordinates
    private int[] getCoordinates(int num, int n) {
        int row = n - 1 - (num - 1) / n;
        int col = (num - 1) % n;

        // Reverse direction for alternating rows
        if (((n - 1 - row) % 2) == 1) {
            col = n - 1 - col;
        }

        return new int[]{row, col};
    }
}