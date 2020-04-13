import java.util.*;
import java.util.regex.Pattern;

public class Second {
    static String line;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        line = scanner.nextLine();
        line = Pattern.compile("[!?,.;:]").matcher(line).replaceAll(" ");
        line = line.replaceAll("-", "");
        if(line.isEmpty())System.out.print(0);
        else System.out.print(line.split("\\s+").length);
    }
}