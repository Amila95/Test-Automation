import org.hamcrest.CoreMatchers;
import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//uthpali
public class FileCopyTests extends Thread {

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
    public static void GlobalShutdown()
    {
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


    //tl-server-300
    @Test
    public void CopyFileWithoutSelectingLocation() throws Exception {
        RunnableResult R2 = new RunnableResult("tl-server-300");
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
        if ((returnString = com2.ClickCopybutton(driver)) != "") {
            R2.Failed("STEP3: Click the Copy button. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com2.CopyToDialogAppears(driver)) != "") {
            R2.Failed("STEP4: Verify the copy to dialog appears. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com2.DialogCopybutton(driver)) != "") {
            R2.Failed("STEP5: Verify the copy button click. " + returnString);
            R2.sendReport();
            return;
        }

        if ((returnString = com2.CheckHomePage(driver)) != "") {
            R2.Failed("STEP6: Verify that we were returned to the home page. " + returnString);
            R2.sendReport();
            return;
        }

        WebDriverWait wait2 = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"deleteFilesForm\"]/div/table/tbody/tr[5]/td[7]/a/i"))).click();

        // driver.navigate().to("https://v9.typefi.com/details/A_Other%20languages_cross_refs.docx");

        if ((returnString = com2.CheckDetailsPage(driver)) != "") {
            R2.Failed("STEP7: Verify that we were navigated to the Details page. " + returnString);
            R2.sendReport();
            return;
        }
        R2.Passed("SUCCESS");
        R2.sendReport();
    }
       //tl-server-302
    @Test
    public void CopyFileWithSelectingLocation() throws Exception {
        RunnableResult R2 = new RunnableResult("tl-server-302");
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
        if ((returnString = com2.ClickCopybutton(driver)) != "") {
            R2.Failed("STEP3: Click the Copy button. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com2.CopyToDialogAppears(driver)) != "") {
            R2.Failed("STEP4: Verify the copy to dialog appears. " + returnString);
            R2.sendReport();
            return;
        }
        WebDriverWait wait2 = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"typefi-filechooser-file\"]/tbody/tr[7]/td[2]/a"))).click();

        if ((returnString = com2.DialogCopybutton(driver)) != "") {
            R2.Failed("STEP5: Verify the copy button click. " + returnString);
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

    //tl-server-304
    @Test
    public void CancelCopyFileWithoutSelectingLocation() throws Exception {
        RunnableResult R2 = new RunnableResult("tl-server-304");
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
        if ((returnString = com2.ClickCopybutton(driver)) != "") {
            R2.Failed("STEP3: Click the Copy button. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com2.CopyToDialogAppears(driver)) != "") {
            R2.Failed("STEP4: Verify the copy to dialog appears. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com2.DialogCancelbutton(driver)) != "") {
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


    //tl-server-307
    @Test
    public void CancelCopyFileWithSelectingLocation() throws Exception {
        RunnableResult R2 = new RunnableResult("tl-server-307");
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
        if ((returnString = com2.ClickCopybutton(driver)) != "") {
            R2.Failed("STEP3: Click the Copy button. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com2.CopyToDialogAppears(driver)) != "") {
            R2.Failed("STEP4: Verify the copy to dialog appears. " + returnString);
            R2.sendReport();
            return;
        }
        WebDriverWait wait2 = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"typefi-filechooser-file\"]/tbody/tr[7]/td[2]/a"))).click();

        if ((returnString = com2.DialogCancelbutton(driver)) != "") {
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


    //tl-server-309
    @Test
    public void CopyFromDropdownmenu() throws Exception {
        RunnableResult R2 = new RunnableResult("tl-server-309");
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

        if ((returnString = com2.ClickDropdownArrow(driver)) != "") {
            R2.Failed("STEP3: Verify the dropdown click. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com2.CheckDropdownAppear(driver)) != "") {
            R2.Failed("STEP4: Verify the dropdown visible. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com2.ClickDropdownCopyBtn(driver)) != "") {
            R2.Failed("STEP5: Click the Copy button. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com2.CopyToDialogAppears(driver)) != "") {
            R2.Failed("STEP6: Verify the copy to dialog appears. " + returnString);
            R2.sendReport();
            return;
        }
        WebDriverWait wait2 = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"typefi-filechooser-file\"]/tbody/tr[7]/td[2]/a"))).click();

        if ((returnString = com2.DialogCopybutton(driver)) != "") {
            R2.Failed("STEP6: Verify the copy button click. " + returnString);
            R2.sendReport();
            return;
        }

        if ((returnString = com2.CheckHomePage(driver)) != "") {
            R2.Failed("STEP7: Verify that we were returned to the home page. " + returnString);
            R2.sendReport();
            return;
        }
        R2.Passed("SUCCESS");
        R2.sendReport();
    }

    //tl-server-313
    @Test
    public void DuplicateFromDropdownmenu() throws Exception {
        RunnableResult R2 = new RunnableResult("tl-server-313");
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

        if ((returnString = com2.ClickDropdownArrow(driver)) != "") {
            R2.Failed("STEP3: Verify the dropdown click. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com2.CheckDropdownAppear(driver)) != "") {
            R2.Failed("STEP4: Verify the dropdown visible. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com2.ClickDropdownDuplicateBtn(driver)) != "") {
            R2.Failed("STEP5: Click the duplicate button. " + returnString);
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



