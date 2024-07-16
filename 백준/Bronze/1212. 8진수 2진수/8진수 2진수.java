import java.util.Scanner;

/**
 * 입력값 길이 % 3 만큼 앞에 0으로 패딩주고 세자리씩 변환
 */
public class Main {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        sc.close();

        String tmp;
        for (int i = 0; i < str.length(); i++) {
            tmp = Integer.toBinaryString(str.charAt(i) - '0');

            if (i == 0) sb.append(tmp);
            else sb.append(String.format("%03d", Integer.parseInt(tmp)));
        }

        System.out.println(sb);
    }
}