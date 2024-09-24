import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int V, M, ans = 0;
    static int[] item;
    static Node[] nodes;

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

    static void search(int start) {

        boolean[] visited = new boolean[V];
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        Queue<Node> q = new PriorityQueue<>();
        q.offer(new Node(start, 0));

        int cnt = 0;

        while(!q.isEmpty()) {
            Node now = q.poll();
            if(visited[now.no]) continue;
            visited[now.no] = true;
            if(++cnt == V)  break;

            for(Node tmp = nodes[now.no]; tmp != null; tmp = tmp.next) {
                if(!visited[tmp.no] && dist[tmp.no] > dist[now.no] + tmp.weight) {
                    dist[tmp.no] = dist[now.no] + tmp.weight;
                    q.offer(new Node(tmp.no, dist[tmp.no]));
                }
            }
        }

        int tmp = 0;
        for(int i=0; i<V; i++)
            if(dist[i] <= M)
                tmp += item[i];

        ans = Math.max(ans, tmp);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        item = new int[V];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<V; i++)
            item[i] = Integer.parseInt(st.nextToken());

        nodes = new Node[V];
        int from, to, weight;
        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken()) - 1;
            to = Integer.parseInt(st.nextToken()) - 1;
            weight = Integer.parseInt(st.nextToken());
            nodes[from] = new Node(to, weight, nodes[from]);
            nodes[to] = new Node(from, weight, nodes[to]);
        }

        for(int i=0; i<V; i++)
            search(i);

        bw.write(ans + "");
        bw.flush();
        bw.close();
        br.close();

    }
}