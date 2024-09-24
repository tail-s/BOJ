import java.io.*;
import java.util.*;

public class Main {
    static int V;
    static int[] p;

    static void make() {
        p = new int[V];
        for(int i=0; i<V; i++)
            p[i] = i;
    }

    static int find(int a) {
        if(p[a] == a)
            return a;
        return p[a] = find(p[a]);
    }

    static boolean union(int a, int b) {
        int ar = find(a);
        int br = find(b);
        if(ar == br)
            return false;
        p[br] = ar;
        return true;
    }

    static class Edge implements Comparable<Edge> {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        boolean[] univ = new boolean[V];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<V; i++)
            univ[i] = st.nextToken().equals("M") ? true : false;

        List<Edge> elist = new ArrayList<>();
        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            if(univ[start] == univ[end])
                continue;
            elist.add(new Edge(start, end, weight));
        }

        Collections.sort(elist);
        make();

        long ans = 0;
        int cnt = 0;
        for(Edge e : elist) {
            if(union(e.from, e.to)) {
                ans += e.weight;
                if(++cnt == V - 1)
                    break;
            }
        }
        ans = cnt == V - 1 ? ans : -1;
        bw.write(ans + "");
        bw.flush();
        bw.close();
        br.close();


    }
}