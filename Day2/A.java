public class Main {
    public static void main(String[] args) {
        String code = """
                """;

        String[] lines = code.split("\n");

        int safetyCount = 0;

        for (String line : lines) {
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
            if ((increaseAmount == 0 || decreaseAmount == 0) && safe) {
                safetyCount++;
            }
        }
        System.out.println(safetyCount);
    }
}
