import java.util.Scanner;

public class Main {
    static int n;
    static boolean[] arr;
    static boolean in(int idx, int i) { return idx - i > 0 && idx + i <= n; }
    static void execute(int sex, int num) {
        if (sex == 1) {
            for (int i = 1; i <= n; i++) if (i % num == 0) arr[i] = !arr[i];
        } else {
            arr[num] = !arr[num];
            for (int i = 1; in(num, i); i++) {
                if (arr[num - i] == arr[num + i]) {
                    arr[num - i] = !arr[num - i];
                    arr[num + i] = !arr[num + i];
                } else break;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        n = sc.nextInt();
        arr = new boolean[n + 1];
        for (int i = 1; i <= n; i++) { arr[i] = sc.nextInt() == 1; }
        int cnt = sc.nextInt();
        while (cnt-- > 0) execute(sc.nextInt(), sc.nextInt());
        sc.close();

        cnt = 0;
        for (int i = 1; i <= n; i++) {
            sb.append(arr[i] ? "1 " : "0 ");
            if (i % 20 == 0) sb.append("\n");
        }
        sb.setLength(sb.length() - 1);
        System.out.println(sb);

    }
}