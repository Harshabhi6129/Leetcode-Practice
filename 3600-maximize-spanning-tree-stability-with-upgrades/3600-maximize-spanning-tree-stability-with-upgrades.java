import java.util.*;

class Solution {

    class DSU {
        int[] parent, rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }

        boolean union(int a, int b) {
            int pa = find(a), pb = find(b);
            if (pa == pb) return false;

            if (rank[pa] < rank[pb]) parent[pa] = pb;
            else if (rank[pb] < rank[pa]) parent[pb] = pa;
            else {
                parent[pb] = pa;
                rank[pa]++;
            }
            return true;
        }
    }

    public int maxStability(int n, int[][] edges, int k) {

        int maxS = 0;
        for (int[] e : edges) maxS = Math.max(maxS, e[2]);

        int left = 1, right = maxS * 2;
        int ans = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (can(n, edges, k, mid)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return ans;
    }

    private boolean can(int n, int[][] edges, int k, int target) {

        DSU dsu = new DSU(n);
        int used = 0;
        int upgrades = 0;

        List<int[]> optional = new ArrayList<>();

        for (int[] e : edges) {
            if (e[3] == 1) {
                if (e[2] < target) return false;
                if (!dsu.union(e[0], e[1])) return false;
                used++;
            } else {
                optional.add(e);
            }
        }

        for (int[] e : optional) {
            if (e[2] >= target) {
                if (dsu.union(e[0], e[1])) used++;
            }
        }

        for (int[] e : optional) {
            if (used == n - 1) break;

            if (e[2] < target && e[2] * 2 >= target) {
                if (dsu.union(e[0], e[1])) {
                    upgrades++;
                    used++;
                    if (upgrades > k) return false;
                }
            }
        }

        return used == n - 1;
    }
}