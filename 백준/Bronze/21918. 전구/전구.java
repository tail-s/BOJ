import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N, M, order, l, r;
        boolean[] arr;
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        arr =  new boolean[N];
        for (int i = 0; i < N; i++) { arr[i] = sc.nextInt() == 1; }

        while (M-- > 0) {
            order = sc.nextInt();
            l = sc.nextInt() - 1;
            r = sc.nextInt() - 1;

            switch (order) {
                case 1:
                    arr[l] = r + 1 == 1;
                    break;
                case 2:
                    for (int i = l; i <= r; i++) { arr[i] = !arr[i]; }
                    break;
                case 3:
                    for (int i = l; i <= r; i++) { arr[i] = false; }
                    break;
                default:
                    for (int i = l; i <= r; i++) { arr[i] = true; }
            }
        }
        sc.close();

        for (boolean flag : arr) sb.append(flag ? 1 + " " : 0 + " ");
        sb.setLength(sb.length() - 1);
        System.out.println(sb);

    }
}