import java.util.Scanner;

public class Main {
    static int N, size;
    static boolean[][] paper;
    static StringBuilder sb = new StringBuilder();
    static void marking(int x) {
        for (int i = x; i < size - x; i++) {
            paper[i][x] = true;
            paper[x][i] = true;
            paper[size - x - 1][i] = true;
            paper[i][size - x - 1] = true;
        }
    }
    static void drawing() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) sb.append(paper[i][j] ? "*" : " ");
            sb.append("\n");
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        sc.close();

        size = 4 * (N - 1) + 1;
        paper = new boolean[size][size];
        for (int i = 0; i < 2 * N; i += 2) marking(i);
        drawing();

        System.out.println(sb);


    }
}