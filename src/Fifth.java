import java.util.*;

public class Fifth {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine(), max_pal = "", pot_pal;
        int max_len = 0, str_len;
        for(int min_len=1;min_len<=s.length();++min_len){
            for(int substr=0;substr<=s.length()-min_len;++substr){
                pot_pal = s.substring(substr, substr + min_len);
                if(pot_pal.equals(new StringBuilder(pot_pal).reverse().toString())){
                    str_len = pot_pal.length();
                    if(str_len > max_len){
                        max_len = str_len;
                        max_pal = pot_pal;
                        break;
                    }
                }
            }
        }
        System.out.println(max_len);
        System.out.println(max_pal);
    }
}