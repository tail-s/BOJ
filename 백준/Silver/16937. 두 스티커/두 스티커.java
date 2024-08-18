import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int h, w, n;
    static int[][] data;
    static boolean checker(int[] a, int[] b) {
        for(int i=0; i<2; i++)
            for(int j=0; j<2; j++) {
                if(a[i] + b[j] <= h && Math.max(a[(i+1)%2], b[(j+1)%2]) <= w)
                    return true;
                if(a[i] + b[j] <= w && Math.max(a[(i+1)%2], b[(j+1)%2]) <= h)
                    return true;
            }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int max = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(br.readLine());

        data = new int[n][2];
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            data[i][0] = Integer.parseInt(st.nextToken());
            data[i][1] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<n-1; i++)
            for(int j=i+1; j<n; j++)
                if(checker(data[i], data[j]))
                    max = Math.max(max, data[i][0] * data[i][1] + data[j][0] * data[j][1]);

        bw.write(max + "");
        bw.flush();
        bw.close();
        br.close();
    }
}