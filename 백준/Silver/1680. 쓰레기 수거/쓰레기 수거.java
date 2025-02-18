import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int W = sc.nextInt();
            int N = sc.nextInt();
            int[][] arr = new int[N][2];
            for (int i = 0; i < N; i++) {
                arr[i][0] = sc.nextInt();
                arr[i][1] = sc.nextInt();
            }

            int len = 0, cap = 0, pos = 0;
            for (int i = 0; i < N; i++) {
                len += arr[i][0] - pos;
                pos = arr[i][0];
                if (cap + arr[i][1] > W) {
                    len += pos * 2;
                    cap = arr[i][1];
                } else if (cap + arr[i][1] == W) {
                    len += pos;
                    pos = 0;
                    cap = 0;
                } else cap += arr[i][1];
            }
            len += pos;
            sb.append(len).append("\n");
        }
        System.out.println(sb);
    }
}