package ch6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boggle {

    static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1}, dc = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int C = Integer.parseInt(br.readLine());
        char[][] board = new char[5][5];
        StringBuilder sb = new StringBuilder();

        for(int tc = 0; tc < C; tc++) {
            for (int i = 0; i < 5; i++) {
                String s = br.readLine();
                for (int j = 0; j < 5; j++) {
                    board[i][j] = s.charAt(j);
                }
            }

            int n = Integer.parseInt(br.readLine());
            for (int i = 0; i < n; i++) {
                String s = br.readLine();

                boolean hasWord = false;
                for (int r = 0; r < 5; r++) {
                    for (int c = 0; c < 5; c++) {
                        if (hasWord(board, r, c, s, 0)) {
                            hasWord = true;
                            break;
                        }
                    }
                    if (hasWord) {
                        break;
                    }
                }
                if (hasWord) {
                    sb.append(s).append(' ').append("YES").append('\n');
                } else {
                    sb.append(s).append(' ').append("NO").append('\n');
                }
            }
        }
        System.out.println(sb);
    }

    public static boolean hasWord(char[][] board, int r, int c, String s, int idx) {
        if (idx == s.length()) {
            return true;
        }
        if (board[r][c] != s.charAt(idx)) {
            return false;
        }

        for (int d = 0; d < 8; d++) {
            int nextR = r + dr[d];
            int nextC = c + dc[d];

            if (!isInRange(nextR, nextC)) {
                continue;
            }

            if (hasWord(board, nextR, nextC, s, idx + 1)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isInRange(int r, int c) {
        if (r < 0 || r >= 5) return false;
        if (c < 0 || c >= 5) return false;
        return true;
    }
}
