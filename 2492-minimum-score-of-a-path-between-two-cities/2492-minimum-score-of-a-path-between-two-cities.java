class Solution {
    public int minScore(int n, int[][] roads) {
        List<List<int[]>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int d = road[2];

            graph.get(u).add(new int[]{v, d});
            graph.get(v).add(new int[]{u, d});
        }

        boolean[] visited = new boolean[n + 1];
        return dfs(1, graph, visited);
    }

    private int dfs(int node, List<List<int[]>> graph, boolean[] visited) {
        visited[node] = true;
        int min = Integer.MAX_VALUE;

        for (int[] edge : graph.get(node)) {
            int next = edge[0];
            int dist = edge[1];

            min = Math.min(min, dist);

            if (!visited[next]) {
                min = Math.min(min, dfs(next, graph, visited));
            }
        }

        return min;
    }
}