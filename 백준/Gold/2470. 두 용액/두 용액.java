import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) { arr[i] = sc.nextInt(); }
        sc.close();

        Arrays.sort(arr);

        int s = 0, e = arr.length - 1, closesum = Integer.MAX_VALUE, ans1 = 0, ans2 = 0;
        while (s != e) {

            int sum = arr[s] + arr[e];

            if (Math.abs(sum) < Math.abs(closesum)) {
                closesum = sum;
                ans1 = arr[s];
                ans2 = arr[e];
            }

            if (sum == 0) {
                break;
            } else if (sum < 0) {
                s++;
            } else {
                e--;
            }
        }
        System.out.println(ans1 + " " + ans2);
    }
}