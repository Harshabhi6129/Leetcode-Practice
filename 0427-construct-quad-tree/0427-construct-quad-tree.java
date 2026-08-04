class Solution {
    public Node construct(int[][] grid) {
        return build(grid, 0, 0, grid.length);
    }

    private Node build(int[][] grid, int row, int col, int size) {
        if (isLeaf(grid, row, col, size)) {
            return new Node(grid[row][col] == 1, true);
        }

        int half = size / 2;

        Node topLeft = build(grid, row, col, half);
        Node topRight = build(grid, row, col + half, half);
        Node bottomLeft = build(grid, row + half, col, half);
        Node bottomRight = build(grid, row + half, col + half, half);

        return new Node(
                true,      // any value is acceptable for non-leaf nodes
                false,
                topLeft,
                topRight,
                bottomLeft,
                bottomRight
        );
    }

    private boolean isLeaf(int[][] grid, int row, int col, int size) {
        int value = grid[row][col];

        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (grid[i][j] != value) {
                    return false;
                }
            }
        }

        return true;
    }
}