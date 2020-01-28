class MapExample {
    public static void main (String[] args) {
        Map<String,Integer> m1 = new BSTMap<String,Integer>();    
        
        m1.put("foo", 7);
        try {
            int x = m1.get("abc");
            System.out.println(x);
        } catch (KeyNotFoundException e) {
            System.out.println("abc not found in map");        
        }
        
        try {
            int x = m1.get("foo");
            System.out.println(x);
        } catch (KeyNotFoundException e) {
            System.out.println("foo not found in map");                
        }

    }
}

