import java.io.*;
import java.util.*;

public class Main {
    static class Town implements Comparable<Town> {
        long idx;
        long people;

        public Town(long idx, long people) {
            this.idx = idx;
            this.people = people;
        }

        @Override
        public int compareTo(Town o) {
            if(this.idx > o.idx)
                return 1;
            else if(this.idx == o.idx) {
                if(this.people > o. people)
                    return 1;
                else if(this.people == o.people)
                    return 0;
                else
                    return -1;
            }
            else
                return -1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        List<Town> list = new ArrayList<>();
        StringTokenizer st;
        long population = 0, target = 0;

        int n = Integer.parseInt(br.readLine());
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            long idx = Long.parseLong(st.nextToken());
            long people = Long.parseLong(st.nextToken());
            population += people;
            Town tmp = new Town(idx, people);
            list.add(tmp);
        }
        Collections.sort(list);

        for(Town t : list) {
            target += t.people;
            if(target >= (population + 1) / 2) {
                bw.write(t.idx + "");
                break;
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}