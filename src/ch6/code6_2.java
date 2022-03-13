package ch6;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class code6_2 {

    public static void main(String[] args) {
        pick(7, new ArrayList<Integer>(), 4);
    }

    public static void pick(int n, List<Integer> picked, int toPick) {
        if (toPick == 0) {
            System.out.println(Arrays.toString(picked.stream().toArray()));
            return;
        }

        int smallest = picked.isEmpty() ? 0 : (picked.get(picked.size()-1) + 1);

        for (int next = smallest; next < n; next++) {
            picked.add(next);
            pick(n, picked, toPick-1);
            picked.remove(picked.size() - 1);
        }
    }
}
