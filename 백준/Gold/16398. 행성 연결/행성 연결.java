import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Node implements Comparable<Node> {
        int no, weight;
        Node next;

        public Node(int no, int weight) {
            this.no = no;
            this.weight = weight;
        }

        public Node(int no, int weight, Node next) {
            this.no = no;
            this.weight = weight;
            this.next = next;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        Queue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0));
        boolean[] visited = new boolean[n];
        int[] min = new int[n];
        Arrays.fill(min, Integer.MAX_VALUE);
        min[0] = 0;

        int cnt = 0;
        long ans = 0;

        while(!pq.isEmpty()) {
            Node now = pq.poll();
            if(visited[now.no])
                continue;
            visited[now.no] = true;
            ans += now.weight;
            if(++cnt == n)
                break;

            for(int i=0; i<n; i++) {
                if(map[now.no][i] != 0 && !visited[i] && min[i] > map[now.no][i]) {
                    min[i] = map[now.no][i];
                    pq.offer(new Node(i, map[now.no][i]));
                }
            }
        }

        bw.write(ans + "");
        bw.flush();
        bw.close();
        br.close();

    }
}