import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] waiting = new int[n];
        int[] counting = new int[1001];

        int ans = 0;
        int accum = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0; i<n; i++) {
            waiting[i] = Integer.parseInt(st.nextToken());
            counting[waiting[i]]++;
        }

        for(int i=0; i<1001; i++) {
            while(counting[i] > 0) {
                counting[i]--;
                ans += i + accum;
                accum += i;
            }
        }

        bw.write(ans + "");
        bw.flush();
        bw.close();
        br.close();
    }
}