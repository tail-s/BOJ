import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    static int N, start;
    static int[] graph;
    static boolean[] visited;
    static Set<Integer> ts = new TreeSet<>();

    // 시작 번호로 돌아오거나(visited된 곳) 번호와 값이 같은 것을 뽑았을 때 만족
    static void dfs(int cur) {
        if (cur == start && visited[start]) {
            for (int i = 0; i < N; i++) if (visited[i]) ts.add(i + 1);
            return;
        }

        visited[cur] = true;
        if (!visited[graph[cur]] || graph[cur] == start) dfs(graph[cur]);
    }

    static void solve() {
        StringBuilder sb = new StringBuilder();

        for (start = 0; start < N; start++) {
            visited = new boolean[N];
            dfs(start);
        }

        sb.append(ts.size()).append("\n");
        for (int i : ts) sb.append(i).append("\n");
        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        graph = new int[N];
        for (int i = 0; i < N; i++) graph[i] = sc.nextInt() - 1;
        sc.close();

        solve();

    }
}