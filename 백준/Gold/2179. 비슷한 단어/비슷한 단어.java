import java.util.*;
import java.io.*;

public class Main {
   
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] strs = new String[N];
        HashMap<String, Integer> map = new HashMap<>();
        for(int i=0; i<N; i++){
            strs[i] = br.readLine();
            map.put(strs[i], i);
        }
        Arrays.sort(strs);

        // 비슷한 단어 탐색
        int max = 0;
        String str1 = "", str2 = "";
        for(int i=0; i<N-1; i++){
            int idx = i+1;
            while(idx < N){
                int cnt = 0;
                int len = Math.min(strs[i].length(), strs[idx].length());
                for(int j=0; j<len; j++){
                    if(strs[i].charAt(j) == strs[idx].charAt(j)) cnt++;
                    else break;
                }
                if(max > cnt) break;
                else if(max < cnt){
                    max = cnt;
                    int idx1 = map.get(strs[i]);
                    int idx2 = map.get(strs[idx]);
                    if(idx1 < idx2){
                        str1 = strs[i];
                        str2 = strs[idx];
                    }
                    else{
                        str1 = strs[idx];
                        str2 = strs[i];
                    }
                }
                else{ // max == cnt
                    int idx1 = map.get(strs[i]);
                    int idx2 = map.get(strs[idx]);
                    if(str1 == "" && str2 == ""){
                        if(idx1 < idx2){
                            str1 = strs[i];
                            str2 = strs[idx];
                        }
                        else{
                            str1 = strs[idx];
                            str2 = strs[i];
                        }
                        idx++;
                        continue;
                    }
                    int idx3 = map.get(str1);
                    int idx4 = map.get(str2);
                    if(idx3 > Math.min(idx1, idx2)){
                        if(idx1 < idx2){
                            str1 = strs[i];
                            str2 = strs[idx];
                        }
                        else{
                            str1 = strs[idx];
                            str2 = strs[i];
                        }
                    }
                    else if(idx3 == Math.min(idx1, idx2)){
                        if(idx3==idx1 && idx2<idx4) str2 = strs[idx];
                        else if(idx3==idx2 && idx1<idx4) str2 = strs[i];
                    }
                }
                idx++;
            }
            
        }

        System.out.println(str1);
        System.out.println(str2);
    }

}