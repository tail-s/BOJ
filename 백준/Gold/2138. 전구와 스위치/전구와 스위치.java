import java.io.*;

public class Main {
   
    static int N;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        boolean[][] arr = new boolean[2][N]; // 0: before, 1: after
        for(int i=0; i<2; i++){
            String s = br.readLine();
            for(int j=0; j<N; j++){
                arr[i][j] = s.charAt(j)=='1';
            }
        }
        
        // before 전구 배열 복사
        int[] cnt = new int[2];
        boolean[][] copy = new boolean[2][N];
        for(int i=0; i<2; i++) copy[i] = arr[0].clone();

        copy[0][0] = !copy[0][0];
        copy[0][1] = !copy[0][1];
        cnt[0]++;
        for(int i=1; i<N; i++){
            if(copy[0][i-1] != arr[1][i-1]){
                swap(copy[0], i);
                cnt[0]++;
            }
            if(copy[1][i-1] != arr[1][i-1]){
                swap(copy[1], i);
                cnt[1]++;
            }
        }

        boolean onOne = isSame(copy[0], arr[1]);
        boolean offOne = isSame(copy[1], arr[1]);
        if(!onOne && !offOne) System.out.println(-1);
        else{
            if(onOne && !offOne) System.out.println(cnt[0]);
            else if(!onOne && offOne) System.out.println(cnt[1]);
            else System.out.println(Math.min(cnt[0], cnt[1]));
        }
    }

    public static boolean isSame(boolean[] before, boolean[] after){
        for(int i=0; i<before.length; i++){
            if(before[i]!=after[i]) return false;
        }
        return true;
    }

    public static void swap(boolean[] arr, int idx){
        arr[idx-1] = !arr[idx-1];
        arr[idx] = !arr[idx];
        if(idx < N-1) arr[idx+1] = !arr[idx+1];
    }

}