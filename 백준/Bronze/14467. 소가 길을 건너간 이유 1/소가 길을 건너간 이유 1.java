import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N, num, pos, ans = 0;
        int[] arr;
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        arr = new int[N];
        Arrays.fill(arr, 2);

        while (N-- > 0) {
            num = sc.nextInt() - 1;
            pos = sc.nextInt();
            if (arr[num] == 2) arr[num] = pos;
            else if (arr[num] != pos) {
                arr[num] = pos;
                ans++;
            }
        }
        sc.close();

        System.out.println(ans);

    }
}