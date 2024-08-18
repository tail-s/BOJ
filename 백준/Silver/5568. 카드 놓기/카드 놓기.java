import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    static int n, k;
    static StringBuilder sb = new StringBuilder();
    static Set<String> filter = new HashSet<>();
    static boolean[] visited;
    static int[] arr, ansbox;
    static void dfs(int depth) {
        if(depth == k) {
            for(int x : ansbox)
                sb.append(x);
            filter.add(sb.toString());
            sb.setLength(0);
            return;
        }

        for(int i=0; i<n; i++) {
            if(!visited[i]) {
                visited[i] = true;
                ansbox[depth] = arr[i];
                dfs(depth + 1);
                visited[i] = false;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        visited = new boolean[n];
        k = Integer.parseInt(br.readLine());
        ansbox = new int[k];
        for(int i=0; i<n; i++)
            arr[i] = Integer.parseInt(br.readLine());
        Arrays.sort(arr);
        dfs(0);

        bw.write(filter.size() + "");

        bw.flush();
        bw.close();
        br.close();
    }
}