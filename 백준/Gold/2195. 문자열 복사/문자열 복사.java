import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

public class Main {
   
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String P = br.readLine();

        int cnt = 1;
        int left = 0, right = 1;
        while(right <= P.length()){
            Pattern pattern = Pattern.compile(P.substring(left, right));
            Matcher matcher = pattern.matcher(S);
            if(matcher.find()) right++;
            else{
                left = right-1;
                cnt++;
            }
        }
        System.out.println(cnt);
    }

}