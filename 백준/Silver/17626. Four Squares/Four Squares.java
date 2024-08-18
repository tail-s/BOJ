import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.close();

        int[] arr = new int[n + 1];
        arr[1] = 1;

        int min = 0;
        for (int i=2; i<=n; i++) {
            min = Integer.MAX_VALUE;

            for (int j=1; j*j <= i; j++) min = Math.min(min, arr[i - j * j]);

            arr[i] = min + 1;
        }

        System.out.println(arr[n]);



    }
}