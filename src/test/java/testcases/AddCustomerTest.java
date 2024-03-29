package testcases;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.TestBase;

public class AddCustomerTest extends TestBase {

@Test(dataProvider="getData")
    public void addCustomer (String firstName, String lastName, String postCode){

        driver.findElement(By.cssSelector(OR.getProperty("addCustBtn"))).click();
        driver.findElement(By.cssSelector(OR.getProperty("firstname"))).sendKeys(firstName);
        driver.findElement(By.cssSelector(OR.getProperty("lastname"))).sendKeys(lastName);
        driver.findElement(By.cssSelector(OR.getProperty("postcode"))).sendKeys(postCode);
        driver.findElement(By.cssSelector(OR.getProperty("subAddBtn"))).click();

    }


    @DataProvider
    public Object[][] getData(){

    String sheetName = "AddCustomerTest";
    int rows = excel.getRowCount(sheetName);
    int cols = excel.getColumnCount(sheetName);

    Object[][] data = new Object[rows - 1][3]; // Three columns for firstName, lastName, and postCode

    for (int rowNum = 2; rowNum <= rows; rowNum++) {
        data[rowNum - 2][0] = excel.getCellData(sheetName, 0, rowNum); // firstName
        data[rowNum - 2][1] = excel.getCellData(sheetName, 1, rowNum); // lastName
        data[rowNum - 2][2] = excel.getCellData(sheetName, 2, rowNum); // postCode
    }

    return data;

}
}
