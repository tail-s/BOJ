import java.util.Scanner;

public class Main {
    public static int calculate(int n) {
        int result = n;
        for (int i=0; i<String.valueOf(n).length(); i++)
            result += String.valueOf(n).charAt(i) - '0';
        return result;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.close();

        int M = N;
        for (int i=M - String.valueOf(M).length() * 9; i<M; i++) {
            if (M == calculate(i)) {
                M = i;
                break;
            }
        }
        System.out.println(M == N ? 0 : M);
        
    }
}