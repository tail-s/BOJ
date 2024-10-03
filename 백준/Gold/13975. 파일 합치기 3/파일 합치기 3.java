import java.io.*;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        Queue<Long> q;

        int t = Integer.parseInt(br.readLine());
        for(int i=0; i<t; i++) {
            long ans = 0;
            q = new PriorityQueue<>();

            int k = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            while(st.hasMoreTokens()) {
                q.offer(Long.parseLong(st.nextToken()));
            }
            while(q.size() > 1) {
                long a = q.poll();
                long b = q.poll();
                ans += a + b;
                q.offer(a + b);
            }
            bw.write(ans + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}