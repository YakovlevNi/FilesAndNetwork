import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static Scanner scanner;
    public static void main(String[] args) {
        System.out.println("Введите путь до папки: ");
        scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        try {
            FileUtils.calculateFolderSize(input);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
