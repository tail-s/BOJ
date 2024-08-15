import java.util.Scanner;

public class Main {
    public static int[] arr;
    public static boolean checker(int x, int y) {
        return arr[0] * x + arr[1] * y - arr[2] == 0 && arr[3] * x + arr[4] * y - arr[5] == 0;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        arr = new int[6];
        for (int i=0; i<6; i++) arr[i] = sc.nextInt();
        sc.close();

        all:
        for (int i=-999; i<1000; i++)
            for (int j=-999; j<1000; j++)
                if (checker(i, j)) {
                    System.out.println(i + " " + j);
                    break all;
                }
    }
}