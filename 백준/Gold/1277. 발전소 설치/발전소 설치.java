import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    // M을 고려하지 않고 풀어도 답은 나오나, 최적화를 위해 M 이상을 사용하는 경우를 pruning하는 방법으로 다시 풀어볼 필요가 있음.

    static int N;
    static List<Point> points = new ArrayList<>();

    static Double distance(Point A, Point B) { return Math.sqrt(Math.pow(Math.abs(A.r - B.r), 2) + Math.pow(Math.abs(A.c - B.c), 2)); }

    static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }


    static class Node implements Comparable<Node> {
        int no;
        double weight;
        Node next;
        public Node(int no, double weight) {
            this.no = no;
            this.weight = weight;
        }
        public Node(int no, double weight, Node next) {
            this.no = no;
            this.weight = weight;
            this.next = next;
        }
        @Override
        public int compareTo(Node o) {
            return Double.compare(this.weight, o.weight);
        }
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        double M = Double.parseDouble(br.readLine());
        Node[] nodes = new Node[N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            points.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        for(int i=0; i<W; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            nodes[from] = new Node(to, 0, nodes[from]);
            nodes[to] = new Node(from, 0, nodes[to]);
        }

        for(int i=0; i<N-1; i++)
            for(int j=0; j<N; j++) {
                nodes[i] = new Node(j, distance(points.get(i), points.get(j)), nodes[i]);
                nodes[j] = new Node(i, distance(points.get(i), points.get(j)), nodes[j]);
            }

        boolean[] visited = new boolean[N];
        double[] dist = new double[N];
        Arrays.fill(dist, Double.MAX_VALUE);
        dist[0] = 0;

        Queue<Node> q = new PriorityQueue<>();
        q.offer(new Node(0, 0));
        int cnt = 0;
        while(!q.isEmpty()) {
            Node now = q.poll();
            if(visited[now.no]) continue;
            visited[now.no] = true;
            if(++cnt == N) break;

            for(Node tmp = nodes[now.no]; tmp != null; tmp = tmp.next) {
                if(!visited[tmp.no] && dist[tmp.no] > dist[now.no] + tmp.weight) {
                    dist[tmp.no] = dist[now.no] + tmp.weight;
                    q.offer(new Node(tmp.no, dist[tmp.no]));
                }
            }
        }

        int ans = (int)Math.floor(dist[N-1] * 1000);

        bw.write(ans + "");
        bw.flush();
        bw.close();
        br.close();

    }
}