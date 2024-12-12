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
    public static ArrayList<Character> debugChart = new ArrayList<>();

    public static void main(String[] args) {
        for (int i = 0; i < code.replace("\n", "").length(); i++) {
            debugChart.add('.');
        }

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

                int counter = 0;
                for (Boolean correct: corrects) {
                    if (correct) {
                        matches++;
                        System.out.println(counter);
                    }

                    counter++;
                }
                System.out.println("-----------------------------------------");
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

    public static void setLetter(int x, int y, int length, char letter) {
        int i = (y * length) + x;
        if (debugChart.get(i) == '.') {
            debugChart.set(i, letter);
        }
    }

    public static boolean checkDirection(int x, int y, int vX, int vY, int length, char checkFor, int depth) {
        if (depth == 1) {
            System.out.println("");
        }
        vX = vX*depth;
        vY = vY*depth;

        int checking = ((y+vY) * length) + (x+vX);
        if (x+vX >= length-1 || checking >= code.replace("\n", "").length() || checking < 0 || x+vX < 0) {
            System.out.println("Leave early: " + checking + ", " + vX + ", " + vY + " (Started at: " + x + "," + y + ")");
            return false;
        }
        boolean correct = false;

        if (getLetter(x+vX, y+vY, length) == checkFor) {
            correct = true;
        }

        System.out.println(getLetter(x+vX, y+vY, length) + ": " + correct);
        switch (checkFor) {
            case 'X' -> checkFor = 'M';
            case 'M' -> checkFor = 'A';
            case 'A' -> checkFor = 'S';
            case 'S' -> checkFor = ' ';
        }

        if (!correct) {
            System.out.println("Leave");
        }
        if (checkFor == ' ' || !correct) {
            if (correct) {
                System.out.println("Success");

                setLetter(x, y, length, 'X');
                setLetter(x+((vX/depth)*2), y+((vY/depth)*2), length, 'M');
                setLetter(x+((vX/depth)*3), y+((vY/depth)*3), length, 'A');
                setLetter(x+((vX/depth)*4), y+((vY/depth)*4), length, 'S');
            }
            return correct;
        }

        return checkDirection(x, y, vX/depth, vY/depth, length, checkFor, depth + 1);
    }
}
