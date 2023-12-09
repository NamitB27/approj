package com.example.project;

import java.util.HashMap;
import java.util.Map;
//flyweight is being used here...

public class wallDistance {
    public int one_block_distance;
    public int getOne_block_height;
    private static final Map<Integer, wallDistance> wal = new HashMap<Integer, wallDistance>();

    private wallDistance() {
    }

    public wallDistance(Integer a) {
    }

    public static wallDistance getInstance(Integer a) {
        if (wal.containsKey(a)) {
            wal.put(a,new wallDistance(a));

        }
        return null;
    }
}
