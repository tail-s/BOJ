import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Black{
        long x, y;
        Black(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    static long H, W;
    static long[] ans;
    static List<Black> blacks;
    static HashMap<Long, HashSet<Long>> map;
    static HashMap<Long, HashSet<Long>> dupMap; // 중복체크
    static long[] dr = {0, -1, -1, -1, 0, 1, 1, 1, 0};
    static long[] dc = {0, -1, 0, 1, 1, 1, 0, -1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Long.parseLong(st.nextToken());
        W = Long.parseLong(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        ans = new long[10];
        ans[0] = (H-2)*(W-2);
        if(N!=0){
            blacks = new ArrayList<Black>();
            map = new HashMap<>();
            dupMap = new HashMap<>();
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                long a = Long.parseLong(st.nextToken());
                long b = Long.parseLong(st.nextToken());
                if(!map.containsKey(a)) map.put(a, new HashSet<>());
                map.get(a).add(b);
                blacks.add(new Black(a, b));
            }

            // 리스트에서 하나씩 뽑아서 확인
            for(Black b : blacks){
                for(int i=0; i<9; i++) {
                    long dx = b.x+dr[i];
                    long dy = b.y+dc[i];
                    if(dx<1 || dy<1 || dx>H || dy>W) continue;
                    check(dx, dy);
                }
            }
        }
        for(int i=0; i<10; i++) System.out.println(ans[i]);
    }

    static void check(long x, long y) {
        long res = 0;
        boolean isOut = false;
        for(int i=0; i<9; i++){
            long dx = x+dr[i];
            long dy = y+dc[i];
            if(dx<1 || dy<1 || dx>H || dy>W){
                isOut = true;
                break;
            }
            // 검정 칸인 경우
            if(map.containsKey(dx) && map.get(dx).contains(dy)) res++;
        }
        if(!isOut){
            // 중복 제거하기 위해
            if(!dupMap.containsKey(x)) dupMap.put(x, new HashSet<>());
            if(!dupMap.get(x).contains(y)){
                ans[(int)res]++;
                ans[0]--;
            }
            dupMap.get(x).add(y);
        }
    }
}