import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int[][] data;
        int scnt, bcnt, ans = 0;
        boolean is_ans;

        int n = Integer.parseInt(br.readLine());
        data = new int[n][3];
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            data[i][0] = Integer.parseInt(st.nextToken());
            data[i][1] = Integer.parseInt(st.nextToken());
            data[i][2] = Integer.parseInt(st.nextToken());
        }

        for(int i=1; i<10; i++)
            for(int j=1; j<10; j++) {
                if(i == j)
                    continue;
                for(int k=1; k<10; k++) {
                    if(i == k || j == k)
                        continue;

                    is_ans = true;
                    for(int l=0; l<n; l++) {
                        scnt = 0;
                        bcnt = 0;
                        if(data[l][0] / 100 == i)
                            scnt++;
                        if(data[l][0] / 10 % 10 == j)
                            scnt++;
                        if(data[l][0] % 10 == k)
                            scnt++;
                        if(data[l][0] / 100 == j || data[l][0] / 100 == k)
                            bcnt++;
                        if(data[l][0] / 10 % 10 == i || data[l][0] / 10 % 10 == k)
                            bcnt++;
                        if(data[l][0] % 10 == i || data[l][0] % 10 == j)
                            bcnt++;
                        if(scnt != data[l][1] || bcnt != data[l][2])
                            is_ans = false;
                    }
                    if(is_ans)
                        ans++;
                }
            }


        bw.write(ans + "");
        bw.flush();
        bw.close();
        br.close();
    }
}