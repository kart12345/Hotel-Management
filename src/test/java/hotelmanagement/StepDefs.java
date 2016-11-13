package hotelmanagement;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class StepDefs {
    public WebDriver driver;

    String adminUserName = "admin", adminPassword = "password";
    String baseURL = "http://localhost:3003/";
    int noOfRows;
    @Before
    public void startBrowser() {

//        //----creating firefox webdriver object----For Windows-----------
//        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");

        //----creating firefox webdriver object----For Mac-----------
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");
        driver = new FirefoxDriver();
        driver.get(baseURL);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @After
    public void stopBrowser() {
//        killing driver object
        driver.quit();
    }

    @Given("^I am on the Home page$")
    public void verifyIamOnThePage() {
        Assert.assertTrue(driver.findElement(By.cssSelector(".navbar-brand")).isDisplayed());
    }

    @When("^I login as an admin$")
    public void adminLogin() {
        driver.findElement(By.cssSelector(".nav.navbar-nav > li:nth-child(2) > a")).click();
        this.waitForElementPresent(By.cssSelector(".modal-content"));
        Assert.assertTrue(driver.findElement(By.cssSelector(".modal-content")).isDisplayed());
        driver.findElement(By.cssSelector("#username")).sendKeys(adminUserName);
        driver.findElement(By.cssSelector("#password")).sendKeys(adminPassword);
        driver.findElement(By.cssSelector("#doLogin")).click();

    }

    @Then("^I should see create option on the page$")
    public void verifyCreateIsAvailable() {
        Assert.assertTrue(driver.findElement(By.cssSelector("#createHotel")).isDisplayed());
    }

    @When("^I enter hotel details as \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\", \"([^\"]*)\"$")
    public void enterDetailsForHotel(String hotelName,String hotelAddress,String ownerName,String phoneNumber,String email) {
        driver.findElement(By.cssSelector("#hotelName")).sendKeys(hotelName);
        driver.findElement(By.cssSelector("#address")).sendKeys(hotelAddress);
        driver.findElement(By.cssSelector("#owner")).sendKeys(ownerName);
        driver.findElement(By.cssSelector("#phone")).sendKeys(phoneNumber);
        driver.findElement(By.cssSelector("#email")).sendKeys(email);
    }

    @When("^I select create$")
    public void selectCreate() {
        driver.findElement(By.cssSelector("#createHotel")).click();
    }

    @Then("^\"([^\"]*)\" entry should be created successfully$")
    public void verifyTheEntry(String hotelName) {
        this.waitForElementPresent(By.cssSelector(".container"));
        Assert.assertTrue(driver.findElement(By.cssSelector(".container")).getText().contains(hotelName));
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='hotelRow']//p[contains(text(),'"+hotelName+"')]")).isDisplayed());
    }

    @When("^I delete the hotel \"([^\"]*)\" details$")
    public void deleteTheEntry(String hotelName) {
        this.waitForElementPresent(By.cssSelector(".container"));
        driver.findElement(By.xpath("//div[@class='hotelRow']//span[@id=1]").click();

    }

    @Then("^I should not see \"([^\"]*)\" details in the list$")
    public void verifyEntryIsDeleted(String hotelName) {
        this.waitForElementPresent(By.cssSelector(".container"));
        Assert.assertFalse(driver.findElement(By.xpath("//div[@class='hotelRow']//span[@id=1]").isDisplayed());

    }

    private void waitForElementPresent(By element) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));

    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
