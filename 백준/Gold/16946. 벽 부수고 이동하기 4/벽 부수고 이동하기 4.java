import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static String[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static Map<Integer, Integer> moveMap;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new String[N][M];
        for(int i=0; i<N; i++) map[i] = br.readLine().split("");

        // 빈칸 bfs
        int idx = -1;
        moveMap = new HashMap<>();
        ArrayList<int[]> walls = new ArrayList<>();
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(map[i][j].equals("0")){
                    bfs(i, j, idx);
                    idx--;
                }
                else if(map[i][j].equals("1")) walls.add(new int[]{i, j});
            }
        }

        int[][] answer = new int[N][M];
        HashSet<Integer>[] visited = new HashSet[walls.size()];
        for(int i=0; i<walls.size(); i++){
            visited[i] = new HashSet<>();
            int[] wall = walls.get(i);
            answer[wall[0]][wall[1]] = 1;

            // 벽 근처에 있는 빈 칸 집단 확인
            for(int k=0; k<4; k++){
                int x = wall[0]+dr[k];
                int y = wall[1]+dc[k];
                if(x<0 || y<0 || x>=N || y>=M || map[x][y].equals("1")) continue;
                visited[i].add(Integer.parseInt(map[x][y]));
            }

            // 빈 칸 개수 합산
            for(Integer v : visited[i]){
                answer[wall[0]][wall[1]] += moveMap.get(v);
            }
        }


        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                sb.append(answer[i][j]%10);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    // 빈칸 bfs
    static void bfs(int i, int j, int idx) {
        int cnt = 1;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j});
        map[i][j] = String.valueOf(idx);
        while(!q.isEmpty()){
            int[] cur = q.poll();
            for(int k=0; k<4; k++){
                int x = cur[0]+dr[k];
                int y = cur[1]+dc[k];
                if(x<0 || y<0 || x>=N || y>=M || !map[x][y].equals("0")) continue;
                cnt++;
                q.add(new int[]{x, y});
                map[x][y] = String.valueOf(idx);
            }
        }
        moveMap.put(idx, cnt);
    }

}