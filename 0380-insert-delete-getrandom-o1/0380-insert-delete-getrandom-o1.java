class RandomizedSet {
    private Map<Integer, Integer> map;  // val -> index in list
    private List<Integer> list;
    private Random rand;

    public RandomizedSet() {
        map  = new HashMap<>();
        list = new ArrayList<>();
        rand = new Random();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) return false;
        map.put(val, list.size());
        list.add(val);
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;

        int idx  = map.get(val);       // index of val to remove
        int last = list.get(list.size() - 1);  // last element

        // Swap val with last element
        list.set(idx, last);
        map.put(last, idx);

        // Remove last slot
        list.remove(list.size() - 1);
        map.remove(val);
        return true;
    }

    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }
}
