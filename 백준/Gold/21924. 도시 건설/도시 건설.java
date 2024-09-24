import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int[] p;
    static int V;

    static void makeSet() {
        p = new int[V];
        for(int i=0; i<V; i++)
            p[i] = i;
    }

    static int findSet(int a) {
        if(p[a] == a)
            return a;
        return p[a] = findSet(p[a]);
    }

    static boolean union(int a, int b) {
        int ar = findSet(a);
        int br = findSet(b);
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
        List<Edge> elist = new ArrayList<>();

        long total = 0;
        while(E-- > 0) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            total += weight;
            elist.add(new Edge(from, to, weight));
        }
        Collections.sort(elist);
        makeSet();

        int cnt = 0;
        long ans = 0;
        for(Edge e : elist) {
            if(union(e.from, e.to)) {
                ans += e.weight;
                if(++cnt == V - 1)
                    break;
            }
        }
        ans = cnt == V - 1 ? total - ans : -1;
//        ans = total - ans;
        bw.write(ans + "");
        bw.flush();
        bw.close();
        br.close();

    }
}