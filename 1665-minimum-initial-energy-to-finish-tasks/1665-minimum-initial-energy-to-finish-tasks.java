class Solution {
    public int minimumEffort(int[][] tasks) {

        // Sort by (minimum - actual) descending
        Arrays.sort(tasks, (a, b) -> {
            return (b[1] - b[0]) - (a[1] - a[0]);
        });

        int energy = 0;
        int current = 0;

        for (int[] task : tasks) {

            int actual = task[0];
            int minimum = task[1];

            // Increase initial energy if current energy is insufficient
            if (current < minimum) {
                energy += (minimum - current);
                current = minimum;
            }

            current -= actual;
        }

        return energy;
    }
}