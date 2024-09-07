import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// swap의 개수는 "보다 작은 수의 개수"에 의해 결정
public class Main {
    static long[] tree;
    static Node[] nodes;

    static class Node implements Comparable<Node> {
        int idx, val;
        Node(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
        @Override
        public int compareTo(Node o) {
            return this.val - o.val;
        }
    }

    //세그먼트 트리에서 Swap개수 구하는 함수(범위에 합)
    static long search(int start, int end, int node, int left, int right){
        if (left > end || right < start) return 0L; //구하는 범위를 벗어났을 때
        if (left <= start && end <= right) return tree[node];   //구하는 범위에 속했을 때
        int mid = (start + end) / 2;    //하위 노드 탐색
        return search(start, mid, node*2, left, right) + search(mid+1, end, node*2 + 1, left, right);
    }

    //세그먼트 트리에 현재 값 저장 및 최신화
    static void update(int start, int end, int node, int idx){
        if (idx == start && idx == end) { //리프 노드일 때
            tree[node] = 1L;
            return;
        }
        int mid = (start + end) / 2;    //하위 노드 탐색
        if (idx <= mid) update(start, mid, node * 2, idx);
        else update(mid + 1, end, node * 2 + 1, idx);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];   //세그먼트 트리 노드 값 최신화
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        nodes = new Node[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) nodes[i] = new Node(i, Integer.parseInt(st.nextToken()));
        br.close();

        long result = 0;
        Arrays.sort(nodes);
        
        // getTreeSize
        int h = (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;
        tree = new long[(int) Math.pow(2, h) - 1];
        // tree = new long[4 * N];

        int pre = Integer.MAX_VALUE;
        List<Integer> idxList = new ArrayList<>();
        for (Node node : nodes) {
            // 같은 값이 아닌 더 큰 값이 들어왔을 때
            if (pre != node.val) {
                // 이전 같은 값들 세그먼트 트리에 저장
                for (int idx : idxList) {
                    update(0, N - 1, 1, idx);
                }
                idxList.clear();    //같은 값들 초기화
                pre = node.val;
            }
            result += search(0, N - 1, 1, node.idx + 1, N - 1); //현재 세그먼트 트리에서 Swap 개수 구하기
            idxList.add(node.idx);  //같은 값 Index정보 저장
        }

        System.out.println(result);

    }
    
}