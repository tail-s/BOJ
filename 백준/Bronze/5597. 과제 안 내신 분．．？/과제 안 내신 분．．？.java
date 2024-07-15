import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        boolean[] arr = new boolean[30];
        for (int i=0; i<28; i++) arr[sc.nextInt() - 1] = true;
        sc.close();
        for (int i=0; i<30; i++) if (!arr[i]) sb.append(i+1).append("\n");
        System.out.println(sb);
    }
}