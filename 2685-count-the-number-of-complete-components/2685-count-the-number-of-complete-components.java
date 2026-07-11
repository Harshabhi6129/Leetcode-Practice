class Solution {

    public int countCompleteComponents(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        int complete = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int[] info = new int[2]; // [nodes, degreeSum]
                dfs(i, graph, visited, info);

                int nodes = info[0];
                int edgesCount = info[1] / 2;

                if (edgesCount == nodes * (nodes - 1) / 2) {
                    complete++;
                }
            }
        }

        return complete;
    }

    private void dfs(int node, List<List<Integer>> graph,
                     boolean[] visited, int[] info) {

        visited[node] = true;
        info[0]++; // number of nodes
        info[1] += graph.get(node).size(); // degree

        for (int next : graph.get(node)) {
            if (!visited[next]) {
                dfs(next, graph, visited, info);
            }
        }
    }
}