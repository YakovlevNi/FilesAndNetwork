import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {

    public static long calculateFolderSize(String path) throws IOException {

        Path folder = Paths.get(path);
        long weight = Files.walk(folder).map(Path::toFile).filter(File::isFile).mapToLong(File::length).sum();
        double roundedResult = ((weight * (9.31 * Math.pow(10, -10))));

        String result = String.format("%.3f", roundedResult);
        System.out.println("Размер папки " + path + " составляет " + result + " Гб");

        return weight;
    }
}
