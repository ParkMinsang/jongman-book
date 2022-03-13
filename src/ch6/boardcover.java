package ch6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boardcover {

    public static int[][][] coverType= new int[][][]{
        { {0, 0}, {0, 1}, {1, 0} },
        { {0, 0}, {0, 1}, {1, 1} },
        { {0, 0}, {1, 0}, {1, 1} },
        { {0, 0}, {1, 0}, {1, -1} }
    };

    static int h, w;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine().trim());

        for (int tc = 0; tc < TC; tc++) {
            setBoard(br);
            System.out.println(countCoverNumberOfCase());
        }
    }

    public static int countCoverNumberOfCase() {
        int firstR = -1, firstC = -1;

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (board[i][j] == 0) {
                    firstR = i;
                    firstC = j;
                    break;
                }
            }
            if (firstR != -1) {
                break;
            }
        }

        if (firstR == -1) {
            return 1;
        }

        int ret = 0;

        for (int type = 0; type < 4; type++) {
            if (cover(firstR, firstC, type, 1)) {
                ret += countCoverNumberOfCase();
                System.out.println("cover success");
            }
            cover(firstR, firstC, type, -1);
        }

        return ret;
    }

    public static boolean cover(int r, int c, int type, int delta) {
        boolean ret = true;

        for (int i = 0; i < 3; i++) {
            final int nextR = r + coverType[type][i][0];
            final int nextC = c + coverType[type][i][1];

            if (!isInRange(nextR, nextC)) {
                ret = false;
                continue;
            }

            board[r][c] += delta;
            if ((board[r][c]) > 1) {
                ret = false;
            }
        }

        return ret;
    }

    public static boolean isInRange(int r, int c) {
        if (r < 0 || r >= h) {
            return false;
        }

        if (c < 0 || c >= w) {
            return false;
        }

        return true;
    }


    public static void setBoard(BufferedReader br) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        board = new int[h][w];

        for (int i = 0; i < h; i++) {
            String s = br.readLine().trim();
            for (int j = 0; j < w; j++) {
                if (s.charAt(j) == '#') {
                    board[i][j] = 1;
                } else {
                    board[i][j] = 0;
                }
            }
        }
    }
}
