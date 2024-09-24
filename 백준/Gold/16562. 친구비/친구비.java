import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[] p;
    static int[] cost;

    static void make() {
        p = new int[n];   //나는 0번째
        for(int i=0; i<n; i++)
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
        if(ar == br)    return false;
        if(cost[br] >= cost[ar])
            p[br] = ar;
        else
            p[ar] = br;
        return true;
    }

//    static class Edge implements Comparable<Edge> {
//        int from, to, weight;
//        public Edge(int from, int to, int weight) {
//            this.from = from;
//            this.to = to;
//            this.weight = weight;
//        }
//
//        @Override
//        public int compareTo(Edge o) {
//            return this.weight - o.weight;
//        }
//    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        cost = new int[n];
        make();

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
            cost[i] = Integer.parseInt(st.nextToken());

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;       // -1 안해줘서 1시간 이상 소비.
            int b = Integer.parseInt(st.nextToken()) - 1;
            union(a, b);
        }

        long ans = 0;
        int cnt = m;
        //나까지 V+1명, 이미 연결된 친구관계는 m개. >> 이 방법 폐기
        //일단 전부 친구가 된 후 친구비가 부족하다면 실패값을 출력하는 방법으로 가자
        for(int i=0; i<n; i++) {
            if(p[i] == i)       //루트 friend라면 친구비 줘야 함.
                ans += cost[i];
        }

        if(ans > k)
            sb.append("Oh no");
        else
            sb.append(ans);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();

    }
}