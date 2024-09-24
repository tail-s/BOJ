import java.util.Scanner;

public class Main {
    static int[] arr, minTree, maxTree;

    static class Tree {
        int[] tree;

        Tree(int size) {
            this.tree = new int[size * 4]; // 세그먼트 트리는 배열의 크기 N에 대해 4배의 메모리가 필요
        }

        // 초기화 함수: 세그먼트 트리를 구간 [start, end]로 초기화
        int initMin(int start, int end, int node) {
            if (start == end) return this.tree[node] = arr[start];
            int mid = (start + end) / 2;
            return tree[node] = Math.min(initMin(start, mid, node * 2), initMin(mid + 1, end, node * 2 + 1));
        }

        int initMax(int start, int end, int node) {
            if (start == end) return this.tree[node] = arr[start];
            int mid = (start + end) / 2;
            return tree[node] = Math.max(initMax(start, mid, node * 2), initMax(mid + 1, end, node * 2 + 1));
        }

        // 구간 검색 함수: [left, right] 구간에서 최소값 혹은 최대값을 구함
        int searchMin(int start, int end, int node, int left, int right) {
            if (right < start || end < left) return Integer.MAX_VALUE; // 범위 벗어나면 큰 값 반환
            if (left <= start && end <= right) return tree[node]; // 현재 구간이 완전히 포함될 때
            int mid = (start + end) / 2;
            return Math.min(searchMin(start, mid, node * 2, left, right), searchMin(mid + 1, end, node * 2 + 1, left, right));
        }

        int searchMax(int start, int end, int node, int left, int right) {
            if (right < start || end < left) return Integer.MIN_VALUE; // 범위 벗어나면 작은 값 반환
            if (left <= start && end <= right) return tree[node]; // 현재 구간이 완전히 포함될 때
            int mid = (start + end) / 2;
            return Math.max(searchMax(start, mid, node * 2, left, right), searchMax(mid + 1, end, node * 2 + 1, left, right));
        }

        // 업데이트 함수: 특정 인덱스의 값을 변경한 후 세그먼트 트리 업데이트
        void updateMin(int start, int end, int node, int index, int value) {
            if (index < start || end < index) return; // 범위 벗어나면 무시
            if (start == end) { // 리프 노드 도달
                tree[node] = value;
                return;
            }
            int mid = (start + end) / 2;
            updateMin(start, mid, node * 2, index, value);
            updateMin(mid + 1, end, node * 2 + 1, index, value);
            tree[node] = Math.min(tree[node * 2], tree[node * 2 + 1]); // 자식 노드의 값으로 갱신
        }

        void updateMax(int start, int end, int node, int index, int value) {
            if (index < start || end < index) return; // 범위 벗어나면 무시
            if (start == end) { // 리프 노드 도달
                tree[node] = value;
                return;
            }
            int mid = (start + end) / 2;
            updateMax(start, mid, node * 2, index, value);
            updateMax(mid + 1, end, node * 2 + 1, index, value);
            tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]); // 자식 노드의 값으로 갱신
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int T = sc.nextInt(); // 테스트 케이스 개수
        while (T-- > 0) {
            int N = sc.nextInt(); // DVD의 개수
            int K = sc.nextInt(); // 쿼리의 개수
            arr = new int[N]; // 초기 DVD 배열
            for (int i = 0; i < N; i++) arr[i] = i; // 처음 DVD는 0 ~ N-1 순서대로

            // 최소 트리와 최대 트리 생성 및 초기화
            Tree minSegTree = new Tree(N);
            Tree maxSegTree = new Tree(N);

            minSegTree.initMin(0, N - 1, 1);
            maxSegTree.initMax(0, N - 1, 1);

            while (K-- > 0) {
                int Q = sc.nextInt(); // 쿼리 타입 (0: Swap, 1: Verify)
                int A = sc.nextInt(); // 인덱스 A
                int B = sc.nextInt(); // 인덱스 B

                if (Q == 0) { // Swap 쿼리 처리
                    // A와 B 위치의 DVD 값을 교환
                    minSegTree.updateMin(0, N - 1, 1, A, arr[B]);
                    minSegTree.updateMin(0, N - 1, 1, B, arr[A]);
                    maxSegTree.updateMax(0, N - 1, 1, A, arr[B]);
                    maxSegTree.updateMax(0, N - 1, 1, B, arr[A]);

                    // 배열에서 DVD의 위치를 교환
                    int temp = arr[A];
                    arr[A] = arr[B];
                    arr[B] = temp;

                } else { // Verify 쿼리 처리
                    // [A, B] 구간의 최소값과 최대값을 구함
                    int minVal = minSegTree.searchMin(0, N - 1, 1, A, B);
                    int maxVal = maxSegTree.searchMax(0, N - 1, 1, A, B);

                    // 조건 만족 여부 판단
                    if (minVal == A && maxVal == B) {
                        sb.append("YES\n");
                    } else {
                        sb.append("NO\n");
                    }
                }
            }
        }
        System.out.print(sb.toString());
        sc.close();


        
    }
    
}