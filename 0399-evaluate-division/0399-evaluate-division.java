class Solution {

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        // Graph: variable -> neighbors with weights
        Map<String, Map<String, Double>> graph = new HashMap<>();

        // Build graph
        for (int i = 0; i < equations.size(); i++) {

            String a = equations.get(i).get(0);
            String b = equations.get(i).get(1);
            double val = values[i];

            graph.putIfAbsent(a, new HashMap<>());
            graph.putIfAbsent(b, new HashMap<>());

            graph.get(a).put(b, val);
            graph.get(b).put(a, 1.0 / val);
        }

        double[] ans = new double[queries.size()];

        // Process queries
        for (int i = 0; i < queries.size(); i++) {

            String src = queries.get(i).get(0);
            String dest = queries.get(i).get(1);

            // Variable not present
            if (!graph.containsKey(src) || !graph.containsKey(dest)) {
                ans[i] = -1.0;
            } else if (src.equals(dest)) {
                ans[i] = 1.0;
            } else {
                Set<String> visited = new HashSet<>();
                ans[i] = dfs(src, dest, 1.0, visited, graph);
            }
        }

        return ans;
    }

    private double dfs(String curr, String target, double product,
                       Set<String> visited,
                       Map<String, Map<String, Double>> graph) {

        visited.add(curr);

        // Found target
        if (curr.equals(target)) {
            return product;
        }

        for (String neighbor : graph.get(curr).keySet()) {

            if (!visited.contains(neighbor)) {

                double weight = graph.get(curr).get(neighbor);

                double result = dfs(neighbor, target,
                                    product * weight,
                                    visited, graph);

                if (result != -1.0) {
                    return result;
                }
            }
        }

        return -1.0;
    }
}