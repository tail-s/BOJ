import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    static int N, M;
    static int[][] trains;
    static Scanner sc = new Scanner(System.in);
    static void execute() {
        int i, x;
        while (M-- > 0) {
            switch (sc.nextInt()) {
                case 1:
                    i = sc.nextInt();
                    x = sc.nextInt();
                    trains[i][x] = 1;
                    break;
                case 2:
                    i = sc.nextInt();
                    x = sc.nextInt();
                    trains[i][x] = 0;
                    break;
                case 3:
                    i = sc.nextInt();
                    for (int j = 20; j > 1; j--) trains[i][j] = trains[i][j - 1];
                    trains[i][1] = 0;
                    break;
                case 4:
                    i = sc.nextInt();
                    for (int j = 1; j < 20; j++) trains[i][j] = trains[i][j + 1];
                    trains[i][20] = 0;
            }
        }
    }

    static int pass() {
        Set<String> set = new HashSet<>();

        for (int i = 1; i < trains.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 1; j < trains[i].length; j++) sb.append(trains[i][j]).append(" ");
            set.add(sb.toString());
        }

        return set.size();
    }

    public static void main(String[] args) {
        N = sc.nextInt();
        M = sc.nextInt();
        trains = new int[N + 1][20 + 1];

        execute();
        System.out.println(pass());



    }
}