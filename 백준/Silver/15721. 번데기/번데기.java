import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int t = sc.nextInt();
        int targetGooho = sc.nextInt();
        sc.close();

        int totalPass = 0;
        int call = 4;
        while(t > call) {
            totalPass += call * 2;
            t -= call++;
        }

        if(t > 2)
            totalPass += targetGooho == 0 ? t + 2 : t + call;
        else {
            totalPass += targetGooho == 0 ? t == 1 ? 1 : 3 : 0;
            totalPass += targetGooho == 1 ? t == 1 ? 2 : 4 : 0;
        }

        System.out.print((totalPass - 1) % a);
    }
}