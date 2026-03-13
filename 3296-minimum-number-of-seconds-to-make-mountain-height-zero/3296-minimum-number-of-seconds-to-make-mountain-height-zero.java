class Solution {

    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {

        long left = 1;
        long right = (long) 1e18;
        long ans = right;

        while (left <= right) {

            long mid = (left + right) / 2;

            if (can(mid, mountainHeight, workerTimes)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return ans;
    }

    private boolean can(long time, int height, int[] workers) {

        long total = 0;

        for (int w : workers) {

            double val = (double) time / w;

            long x = (long) ((Math.sqrt(1 + 8 * val) - 1) / 2);

            total += x;

            if (total >= height) return true;
        }

        return false;
    }
}