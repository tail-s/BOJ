import java.util.Scanner;

public class Main {
    static int N, K;
    static int[] arr;
    static int[] d;
    static StringBuilder sb = new StringBuilder();

    static void shuffle() {
        int[] tmp = new int[N];
        while (K-- > 0) {
            for (int i = 0; i < N; i++) tmp[d[i] - 1] = arr[i];
            for (int i = 0; i < N; i++) arr[i] = tmp[i];
        }
        for (int i : arr) sb.append(i + " ");
        sb.setLength(sb.length() - 1);
        System.out.println(sb);

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        arr = new int[N];
        d = new int[N];
        for (int i = 0; i < N; i++) arr[i] = sc.nextInt();
        for (int i = 0; i < N; i++) d[i] = sc.nextInt();
        sc.close();

        shuffle();

    }
}