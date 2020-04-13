import java.util.*;

public class Third {
    static int n, best_point;
    static double opt_k, cur_k, answer = 0;
    static boolean x, y, isNewFound;
    static ArrayList<ArrayList<Integer>> points, last_point, opora;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        points = new ArrayList<>();
        last_point = new ArrayList<>();
        opora = new ArrayList<>();
        ArrayList<ArrayList<Integer>> left = new ArrayList<>();
        ArrayList<ArrayList<Integer>> right = new ArrayList<>();
        ArrayList<ArrayList<Integer>> top = new ArrayList<>();
        ArrayList<ArrayList<Integer>> bot = new ArrayList<>();


        n = scan.nextInt();
        for(int i=0;i<n;++i) points.add(new ArrayList<>());
        for(int i=0;i<n;++i){
            points.get(i).add(0, scan.nextInt());
            points.get(i).add(1, scan.nextInt());
        }

        points.sort(Comparator.comparing(o -> o.get(0)));
        ender(left, 0);
        Collections.reverse(points);
        ender(right, 0);
        points.sort(Comparator.comparing(o -> o.get(1)));
        ender(bot, 1);
        Collections.reverse(points);
        ender(top, 1);

        best_point = 0;
        left.sort(Comparator.comparing(o -> o.get(1)));
        top.sort(Comparator.comparing(o -> o.get(0)));
        right.sort(Comparator.comparing(o -> o.get(1)));
        Collections.reverse(right);
        bot.sort(Comparator.comparing(o -> o.get(0)));
        Collections.reverse(bot);
        last_point.add(points.get(0));//костыль, чтоб не править метод из-за пустой точки
        //IV
        for(int i=0;i<left.size();++i) addToOpora(-1, 0, left, i);
        points.sort(Comparator.comparing(o -> o.get(0)));
        do {
            calc(true, 4);
            if(isNewFound) addToOpora(0, 1, points, best_point);
        } while (!top.get(0).equals(last_point.get(0)));
////////I////////////////////////////////////////////////////////////
        for(int i=0;i<top.size();++i) addToOpora(0, 1, top, i);
        do {
            calc(false, 1);
            if(isNewFound) addToOpora(1, 0, points, best_point);
        } while (!right.get(0).equals(last_point.get(0)));
////////II//////////////////////////////////////////////////////////////////////////////////////
        Collections.reverse(points);
        for(int i=0;i<right.size();++i) addToOpora(1, 0, right, i);
        best_point = 0;
        do {
            calc(true, 2);
            if(isNewFound) addToOpora(0, -1, points, best_point);
        } while (!bot.get(0).equals(last_point.get(0)));
////////III/////////////////////////////////////
        for(int i=0;i<bot.size();++i) addToOpora(0, -1, bot, i);
        do {
            calc(false, 3);
            if(isNewFound) addToOpora(-1, 0, points, best_point);
        } while (!left.get(0).equals(last_point.get(0)));

        for(int i=0;i<opora.size();i++){
            if(i == opora.size() - 1) answer += beauty(opora.get(i), opora.get(0));
            else answer += beauty(opora.get(i), opora.get(i + 1));
        }
        System.out.printf("%.5f", answer);
    }

    private static void ender(ArrayList<ArrayList<Integer>> ender, int axis) {
        for(int i=0;i<n;++i){
            if(i == 0) {
                ender.add(new ArrayList<>());
                ender.set(ender.size() - 1, points.get(i));
            }else if(points.get(i).get(axis).equals(ender.get(0).get(axis))){
                ender.add(new ArrayList<>());
                ender.set(ender.size() - 1, points.get(i));
            }else break;
        }
    }

    private static double beauty(ArrayList<Integer> a, ArrayList<Integer> b){
        long dx = diff(a.get(0), b.get(0)), dy = diff(a.get(1), b.get(1));
        long diagonals = Math.min(dx, dy);
        return diagonals * Math.sqrt(2) + Math.max(Math.abs(dx - diagonals), Math.abs(dy - diagonals));
    }

    private static Integer diff(Integer a, Integer b){
        return Math.abs(a - b);
    }

    private static void addToOpora(int modX, int modY, ArrayList<ArrayList<Integer>> from, int fromIndex){
        opora.add(new ArrayList<>());
        opora.get(opora.size() - 1).add(from.get(fromIndex).get(0) + modX);
        opora.get(opora.size() - 1).add(from.get(fromIndex).get(1) + modY);
        last_point.set(0, from.get(fromIndex));
    }

    private static void calc(boolean max_k, int quarter){
        isNewFound = false;
        if(quarter % 2 == 0)opt_k = 0;
        else opt_k = Integer.MAX_VALUE;
        for (int i = best_point + 1; i < n; ++i) {
            switch (quarter){
                case 1:
                    x = points.get(i).get(0) > last_point.get(0).get(0);
                    y = points.get(i).get(1) < last_point.get(0).get(1);
                    break;
                case 2:
                    x = points.get(i).get(0) < last_point.get(0).get(0);
                    y = points.get(i).get(1) < last_point.get(0).get(1);
                    break;
                case 3:
                    x = points.get(i).get(0) < last_point.get(0).get(0);
                    y = points.get(i).get(1) > last_point.get(0).get(1);
                    break;
                case 4:
                    x = points.get(i).get(0) > last_point.get(0).get(0);
                    y = points.get(i).get(1) > last_point.get(0).get(1);
                    break;
            }
            if (x && y) {
                cur_k = (double)diff(points.get(i).get(1),last_point.get(0).get(1))/
                        diff(points.get(i).get(0),last_point.get(0).get(0));
                isNewFound = true;
                if(max_k) {
                    if (cur_k > opt_k) {
                        opt_k = cur_k;
                        best_point = i;
                    }
                }else{
                    if (cur_k < opt_k) {
                        opt_k = cur_k;
                        best_point = i;
                    }
                }
            }
        }
    }
}