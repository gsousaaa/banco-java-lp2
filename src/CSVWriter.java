import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {
    public static void appendToCSV(String fileName, String data) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(data + "\n");
        } catch (IOException e) {
            System.out.println("Erro ao gravar no arquivo CSV: " + e.getMessage());
        }
    }

}