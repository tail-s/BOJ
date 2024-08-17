import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        boolean[][] stop = new boolean[200][200];
        int a, b, ans = 0;

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken()) - 1;
            b = Integer.parseInt(st.nextToken()) - 1;
            stop[a][b] = stop[b][a] = true;
        }

        for(int i=0; i<n-2; i++)
            for(int j=i+1; j<n-1; j++) {
                if(stop[i][j])
                    continue;
                for(int k=j+1; k<n; k++) {
                    if(stop[i][k] || stop[j][k])
                        continue;
                    ans++;
                }
            }

        bw.write(ans + "");
        bw.flush();
        bw.close();
        br.close();
    }
}