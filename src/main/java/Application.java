import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Logger;

public class Application {
    private static Logger logger = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) throws Exception {
        logger.info("Hello World");

        String inputPath = "src/main/resources/input.csv";
        String outputPath = "src/main/resources/output.csv";

        List<List<String>> records = new ArrayList<>();

        try (CSVReader csvReader = new CSVReader(new FileReader(inputPath))) {

            String[] values = null;
            while((values = csvReader.readNext()) != null) {
                records.add(new ArrayList<>(Arrays.asList(values)));
            }

            



        }
    }
}
