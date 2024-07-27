import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    static char[] input, target = "quack".toCharArray();

    static int count() {
        int result = 0;
        Stack<Integer> s = new Stack<Integer>();

        while (true) {
            s.setSize(0);
            int checker = 0;
            boolean full = false;   // 최소 한 마리의 오리 울음소리를 발견했는지
            for (int i = 0; i < input.length; i++) {
                if (input[i] == target[checker]) {
                    s.push(i);
                    checker = (checker + 1) % 5;

                    if (s.size() == 5) {
                        full = true;
                        while (!s.isEmpty()) input[s.pop()] = '^';
                    }
                }
            }
            if (full) result++;
            else break;
        }

        for (int i = 0; i < input.length; i++) if (input[i] != '^') return -1;

        return result;

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        input = sc.next().toCharArray();
        sc.close();

        System.out.println(count());

    }
}