import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static class Node {
        int no;
        Node next;

        public Node(int no, Node next) {
            this.no = no;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] parents = new int[N + 1];
        Node[] nodes = new Node[N + 1];
        for (int i = 1; i < N; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            nodes[from] = new Node(to, nodes[from]);
            nodes[to] = new Node(from, nodes[to]);
        }
        sc.close();

        Queue<Integer> queue = new LinkedList<>();
        parents[1] = 1;
        queue.add(1);

        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (Node tmp = nodes[now]; tmp != null; tmp = tmp.next) {
                if (parents[tmp.no] != 0) continue;
                parents[tmp.no] = now;
                queue.add(tmp.no);
            }
        }

        for (int i = 2; i <= N; i++) {
            System.out.println(parents[i]);
        }

    }
}