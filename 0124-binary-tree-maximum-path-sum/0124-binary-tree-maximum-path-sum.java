class Solution {
    private int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return maxSum;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftGain = Math.max(0, dfs(node.left));
        int rightGain = Math.max(0, dfs(node.right));

        // Path passing through current node
        int currentPath = node.val + leftGain + rightGain;

        maxSum = Math.max(maxSum, currentPath);

        // Return best single path upward
        return node.val + Math.max(leftGain, rightGain);
    }
}