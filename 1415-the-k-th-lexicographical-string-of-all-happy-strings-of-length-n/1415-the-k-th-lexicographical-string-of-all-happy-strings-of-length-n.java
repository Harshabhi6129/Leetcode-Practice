class Solution {

    int count = 0;
    String result = "";

    public String getHappyString(int n, int k) {
        dfs(n, k, new StringBuilder());
        return result;
    }

    private void dfs(int n, int k, StringBuilder sb) {

        if (sb.length() == n) {
            count++;

            if (count == k) {
                result = sb.toString();
            }
            return;
        }

        for (char c : new char[]{'a','b','c'}) {

            if (sb.length() > 0 && sb.charAt(sb.length()-1) == c)
                continue;

            sb.append(c);

            dfs(n, k, sb);

            if (!result.equals("")) return;

            sb.deleteCharAt(sb.length()-1);
        }
    }
}