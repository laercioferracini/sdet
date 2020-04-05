import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author lferracini
 * @project = json-project
 * @since <pre>04/04/2020</pre>
 */
public class CustomerDetailsTest {

    @Test(dataProvider = "testDataProvider")
    public void testCustomer(String id, String user, String obs) {

        System.out.printf("%s - %s - %s -%n", id, user, obs);

    }

    @DataProvider(name = "testDataProvider")
    public Object[][] getDataProvider() {
        FileInputStream fileInputStream = null;
        Object[][] data;
        DataFormatter formatter = new DataFormatter();
        try {
            fileInputStream = new FileInputStream("testdata.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            int rows = sheet.getPhysicalNumberOfRows();
            XSSFRow row = sheet.getRow(0);
            int columns = row.getLastCellNum();
            data = new Object[rows - 1][columns];
            for (int i = 0; i < rows - 1; i++) {
                row = sheet.getRow(i + 1);
                for (int j = 0; j < columns; j++) {
                    XSSFCell cell = row.getCell(j);
                    data[i][j] = formatter.formatCellValue(cell);
                    System.out.println(formatter.formatCellValue(cell));
                }
            }

        } catch (IOException e) {
            data = new Object[0][0];
            e.printStackTrace();
        }

        return data;
    }
}
