import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Gem implements Comparable<Gem>{
        int weight, value;
        Gem(int weight, int value){
            this.weight = weight;
            this.value = value;
        }
        public int compareTo(Gem g){
            return this.weight-g.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 보석 개수
        int K = Integer.parseInt(st.nextToken()); // 가방 개수

        ArrayList<Gem> gems = new ArrayList<>();
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            gems.add(new Gem(w, v));
        }

        ArrayList<Integer> bags = new ArrayList<>();
        for(int i=0; i<K; i++) bags.add(Integer.parseInt(br.readLine()));

        Collections.sort(gems);
        Collections.sort(bags);

        long answer = 0;
        int idx = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i=0; i<K; i++){
            int c = bags.get(i);
            while(idx<N && gems.get(idx).weight<=c){
                pq.offer(gems.get(idx).value);
                idx++;
            }
            if(!pq.isEmpty()) answer += pq.poll();
        }
        System.out.println(answer);
    }
}