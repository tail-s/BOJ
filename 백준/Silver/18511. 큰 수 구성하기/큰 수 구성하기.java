import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    static int n, k;
    static int[] arr;
    static TreeSet<Integer> set = new TreeSet<>();
    static void dfs(int depth, int result) {
        if(result > n)
            return;
        set.add(result);
        for(int i=0; i<k; i++)
            dfs(depth + 1, result * 10 + arr[i]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[k];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<k; i++)
            arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);
        dfs(0, 0);
        bw.write(set.last() + "");

        bw.flush();
        bw.close();
        br.close();
    }
}