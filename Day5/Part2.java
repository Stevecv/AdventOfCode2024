import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
    public static HashMap<String, ArrayList<String>> pageList = new HashMap<>();
    public static void main(String[] args) {
        String code = "\n" +
                "47|53\n" +
                "97|13\n" +
                "97|61\n" +
                "97|47\n" +
                "75|29\n" +
                "61|13\n" +
                "75|53\n" +
                "29|13\n" +
                "97|29\n" +
                "53|29\n" +
                "61|53\n" +
                "97|53\n" +
                "61|29\n" +
                "47|13\n" +
                "75|47\n" +
                "97|75\n" +
                "47|61\n" +
                "75|61\n" +
                "47|29\n" +
                "75|13\n" +
                "53|13\n" +
                "\n" +
                "75,47,61,53,29\n" +
                "97,61,53,29,13\n" +
                "75,29,13\n" +
                "75,97,47,61,53\n" +
                "61,13,29\n" +
                "97,13,75,29,47";
        ArrayList<String> incorrectOrders = new ArrayList<>();
        String[] lines = code.split("\n");
        for (String line : lines) {
            if (line.contains("|")) {
                String[] values = line.split("\\|");
                if (!pageList.containsKey(values[0])) {
                    pageList.put(values[0], new ArrayList<>());
                }
                pageList.get(values[0]).add(values[1]);
            } else if (line.contains(",")) {
                if (!isCorrect(line)) {
                    System.out.println("Incorrect line: " + line);
                    incorrectOrders.add(String.join(",", line.split(",")));
                }
            }
        }

        ArrayList<String> correctOrders = new ArrayList<>();
        for (String incorrectOrder : incorrectOrders) {
            correctOrders.add(orderList(incorrectOrder));
        }

        int total = 0;
        for (String correctOrder : correctOrders) {
            System.out.println(correctOrder);
            String[] values = correctOrder.split(",");
            int middle = (int) Math.floor((double) values.length / 2);
            total += Integer.parseInt(values[middle]);
        }

        System.out.println(total);
    }

    public static String orderList(String line) {
        if (isCorrect(line)) {
            return line;
        }

        String[] values = line.split(",");
        boolean correct = true;
        int i = 0;
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> addToEnd = new ArrayList<>();
        for (String val : values) {
            if (!isValueCorrect(values, val, i)) {
                addToEnd.add(val);
            } else {
                list.add(val);
            }
            i++;
        }

        list.addAll(addToEnd);
        return orderList(String.join(",", list));
    }

    public static boolean isValueCorrect(String[] values, String val, int i) {
        boolean correct = true;
        for (String checkVal: Arrays.copyOfRange(values, i+1, values.length)) {
            if (!pageList.containsKey(val)) {
                correct = false;
                continue;
            }

            if (!pageList.get(val).contains(checkVal)) {
                correct = false;
            }
        }
        return correct;
    }

    public static boolean isCorrect(String line) {
        String[] values = line.split(",");
        boolean correct = true;
        int i = 0;
        for (String val : values) {
            if (!isValueCorrect(values, val, i)) {
                correct = false;
            }
            i++;
        }

        return correct;
    }
}
