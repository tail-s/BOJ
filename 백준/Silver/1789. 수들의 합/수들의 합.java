import java.util.Scanner;

public class Main {

    static long S, ans;
    static long _sum(long N) { return (1 + N) * N / 2; }

    static void binarySearch(long s, long e) {

        if (s > e) return;
        long mid = (s + e) / 2;
        long sum = _sum(mid);

        if (sum > S) binarySearch(s, mid - 1);
        else if (sum < S) {
            ans = Math.max(ans, mid);
            binarySearch(mid + 1, e);
        }
        else ans = mid;


//        while (s <= e) {
//            long mid = (s + e) / 2;
//            long sum = _sum(mid);
//
//            if (sum <= S) {
//                ans = mid;
//                s = mid + 1;
//            } else e = mid - 1;
//        }


    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        S = sc.nextLong();
        sc.close();
        ans = 0L;

//        for (int i = 1; sum <= S; i++) {
//            sum += i;
//            ans++;
//        }
//        ans--;
//        System.out.println(ans);

        binarySearch(1, S);
        System.out.println(ans);



    }
}