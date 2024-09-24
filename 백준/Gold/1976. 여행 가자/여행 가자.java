import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

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
        if(ar == br)    return false;
        p[br] = ar;
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        V = Integer.parseInt(br.readLine());
        int plan = Integer.parseInt(br.readLine());

        make();

        for(int i=0; i<V; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<V; j++)
                if(Integer.parseInt(st.nextToken()) == 1)
                    union(i, j);
        }

        st = new StringTokenizer(br.readLine());
        int startRoot = find(Integer.parseInt(st.nextToken()) - 1);
        int target;
        String ans = "YES";
        while(st.hasMoreTokens()) {
            target = Integer.parseInt(st.nextToken())-1;
            if(startRoot != find(target))
                ans = "NO";
        }

        bw.write(ans);
        bw.flush();
        bw.close();
        br.close();

    }
}