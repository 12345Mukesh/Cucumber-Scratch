package GenericLibraries;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class WebDriverUtility
{
    private WebDriver driver; // Make it private, and it will be initialized by the constructor

    // *** CRITICAL FIX: Constructor to inject the WebDriver instance ***
    public WebDriverUtility(WebDriver driver) {
        this.driver = driver;
    }

    /*
     * @author Mukesh
     * Description This method is going to work as implicitly wait
     * @param WebDriver driver (removed as it's now 'this.driver')
     */
    public void pageloadtimeout() throws InterruptedException { // No longer needs WebDriver as parameter
        // Using modern Duration for implicit wait. TimeUnit is deprecated.
       TimeUnit.SECONDS.sleep(5); // Using 'this.driver'
    }

    public void frameAvailable(String text)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(text)));
    }

    public WebElement elementToBeClickable(String text)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(text)));
        return null;
    }

    public boolean clickIfVisibleAndClickable(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Wait until element is visible and clickable
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));

            element.click();
            System.out.println("Element clicked successfully: " + element.getText());
            return true;
        } catch (Exception e) {
            System.out.println("Element not present: " + e.getMessage());
            return false;
        }
    }

    public void elementClickUsingJse()
    {
        JavascriptExecutor jse = (JavascriptExecutor) driver; // Using 'this.driver'
        jse.executeScript("arguments[0].click();", driver); // Using 'this.driver'
    }
    public void acceptAlert() { // No longer needs WebDriver as parameter
        this.driver.switchTo().alert().accept(); // Using 'this.driver'
    }

    public void acceptDismiss() { // No longer needs WebDriver as parameter
        this.driver.switchTo().alert().dismiss(); // Using 'this.driver'
    }


}
