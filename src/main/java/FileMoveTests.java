import org.hamcrest.CoreMatchers;
import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//uthpali
public class FileMoveTests extends Thread {

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

    //tl-server-321
    @Test
    public void MoveFileWithoutLocation() throws Exception {
        RunnableResult R2 = new RunnableResult("tl-server-321");
        String returnString = "";

        if ((returnString = com2.CheckHomePage(driver)) != "") {
            R2.Failed("STEP1: Check that we are starting from the home page. " + returnString);
            R2.sendReport();
            return;
        }
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"deleteFilesForm\"]/div/table/tbody/tr[6]/td[3]/a"))).click();


        WebDriverWait wait2 = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"deleteFilesForm\"]/div/table/tbody/tr[2]/td[1]/input"))).click();


        if ((returnString = com2.CheckToolbar(driver)) != "") {
            R2.Failed("STEP2: Verify the toolbar appears. " + returnString);
            R2.sendReport();
            return;
        }

        if ((returnString = com2.ClickMovebutton(driver)) != "") {
            R2.Failed("STEP3: Verify the move button click. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com2.MoveFileDialogAppears(driver)) != "") {
            R2.Failed("STEP4: Verify the move dialog appears. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com2.DialogMovebutton(driver)) != "") {
            R2.Failed("STEP5: Verify the move button click. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com2.ErrorMsgChecking(driver)) != "") {
            R2.Failed("STEP6: Verify the error msg appears. " + returnString);
            R2.sendReport();
            return;
        }
        R2.Passed("SUCCESS");
        R2.sendReport();
    }

    //tl-server-322
    @Test
    public void CancelMoveFileWithoutLocation() throws Exception {
        RunnableResult R2 = new RunnableResult("tl-server-322");
        String returnString = "";

        if ((returnString = com2.CheckHomePage(driver)) != "") {
            R2.Failed("STEP1: Check that we are starting from the home page. " + returnString);
            R2.sendReport();
            return;
        }
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"deleteFilesForm\"]/div/table/tbody/tr[6]/td[3]/a"))).click();


        WebDriverWait wait2 = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"deleteFilesForm\"]/div/table/tbody/tr[2]/td[1]/input"))).click();


        if ((returnString = com2.CheckToolbar(driver)) != "") {
            R2.Failed("STEP2: Verify the toolbar appears. " + returnString);
            R2.sendReport();
            return;
        }

        if ((returnString = com2.ClickMovebutton(driver)) != "") {
            R2.Failed("STEP3: Verify the move button click. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com2.MoveFileDialogAppears(driver)) != "") {
            R2.Failed("STEP4: Verify the move dialog appears. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com2.MoveDialogCancelbutton(driver)) != "") {
            R2.Failed("STEP5: Verify the cancel button click. " + returnString);
            R2.sendReport();
            return;
        }

        R2.Passed("SUCCESS");
        R2.sendReport();
    }

    //tl-server-323
    @Test
    public void MoveFileWithLocation() throws Exception {
        RunnableResult R2 = new RunnableResult("tl-server-323");
        String returnString = "";

        if ((returnString = com2.CheckHomePage(driver)) != "") {
            R2.Failed("STEP1: Check that we are starting from the home page. " + returnString);
            R2.sendReport();
            return;
        }
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"deleteFilesForm\"]/div/table/tbody/tr[2]/td[1]/input"))).click();


        if ((returnString = com2.CheckToolbar(driver)) != "") {
            R2.Failed("STEP2: Verify the toolbar appears. " + returnString);
            R2.sendReport();
            return;
        }

        if ((returnString = com2.ClickMovebutton(driver)) != "") {
            R2.Failed("STEP3: Verify the move button click. " + returnString);
            R2.sendReport();
            return;
        }

        if ((returnString = com2.MoveFileDialogAppears(driver)) != "") {
            R2.Failed("STEP4: Verify the move dialog appears. " + returnString);
            R2.sendReport();
            return;
        }
        WebDriverWait wait2 = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"typefi-filechooser-file\"]/tbody/tr[7]/td[2]/a"))).click();

        if ((returnString = com2.DialogMovebutton(driver)) != "") {
            R2.Failed("STEP5: Verify the move button click. " + returnString);
            R2.sendReport();
            return;
        }

        R2.Passed("SUCCESS");
        R2.sendReport();
    }

    //tl-server-324
    @Test
    public void CancelMoveFileWithLocation() throws Exception {
        RunnableResult R2 = new RunnableResult("tl-server-324");
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

        if ((returnString = com2.ClickMovebutton(driver)) != "") {
            R2.Failed("STEP3: Verify the move button click. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com2.MoveFileDialogAppears(driver)) != "") {
            R2.Failed("STEP4: Verify the move dialog appears. " + returnString);
            R2.sendReport();
            return;
        }
        WebDriverWait wait2 = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"typefi-filechooser-file\"]/tbody/tr[7]/td[2]/a"))).click();

        if ((returnString = com2.MoveDialogCancelbutton(driver)) != "") {
            R2.Failed("STEP5: Verify the cancel button click. " + returnString);
            R2.sendReport();
            return;
        }

        R2.Passed("SUCCESS");
        R2.sendReport();
    }
    //tl-server-325
    @Test
    public void CloseMoveToDialog() throws Exception {
        RunnableResult R2 = new RunnableResult("tl-server-325");
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

        if ((returnString = com2.ClickMovebutton(driver)) != "") {
            R2.Failed("STEP3: Verify the move button click. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com2.MoveFileDialogAppears(driver)) != "") {
            R2.Failed("STEP4: Verify the move dialog appears. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com2.MoveDialogClosebutton(driver)) != "") {
            R2.Failed("STEP5: Verify the close button click. " + returnString);
            R2.sendReport();
            return;
        }

        R2.Passed("SUCCESS");
        R2.sendReport();
    }

}