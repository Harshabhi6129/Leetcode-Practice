class Solution {
    public boolean canBeEqual(String s1, String s2) {
        // check group {0,2}
        char[] g1_s1 = {s1.charAt(0), s1.charAt(2)};
        char[] g1_s2 = {s2.charAt(0), s2.charAt(2)};
        
        // check group {1,3}
        char[] g2_s1 = {s1.charAt(1), s1.charAt(3)};
        char[] g2_s2 = {s2.charAt(1), s2.charAt(3)};
        
        Arrays.sort(g1_s1);
        Arrays.sort(g1_s2);
        Arrays.sort(g2_s1);
        Arrays.sort(g2_s2);
        
        return Arrays.equals(g1_s1, g1_s2) &&
               Arrays.equals(g2_s1, g2_s2);
    }
}