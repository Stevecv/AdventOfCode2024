import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        String code = "";

        HashMap<String, ArrayList<String>> pageList = new HashMap<>();
        ArrayList<String> correctOrders = new ArrayList<>();
        String[] lines = code.split("\n");
        for (String line : lines) {
            if (line.contains("|")) {
                String[] values = line.split("\\|");
                if (!pageList.containsKey(values[0])) {
                    pageList.put(values[0], new ArrayList<>());
                }
                pageList.get(values[0]).add(values[1]);
            } else if (line.contains(",")) {
                String[] values = line.split(",");
                boolean correct = true;
                int i = 0;
                for (String val : values) {
                    for (String checkVal: Arrays.copyOfRange(values, i+1, values.length)) {
                        if (!pageList.containsKey(val)) {
                            correct = false;
                            continue;
                        }

                        if (!pageList.get(val).contains(checkVal)) {
                            correct = false;
                        }
                    }
                    i++;
                }

                if (correct) {
                    correctOrders.add(String.join(",", values));
                }
            }
        }

        int total = 0;
        for (String correctOrder : correctOrders) {
            String[] values = correctOrder.split(",");
            int middle = (int) Math.floor((double) values.length / 2);
            total += Integer.parseInt(values[middle]);
        }

        System.out.println(total);
    }
}
