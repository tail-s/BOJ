import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] value = new int[N];
        int ans = 0;

        for(int i=N-1; i>=0; i--) {
            value[i] = Integer.parseInt(br.readLine());
        }

        for(int X : value) {
            if(X <= K) {
                ans += K/X;
                K %= X;
            }
        }

        bw.write(ans + "");
        bw.flush();
        bw.close();
        br.close();
    }
}