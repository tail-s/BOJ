import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n, ans = Integer.MAX_VALUE;
    static int[] target = new int['Z' - 'A' + 1];
    static int[] now = new int['Z' - 'A' + 1];
    static List<Book> list = new ArrayList<>();
    static class Book {
        int price;
        String title;
        int[] arr = new int['Z' - 'A' + 1];

        public Book(int price, String title) {
            this.price = price;
            this.title = title;
            for(char ch : title.toCharArray())
                arr[ch - 'A']++;
        }
    }

    static void dfs(int depth, int start, int price) {
        if(depth == n) {
            boolean checker = true;
            for(int j=0; j<26; j++)
                if(now[j] < target[j]) {
                    checker = false;
                    break;
                }
            if(checker)
                ans = Math.min(ans, price);
            return;
        }

        for(int i=start; i<n; i++) {
            for(int j=0; j<26; j++)
                now[j] += list.get(i).arr[j];
            dfs(depth + 1, i + 1, price + list.get(i).price);
            for(int j=0; j<26; j++)
                now[j] -= list.get(i).arr[j];
            dfs(depth + 1, i + 1, price);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        String t = br.readLine();
        for(char ch : t.toCharArray())
            target[ch - 'A']++;
        n = Integer.parseInt(br.readLine());

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            list.add(new Book(Integer.parseInt(st.nextToken()), st.nextToken()));
        }

        dfs(0, 0, 0);

        ans = ans == Integer.MAX_VALUE ? -1 : ans;
        bw.write(ans + "");
        bw.flush();
        bw.close();
        br.close();
    }
}