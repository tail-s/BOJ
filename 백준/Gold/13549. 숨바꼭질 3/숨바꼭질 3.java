import java.util.*;

public class Main {
    static int N, K, ans = Integer.MAX_VALUE;
    static boolean[] visited = new boolean[100001];;
    static boolean visitable(int a) { return a < 100001 && a >= 0 && !visited[a]; }

    static class Pos {
        int pos, timer;

        public Pos(int pos, int timer) {
            this.pos = pos;
            this.timer = timer;
        }
    }
    static void bfs() {
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(N, 0));
        visited[N] = true;
        while (!q.isEmpty()) {
            Pos cur = q.poll();
            if (cur.pos == K) ans = Math.min(ans, cur.timer);
            visited[cur.pos] = true;

            if (visitable(cur.pos * 2)) q.offer(new Pos(cur.pos * 2, cur.timer));
            if (visitable(cur.pos + 1)) q.offer(new Pos(cur.pos + 1, cur.timer + 1));
            if (visitable(cur.pos - 1)) q.offer(new Pos(cur.pos - 1, cur.timer + 1));
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        sc.close();

        bfs();

        System.out.println(ans);


    }
}