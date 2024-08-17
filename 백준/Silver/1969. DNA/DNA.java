import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        char[][] data;
        int[] spell;
        int max, tmp, anscnt = 0;
        String ans = "";

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        data = new char[n][m];

        for(int i=0; i<n; i++)
            data[i] = br.readLine().toCharArray();

        for(int i=0; i<m; i++) {
            spell = new int[4];
            max = tmp = 0;
            for(int j=0; j<n; j++) {
                switch (data[j][i]) {
                    case 'A':
                        spell[0]++;
                        break;
                    case 'C':
                        spell[1]++;
                        break;
                    case 'G':
                        spell[2]++;
                        break;
                    case 'T':
                        spell[3]++;
                }
            }

            for(int j=3; j>=0; j--)
                if(max <= spell[j]) {
                    max = spell[j];
                    tmp = j;
                }


            anscnt += n - max;

            switch (tmp) {
                case 0:
                    ans += "A";
                    break;
                case 1:
                    ans += "C";
                    break;
                case 2:
                    ans += "G";
                    break;
                case 3:
                    ans += "T";
                    break;
            }
        }

        bw.write(ans + "\n" + anscnt);

        bw.flush();
        bw.close();
        br.close();
    }
}