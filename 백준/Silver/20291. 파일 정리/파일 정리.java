import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        TreeMap<String, Integer> map = new TreeMap<>();
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        while (n-- > 0) {
            String str = sc.next();
            int idx = str.lastIndexOf('.');
            str = str.substring(idx + 1);
            map.put(str, map.getOrDefault(str, 0) + 1);
        }
        sc.close();

        for (String key : map.keySet()) sb.append(key + " " + map.get(key) + "\n");
        sb.setLength(sb.length() - 1);
        System.out.println(sb);

    }
}