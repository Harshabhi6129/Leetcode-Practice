class Solution {
    public int minMoves(int[] nums, int limit) {

        int n = nums.length;

        // difference array
        int[] diff = new int[2 * limit + 2];

        for (int i = 0; i < n / 2; i++) {

            int a = nums[i];
            int b = nums[n - 1 - i];

            int low = Math.min(a, b) + 1;
            int high = Math.max(a, b) + limit;
            int sum = a + b;

            // default: 2 moves
            diff[2] += 2;

            // 1 move range
            diff[low] -= 1;
            diff[high + 1] += 1;

            // 0 move at exact sum
            diff[sum] -= 1;
            diff[sum + 1] += 1;
        }

        int ans = Integer.MAX_VALUE;
        int current = 0;

        for (int s = 2; s <= 2 * limit; s++) {

            current += diff[s];
            ans = Math.min(ans, current);
        }

        return ans;
    }
}