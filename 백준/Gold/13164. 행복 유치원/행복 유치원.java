import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        List<Integer> list = new ArrayList<>();
        List<Integer> diff = new ArrayList<>();
        int ans = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        while(st.hasMoreTokens()) {
            list.add(Integer.parseInt(st.nextToken()));
        }
//        Collections.sort(list);     //이미 정렬된 상태로 주어지기에 이 부분은 필요 없음.

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