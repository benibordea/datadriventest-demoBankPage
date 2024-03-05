package testcases;

import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.TestBase;

public class AddCustomerTest extends TestBase {

@Test(dataProvider="getData")
    public void addCustomer (String firstName, String lastName, String postCode) throws InterruptedException{

        driver.findElement(By.cssSelector(OR.getProperty("addCustBtn"))).click();
        driver.findElement(By.cssSelector(OR.getProperty("firstname"))).sendKeys(firstName);
        driver.findElement(By.cssSelector(OR.getProperty("lastname"))).sendKeys(lastName);
        Assert.fail("somereason");
        Thread.sleep(3000);
        driver.findElement(By.cssSelector(OR.getProperty("postcode"))).sendKeys(postCode);
        driver.findElement(By.cssSelector(OR.getProperty("subAddBtn"))).click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alerttext = "Customer added successfully with customer";
        Assert.assertTrue(alert.getText().contains(alerttext));
        
        alert.accept();
       
        
        


    }

//////////////////////////////////////////////
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
