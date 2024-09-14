import java.util.HashSet;
import java.util.Scanner;

public class Main {
    static int N, M;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        StringBuilder sb = new StringBuilder();
        HashSet<Integer> target = new HashSet<Integer>();
        while (N-- > 0) target.add(sc.nextInt());
        M = sc.nextInt();
        while (M-- > 0) sb.append(target.contains(sc.nextInt()) ? "1 " : "0 ");
        sb.setLength(sb.length()-1);
        sc.close();
        System.out.println(sb.toString());
    }
}