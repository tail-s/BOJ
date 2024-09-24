import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] arr = new long[1000003];
        long tmp2, tmp3, ans = 0;
        int idx = 0;

        int n = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens())
            arr[idx++] = Integer.parseInt(st.nextToken());

        for(int i=0; i<n; i++) {
            if(b > c) {
                if(arr[i+1] > arr[i+2]) {
                    tmp2 = Math.min(arr[i], arr[i+1] - arr[i+2]);
                    arr[i] -= tmp2;
                    arr[i+1] -= tmp2;
                    ans += tmp2 * (b + c);

                    tmp3 = Math.min(arr[i], Math.min(arr[i+1], arr[i+2]));
                    arr[i] -= tmp3;
                    arr[i+1] -= tmp3;
                    arr[i+2] -= tmp3;
                    ans += tmp3 * (b + 2 * c);
                }
                else {
                    tmp3 = Math.min(arr[i], Math.min(arr[i+1], arr[i+2]));
                    arr[i] -= tmp3;
                    arr[i+1] -= tmp3;
                    arr[i+2] -= tmp3;
                    ans += tmp3 * (b + 2 * c);

                    tmp2 = Math.min(arr[i], arr[i+1]);
                    arr[i] -= tmp2;
                    arr[i+1] -= tmp2;
                    ans += tmp2 * (b + c);
                }
                ans += arr[i] * b;
            }
            else
                ans += arr[i] * b;
        }
        bw.write(ans + "");
        bw.flush();
        bw.close();
        br.close();
    }
}