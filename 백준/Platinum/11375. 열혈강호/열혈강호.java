import java.util.*;

public class Main {
    static int N, M;
    static int[] task;
    static boolean[] clear;
    static List<Integer>[] lists;

    static boolean dfs(int cur) {
        for (int i : lists[cur]) {
            if (clear[i]) continue;
            clear[i] = true;

            if (task[i] == 0 || dfs(task[i])) {
                task[i] = cur;
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        lists = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) lists[i] = new ArrayList<>();

        task = new int[M + 1];
        clear = new boolean[M + 1];
        for (int i = 1; i <= N; i++) {
            int taskNum = sc.nextInt();
            while (taskNum-- > 0) lists[i].add(sc.nextInt());
        }
        sc.close();

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            if (dfs(i)) ans++;
            Arrays.fill(clear, false);
        }

        System.out.println(ans);

    }
    
}