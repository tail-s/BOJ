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

        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        Node[] nodes = new Node[V];

        int from, to, weight, cnt = 0;
        long ans = 0;

        while(E-- > 0) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken()) - 1;
            to = Integer.parseInt(st.nextToken()) - 1;
            weight = Integer.parseInt(st.nextToken());
            nodes[from] = new Node(to, weight, nodes[from]);
            nodes[to] = new Node(from , weight, nodes[to]);
        }

        Queue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0));
        boolean[] visited = new boolean[V];
        long[] min = new long[V];
        Arrays.fill(min, Long.MAX_VALUE);
        min[0] = 0;

        int max = 0;
        while(!pq.isEmpty()) {
            Node now = pq.poll();
            if(visited[now.no])
                continue;
            visited[now.no] = true;
            ans += now.weight;
            max = Math.max(max, now.weight);        //최장 길이가 초반에 연결될 수 있다. 단순히 마지막 V-1에서 break한다고 정답이 되진 않는다.
            if(++cnt == V)
                break;

            for(Node tmp = nodes[now.no]; tmp != null; tmp = tmp.next) {
                if(!visited[tmp.no] && min[tmp.no] > tmp.weight) {
                    min[tmp.no] = tmp.weight;
                    pq.offer(new Node(tmp.no, tmp.weight));
                }
            }
        }

        ans -= max;

        bw.write(ans + "");
        bw.flush();
        bw.close();
        br.close();

    }

}