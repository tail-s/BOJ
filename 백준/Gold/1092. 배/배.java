import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        List<Integer> c = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        int cnum, bnum, cidx, bidx, ans;

        cnum = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()) {
            c.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(c, Collections.reverseOrder());

        bnum = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()) {
            b.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(b, Collections.reverseOrder());

        ans = 0;
        if(c.get(0) < b.get(0))                              //크레인 용량보다 박스가 더 무거울 경우
            bw.write("-1");                              //-1 출력하고 끝
        else {
            while(!b.isEmpty()) {                            //박스를 모두 옮겨 리스트가 비어버릴 때까지 반복
                cidx = 0;                                    //가장 용량이 큰 크레인부터
                bidx = 0;                                    //가장 무거운 박스부터
                while(cidx < cnum) {                         //모든 크레인에 박스를 배정할 수 있는지 비교
                    if(bidx == b.size())                     //박스 리스트를 모두 탐색한 경우
                        break;                               //크레인 가동 시작
                    else if(c.get(cidx) >= b.get(bidx)) {    //크레인의 용량이 박스를 옮기기에 충분한 경우
                        b.remove(bidx);                      //박스 옮기기 > ArrayList는 remove할 경우 index가 자동정렬됨
                        cidx++;                              //다음 크레인 가져오기
                    }
                    else                                     //크레인을 사용할 수 없어 더 가벼운 박스와 비교해야 할 경우
                        bidx++;                              //다음 박스 가져오기
                }
                ans++;                                       //크레인 가동 완료 > 1분 추가
            }
            bw.write(ans + "");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}