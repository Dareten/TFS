import java.util.*;

public class First {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt() * scanner.nextInt(), b = scanner.nextInt() * scanner.nextInt();
        if(a < b){
            System.out.println('P');
        }else if(a > b){
            System.out.println('M');
        }else System.out.println('E');
    }
}