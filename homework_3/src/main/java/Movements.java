import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.BiFunction;


public class Movements {
    private static List<Double> income = new ArrayList<>();
    private static List<Double> expencees = new ArrayList<>();

    public Movements(String pathMovementsCsv) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(pathMovementsCsv));
            for (String line : lines) {
                String[] fragments = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                line.replaceAll("[А-Яа-я]+", "");
                fragments[5] = fragments[5].replaceAll("\\W+\\d+", " ");
                fragments[7] = fragments[7].replaceAll("\"", "").replaceAll(",", ".");
                fragments[6] = fragments[6].replaceAll("\"", "").replaceAll(",", ".");
                income.add(Double.parseDouble(fragments[6]));
                double buffer = Double.parseDouble(fragments[7]);
                expencees.add(buffer);
                HashMap<String, Double> movementsMap = new HashMap<>();
                movementsMap.put(fragments[5], buffer);
                for (Map.Entry<String, Double> item : movementsMap.entrySet()) {
                    System.out.println(item.getKey() + " - " + item.getValue());


                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static double getExpenseSum() {
        double sum = 0;
        for (int i = 0; i < expencees.size(); i++) {
            sum += expencees.get(i);
        }
        return sum;
    }


    public double getIncomeSum() {
        double sum = 0;
        for (int i = 0; i < income.size(); i++) {
            sum += income.get(i);
        }
        return sum / 2;
    }

}
