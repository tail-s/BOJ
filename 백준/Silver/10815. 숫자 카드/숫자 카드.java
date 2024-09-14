import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

//        int N = sc.nextInt();
//        HashSet<Integer> target = new HashSet<Integer>();
//        while (N-- > 0) target.add(sc.nextInt());
//        int M = sc.nextInt();
//        while (M-- > 0) sb.append(target.contains(sc.nextInt()) ? "1 " : "0 ");
//        sb.setLength(sb.length()-1);
//        sc.close();
//        System.out.println(sb.toString());

        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = sc.nextInt();
        Arrays.sort(arr);
        int M = sc.nextInt();
        while (M-- > 0) {
            int target = sc.nextInt();
            int s = 0;
            int e = N - 1;
            while (s <= e) {
                int mid = (s + e) / 2;
                int val = arr[mid];
                if (val == target) {
                    sb.append("1 ");
                    break;
                }

                if (val > target) e = mid - 1;
                else s = mid + 1;

                if (s > e) {
                    sb.append("0 ");
                    break;
                }
            }
        }
        System.out.println(sb.toString());
    }
}