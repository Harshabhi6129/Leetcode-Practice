class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0)
            return result;

        int n = s.length();
        int w = words[0].length(); // word length
        int k = words.length;      // number of words
        int total = w * k;         // total window size

        if (n < total) return result;

        // Build frequency map for words
        Map<String, Integer> wordFreq = new HashMap<>();
        for (String word : words)
            wordFreq.merge(word, 1, Integer::sum);

        // Run one sliding window per offset (0..w-1)
        for (int offset = 0; offset < w; offset++) {
            Map<String, Integer> windowFreq = new HashMap<>();
            int left = offset;   // left boundary of current window
            int count = 0;       // number of valid words in window

            for (int right = offset; right + w <= n; right += w) {
                String word = s.substring(right, right + w);

                if (wordFreq.containsKey(word)) {
                    // Add word to window
                    windowFreq.merge(word, 1, Integer::sum);
                    count++;

                    // Shrink window from left if this word is over-represented
                    while (windowFreq.get(word) > wordFreq.get(word)) {
                        String leftWord = s.substring(left, left + w);
                        windowFreq.merge(leftWord, -1, Integer::sum);
                        count--;
                        left += w;
                    }

                    // Valid window found
                    if (count == k) {
                        result.add(left);
                        // Slide window: remove leftmost word
                        String leftWord = s.substring(left, left + w);
                        windowFreq.merge(leftWord, -1, Integer::sum);
                        count--;
                        left += w;
                    }

                } else {
                    // Invalid word — reset window entirely
                    windowFreq.clear();
                    count = 0;
                    left = right + w;
                }
            }
        }

        return result;
    }
}