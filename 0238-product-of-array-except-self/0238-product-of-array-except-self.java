class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        // Step 1: prefix products
        // result[i] = product of all elements to the LEFT of i
        result[0] = 1;
        for (int i = 1; i < n; i++)
            result[i] = result[i - 1] * nums[i - 1];

        // Step 2: multiply each index by its suffix product (right to left)
        int suffix = 1;
        for (int i = n - 1; i >= 0; i--) {
            result[i] *= suffix;
            suffix    *= nums[i];
        }

        return result;
    }
}