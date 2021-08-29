import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * 2805. 농작물 수확하기
 */

public class Q2805 {

    private static int T;
    private static int N;
    private static int[][] farm;
    private static int mid;
    private static int profit;
    private static StringBuilder sb = new StringBuilder();

    private static void getProfit() {
        int gap = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (j >= mid - gap && j <= mid + gap) profit += farm[i][j];
            }
            if (i >= mid) gap--;
            else gap++;
        }
    }

    private static void input() throws Exception {
        System.setIn(new FileInputStream("src/res/input2805.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            profit = 0;
            N = Integer.parseInt(br.readLine());
            mid = N / 2;
            farm = new int[N][N];
            for (int i = 0; i < N; i++) {
                String[] strs = br.readLine().split("");
                for (int j = 0; j < N; j++) {
                    farm[i][j] = Integer.parseInt(strs[j]);
                }
            }
            getProfit();
            sb.append("#").append(test_case).append(" ").append(profit).append("\n");
        }
        br.close();
    }

    public static void main(String[] args) throws Exception {
        input();
        System.out.println(sb.toString());
    }
}
