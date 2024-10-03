import java.io.*;
import java.util.*;

public class Main {
    static class c {
        int start, end;

        public c(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        List<c> list = new ArrayList<>();
        Queue<Integer> q = new PriorityQueue<>();
        int start, end;
        int tmp = 0;

        int n = Integer.parseInt(br.readLine());
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            list.add(new c(start, end));
        }

        Collections.sort(list, new Comparator<c>() {
            @Override
            public int compare(c o1, c o2) {
                if(o1.start == o2.start)
                    return o1.end - o2.end;
                return o1.start - o2.start;
            }
        });

        for(c c : list) {
            tmp = c.end;
            if(!q.isEmpty() && c.start >= q.peek())
                q.poll();
            q.offer(tmp);
        }

        bw.write(q.size() + "");
        bw.flush();
        bw.close();
        br.close();
    }
}