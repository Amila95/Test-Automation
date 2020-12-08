import org.hamcrest.CoreMatchers;
import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//uthpali
public class NewFolderCreate extends Thread {

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


    //tl-server-296
    @Test
    public void NewFolderCreate() throws Exception {
        RunnableResult R2 = new RunnableResult("tl-server-296");
        String returnString = "";

        if ((returnString = com2.CheckHomePage(driver)) != "") {
            R2.Failed("STEP1: Check that we are starting from the home page. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com2.ClickNewFoldrButton(driver)) != "") {
            R2.Failed("STEP2: Verify the New folder button click. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com2.NewFolderDialogAppears(driver)) != "") {
            R2.Failed("STEP3: Verify the NewFolder Dialog Appears. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com2. CheckNeFolderTxtBox(driver)) != "") {
            R2.Failed("STEP4: Verify the textbox Appears. " + returnString);
            R2.sendReport();
            return;
        }

        driver.findElement(By.id("folderName")).sendKeys("Uthpali-Folder");


        if ((returnString = com2.NewFolderDialogCreatebutton(driver)) != "") {
            R2.Failed("STEP5: Verify the create button click. " + returnString);
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