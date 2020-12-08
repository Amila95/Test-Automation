import org.hamcrest.CoreMatchers;
import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.junit.internal.TextListener;
import org.junit.jupiter.api.Order;
import org.junit.runner.JUnitCore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import testlink.api.java.client.TestLinkAPIResults;
import testlink.api.java.client.TestLinkAPIException;
import java.io.IOException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import org.openqa.selenium.JavascriptExecutor;
import static org.junit.Assert.*;
import org.openqa.selenium.JavascriptExecutor;



public class AdminUserTests {
    @Rule
    public ErrorCollector collector = new ErrorCollector();

    LoginOperations loginOps = new LoginOperations();


    DriverSetup driverSetup = new DriverSetup();

    String user2 = "admin";
    String pass2 = "admin";

    String user3 = "";
    String pass3 = "";

    String user4 = "";
    String pass4 = "Typefi_2019";

    String user5 = "";
    String pass5 = "Typefi_2019ffffff";

    String user6 = "udissanayake@typefi.com";
    String pass6 = "";

    String user7 = "udissanayake@typefi.comhjg";
    String pass7 = "";


    String LoginPageUrl = "http://localhost:8080/";


    WebDriver driver;

    private void preTestActions() throws IOException {

        DriverSetup.currentDriverType = DriverSetup.DriverTypes.Chrome;
        driver = DriverSetup.SetupWebDriver();
    }

    private void postTestActions() {
        try {

            driver.close();
        } catch (Exception e) {
            System.out.println("Problem closing browser");
        }
    }

    @Test
    public void AddActiveUser() throws Exception {

        preTestActions();
        driver.get(LoginPageUrl);
        driver.manage().window().maximize();

        try {
            collector.checkThat(
                    "Succusfull log in to '" + LoginPageUrl + "' with username: '" + user2 + "' and password: '" + pass2 + "' browser: '" + DriverSetup.currentDriverType.toString() + "'",
                    loginOps.login(driver, user2, pass2, false, LoginPageUrl),
                    CoreMatchers.equalTo(true)
            );

            collector.checkThat(
                    "Was allowed to log in with valid credentials. Browser: '" + DriverSetup.currentDriverType.toString() + "'",
                    loginOps.CheckIfLoggedIn(driver),
                    CoreMatchers.equalTo(true)
            );
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            driver.findElement(By.linkText("Admin")).click();

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            driver.findElement(By.linkText("Users")).click();

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            driver.findElement(By.xpath("//*[@id=\"typefi-add-user\"]")).click();


            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            driver.findElement(By.xpath("//*[@id=\"input-user-name\"]")).sendKeys("user1");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            driver.findElement(By.xpath("//*[@id=\"input-user-email\"]")).sendKeys("desktopuser1@typefi.aws");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            driver.findElement(By.xpath("//*[@id=\"input-user-password\"]")).sendKeys("-lL3eOmdO$");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            driver.findElement((By.xpath("//*[@id=\"input-confirm-user-password\"]"))).sendKeys("-lL3eOmdO$");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            driver.findElement(By.xpath("//*[@id=\"input-user-active\"]")).click();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            driver.findElement(By.xpath("//*[@id=\"typefi-add-new-user\"]")).click();


            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();

            }




//           WebElement checkbox = driver.findElement(By.xpath(""));
//            Assert.assertEquals("null",checkbox.getAttribute("checked"));

//            checkbox.click();

//            Assert.assertEquals("true",checkbox.getAttribute("checked"));


//            WebElement element = driver.findElement(By.xpath("//body/div[1]/div[1]/div[2]/nav[1]/div[2]/div[1]/div[1]/button[1]/i[1]"));
//            String strng = element.getText();
//            System.out.println(strng);
//            Assert.assertEquals("Copy",strng);




            //System.out.println(driver.switchTo().alert().getText());



            //System.out.println(driver.switchTo().alert().getText());

            postTestActions();

//            TestLinkIntegration.updateResults("", null, TestLinkAPIResults.TEST_PASSED);
        } catch (Exception e) {
//            TestLinkIntegration.updateResults("", e.getMessage(), TestLinkAPIResults.TEST_FAILED);
        }
    }

}

