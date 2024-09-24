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

    static long search(int start, int end, int node, int left, int right){
        if (left > end || right < start) return 0L;
        if (left <= start && end <= right) return tree[node];
        int mid = (start + end) / 2;
        return search(start, mid, node*2, left, right) + search(mid+1, end, node*2 + 1, left, right);
    }

    static void update(int start, int end, int node, int idx){
        if (idx == start && idx == end) {
            tree[node] = 1L;
            return;
        }
        int mid = (start + end) / 2;
        if (idx <= mid) update(start, mid, node * 2, idx);
        else update(mid + 1, end, node * 2 + 1, idx);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
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
        
        tree = new long[4 * N];

        int pre = Integer.MAX_VALUE;
        List<Integer> idxList = new ArrayList<>();
        for (Node node : nodes) {
            if (pre != node.val) {
                for (int idx : idxList) {
                    update(0, N - 1, 1, idx);
                }
                idxList.clear();
                pre = node.val;
            }
            result += search(0, N - 1, 1, node.idx + 1, N - 1);
            idxList.add(node.idx);
        }

        System.out.println(result);

    }
    
}