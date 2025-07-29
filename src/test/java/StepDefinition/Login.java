package StepDefinition;

import GenericLibraries.BaseClass;
import GenericLibraries.WebDriverUtility;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class Login extends BaseClass
{
    WebDriverUtility wu = new WebDriverUtility(driver);
    @Given("User Launch Chrome browser")
    public void user_launch_chrome_browser() {
        try {
            setup();
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("Browser launched successfully");
    }

    @Given("User enters URL {string}")
    public void user_enters_url(String url) {
        url = resolvePlaceholders(url);
        logger.info("-------------- Entering URL: " + url + " ----------------");
        driver.get(url);
        logger.info("URL entered successfully");
        driver.manage().window().maximize();

    }

    @Given("User clicks on Create An Account button")
    public void user_clicks_on_create_an_account_button() throws InterruptedException {
//        lp.setLnkCreateAccount();
        logger.info("Clicked on Create An Account button successfully");
//        lp.setBtnDismiss();
    }

    @When("User enters First Name as {string} and Last Name as {string}")
    public void user_enters_first_name_as_and_last_name_as(String firstName, String lastName) {
        firstName = resolvePlaceholders(firstName) + randomstring();
        lastName = resolvePlaceholders(lastName) + randomstring();
        lp.setUserName(firstName);
        lp.setLastName(lastName);
        logger.info("Entered First Name and Last Name successfully: " + firstName + " " + lastName);
    }

    @When("User enters Email as {string}")
    public void user_enters_email_as(String email) {
        email = resolvePlaceholders(email);
        lp.setEmailAddress(email+randomstring()+"@gmail.com");
        logger.info("Entered Email: " + email);
    }

    @When("User enters password as {string}")
    public void user_enters_password_as(String pwd) {
        pwd = resolvePlaceholders(pwd);
        lp.setPassword(pwd);
        logger.info("Entered Password successfully");
    }

    @When("User enters Confirm Password as {string}")
    public void user_enters_confirm_password_as(String confirmPwd) {
        confirmPwd = resolvePlaceholders(confirmPwd);
        lp.setConfirmPassword(confirmPwd);
        logger.info("Entered Confirm Password successfully");
    }

    @Then("User clicks on Create Account button")
    public void user_clicks_on_create_account_button() throws InterruptedException {
        wu.pageloadtimeout(); // Wait for 5 seconds to ensure all elements are loaded
        lp.clickCreateAccount();
        logger.info("Clicked on Create Account button successfully");
        wu.pageloadtimeout();
        System.out.println(driver.getTitle());
    }

    @Then("Home Page Title should be {string}")
    public void home_page_title_should_be(String title) {
        title = resolvePlaceholders(title);
        String actualTitle = driver.getTitle();
        if(actualTitle != null && !actualTitle.isEmpty())
        {
            Assert.assertEquals(title, actualTitle);
            logger.info("Home Page Title verified successfully: " + actualTitle);
        }
        else {
            logger.info("Home Page Mismatch");
            Assert.fail("Home Page Title validation failed. Expected: " + title + ", Actual: " + actualTitle);
        }
    }

    @Then("close browser")
    public void close_browser() {
        if (driver != null) {
            driver.quit();
            logger.info("Browser closed successfully");
        } else {
            logger.info("Driver is null, cannot close browser");
        }
    }


}
