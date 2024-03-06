package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import utilities.ExcelReader;

public class TestBase {

    /*-----Staff to initialize here:
     * Webdriver
     * Properties
     * Logs-add dep. -> create .log -> log3j.properties -> Logger class
     * ExtentReports
     * DB
     * Excel
     * Mail
     */
    public static WebDriver driver;
    public static Properties config = new Properties();
    public static Properties OR = new Properties();
    public static FileInputStream fis;
    private static final Logger log = Logger.getLogger("devpinoyLogger");
    public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir") + "\\src\\main\\resources\\excel\\testdata.xlsx");
    public static WebDriverWait wait;
        protected static ExtentReports extent;
        protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    
    @SuppressWarnings("deprecation")
    @BeforeSuite
    public void setUp() throws FileNotFoundException,IOException {

        PropertyConfigurator.configure(System.getProperty("user.dir") + "\\src\\main\\resources\\properties\\log4j.properties");
        
        if(driver==null){
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\properties\\Config.properties");
            config.load(fis);
            log.debug("Config file loaded !!!");

            fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\properties\\OR.properties");
            OR.load(fis);
            log.debug("OR file loaded !!!");
        }

        if(config.getProperty("browser").equals("firefox")){

            driver = new FirefoxDriver();
            log.debug("Firefox launched !!!");
        }else if (config.getProperty("browser").equals("chrome")) {

            driver=new ChromeDriver();
            log.debug("Chrome launched !!!");
            
        }else if (config.getProperty("browser").equals("edge")) {

            driver=new EdgeDriver();
            log.debug("Edge launched !!!");
        }
        driver.get(config.getProperty("testsiteurl"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        extent = new ExtentReports(System.getProperty("user.dir") + "/target/surefire-reports/html/ExtentReport.html", true); //D:\JavaProjects\datadriven\target\surefire-reports\html\
        System.out.println(System.getProperty("user.dir") + "/target/surefire-reports/html/ExtentReport.html");
    }
    

    public boolean isElementPresent (By by){
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @AfterSuite
    public void testDown() {
        
        if(driver!=null){
       driver.quit();
       extent.flush();
        extent.close();
    }
    log.debug("Test execution completed");
}

    @AfterMethod
    public void AfterMethod(){
        extent.endTest(test.get());
    }
}

