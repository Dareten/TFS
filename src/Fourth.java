import java.util.*;

public class Fourth {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt(), n = scanner.nextInt(), answer = 0;
        int[] a = new int[n];
        for(int i=0;i<n;++i) a[i] = scanner.nextInt();
        for(int i=0;i<n;++i){
            if(a[i] == 1){
                ++answer;
                i += x - 1;
            }
        }
        System.out.println(answer);
    }
}