import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K;
    static long ans = 0;
    static boolean[] visited = new boolean[200200002];
    static Queue<Integer> q = new ArrayDeque<>();

    static void bfs() {
        int len = 1;

        build:
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int pos = q.poll();

                if (!visited[pos - 1]) {
                    q.offer(pos - 1);
                    visited[pos - 1] = true;
                    ans += len;
                    if (--K == 0) break build;
                }

                if (!visited[pos + 1]) {
                    q.offer(pos + 1);
                    visited[pos + 1] = true;
                    ans += len;
                    if (--K == 0) break build;
                }
            }
            len++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);

        StringTokenizer st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            int cur = Integer.parseInt(st.nextToken()) + 100100000;
            q.offer(cur);
            visited[cur] = true;
        }

        bfs();

        System.out.println(ans);



    }
}