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

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.awt.event.KeyEvent;
import org.openqa.selenium.JavascriptExecutor;
import static org.junit.Assert.*;

public class AddFileTests {
    @Rule
    public ErrorCollector collector = new ErrorCollector();

    LoginOperations loginOps = new LoginOperations();


    DriverSetup driverSetup = new DriverSetup();

    String user2 = "desktopuser1@typefi.aws";
    String pass2 = "-lL3eOmdO$";

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


    String LoginPageUrl = "https://v8.typefi.com/login";


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
    public void AddFile() throws Exception {

        preTestActions();
        driver.get(LoginPageUrl);
        // driver.manage().window().maximize();

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


            driver.findElement(By.xpath("//*[@id=\"deleteFilesForm\"]/div/table/tbody/tr[3]/td[3]/a")).click();





            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            WebElement element = driver.findElement(By.id("typefi-files-add"));
            element.click();
            //element.sendKeys("/Users/udarak/Downloads/A_Other languages_cross_refs.docx");
            uploadFile("/Users/udarak/Downloads/A_Other languages_cross_refs.docx");


            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //System.out.println(driver.switchTo().alert().getText());

            postTestActions();

//            TestLinkIntegration.updateResults("", null, TestLinkAPIResults.TEST_PASSED);
        } catch (Exception e) {
//            TestLinkIntegration.updateResults("", e.getMessage(), TestLinkAPIResults.TEST_FAILED);
        }
    }

    public static void setClipboardData(String string) {
        //StringSelection is a class that can be used for copy and paste operations.
        StringSelection stringSelection = new StringSelection(string);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    }

    private void uploadFile(String path_to_the_file) {
        try {
            //Setting clipboard with file location
            setClipboardData("/Users/udarak/Downloads/A_Other languages_cross_refs.docx");
            //native key strokes for CTRL, V and ENTER keys
            Robot robot = new Robot();

            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }


}


