class Solution {
    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] > nums[high]) {
                // minimum lies in right half
                low = mid + 1;
            } else if (nums[mid] < nums[high]) {
                // minimum could be mid or left half
                high = mid;
            } else {
                // duplicates, cannot decide
                high--;
            }
        }

        return nums[low];
    }
}