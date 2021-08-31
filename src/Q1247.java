import java.io.FileInputStream;
import java.util.Scanner;

/**
 * 1247. [S/W 문제해결 응용] 3일차 - 최적 경로
 */

public class Q1247 {

    private static int N; // 고객의 수
    private static int[][] map;
    private static boolean[] visited;
    private static int[][] distance;
    private static int answer;
    private static StringBuilder sb = new StringBuilder();

    private static int getDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    private static void dfs(int from, int count, int dist) {
        if (from == 1) {
            if (count == N + 2) {
                if (dist < answer) answer = dist;
            } else return;
        }
        for (int i = 0; i < N + 2; i++) {
            if (!visited[i] && distance[from][i] != 0 && dist + distance[from][i] < answer) {
                visited[i] = true;
                dfs(i, count + 1, dist + distance[from][i]);
                visited[i] = false;
            }
        }
    }

    private static void input() throws Exception {
        System.setIn(new FileInputStream("src/res/input1247.txt"));
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            N = sc.nextInt();
            map = new int[N + 2][2];
            distance = new int[N + 2][N + 2];
            visited = new boolean[N + 2];

            for (int i = 0; i < N + 2; i++) {
                map[i][0] = sc.nextInt();
                map[i][1] = sc.nextInt();
            }

            // i 부터 j 까지 가는 거리 저장하는 배열
            for (int i = 0; i < N + 2; i++) {
                for (int j = 0; j < N + 2; j++) distance[i][j] = getDistance(map[i][0], map[i][1], map[j][0], map[j][1]);
            }

            answer = Integer.MAX_VALUE;
            visited[0] = true;
            dfs(0, 1, 0);

            sb.append("#").append(test_case).append(" ").append(answer).append("\n");
        }
    }

    public static void main(String args[]) throws Exception {
        input();
        System.out.println(sb.toString());
    }
}