package core.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ExcelUtils {

    private ExcelUtils() {
    }

    public static List<Map<String, String>> readExcelAsList(String filePath, String sheetName) {
        List<Map<String, String>> maps = new ArrayList<>();

        try (Workbook workbook = getWorkbook(filePath)) {
            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new RuntimeException("Excel sheet not found. Please provide valid sheet name.");
            }

            Row headerRow = sheet.getRow(0);
            List<String> headers = IntStream.range(0, headerRow.getLastCellNum())
                    .mapToObj(j -> headerRow.getCell(j).getStringCellValue())
                    .toList();

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                Map<String, String> map = IntStream.range(0, headers.size())
                        .boxed()
                        .collect(Collectors.toMap(
                                j -> headers.get(j),
                                j -> row.getCell(j).getStringCellValue()
                        ));

                maps.add(map);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found at location " + filePath);
        } catch (IOException e) {
            throw new RuntimeException("An error encountered while reading file present at location " + filePath);
        }

        System.out.println(maps);
        return maps;
    }

    public static void writeExcelValue(String filePath, String sheetName, int rowIndex, int columnIndex, String value) {
        Workbook workbook;

        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            if (filePath.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(fileInputStream);
            } else if (filePath.endsWith(".xls")) {
                workbook = new HSSFWorkbook(fileInputStream);
            } else {
                throw new RuntimeException("Invalid file format. Only .xlsx and .xls files are supported.");
            }

            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new RuntimeException("Excel sheet not found. Please provide valid sheet name.");
            }

            Row row = getRow(sheet, rowIndex);
            Cell cell = getCell(row, columnIndex);
            cell.setCellValue(value);

            try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
                workbook.write(fileOutputStream);
            }

            workbook.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found at location " + filePath);
        } catch (IOException e) {
            throw new RuntimeException("An error encountered while reading file present at location " + filePath);
        }

        System.out.println("Value written to Excel successfully.");
    }

    private static Workbook getWorkbook(String filePath) {
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            if (filePath.endsWith(".xlsx")) {
                return new XSSFWorkbook(fileInputStream);
            } else if (filePath.endsWith(".xls")) {
                return new HSSFWorkbook(fileInputStream);
            } else {
                throw new RuntimeException("Invalid file format. Only .xlsx and .xls files are supported.");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found at location " + filePath);
        } catch (IOException e) {
            throw new RuntimeException("An error encountered while reading file present at location " + filePath);
        }
    }

    private static Row getRow(Sheet sheet, int rowIndex) {
        Row row = sheet.getRow(rowIndex);
        if (row == null) {
            row = sheet.createRow(rowIndex);
        }
        return row;
    }

    private static Cell getCell(Row row, int columnIndex) {
        Cell cell = row.getCell(columnIndex);
        if (cell == null) {
            cell = row.createCell(columnIndex);
        }
        return cell;
    }
}
