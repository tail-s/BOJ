import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N, M, ans = 0;
    static Node[] nodes;
    static boolean[] visited;

    static class Node {
        int no;
        Node next;
        public Node(int no, Node next) {
            this.no = no;
            this.next = next;
        }
    }

    static void dfs(int no, int depth) {

        if (ans == 1 || depth >= 5) {
            ans = 1;
            return;
        }

        for (Node cur = nodes[no]; cur.next != null; cur = cur.next) {
            if (visited[cur.no]) continue;
            visited[cur.no] = true;
            dfs(cur.no, depth + 1);
            visited[cur.no] = false;
        }
    }

    static void solve() {
        for (int i = 0; i < N; i++) {
            visited[i] = true;
            dfs(i, 1);
            visited[i] = false;
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        nodes = new Node[N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) nodes[i] = new Node(i, null);

        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            int from = Integer.parseInt(input[0]);
            int to = Integer.parseInt(input[1]);
            nodes[from] = new Node(to, nodes[from]);
            nodes[to] = new Node(from, nodes[to]);
        }
        br.close();

        solve();

    }
}