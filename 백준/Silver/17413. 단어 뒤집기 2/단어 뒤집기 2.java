import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        Stack<Character> s = new Stack<Character>();
        Scanner sc = new Scanner(System.in);
        String target = sc.nextLine();
        sc.close();

        boolean checker = false;
        for(char ch : target.toCharArray()) {
            switch(ch) {
                case '<':
                    checker = true;
                    while(!s.isEmpty())
                        sb.append(s.pop());
                    sb.append("<");
                    break;
                case '>':
                    checker = false;
                    sb.append(">");
                    break;
                case ' ':
                    while(!s.isEmpty())
                        sb.append(s.pop());
                    sb.append(" ");
                    break;
                default:
                    if(checker)
                        sb.append(ch);
                    else
                        s.push(ch);
            }
        }

        while (!s.isEmpty()) {
            sb.append(s.pop());
        }

        System.out.println(sb);


    }
}