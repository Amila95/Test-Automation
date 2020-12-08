import org.hamcrest.CoreMatchers;
import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//uthpali
public class FileDeleteTests extends Thread {

    private static WebDriver driver;
    LoginOperations loginOps = new LoginOperations();
    //DriverSetup driversetup = new DriverSetup();
    CommonMethods2 com2 = new CommonMethods2();

    @Rule
    public ErrorCollector collector = new ErrorCollector();

    String testUser = "desktopuser1@typefi.aws";
    String testPw = "-lL3eOmdO$";
    String LoginPageUrl = Configuration.currentServerURL + "/login";

    @AfterClass
    public static void GlobalShutdown() {
        TestlinkThreadManager.waitForThreadsToComplete();
    }

    @Before
    public void preTestActions() {
        DriverSetup.currentDriverType = DriverSetup.DriverTypes.Chrome;
        driver = DriverSetup.SetupWebDriver();
        try {
            collector.checkThat(
                    "Failure while trying to log in to '" + LoginPageUrl + "' with username: '" + testUser + "' and password: '" + testPw + "' browser: '" + DriverSetup.currentDriverType + "'",
                    loginOps.login(driver, testUser, testPw, false, LoginPageUrl),
                    CoreMatchers.equalTo(true)
            );

            collector.checkThat(
                    "Was not allowed to log in with valid credentials. Browser: '" + DriverSetup.currentDriverType + "'",
                    loginOps.CheckIfLoggedIn(driver),
                    CoreMatchers.equalTo(true)
            );
        } catch (Exception e) {
            System.out.println("login failed");
        }
    }

    @After
    //Actions that should be run after each test
    public void postTestActions() {
        try {
            //Try to close the web browser (so that we don't end up with a million open browsers)
            driver.close();
        } catch (Exception e) {
            System.out.println("Problem closing browser");
        }
    }

    //tl-server-294
    @Test
    public void DeleteFile() throws Exception {
        RunnableResult R2 = new RunnableResult("tl-server-294");
        String returnString = "";

        if ((returnString = com2.CheckHomePage(driver)) != "") {
            R2.Failed("STEP1: Check that we are starting from the home page. " + returnString);
            R2.sendReport();
            return;
        }
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"deleteFilesForm\"]/div/table/tbody/tr[4]/td[1]/input"))).click();


        if ((returnString = com2.CheckToolbar(driver)) != "") {
            R2.Failed("STEP2: Verify the toolbar appears. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com2.ClickDeletebutton(driver)) != "") {
            R2.Failed("STEP3: Verify the delete button click. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com2.DeleteDialogAppears(driver)) != "") {
            R2.Failed("STEP4: Verify the Delete dialog appears. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com2.DialogDeletebutton(driver)) != "") {
            R2.Failed("STEP5: Verify the delete button click. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com2.CheckHomePage(driver)) != "") {
            R2.Failed("STEP6: Verify that we were returned to the home page. " + returnString);
            R2.sendReport();
            return;
        }

        R2.Passed("SUCCESS");
        R2.sendReport();
    }

    //tl-server-298
    @Test
    public void CancelDeleteFile() throws Exception {
        RunnableResult R2 = new RunnableResult("tl-server-298");
        String returnString = "";

        if ((returnString = com2.CheckHomePage(driver)) != "") {
            R2.Failed("STEP1: Check that we are starting from the home page. " + returnString);
            R2.sendReport();
            return;
        }
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"deleteFilesForm\"]/div/table/tbody/tr[4]/td[1]/input"))).click();


        if ((returnString = com2.CheckToolbar(driver)) != "") {
            R2.Failed("STEP2: Verify the toolbar appears. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com2.ClickDeletebutton(driver)) != "") {
            R2.Failed("STEP3: Verify the delete button click. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com2.DeleteDialogAppears(driver)) != "") {
            R2.Failed("STEP4: Verify the Delete dialog appears. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com2.DeleteDialogCancelbutton(driver)) != "") {
            R2.Failed("STEP5: Verify the cancel button click. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com2.CheckHomePage(driver)) != "") {
            R2.Failed("STEP6: Verify that we were returned to the home page. " + returnString);
            R2.sendReport();
            return;
        }

        R2.Passed("SUCCESS");
        R2.sendReport();
    }
    //tl-server-299
    @Test
    public void CloseDeleteDialog() throws Exception {
        RunnableResult R2 = new RunnableResult("tl-server-299");
        String returnString = "";

        if ((returnString = com2.CheckHomePage(driver)) != "") {
            R2.Failed("STEP1: Check that we are starting from the home page. " + returnString);
            R2.sendReport();
            return;
        }
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"deleteFilesForm\"]/div/table/tbody/tr[4]/td[1]/input"))).click();


        if ((returnString = com2.CheckToolbar(driver)) != "") {
            R2.Failed("STEP2: Verify the toolbar appears. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com2.ClickDeletebutton(driver)) != "") {
            R2.Failed("STEP3: Verify the delete button click. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com2.DeleteDialogAppears(driver)) != "") {
            R2.Failed("STEP4: Verify the Delete dialog appears. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com2.DeleteDialogClosebutton(driver)) != "") {
            R2.Failed("STEP5: Verify the Delete dialog close. " + returnString);
            R2.sendReport();
            return;
        }


        if ((returnString = com2.CheckHomePage(driver)) != "") {
            R2.Failed("STEP6: Verify that we were returned to the home page. " + returnString);
            R2.sendReport();
            return;
        }

        R2.Passed("SUCCESS");
        R2.sendReport();
    }


}
