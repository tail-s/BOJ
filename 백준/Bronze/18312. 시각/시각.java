import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        sc.close();

        int ans = 0;

        StringBuilder sb = new StringBuilder();
        for (int i=0; i<=N; i++) {
            for (int j=0; j<=59; j++) {
                for (int k=0; k<=59; k++) {
                    sb.setLength(0);
                    sb.append(i < 10 ? "0" + i : i).append(j < 10 ? "0" + j : j).append(k < 10 ? "0" + k : k);
                    ans += sb.toString().contains(String.valueOf(K)) ? 1 : 0;
                }
            }
        }

        System.out.println(ans);

    }
}