import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int n, ans = Integer.MAX_VALUE;
    static int[][] stat;
    static boolean[] seleted;
    static void dfs(int depth) {
        if(depth == n) {
            int teamL = 0;
            int teamS = 0;
            for(int i=0; i<n-1; i++)
                for(int j=i+1; j<n; j++) {
                    if(seleted[i] && seleted[j])
                        teamL += stat[i][j] + stat[j][i];
                    else if(!seleted[i] && !seleted[j])
                        teamS += stat[i][j] + stat[j][i];
                }
            ans = Math.min(ans, Math.abs(teamL - teamS));
            return;
        }

        seleted[depth] = true;
        dfs(depth + 1);
        seleted[depth] = false;
        dfs(depth + 1);

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        seleted = new boolean[n];
        stat = new int[n][n];
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++)
                stat[i][j] = Integer.parseInt(st.nextToken());
        }

        dfs(0);

        bw.write(ans + "");
        bw.flush();
        bw.close();
        br.close();
    }
}