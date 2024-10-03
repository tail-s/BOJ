import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Stack<Integer> stack = new Stack<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int len = n - k;

        String[] tmp = br.readLine().split("");
        for(int i=0; i<n; i++) {
            while(!stack.isEmpty() && k > 0 && stack.peek() < Integer.parseInt(tmp[i])) {
                stack.pop();
                k--;
            }
            stack.push(Integer.parseInt(tmp[i]));
        }
        for(int i=0; i<len; i++) {
            bw.write(stack.elementAt(i) + "");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}