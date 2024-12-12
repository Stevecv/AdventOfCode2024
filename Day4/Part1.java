import java.util.ArrayList;

public class Day4 {
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
        for (String line: code.split("\n")) {
            int j = 0;
            int lineWidth = line.length();
            for (char letter: line.toCharArray()) {
                if (letter != 'X') {
                    j++;
                    continue;
                }
                ArrayList<Boolean> corrects = new ArrayList<>();
                corrects.add(checkDirection(j, i, 1, 0, lineWidth, 'M', 1));
                corrects.add(checkDirection(j, i, -1, 0, lineWidth, 'M', 1));
                corrects.add(checkDirection(j, i, 0, 1, lineWidth, 'M', 1));
                corrects.add(checkDirection(j, i, 0, -1, lineWidth, 'M', 1));

                corrects.add(checkDirection(j, i, 1, 1, lineWidth, 'M', 1));
                corrects.add(checkDirection(j, i, -1, 1, lineWidth, 'M', 1));
                corrects.add(checkDirection(j, i, 1, -1, lineWidth, 'M', 1));
                corrects.add(checkDirection(j, i, -1, -1, lineWidth, 'M', 1));

                for (Boolean correct: corrects) {
                    if (correct) {
                        matches++;
                    }
                }
                j++;
            }
            i++;
        }

        System.out.println(matches);
    }

    public static char getLetter(int x, int y, int length) {
        int i = (y * length) + x;
        return code.replace("\n", "").toCharArray()[i];
    }

    public static boolean checkDirection(int x, int y, int vX, int vY, int length, char checkFor, int depth) {
        vX = vX*depth;
        vY = vY*depth;

        int checking = ((y+vY) * length) + (x+vX);
        if (x+vX >= length || checking >= code.replace("\n", "").length() || checking < 0 || x+vX < 0) {
            return false;
        }
        boolean correct = false;

        if (getLetter(x+vX, y+vY, length) == checkFor) {
            correct = true;
        }

        switch (checkFor) {
            case 'X' -> checkFor = 'M';
            case 'M' -> checkFor = 'A';
            case 'A' -> checkFor = 'S';
            case 'S' -> checkFor = ' ';
        }


        if (checkFor == ' ' || !correct) {
            return correct;
        }

        return checkDirection(x, y, vX/depth, vY/depth, length, checkFor, depth + 1);
    }
}
