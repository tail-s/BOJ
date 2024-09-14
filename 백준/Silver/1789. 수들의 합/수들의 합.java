import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long S = sc.nextLong();
        sc.close();

        Long sum = 0L;
        int ans = 0;

        for (int i = 1; sum <= S; i++) {
            sum += i;
            ans++;
        }
        ans--;
        System.out.println(ans);

    }
}