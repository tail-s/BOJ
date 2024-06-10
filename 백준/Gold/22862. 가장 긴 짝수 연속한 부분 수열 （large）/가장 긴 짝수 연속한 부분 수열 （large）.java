import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int[] S = new int[N];
        for (int i = 0; i < N; i++) { S[i] = sc.nextInt(); }
        sc.close();

        int s = 0, e = 0, del = 0, cnt = 0, ans = 0;
        while (e < N) {
            if (S[e] % 2 != 0) {
                if (del < K) {
                    del++;
                    e++;
                } else {
                    while (del == K) {
                        if (S[s] % 2 != 0) del--;
                        s++;
                    }
                }
            } else e++;
            ans = Math.max(ans, e - s - del);
        }
        System.out.println(ans);

        
    }
}