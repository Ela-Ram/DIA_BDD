package common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

    private static final String FILE_PATH = ConfigReader.getProperty("Excel_Path");
    private static final Map<String, List<Map<String, String>>> sheetDataCache = new HashMap<>();

    // Load Excel file and cache all sheet data
    public static void loadAllSheets() {
        try (FileInputStream fis = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(fis)) {

            DataFormatter formatter = new DataFormatter();

            for (Sheet sheet : workbook) {
                List<Map<String, String>> sheetData = new ArrayList<>();
                Iterator<Row> rowIterator = sheet.iterator();
                if (!rowIterator.hasNext()) continue;

                Row headerRow = rowIterator.next();
                List<String> headers = new ArrayList<>();
                for (Cell cell : headerRow) {
                    headers.add(formatter.formatCellValue(cell));
                }

                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    Map<String, String> rowData = new LinkedHashMap<>();
                    for (int i = 0; i < headers.size(); i++) {
                        Cell cell = row.getCell(i);
                        String value = formatter.formatCellValue(cell);
                        rowData.put(headers.get(i), value);
                    }
                    sheetData.add(rowData);
                }

                sheetDataCache.put(sheet.getSheetName(), sheetData);
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to load Excel file: " + FILE_PATH, e);
        }
    }

    // Get test data by testCaseID
    public static Map<String, String> getTestData(String sheetName, String testCaseID) {
        List<Map<String, String>> rows = sheetDataCache.get(sheetName);
        if (rows == null) {
            throw new RuntimeException("Sheet '" + sheetName + "' not found in cache. Did you call loadAllSheets()?");
        }

        for (Map<String, String> row : rows) {
            String id = row.getOrDefault("TestCaseID", "");
            if (id.equalsIgnoreCase(testCaseID)) {
                return row;
            }
        }

        throw new RuntimeException("TestCaseID '" + testCaseID + "' not found in sheet '" + sheetName + "'");
    }

    // Get all values for a specific column (header)
    public static List<String> getValuesForHeader(String sheetName, String headerName) {
        List<Map<String, String>> rows = sheetDataCache.get(sheetName);
        if (rows == null) {
            throw new RuntimeException("Sheet '" + sheetName + "' not found in cache.");
        }

        List<String> values = new ArrayList<>();
        for (Map<String, String> row : rows) {
            values.add(row.getOrDefault(headerName, ""));
        }
        return values;
    }
}
