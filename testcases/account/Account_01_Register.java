package account;

import commons.BasePage;
import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.CustomerPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Account_01_Register extends BaseTest {
    private WebDriver driver;
    private HomePageObject homePage;
    private RegisterPageObject registerPage;
    private LoginPageObject loginPage;
    private CustomerPageObject customerPage;
    private String emailAddress = getEmailRandom();

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getBrowserName(browserName);
    }
    @Test
    public void User_01_Register_Empty_Data() {

        homePage = new HomePageObject(driver);
        homePage.clickToRegisterLink();

        registerPage = new RegisterPageObject(driver);
        registerPage.clickToRegisterButton();
        Assert.assertEquals(registerPage.getFirstNameErrorMessageText(), "First name is required.");
        Assert.assertEquals(registerPage.getLastNameErrorMessageText(), "Last name is required.");
        Assert.assertEquals(registerPage.getEmailErrorMessageText(), "Email is required.");
        Assert.assertEquals(registerPage.getPasswordErrorMessageText(), "Password is required.");
        Assert.assertEquals(registerPage.getConfirmPasswordErrorMessageText(), "Password is required.");
    }

    public void User_02_Register_Invalid_Email() {
        registerPage.clickToNopCommerceImage();
        homePage = new HomePageObject(driver);

        homePage.clickToRegisterLink();
        registerPage = new RegisterPageObject(driver);

        registerPage.enterToFirstNameTextbox("John");
        registerPage.enterToFLastNameTextbox("Kennedy");
        registerPage.enterToEmailTextbox(getEmailRandom());
        registerPage.enterToFPasswordextbox("123456");
        registerPage.enterToConfirmPasswordTextbox("123456");
        registerPage.clickToRegisterButton();
        Assert.assertEquals(registerPage.getEmailErrorMessageText(), "Please enter a valid email address.");
    }

    public void User_03_Register_Invalid_Password() {
        registerPage.clickToNopCommerceImage();
        homePage = new HomePageObject(driver);

        homePage.clickToRegisterLink();
        registerPage = new RegisterPageObject(driver);

        registerPage.enterToFirstNameTextbox("John");
        registerPage.enterToFLastNameTextbox("Kennedy");
        registerPage.enterToEmailTextbox(getEmailRandom());
        registerPage.enterToFPasswordextbox("123");
        registerPage.enterToConfirmPasswordTextbox("123");
        registerPage.clickToRegisterButton();
        Assert.assertEquals(registerPage.getPasswordErrorMessageText(), "Password must meet the following rules:\nmust have at least 6 characters and not greater than 64 characters");

    }

    public void User_04_Register_Incorrect_Confirm_Password() {
        registerPage.clickToNopCommerceImage();
        homePage = new HomePageObject(driver);

        homePage.clickToRegisterLink();
        registerPage = new RegisterPageObject(driver);

        registerPage.enterToFirstNameTextbox("John");
        registerPage.enterToFLastNameTextbox("Kennedy");
        registerPage.enterToEmailTextbox(getEmailRandom());
        registerPage.enterToFPasswordextbox("123456");
        registerPage.enterToConfirmPasswordTextbox("123123");
        registerPage.clickToRegisterButton();
        Assert.assertEquals(registerPage.getConfirmPasswordErrorMessageText(), "The password and confirmation password do not match.");
    }

    public void User_05_Register_Success() {
        registerPage.clickToNopCommerceImage();
        homePage = new HomePageObject(driver);

        homePage.clickToRegisterLink();
        registerPage = new RegisterPageObject(driver);

        registerPage.enterToFirstNameTextbox("John");
        registerPage.enterToFLastNameTextbox("Kennedy");
        registerPage.enterToEmailTextbox(emailAddress);
        registerPage.enterToFPasswordextbox("123456");
        registerPage.enterToConfirmPasswordTextbox("123456");
        registerPage.clickToRegisterButton();
        Assert.assertEquals(registerPage.getRegisterSuccessMessageText(), "Your registration completed");

    }

    public void User_06_Login_Success() {
        registerPage.clickToNopCommerceImage();
        homePage = new HomePageObject(driver);
        homePage.clickToLoginLink();

        loginPage = new LoginPageObject(driver);
        loginPage.enterToEmailTextbox(emailAddress);
        loginPage.enterToPasswordTextbox("123456");
        loginPage.clickToLoginButton();

        homePage = new HomePageObject(driver);
        homePage.clickToMyAccountLink();

        customerPage = new CustomerPageObject(driver);
        Assert.assertEquals(customerPage.getFirstNameTextboxAttributeValue(), "John");
        Assert.assertEquals(customerPage.getLastNameTextboxAttributeValue(), "Kennedy");
        Assert.assertEquals(customerPage.getEmailTextboxAttributeValue(), emailAddress);




    }

    @AfterClass
    public void afterClass() {
        closeBrowser();
    }
}
