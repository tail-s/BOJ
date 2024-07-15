import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int T, N, Max, Min;
        int[] arr;
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();
        while (T-- > 0) {
            N = sc.nextInt();
            arr = new int[N];
            Max = Integer.MIN_VALUE;
            Min = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                arr[i] = sc.nextInt();
                if (arr[i] > Max) {
                    Max = arr[i];
                }
                if (arr[i] < Min) {
                    Min = arr[i];
                }
            }
            sb.append(Min + " " + Max + "\n");
        }
        sc.close();
        System.out.println(sb);

    }
}