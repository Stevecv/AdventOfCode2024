import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Day4B {
    public static String code = "MMMSXXMASM\n" +
            "MSAMXMSMSA\n" +
            "AMXSXMAAMM\n" +
            "MSAMASMSMX\n" +
            "XMASAMXAMM\n" +
            "XXAMMXXAMA\n" +
            "SMSMSASXSS\n" +
            "SAXAMASAAA\n" +
            "MAMMMXMMMM\n" +
            "MXMXAXMASX";

    public static void main(String[] args) {
        int matches = 0;
        int i = 0;

        Map<String, String> opposites = new HashMap<>() {{
            put("M", "S");
            put("S", "M");
        }};
        for (String line: code.split("\n")) {
            int j = 0;
            int lineWidth = line.length();
            for (char letter: line.toCharArray()) {
                if (letter != 'A') {
                    j++;
                    continue;
                }

                int x = j;
                int y = i;

                String tLLetter = String.valueOf(getLetter(x-1, y+1, lineWidth));
                String tRLetter = String.valueOf(getLetter(x+1, y+1, lineWidth));
                String bLLetter = String.valueOf(getLetter(x-1, y-1, lineWidth));
                String bRLetter = String.valueOf(getLetter(x+1, y+1, lineWidth));

                System.out.println(tLLetter + ". " + tRLetter);
                System.out.println(". " + letter + ". ");
                System.out.println(bLLetter + ". " + bRLetter);

                System.out.println("bRLetter: " + bRLetter + ", bLLetter: " + bLLetter);
                System.out.println(tLLetter +" == " + opposites.get(bRLetter) + " && " + tRLetter + " == " + opposites.get(bLLetter));
                if (tLLetter.equals(opposites.get(bRLetter)) && tRLetter.equals(opposites.get(bLLetter))) {
                    matches++;
                }
                System.out.println("");

                j++;
            }
            i++;
        }

        System.out.println(matches);
    }

    public static char getLetter(int x, int y, int length) {
        int i = (y * length) + x;
        if (x >= length || i >= code.replace("\n", "").length() || i < 0 || x < 0) {
            return ' ';
        }
        return code.replace("\n", "").toCharArray()[i];
    }
}
