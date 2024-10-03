import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] honey = new int[n];
        int[] lmov = new int[n];
        int[] rmov = new int[n];
        int b1, b2;
        int tmp = 0;

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            honey[i] = Integer.parseInt(st.nextToken());
            tmp += honey[i];
            rmov[i] = tmp;
        }
        tmp = 0;
        for(int i=n-1; i>=0; i--) {
            tmp += honey[i];
            lmov[i] = tmp;
        }
        int honeys = rmov[n-1];
        int ans = 0;

        //벌통이 가장 오른쪽 위치일 경우, b1 가장 왼쪽 위치에 고정, b2 이동하며 탐색
        for(int i=1; i<n-1; i++) {
            b1 = honeys - honey[0] - honey[i];
            b2 = honeys - rmov[i];
            ans = Math.max(ans, b1+b2);
        }

        //벌통이 가장 왼쪽 위치일 경우, b1 가장 오른쪽 위치에 고정, b2 이동하며 탐색
        for(int i=1; i<n-1; i++) {
            b1 = honeys - honey[n-1] - honey[i];
            b2 = honeys - lmov[i];
            ans = Math.max(ans, b1+b2);
        }

        //벌 두마리가 양쪽 끝에 위치할 경우, 벌통을 이동하며 탐색
        for(int i=1; i<n-1; i++) {
            b1 = rmov[i] - honey[0];
            b2 = lmov[i] - honey[n-1];
            ans = Math.max(ans, b1+b2);
        }

        bw.write(ans + "");
        bw.flush();
        bw.close();
        br.close();
    }
}