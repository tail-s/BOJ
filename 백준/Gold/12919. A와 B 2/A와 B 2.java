import java.util.Scanner;

public class Main {
    static String S, T;
    static StringBuilder sb;
    static void solve() {
        if (sb.length() == S.length()) {
            if (sb.toString().equals(S)) {
                System.out.println(1);
                System.exit(0);
            }
            return;
        }

        if (sb.charAt(sb.length() - 1) == 'A') {
            sb.setLength(sb.length() - 1);
            solve();
            sb.append('A');
        }

        if (sb.charAt(0) == 'B') {
            sb.reverse();
            sb.setLength(sb.length() - 1);
            solve();
            sb.append('B');
            sb.reverse();
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        S = sc.next();
        T = sc.next();
        sc.close();

        sb = new StringBuilder(T);
        solve();
        System.out.println(0);


    }
}