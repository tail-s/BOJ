import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        List<Integer> list = new ArrayList<>();
        List<Integer> diff = new ArrayList<>();
        int ans = 0;

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        while(st.hasMoreTokens()) {
            list.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(list);

        for(int i=0; i<n-1; i++) {
            diff.add(list.get(i+1) - list.get(i));
        }
        Collections.sort(diff);

        for(int i=0; i<n-k; i++) {
            ans += diff.get(i);
        }

        bw.write(ans + "");
        bw.flush();
        bw.close();
        br.close();
    }
}