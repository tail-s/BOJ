import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static Map<Integer, int[]> map = new HashMap<>();
    static int[][] table = new int[5][5];
    static boolean[][] called = new boolean[5][5];
    static int bingo(int a) {
        int bcnt = 0;
        int[] arr = map.get(a);
        called[arr[0]][arr[1]] = true;
        int l1 = 0, l2 = 0;
        for (int i = 0; i < 5; i++) {
            int cnt = 0;
            for (int j = 0; j < 5; j++) {
                if (!called[i][j]) break;
                cnt++;
            }
            if (cnt == 5) bcnt++;

            cnt = 0;
            for (int j = 0; j < 5; j++) {
                if (!called[j][i]) break;
                cnt++;
            }
            if (cnt == 5) bcnt++;

            if (called[i][i]) l1++;
            if (called[i][4 - i]) l2++;
        }
        if (l1 == 5) bcnt++;
        if (l2 == 5) bcnt++;

        return bcnt;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                int input = sc.nextInt();
                table[i][j] = input;
                map.put(input, new int[] {i, j});
            }
        }

        int n = 0;
        while (n++ < 25) {
            if (bingo(sc.nextInt()) >= 3) break;
        }
        System.out.println(n);

    }
}