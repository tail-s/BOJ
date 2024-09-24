import java.io.*;
import java.util.*;

public class Main {

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

    static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static double dist(Point A, Point B) {
        return Math.sqrt(Math.pow(A.r - B.r, 2) + Math.pow(A.c - B.c, 2));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int linked = Integer.parseInt(st.nextToken());

        List<Point> gods = new ArrayList<>();
        for(int i=0; i<V; i++) {
            st = new StringTokenizer(br.readLine());
            gods.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Node[] nodes = new Node[V];
        for(int i=0; i<V-1; i++)
            for(int j=i+1; j<V; j++) {
                double tmp = dist(gods.get(i), gods.get(j));
                nodes[i] = new Node(j, tmp, nodes[i]);
                nodes[j] = new Node(i, tmp, nodes[j]);
            }

        boolean[] visited = new boolean[V];
        for(int i=0; i<linked; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            nodes[a] = new Node(b, 0, nodes[a]);
            nodes[b] = new Node(a, 0, nodes[b]);
        }


        Queue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0));
        double[] min = new double[V];
        Arrays.fill(min, Double.MAX_VALUE);
        min[0] = 0;

        double ans = 0;
        int cnt = 0;
        while(!pq.isEmpty()) {
            Node now = pq.poll();
            if(visited[now.no])
                continue;
            visited[now.no] = true;
            ans += now.weight;
            if(++cnt == V)
                break;

            for(Node tmp = nodes[now.no]; tmp != null; tmp = tmp.next) {
                if(!visited[tmp.no] && min[tmp.no] > tmp.weight) {
                    min[tmp.no] = tmp.weight;
                    pq.offer(new Node(tmp.no, tmp.weight));
                }
            }
        }

        bw.write(String.format("%.2f", ans));
        bw.flush();
        bw.close();
        br.close();



    }
}