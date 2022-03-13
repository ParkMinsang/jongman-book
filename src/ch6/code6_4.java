package ch6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class code6_4 {

    static int n, m;
    static boolean[][] isFriend = new boolean[10][10];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < TC; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            for (int i = 0; i < n; i++) {
                Arrays.fill(isFriend[i], false);
            }

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < m; i++) {
                int member1 = Integer.parseInt(st.nextToken());
                int member2 = Integer.parseInt(st.nextToken());

                isFriend[member1][member2] = true;
                isFriend[member2][member1] = true;
            }

            boolean[] isPaired = new boolean[n];
            Arrays.fill(isPaired, false);

            System.out.println(getParings(isPaired));
        }
    }

    public static int getParings(boolean[] isPaired) {
        boolean isFinished = true;
        for (int i = 0; i < n; i++) {
            if (!isPaired[i]) {
                isFinished = false;
            }
        }

        if (isFinished) {
            return 1;
        }

        int ret = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!isPaired[i] && !isPaired[j] && isFriend(i, j)) {
                    isPaired[i] = isPaired[j] = true;
                    ret += getParings(isPaired);
                    isPaired[i] = isPaired[j] = false;
                }

            }
        }

        return ret;
    }

    public static boolean isFriend(int member1, int member2) {
        return isFriend[member1][member2];
    }
}
