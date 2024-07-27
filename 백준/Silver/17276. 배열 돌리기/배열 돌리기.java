import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 배열 크기
            int d = Integer.parseInt(st.nextToken()); // 각도
            map = new int[N][N];
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 배열 돌리기
            d /= 45;
            if(d < 0)  d += 8;
            int len = N-(N-1)/2-1;
            int len2 = N;
            for(int i=0; i<len; i++) {
                rotate(i, (len2-1)/2, len2-1, d);
                len2 -= 2;
            }

            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    sb.append(map[i][j]).append(" ");
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }

    public static void rotate(int x, int half, int N, int d) {
        for(int k=0; k<d; k++){
            int temp = map[x][x]; // 시작점
            // 상
            for(int i=0; i<2; i++) map[x+(half*i)][x] = map[x+half*(1+i)][x];
            // 좌
            for(int i=0; i<2; i++) map[x+N][x+(half*i)] = map[x+N][x+half*(1+i)];
            // 하
            for(int i=1; i>=0; i--) map[x+half*(1+i)][x+N] = map[x+(half*i)][x+N];
            // 우
            for(int i=1; i>=0; i--) map[x][x+half*(1+i)] = map[x][x+half*i];
            map[x][x+half] = temp;
        }
    }

}