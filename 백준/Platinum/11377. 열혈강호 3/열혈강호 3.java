import java.util.*;

public class Main {
    static int N, M, K;
    static int[] task;  // task를 맡고있는 사람의 정보
    static boolean[] clear; // 특정 task를 처리했는지에 대한 정보
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
        K = sc.nextInt();

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
        for (int i = 1; i <= N * 2; i++) {
            if (i <= N && dfs(i)) ans++;
            else if (i > N && K > 0 && dfs(i - N)) {
                K--;
                ans++;
            }
            Arrays.fill(clear, false);
        }

        System.out.println(ans);

    }
    
}