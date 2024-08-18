import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int n, m, ans = Integer.MIN_VALUE;
    static int[] arr = new int[3];
    static int[][] data;
    static void dfs(int depth, int start) {
        if(depth == 3) {
            int tmpans = 0;
            for(int i=0; i<n; i++) {
                int tmp = 0;
                for(int x : arr)
                    tmp = Math.max(tmp, data[i][x]);
                tmpans += tmp;
            }
            ans = Math.max(ans, tmpans);
            return;
        }

        for(int i=start; i<m; i++) {
            arr[depth] = i;
            dfs(depth + 1, i + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        data = new int[n][m];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++)
                data[i][j] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0);

        bw.write(ans + "");
        bw.flush();
        bw.close();
        br.close();

    }
}