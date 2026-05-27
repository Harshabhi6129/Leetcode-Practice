import java.util.*;

class Solution {

    public int minMutation(String startGene, String endGene, String[] bank) {

        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));

        // End gene must exist in bank
        if (!bankSet.contains(endGene)) {
            return -1;
        }

        char[] genes = {'A', 'C', 'G', 'T'};

        Queue<String> queue = new LinkedList<>();
        queue.offer(startGene);

        Set<String> visited = new HashSet<>();
        visited.add(startGene);

        int mutations = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {

                String current = queue.poll();

                // Reached target
                if (current.equals(endGene)) {
                    return mutations;
                }

                char[] arr = current.toCharArray();

                // Try changing every position
                for (int j = 0; j < 8; j++) {

                    char original = arr[j];

                    for (char ch : genes) {

                        arr[j] = ch;

                        String next = new String(arr);

                        // Valid mutation
                        if (bankSet.contains(next) &&
                            !visited.contains(next)) {

                            visited.add(next);
                            queue.offer(next);
                        }
                    }

                    arr[j] = original; // Backtrack
                }
            }

            mutations++;
        }

        return -1;
    }
}