import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {       //bfs로 가능해보이지만 이악물고 다익스트라

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

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());       //도시 수
        int m = Integer.parseInt(st.nextToken());       //도로 수
        int k = Integer.parseInt(st.nextToken());       //타겟 거리
        int x = Integer.parseInt(st.nextToken()) - 1;   //출발 도시 번호

        Node[] nodes = new Node[n];
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            nodes[from] = new Node(to, 1, nodes[from]);
//            nodes[to] = new Node(from, 1, nodes[to]);     //단방향 그래프?
        }

        int[] min = new int[n];
        Arrays.fill(min, Integer.MAX_VALUE);
        min[x] = 0;

        boolean[] visited = new boolean[n];
        Queue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(x, 0));

        int cnt = 0;
        while(!pq.isEmpty()) {
            Node now = pq.poll();
            if(visited[now.no]) continue;
            visited[now.no] = true;
            if(++cnt == n)  break;

            for(Node tmp = nodes[now.no]; tmp != null; tmp = tmp.next) {
                if(!visited[tmp.no] && min[tmp.no] > min[now.no] + tmp.weight) {
                    min[tmp.no] = min[now.no] + tmp.weight;
                    pq.offer(new Node(tmp.no, min[tmp.no]));
                }
            }
        }

        for(int i=0; i<n; i++)
            if(min[i] == k)
                sb.append(i+1).append("\n");

        if(sb.length() == 0)
            sb.append("-1");

        bw.write(sb.toString().trim());
        bw.flush();
        bw.close();
        br.close();



    }
}