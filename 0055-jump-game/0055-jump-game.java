class Solution {
    public boolean canJump(int[] nums) {
        int maxReach = 0;

        for (int i = 0; i < nums.length; i++) {
            // If current index is not reachable
            if (i > maxReach) return false;

            // Update farthest reachable index
            maxReach = Math.max(maxReach, i + nums[i]);

            // Early exit if we can reach last index
            if (maxReach >= nums.length - 1) return true;
        }

        return true;
    }
}