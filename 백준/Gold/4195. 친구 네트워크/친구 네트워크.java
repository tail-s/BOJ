import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {    // 18116 로봇조립 문제랑 같은거네 > String마다 idx를 매칭해주어야 함 > Map 이용

    static int V;
    static int[] p;
    static int[] cnt;

    static void make() {
        p = new int[V * 2];       //한줄에 두 명씩, 최대 V * 2
        cnt = new int[V * 2];
        for(int i=0; i<V * 2; i++) {
            p[i] = i;
            cnt[i] = 1;
        }
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
        cnt[ar] += cnt[br];
        p[br] = ar;
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        int idx;
        String a, b;
        Map<String, Integer> map;

        while(t-- > 0) {

            idx = 0;
            map = new HashMap<>();
            V = Integer.parseInt(br.readLine());
            make();

            for(int i=0; i<V; i++) {
                st = new StringTokenizer(br.readLine());
                a = st.nextToken();
                b = st.nextToken();

                if(!map.containsKey(a))
                    map.put(a, idx++);
                if(!map.containsKey(b))
                    map.put(b, idx++);

                union(map.get(a), map.get(b));
                sb.append(cnt[find(map.get(a))]).append("\n");
            }

        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();


    }
}