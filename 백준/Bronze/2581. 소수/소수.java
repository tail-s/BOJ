import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt();
        int N = sc.nextInt();
        sc.close();

        boolean[] arr = new boolean[10001];
        Arrays.fill(arr, true);
        arr[0] = false;
        arr[1] = false;
        for (int i=2; i*i<=N; i++) if (arr[i]) for (int j=i*i; j<=N; j+=i) arr[j] = false;

        int sum = 0, min = -1;
        for (int i=N; i>=M; i--) {
            if (arr[i]) {
                sum += i;
                min = i;
            }
        }
        System.out.println(min == -1 ? -1 : sum + "\n" + min);
    }
}