import java.io.*;
import java.util.*;

public class Main {
    static class c {
        long start, end;

        public c(long start, long end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        List<c> list = new ArrayList<>();
        Queue<Long> q = new PriorityQueue<>();
        long start, end;
        long tmp = 0;

        int n = Integer.parseInt(br.readLine());
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            start = Long.parseLong(st.nextToken());
            end = Long.parseLong(st.nextToken());
            list.add(new c(start, end));
        }
        Collections.sort(list, new Comparator<c>() {
            @Override
            public int compare(c o1, c o2) {
                if(o1.start == o2.start) {
                    if(o1.end > o2.end)
                        return 1;
                    else if(o1.end == o2.end)
                        return 0;
                    else
                        return -1;
                }
                if(o1.start > o2.start)
                    return 1;
                else
                    return -1;
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