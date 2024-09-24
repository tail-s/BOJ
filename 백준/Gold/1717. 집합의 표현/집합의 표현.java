import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    static int V;
    static int[] p;

    static void make() {
        p = new int[V + 1];
        for(int i=0; i<=V; i++)
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
        p[br] = ar;
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        int tc = Integer.parseInt(st.nextToken());

        make();

        for(int i=0; i<tc; i++) {
            st = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            switch (order) {
                case 0:
                    union(a, b);
                    break;
                case 1:
                    if(find(a) == find(b))
                        sb.append("YES\n");
                    else
                        sb.append("NO\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}