import java.util.*;
import java.io.*;

public class Main {

    static int N, L;
    static Point root = new Point(Long.MAX_VALUE, Long.MAX_VALUE);
    static List<Point> points;

    static double dist(Point p1, Point p2) {    // 유클리드 거리
        return Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
    }
    static long count_Clock_Wise(Point p1, Point p2, Point p3) {
        return (p1.x * p2.y + p2.x * p3.y + p3.x * p1.y) - (p1.y * p2.x + p2.y * p3.x + p3.y * p1.x);
    }

    static class Point {
        long x, y;
        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    static int grahamScan() {
        // 기준점 찾기
        for (int i = 0; i < points.size(); i++) {
            if (points.get(i).y < root.y) root = points.get(i);
            else if (points.get(i).y == root.y) if (points.get(i).x < root.x) root = points.get(i);
        }

        // points를 반시계 방향으로 정렬
        points.sort(new Comparator<Point>() {

            @Override
            public int compare(Point o1, Point o2) {
                long result = count_Clock_Wise(root, o1, o2);

                if (result > 0) return -1;
                else if (result < 0) return 1;
                else if (dist(root, o1) > dist(root, o2)) return 1;
                return -1;
            }

        });

        Stack<Point> stack = new Stack<>();
        stack.push(root);
        for (int i = 1; i < points.size(); i++) {   // first, secont, next
            while (stack.size() > 1 && count_Clock_Wise(stack.get(stack.size() - 2), stack.get(stack.size() - 1), points.get(i)) <= 0) stack.pop();
            stack.push(points.get(i));
        }


        double ans = 0;
        while (!stack.isEmpty()) {
            Point next = stack.pop();
            ans += dist(root, next);
            root = next;
        }
        ans += 2 * Math.PI * L;

        return (int) Math.round(ans * 10 / 10);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        L = Integer.parseInt(input[1]);
        points = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            points.add(new Point(Integer.parseInt(input[0]), Integer.parseInt(input[1])));
        }

        System.out.println(grahamScan());

    }
    
}