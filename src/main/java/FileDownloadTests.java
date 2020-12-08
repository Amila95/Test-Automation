import org.hamcrest.CoreMatchers;
import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//uthpali
public class FileDownloadTests extends Thread {

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

    //tl-server-326
    @Test
    public void DownloadFile() throws Exception {
        RunnableResult R2 = new RunnableResult("tl-server-326");
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

        if ((returnString = com2.ClickDownloadbutton(driver)) != "") {
            R2.Failed("STEP3: Verify the download button click. " + returnString);
            R2.sendReport();
            return;
        }
        R2.Passed("SUCCESS");
        R2.sendReport();
    }

}