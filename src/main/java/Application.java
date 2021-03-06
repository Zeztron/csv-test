import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Supplier;
import java.util.logging.Logger;

public class Application {
    private static Logger logger = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) throws Exception {
        logger.info("Hello World");

        String inputPath = "src/main/resources/input.csv";
        String outputPath = "src/main/resources/output.csv";

        List<ArrayList<String>> records = new ArrayList<>();

        try (
                CSVReader csvReader = new CSVReader(new FileReader(inputPath));
                CSVWriter csvWriter = new CSVWriter(new FileWriter(outputPath), CSVWriter.DEFAULT_SEPARATOR,
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.RFC4180_LINE_END)
        ) {

            String[] values = null;
            while((values = csvReader.readNext()) != null) {
                records.add(new ArrayList<>(Arrays.asList(values)));
            }

            records.get(0).add("report_date");

            int index = 0;
            ArrayList<Integer> rowNumsToDelete = new ArrayList<>();
            for (List<String> record : records.subList(1, records.size())) {

                record.add("date");
                index++;
                if (record.contains("median")) {
                    rowNumsToDelete.add(index);
                }


                Collections.replaceAll(record, "-", null);

            }



            if (rowNumsToDelete.size() > 0) {
                // Sort the arraylist of row numbers to delete so that each removal does not shift the records.

                rowNumsToDelete.sort(Collections.reverseOrder());

                for (int rowNumToDelete : rowNumsToDelete) {
                    records.remove(rowNumToDelete);
                }
            }


//            if (medianFound) {
//                logger.info(String.valueOf(medianRowNumber));
//                records.remove(medianRowNumber);
//            }

            for (ArrayList<String> record : records) {
                String[] temp = record.toArray(new String[] {});
                csvWriter.writeNext(temp);
            }





        }
    }
}
