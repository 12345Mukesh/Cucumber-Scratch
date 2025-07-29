package PageObjectModel;

import GenericLibraries.WebDriverUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.logging.Logger;

public class LoginPage {
    public WebDriver ldriver;
    WebDriverUtility wl = new WebDriverUtility(ldriver) ;
    public Logger logger;



    public LoginPage(WebDriver rdriver)
    {
        ldriver=rdriver;
        PageFactory.initElements(rdriver,this);
    }

    @FindBy(xpath="//div[@class='panel header']//a[normalize-space()='Create an Account']")
  @CacheLookup
    WebElement lnkCreateAccount;

    @FindBy(xpath="//div[@id='dismiss-button' and @class='btn skip']")
    @CacheLookup
    WebElement btnDismiss;

    @FindBy(xpath="//div[@id='dismiss-button']")
    @CacheLookup
    WebElement btnClosebtn;

    @FindBy(id="firstname")
    @CacheLookup
    WebElement txtFirstName;

    @FindBy(id="lastname")
    @CacheLookup
    WebElement txtLastName;

    @FindBy(id="email_address")
    @CacheLookup
    WebElement txtEmailAddress;

    @FindBy(name="password")
    @CacheLookup
    WebElement txtPassword;

    @FindBy(name="password_confirmation")
    @CacheLookup
    WebElement txtConfirmPassword;

    @FindBy(xpath="//button[@title='Create an Account']//span[contains(text(),'Create an Account')]")
@CacheLookup
    WebElement btnCreateAccount;

    public void setBtnDismiss() {
        try {
            // Switch to iframe safely
            wl.frameAvailable("aswift_3");

            // Attempt to click dismiss button inside iframe by ID
            WebElement dismissButton = wl.elementToBeClickable("dismiss-button");  // Assuming this uses By.id internally
            dismissButton.click();

            logger.info("Clicked on dismiss button inside iframe.");
        }
        catch (Exception e)
        {
//            logger.info("Dismiss button in iframe not found or not clickable. Trying fallback options...");

            // Fallback: check for both types of dismiss buttons dynamically
            try {
                List<WebElement> dismissList = ldriver.findElements(By.xpath("//div[@id='dismiss-button' and @class='btn skip']"));
                List<WebElement> closeBtnList = ldriver.findElements(By.xpath("//div[@id='dismiss-button']"));

                if (!dismissList.isEmpty()) {
                    wl.clickIfVisibleAndClickable(dismissList.get(0));
                    logger.info("Clicked on btnDismiss (class='btn skip')");
                } else if (!closeBtnList.isEmpty()) {
                    wl.clickIfVisibleAndClickable(closeBtnList.get(0));
                    logger.info("Clicked on btnClosebtn (fallback)");
                } else {
                    logger.info("No dismiss buttons found in fallback.");
                }
            } catch (Exception ex) {
//                logger.info("Exception while clicking fallback dismiss buttons: " + ex.getMessage());
            }
        } finally {
            // Always return to default content
            try {
                ldriver.switchTo().defaultContent();
            } catch (Exception e) {
//                logger.info("Unable to switch to default content: " + e.getMessage());
            }
        }
    }





    public void setUserName(String uname)
    {
        txtFirstName.clear();
        txtFirstName.sendKeys(uname);
    }

    public void setLastName(String pwd)
    {
        txtLastName.clear();
        txtLastName.sendKeys(pwd);
    }


    public void setEmailAddress(String email)
    {
        txtEmailAddress.clear();
        txtEmailAddress.sendKeys(email);
    }


    public void setPassword(String pwd)
    {
        txtPassword.clear();
        txtPassword.sendKeys(pwd);
    }

    public void setConfirmPassword(String confirmPwd)
    {
        txtConfirmPassword.clear();
        txtConfirmPassword.sendKeys(confirmPwd);
    }


    public void setLnkCreateAccount()
    {
        lnkCreateAccount.click();
    }

    public void clickCreateAccount()
    {
        btnCreateAccount.click();
    }



}
