import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int n, m, ans = Integer.MIN_VALUE;
    static int[][] data;
    static boolean is_Jegopsu(int target) {
        int tmp = (int)Math.sqrt(target);
        return tmp * tmp == target;
    }

    static void check(int r, int c) {

        int tr, tc;
        for(int i=-n; i<=n; i++)
            for(int j=-m; j<=m; j++) {      //모든 공차의 가능성을 검색
                if(i == 0 && j == 0)
                    continue;
                tr = r;
                tc = c;
                int tmp = 0;
                while(tr >= 0 && tr < n && tc >= 0 && tc < m) {
                    tmp *= 10;
                    tmp += data[tr][tc];
                    if(is_Jegopsu(tmp))
                        ans = Math.max(ans, tmp);
                    tr += i;
                    tc += j;
                }
            }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        data = new int[n][m];

        String str;
        for(int i=0; i<n; i++) {
            str = br.readLine();
            for(int j=0; j<m; j++)
                data[i][j] = Character.getNumericValue(str.charAt(j));
        }

        for(int i=0; i<n; i++)
            for(int j=0; j<m; j++)
                check(i, j);

        ans = ans == Integer.MIN_VALUE ? -1 : ans;

        bw.write(ans + "");
        bw.flush();
        bw.close();
        br.close();
    }
}