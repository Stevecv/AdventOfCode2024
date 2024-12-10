import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String code = """
                """;

        String[] lines = code.split("\n");

        int safetyCount = 0;
        for (String line : lines) {
            if (isSafeLine(line)) {
                safetyCount++;
            } else {
                // Handle removing one
                int removeSafe = 0;
                String[] splitLine = line.split(" ");
                for (int j = 0; j < splitLine.length; j++) {
                    ArrayList<String> newSplitLine = new ArrayList<>(Arrays.asList(splitLine));
                    newSplitLine.remove(j);
                    if (isSafeLine(String.join(" ", newSplitLine))) {
                        removeSafe++;
                    }
                }

                if (removeSafe >= 1) {
                    safetyCount++;
                }
            }
        }
        System.out.println(safetyCount);
    }

    public static boolean isSafeLine(String line) {
        String[] values = line.split(" ");

        int i = 0;
        int increaseAmount = 0;
        int decreaseAmount = 0;
        boolean safe = true;
        for (String value : values) {
            if (i == values.length-1) {
                break;
            }

            int current = Integer.parseInt(value);
            int next = Integer.parseInt(values[i+1]);

            if (current < next) {
                increaseAmount++;
            } else {
                decreaseAmount++;
            }

            int abs = Math.abs(current - next);
            if (safe) {
                safe = (abs >= 1 && abs <= 3);
            }
            i++;
        }
        return (increaseAmount == 0 || decreaseAmount == 0) && safe;
    }
}
