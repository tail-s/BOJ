import java.io.*;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Queue<Integer> q = new PriorityQueue<>();
        int ans = 0, a, b;

        int n = Integer.parseInt(br.readLine());
        for(int i=0; i<n; i++) {
            q.offer(Integer.parseInt(br.readLine()));
        }

        while(q.size() > 1) {
            a = q.poll();
            b = q.poll();
            ans += a + b;
            q.offer(a + b);
        }

        bw.write(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}