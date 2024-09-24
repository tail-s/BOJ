import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] arr = new int[10003];
        int n = Integer.parseInt(br.readLine()), idx = 0, tmp2, tmp3, ans = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens())
            arr[idx++] = Integer.parseInt(st.nextToken());

        for(int i=0; i<n; i++) {
            if(arr[i+1] > arr[i+2]) {
                tmp2 = Math.min(arr[i], arr[i+1] - arr[i+2]);
                arr[i] -= tmp2;
                arr[i+1] -= tmp2;
                ans += tmp2 * 5;

                tmp3 = Math.min(arr[i], Math.min(arr[i+1], arr[i+2]));
                arr[i] -= tmp3;
                arr[i+1] -= tmp3;
                arr[i+2] -= tmp3;
                ans += tmp3 * 7;
            }
            else {
                tmp3 = Math.min(arr[i], Math.min(arr[i+1], arr[i+2]));
                arr[i] -= tmp3;
                arr[i+1] -= tmp3;
                arr[i+2] -= tmp3;
                ans += tmp3 * 7;

                tmp2 = Math.min(arr[i], arr[i+1]);
                arr[i] -= tmp2;
                arr[i+1] -= tmp2;
                ans += tmp2 * 5;
            }
            ans += arr[i] * 3;
        }
        bw.write(ans + "");
        bw.flush();
        bw.close();
        br.close();
    }
}