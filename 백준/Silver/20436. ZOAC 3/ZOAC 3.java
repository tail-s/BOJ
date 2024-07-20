import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    static int A = 0, B = 0;
    static int[] a_pos, b_pos;
    static List<Character> a_list = "qwertasdfgzxcv"
            .chars()
            .mapToObj(c -> (char)c)
            .collect(Collectors.toList());
    static String[] keys = new String[] {"qwertyuiop", "asdfghjkl", "zxcvbnm"};

    static int[] search(char ch) {
        int[] result = new int[2];
        find:
        for (int i = 0; i < keys.length; i++) {
            for (int j = 0; j < keys[i].length(); j++) {
                if (ch == keys[i].charAt(j)) {
                    result[0] = i;
                    result[1] = j;
                    break find;
                }
            }
        }
        return result;
    }

    static void execute(char ch) {
        int[] pos = search(ch);

        if (a_list.contains(ch)) {
            A += Math.abs(a_pos[0] - pos[0]) + Math.abs(a_pos[1] - pos[1]) + 1;
            a_pos = pos;
        } else {
            B += Math.abs(b_pos[0] - pos[0]) + Math.abs(b_pos[1] - pos[1]) + 1;
            b_pos = pos;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        a_pos = search(sc.next().charAt(0));
        b_pos = search(sc.next().charAt(0));
        String target = sc.next();
        sc.close();

        for (char ch : target.toCharArray()) execute(ch);
        System.out.println(A + B);


    }
}