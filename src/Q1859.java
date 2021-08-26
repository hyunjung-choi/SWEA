import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1859. 백만 장자 프로젝트
 */

public class Q1859 {
    private static int T;
    private static int N;
    private static int[] salePrice; // 매매가
    private static long buy, count, profit;
    private static int maxDay;
    private static StringBuilder answer = new StringBuilder();

    // 최대값이 나오는 날
    private static void getMaxDay() {
        maxDay = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            if (max < salePrice[i]) {
                max = salePrice[i];
                maxDay = i;
            }
        }
    }

    private static void buyOrSell() {
        getMaxDay();
        for (int i = 0; i < N - 1; i++) {
            // 최대값인 날이면 무조건 판다.
            if (i == maxDay) {
                profit += (salePrice[i] * count) - buy;
                buy = 0; count = 0;
                salePrice[i] = 0;
                getMaxDay(); // 이후 최대값인 날 구하기
                continue;
            }

            // 최대값 전 까지는 무조건 산다.
            buy += salePrice[i];
            count += 1;
            salePrice[i] = 0;
        }

        // 마지막 날 처리
        if (salePrice[N - 2] < salePrice[N - 1]) {
            profit += (salePrice[N - 1] * count) - buy;
            buy = 0; count = 0;
        }
    }

    private static void input() throws Exception {
        System.setIn(new FileInputStream("src/res/input1859.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine()); // 테스트 케이스 수
        for (int test_case = 1; test_case <= T; test_case++) {
            buy = 0; count = 0; profit = 0;
            N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            salePrice = new int[N];
            for (int i = 0; i < N; i++) {
                salePrice[i] = Integer.parseInt(st.nextToken());
            }
            buyOrSell();
            answer.append("#").append(test_case).append(" ").append(profit).append("\n");
        }
        br.close();
    }

    public static void main(String[] args) throws Exception {
        input();
        System.out.println(answer.toString());
    }
}
