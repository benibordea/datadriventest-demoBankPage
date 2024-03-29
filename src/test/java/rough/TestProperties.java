package rough;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {

    public static void main(String[] args) throws IOException {
        
        Properties config = new Properties();
        Properties OR = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\resources\\properties\\Config.properties");
        config.load(fis);

        fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\resources\\properties\\OR.properties");
        OR.load(fis);

        config.getProperty("browser");
        OR.getProperty("bmlBtn");

    }

}
