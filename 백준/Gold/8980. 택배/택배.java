import java.io.*;
import java.util.*;

public class Main {
    static class Parcel {
        int from, to, box;

        public Parcel(int from, int to, int box) {
            this.from = from;
            this.to = to;
            this.box = box;
        }

        @Override
        public String toString() {
            return this.from + " " + this.to + " " + this.box;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        List<Parcel> order = new ArrayList<>();
        int from, to, box, space, ans = 0;
        boolean acceptable;

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] truck = new int[n+1];
        int c = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(br.readLine());

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            box = Integer.parseInt(st.nextToken());
            order.add(new Parcel(from, to, box));
        }
        Collections.sort(order, new Comparator<Parcel>() {
            @Override
            public int compare(Parcel o1, Parcel o2) {
                if(o1.to == o2.to)
                    return o1.from - o2.from;
                return o1.to - o2.to;
            }
        });

        for(Parcel p : order) {
            from = p.from;
            to = p.to;
            box = p.box;
            acceptable = true;
            space = Integer.MAX_VALUE;

            for(int i=from; i<to; i++) {        //from에서 to로 이동하는동안
                if(truck[i] + box > c) {        //트럭에 박스를 다 싣기에는 공간이 부족할 때
                    if(truck[i] >= c) {         //트럭이 가득 찼다면
                        acceptable = false;     //그 택배는 서비스 불가
                        break;
                    }
                    else {                                      //조금 공간이 남아 일부 박스만 실으려고 할 때
                        space = Math.min(space, c - truck[i]);  //남은 공간을 계산한 후
                        box = space;                            //그 공간만큼만 박스를 넣는다.
                    }
                }
            }

            if(acceptable) {                    //서비스 가능한 택배라면
                for(int i=from; i<to; i++)      //from에서 to로 이동하는동안
                    truck[i] += box;            //트럭에 넣어두기
                ans += box;                     //일단 트럭에 넣는게 가능하다면 서비스 가능
            }
        }
        bw.write(ans + "");
        bw.flush();
        bw.close();
        br.close();
    }
}