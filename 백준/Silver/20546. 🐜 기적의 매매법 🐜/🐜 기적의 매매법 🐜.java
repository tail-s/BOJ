import java.util.Scanner;

public class Main {
    static int MONEY, B, T;
    static int[] data;

    static void BNT() {
        int bal = MONEY;
        int qty = 0;

        for (int i = 0; i < 14; i++) {
            int cap = bal / data[i];
            qty += cap;
            bal -= cap * data[i];
        }
        B = bal + qty * data[13];
    }

    static void TIMING() {
        int bal = MONEY;
        int qty = 0;
        int u = 0;
        int d = 0;

        for (int i = 1; i < 14; i++) {
            if (data[i-1] < data[i]) {
                u++;
                d = 0;
            } else if (data[i-1] == data[i]) {
                u = 0;
                d = 0;
            } else {
                u = 0;
                d++;
            }

            if (u == 3) {
                bal += data[i] * qty;
                qty = 0;
            }
            if (d == 3) {
                int cap = bal / data[i];
                qty += cap;
                bal -= cap * data[i];
            }
        }
        T = bal + qty * data[13];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MONEY = sc.nextInt();
        B = 0;
        T = 0;
        data = new int[14];
        for (int i = 0; i < 14; i++) { data[i] = sc.nextInt(); }
        sc.close();

        BNT();
        TIMING();

        System.out.println(B > T ? "BNP" : B == T ? "SAMESAME" : "TIMING");

    }
}