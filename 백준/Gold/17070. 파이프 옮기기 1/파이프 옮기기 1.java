import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int n, ans = 0;
    static int[][] map;
    static void check(int x, int y, int dir) {
        if(x == n-1 && y == n-1) {
            if(map[n-1][n-1] != 1)
                ans++;
            return;
        }
        else if(x == n || y == n || map[x][y] == 1)
            return;

        switch(dir) {
            case 0://가로
                check(x, y+1, 0);
                break;
            case 1://대각선
                check(x, y+1, 0);
                check(x+1, y, 2);
                break;
            case 2://세로
                check(x+1, y, 2);
        }
        if(map[x+1][y] == 0 && map[x][y+1] == 0)
            check(x+1, y+1, 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n+1][n+1];
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
        check(0, 1, 0);
        bw.write(ans + "");
        bw.flush();
        bw.close();
        br.close();
    }
}