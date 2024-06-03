import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] arr1 = new int[N];
        int[] arr2 = new int[M];
        for (int i = 0; i < N; i++) { arr1[i] = sc.nextInt(); }
        for (int i = 0; i < M; i++) { arr2[i] = sc.nextInt(); }
        sc.close();

        StringBuilder sb = new StringBuilder();

        int cur1 = 0, cur2 = 0;
        while (cur1 != N && cur2 != M) {
            if (arr1[cur1] < arr2[cur2]) {
                sb.append(arr1[cur1] + " ");
                cur1++;
            } else if (arr1[cur1] > arr2[cur2]) {
                sb.append(arr2[cur2] + " ");
                cur2++;
            } else {
                sb.append(arr1[cur1] + " ").append(arr2[cur2] + " ");
                cur1++;
                cur2++;
            }
        }

        while (cur1 < N) {
            sb.append(arr1[cur1++] + " ");
        }

        while (cur2 < M) {
            sb.append(arr2[cur2++] + " ");
        }

        System.out.println(sb.toString());

    }
}