import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int N, ANS = Integer.MIN_VALUE;
    static char[] target;
    static List<Integer> num = new ArrayList<>();
    static List<Character> ops = new ArrayList<>();
    static int calculate(char op, int a, int b) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            default:
                return 0;
        }
    }

    static void solve(int depth, int value) {
        if (depth == ops.size()) {
            ANS = Math.max(ANS, value);
            return;
        }

        solve(depth + 1, calculate(ops.get(depth), value, num.get(depth + 1)));
        if (depth <= ops.size() - 2) {
            int preCalculate = calculate(ops.get(depth + 1), num.get(depth + 1), num.get(depth + 2));
            solve(depth + 2, calculate(ops.get(depth), value, preCalculate));
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        target = sc.next().toCharArray();
        sc.close();

        for (char ch : target) {
            if (ch == '+' || ch == '-' || ch == '*') ops.add(ch);
            else num.add(Character.getNumericValue(ch));
        }

        solve(0, num.get(0));
        System.out.println(ANS);

    }
}