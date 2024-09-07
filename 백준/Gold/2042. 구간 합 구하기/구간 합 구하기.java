import java.util.Scanner;

public class Main {
    static long[] arr, tree;
    // start : 시작 인덱스, end : 끝 인덱스
    static long init(int start, int end, int node) {
        if (start == end) return tree[node] = arr[start];
        int mid = (start + end) / 2;
        // 재귀적으로 두 부분으로 나누어 그 합을 자기 자신으로 함
        return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
    }

    // left, right : 구하고자 하는 구간
    static long sum(int start, int end, int node, int left, int right) {
        if (left > end || right < start) return 0;  // 범위 밖에 있는 경우
        if (left <= start && end <= right) return tree[node];   // 범위 안에 있는 경우
        int mid = (start + end) / 2;    // 둘 다 아니라면, 두 부분으로 나누어 합을 구하기
        return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
    }

    // index : 구간 합을 수정하고자 하는 노드, diff : 수정할 값
    static void update(int start, int end, int node, int index, long diff) {
        if (index < start || index > end) return;   // 범위 밖
        tree[node] += diff; // 범위 안에 있는 있는 경우
        if (start == end) return;

        int mid = (start + end) / 2;
        update(start, mid, node * 2, index, diff);
        update(mid + 1, end, node * 2 + 1, index, diff);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int K = sc.nextInt();

        arr = new long[N + 1];
        for (int i = 1; i <= N; i++) arr[i] = sc.nextLong();

        /*
         * 2^k >= N인 최소의 k를 찾아야 함
         * 
         * k >= logN / log2
         * (logN / log2)의 값을 올림한 후 1을 더하면 k
         * k를 제곱하면 세그먼트 트리의 size
         */

        // int k = (int) Math.ceil(Math.log(N) / Math.log(2) + 1);
        // int size = (int) Math.pow(2, k);
        // tree = new long[size];

        // 위 과정 대신 대략적인 값 사용
        tree = new long[4 * N];
        init(1, N, 1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M + K; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            long c = sc.nextLong();

            if (a == 1) {
                long diff = c - arr[b];
                arr[b] = c;
                update(1, N, 1, b, diff);
            } else if (a == 2) {
                sb.append(sum(1, N, 1, b, (int) c)).append('\n');
            }
        }

        System.out.println(sb.toString());

    }
    
}