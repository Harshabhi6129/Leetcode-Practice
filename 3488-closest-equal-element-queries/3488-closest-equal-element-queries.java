import java.util.*;

class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        List<Integer> res = new ArrayList<>();

        for (int idx : queries) {
            int val = nums[idx];
            List<Integer> list = map.get(val);

            if (list.size() == 1) {
                res.add(-1);
                continue;
            }

            int pos = Collections.binarySearch(list, idx);

            int leftIdx = (pos - 1 + list.size()) % list.size();
            int rightIdx = (pos + 1) % list.size();

            int left = list.get(leftIdx);
            int right = list.get(rightIdx);

            int d1 = circularDist(idx, left, n);
            int d2 = circularDist(idx, right, n);

            res.add(Math.min(d1, d2));
        }

        return res;
    }

    private int circularDist(int a, int b, int n) {
        int diff = Math.abs(a - b);
        return Math.min(diff, n - diff);
    }
}