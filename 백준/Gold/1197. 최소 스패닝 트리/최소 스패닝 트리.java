import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] parents;
    static int V, E;
    static Edge[] edgelist;

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

    static void make() {    //크기가 1인 서로 소 집합 생성
        parents = new int[V];
        for(int i=0; i<V; i++) {    //모든 노드가 자신을 부모로 하는(대표자) 집합으로 만든다.
            parents[i] = i;
        }
    }

    static int find(int a) {
        if(parents[a] == a)
            return a;
        return parents[a] = find(parents[a]);   //우리의 대표자를 나의 부모로 : path compression
    }

    static boolean union(int a, int b) {    //리턴값 : true => union 성공
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot)
            return false;
        parents[bRoot] = aRoot;
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int cnt;
        long ans;

        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        edgelist = new Edge[E];
        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            edgelist[i] = new Edge(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(edgelist);
        make();

        cnt = 0;
        ans = 0;
        for(Edge e : edgelist) {
            if(union(e.from, e.to)) {
                ans += e.weight;
                if(++cnt == V-1)
                    break;
            }
        }

        bw.write(ans + "");
        bw.flush();
        bw.close();
        br.close();
    }
}