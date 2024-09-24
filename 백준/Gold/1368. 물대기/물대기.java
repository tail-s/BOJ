import java.io.*;
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

        int V = Integer.parseInt(br.readLine());
        int[] min = new int[V];
        int minvalue = Integer.MAX_VALUE;
        int minidx = 0;
        for(int i=0; i<V; i++) {
            min[i] = Integer.parseInt(br.readLine());
            if(minvalue > min[i]) {
                minvalue = min[i];
                minidx = i;
            }
        }

        int[][] map = new int[V][V];
        for(int i=0; i<V; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<V; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                map[i][j] = tmp > min[j] ? min[j] : tmp;
            }
        }

        Queue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(minidx, minvalue));
        boolean[] visited = new boolean[V];
        Arrays.fill(min, Integer.MAX_VALUE);
        min[minidx] = minvalue;

        int cnt = 0;
        long ans = 0;

        while(!pq.isEmpty()) {
            Node n = pq.poll();
            if(visited[n.no])   continue;
            visited[n.no] = true;
            ans += n.weight;
            if(++cnt == V)  break;

            for(int i=0; i<V; i++)
                if(map[n.no][i] != 0 && !visited[i] && min[i] > map[n.no][i]) {
                    min[i] = map[n.no][i];
                    pq.offer(new Node(i, map[n.no][i]));
                }
        }
        bw.write(ans + "");
        bw.flush();
        bw.close();
        br.close();

    }
}