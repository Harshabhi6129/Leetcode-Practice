class Solution {
    public int[] maxValue(int[] nums) {
        int n = nums.length;
        int[] suffixMin = new int[n + 1];
        suffixMin[n] = Integer.MAX_VALUE;

        for (int i = n - 1; i >= 0; i--) {
            suffixMin[i] = Math.min(suffixMin[i + 1], nums[i]);
        }

        int[] ans = new int[n];

        int start = 0;
        int compMax = nums[0];
        int prefixMax = nums[0];

        for (int i = 0; i < n; i++) {
            prefixMax = Math.max(prefixMax, nums[i]);
            compMax = Math.max(compMax, nums[i]);

            // End of strongly connected component
            if (prefixMax <= suffixMin[i + 1]) {
                for (int j = start; j <= i; j++) {
                    ans[j] = compMax;
                }

                if (i + 1 < n) {
                    start = i + 1;
                    compMax = nums[i + 1];
                    prefixMax = nums[i + 1];
                }
            }
        }

        return ans;
    }
}