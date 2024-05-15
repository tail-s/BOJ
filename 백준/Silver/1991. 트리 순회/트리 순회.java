import java.util.Scanner;

public class Main {

    public static int N;

    public static StringBuilder sb = new StringBuilder();

    public static class Node {
        char ch;
        Node left, right;

        public Node(char ch) {
            this.ch = ch;
        }
    }

    public static void preorder(Node node) {
        if (node == null) return;
        sb.append(node.ch);
        preorder(node.left);
        preorder(node.right);
    }

    public static void inorder(Node node) {
        if (node == null) return;
        inorder(node.left);
        sb.append(node.ch);
        inorder(node.right);
    }

    public static void postorder(Node node) {
        if (node == null) return;
        postorder(node.left);
        postorder(node.right);
        sb.append(node.ch);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        Node[] nodes = new Node[N];
        for (int i = 0; i < N; i++) {
            char ch = sc.next().charAt(0);
            char left = sc.next().charAt(0);
            char right = sc.next().charAt(0);

            if (nodes[ch - 'A'] == null) nodes[ch - 'A'] = new Node(ch);
            if (left != '.') {
                nodes[left - 'A'] = new Node(left);
                nodes[ch - 'A'].left = nodes[left - 'A'];
            }
            if (right != '.') {
                nodes[right - 'A'] = new Node(right);
                nodes[ch - 'A'].right = nodes[right - 'A'];
            }
        }

        sc.close();

        preorder(nodes[0]);
        sb.append("\n");
        inorder(nodes[0]);
        sb.append("\n");
        postorder(nodes[0]);

        System.out.println(sb);

    }
}