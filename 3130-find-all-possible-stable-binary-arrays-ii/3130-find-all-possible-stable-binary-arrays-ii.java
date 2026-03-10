class Solution {

    private static final int MOD = 1_000_000_007;

    public int numberOfStableArrays(int zero, int one, int limit) {

        long[][] endZero = new long[one + 1][zero + 1];
        long[][] endOne  = new long[one + 1][zero + 1];

        for (int o = 1; o <= Math.min(one, limit); o++) {
            endOne[o][0] = 1;
        }

        for (int z = 1; z <= Math.min(zero, limit); z++) {
            endZero[0][z] = 1;
        }

        for (int o = 1; o <= one; o++) {
            for (int z = 1; z <= zero; z++) {
                long waysZero = (endZero[o][z - 1] + endOne[o][z - 1]) % MOD;

                if (z - limit - 1 >= 0) {
                    waysZero = (waysZero - endOne[o][z - limit - 1] + MOD) % MOD;
                }

                endZero[o][z] = waysZero;
                long waysOne = (endOne[o - 1][z] + endZero[o - 1][z]) % MOD;

                if (o - limit - 1 >= 0) {
                    waysOne = (waysOne - endZero[o - limit - 1][z] + MOD) % MOD;
                }

                endOne[o][z] = waysOne;
            }
        }

        return (int)((endZero[one][zero] + endOne[one][zero]) % MOD);
    }
}