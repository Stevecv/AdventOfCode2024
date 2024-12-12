import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3A {
    public static void main(String[] args) {
        String code = "";
        String regex = "(mul\\([0-9]{1,3},[0-9]{1,3}\\)|do\\(\\)|don't\\(\\))";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(code);
        int total = 0;
        boolean allow = true;
        while (matcher.find()) {
            String match = matcher.group();
            if (match.equals("do()")) {
                allow = true;
            } else if (match.equals("don't()")) {
                allow = false;
            } else if (allow) {
                String line = matcher.group().replace("mul(", "").replace(")", "");
                String[] values = line.split(",");

                total += Integer.parseInt(values[0]) * Integer.parseInt(values[1]);
            }
        }

        System.out.println(total);
    }
}
