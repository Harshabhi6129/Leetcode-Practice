class Solution {
    public char processStr(String s, long k) {
        int n = s.length();

        long[] len = new long[n + 1];

        // Forward pass: compute lengths
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            if (ch >= 'a' && ch <= 'z') {
                len[i + 1] = len[i] + 1;
            } else if (ch == '*') {
                len[i + 1] = Math.max(0L, len[i] - 1);
            } else if (ch == '#') {
                len[i + 1] = len[i] * 2;
            } else { // '%'
                len[i + 1] = len[i];
            }
        }

        if (k >= len[n]) {
            return '.';
        }

        // Backtrack from final state to find kth character
        for (int i = n - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            long prevLen = len[i];
            long curLen = len[i + 1];

            if (ch >= 'a' && ch <= 'z') {
                if (k == curLen - 1) {
                    return ch;
                }
                // Character came from previous string
            } else if (ch == '*') {
                // Deleted last character; surviving positions are unchanged
            } else if (ch == '#') {
                if (k >= prevLen) {
                    k -= prevLen;
                }
            } else { // '%'
                k = prevLen - 1 - k;
            }
        }

        return '.';
    }
}