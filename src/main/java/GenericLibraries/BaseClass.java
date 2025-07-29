package GenericLibraries;

import PageObjectModel.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;


public class BaseClass
{
     public WebDriver driver;
     public LoginPage lp;
     public Logger logger;

    public Properties configprop;


    public void setup() throws IOException, InterruptedException {

        //Reading properties
        configprop = ConfigReader.getProperties();

        //For getting logs adding log properties class to our class
        logger= Logger.getLogger("LUMA");
        PropertyConfigurator.configure("src/test/resources/log4j.properties");
        //Getting the properties from config.properties

        TimeUnit.SECONDS.sleep(5);
        String browser=configprop.getProperty("browser");
        System.out.println(browser);
        if(browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        else if(browser.equals("firefox"))
        {
            WebDriverManager.firefoxdriver().setup();
            driver= new FirefoxDriver();
        }
        logger.info("-------------- Launching Browser----------------");

        lp = new LoginPage(driver);
    }

    //created for generating string for unique email id
     public static String randomstring()
     {
          String generatedString1= RandomStringUtils.randomAlphabetic(3);
         return(generatedString1);
     }


    public String resolvePlaceholders(String input) {
        if (input != null && input.startsWith("${") && input.endsWith("}")) {
            String key = input.substring(2, input.length() - 1);
            String value = configprop.getProperty(key);
            if (value == null) {
                throw new RuntimeException("Key '" + key + "' not found in Config.properties");
            }
            return value;
        }
        return input;
    }



}
