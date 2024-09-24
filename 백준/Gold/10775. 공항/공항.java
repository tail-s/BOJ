import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int V;
    static int[] p;

    static void make() {
        p = new int[V+1];       //도킹 불가능한 상황을 0으로 맞추기 위해 눈물의 배열길이-인덱스 보정...할 필요는 없나? > 해야 됨 > 왜 인덱스에러?
        for(int i=0; i<V+1; i++)
            p[i] = i;
    }

    static int find(int a) {
        if(p[a] == a)
            return a;
        return p[a] = find(p[a]);
    }

    static boolean union(int a, int b) {
        int ar = find(a);
        int br = find(b);
        if(ar == br)    return false;
        p[br] = ar;
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        V = Integer.parseInt(br.readLine());             //gate 수
        int planes = Integer.parseInt(br.readLine());    //도착할 비행기 수 > N번 비행기는 1 ~ N번 게이트 도킹 가능 > 1번 게이트는 아무거나 도킹 가능 + 그리디 > 최대한 높은 번호의 게이트부터 도킹 let's go

        make();
        int ans = 0;

        for(int i=0; i<planes; i++) {
            int plane = Integer.parseInt(br.readLine());        //n번 비행기가 n번 비행기에 도킹한 후 다시 n번 비행기가 들어올 때, n-1번 게이트로 도킹 유도
            if(find(plane) == 0)   break;                          //위 과정이 반복되어 0번(존재하지않는)게이트로 유도될 경우 공항이 폐쇄되고 박승원이 행복하게 됨
            ans++;
            union(find(plane) - 1, find(plane));             //find(plane)의 root가 find(plane) - 1을 가리키도록
        }

        bw.write(ans + "");
        bw.flush();
        bw.close();
        br.close();

    }
}