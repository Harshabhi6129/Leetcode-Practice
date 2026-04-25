import java.util.*;

class Solution {
    public int maxDistance(int side, int[][] points, int k) {
        int n = points.length;
        long perimeter = 4L * side;

        long[] pos = new long[n];
        for (int i = 0; i < n; i++) {
            int x = points[i][0];
            int y = points[i][1];

            if (y == 0) pos[i] = x;
            else if (x == side) pos[i] = side + y;
            else if (y == side) pos[i] = 3L * side - x;
            else pos[i] = 4L * side - y;
        }

        Arrays.sort(pos);

        long[] extended = new long[2 * n];
        for (int i = 0; i < n; i++) {
            extended[i] = pos[i];
            extended[i + n] = pos[i] + perimeter;
        }

        long low = 0, high = perimeter, ans = 0;

        while (low <= high) {
            long mid = (low + high) / 2;
            if (canPick(extended, pos, n, k, mid, perimeter)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return (int) ans;
    }

    private boolean canPick(long[] ext, long[] pos, int n, int k, long d, long perimeter) {
        for (int start = 0; start < n; start++) {
            int count = 1;
            long last = ext[start];
            int idx = start;

            while (count < k) {
                long target = last + d;
                idx = lowerBound(ext, idx + 1, start + n, target);
                if (idx == start + n) break;

                last = ext[idx];
                count++;
            }

            if (count == k && perimeter - (last - ext[start]) >= d) {
                return true;
            }
        }
        return false;
    }

    private int lowerBound(long[] arr, int l, int r, long target) {
        int res = r;
        while (l <= r - 1) {
            int mid = (l + r - 1) / 2;
            if (arr[mid] >= target) {
                res = mid;
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return res;
    }
}