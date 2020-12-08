import org.hamcrest.CoreMatchers;
import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.awt.event.KeyEvent;

// Nuwan Sampath
public class FilesPageTest {

    private static WebDriver driver;
    LoginOperations loginOps = new LoginOperations();
    CommonMethod com = new CommonMethod();

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
    public void postTestActions() {
        try {
            //Try to close the web browser (so that we don't end up with a million open browsers)
            driver.close();
        } catch (Exception e) {
            System.out.println("Problem closing browser");
        }
    }

    //tl-server-290
    @Test
    public void refreshFiles() throws Exception {
        RunnableResult R2 = new RunnableResult("tl-server-290");
        String returnString = "";

        if ((returnString = com.CheckHomePage(driver)) != "") {
            R2.Failed("STEP1: Check that we are starting from the home page. " + returnString);
            R2.sendReport();
            return;
        }

        if ((returnString = com.ClickFilesTab(driver)) != "") {
            R2.Failed("STEP2: Click the Files tab button. " + returnString);
            R2.sendReport();
            return;
        }

        if ((returnString = com.CheckHomePage(driver)) != "") {
            R2.Failed("STEP3: Verify that we were returned to the home page. " + returnString);
            R2.sendReport();
            return;
        }

        R2.Passed("SUCCESS");
        R2.sendReport();

    }


    //tl-server-289
    @Test
    public void newFolderClicked() throws Exception {
        RunnableResult R2 = new RunnableResult("tl-server-289");
        String returnString = "";

        if ((returnString = com.CheckHomePage(driver)) != "") {
            R2.Failed("STEP1: Check that we are starting from the home page. " + returnString);
            R2.sendReport();
            return;
        }

        if ((returnString = com.ClickNewFolderBtn(driver)) != "") {
            R2.Failed("STEP2: Click the New Folder button. " + returnString);
            R2.sendReport();
            return;
        }

        if ((returnString = com.NewFolderDialogAppears(driver)) != "") {
            R2.Failed("STEP3: Verify that New Folder dialog appears. " + returnString);
            R2.sendReport();
            return;
        }

        if ((returnString = com.CheckHomePage(driver)) != "") {
            R2.Failed("STEP4: Verify that we were returned to the home page. " + returnString);
            R2.sendReport();
            return;
        }

        R2.Passed("SUCCESS");
        R2.sendReport();
    }

    //tl-server-291
    @Test
    public void newProjectClicked() throws Exception {
        RunnableResult R2 = new RunnableResult("tl-server-291");
        String returnString = "";

        if ((returnString = com.CheckHomePage(driver)) != "") {
            R2.Failed("STEP1: Check that we are starting from the home page. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com.ClickNewProjectBtn(driver)) != "") {
            R2.Failed("STEP2: Click the New Project button. " + returnString);
            R2.sendReport();
            return;
        }

        if ((returnString = com.CheckProjPage(driver)) != "") {
            R2.Failed("STEP3: Verify that we are now on the New Project page. " + returnString);
            R2.sendReport();
            return;
        }

        driver.navigate().back();

        if ((returnString = com.CheckHomePage(driver)) != "") {
            R2.Failed("STEP4: Verify that we were returned to the home page. " + returnString);
            R2.sendReport();
            return;
        }
        R2.Passed("SUCCESS");
        R2.sendReport();

    }

    //tl-server-292
    @Test
    public void newWorkflowClicked() throws Exception {
        RunnableResult R2 = new RunnableResult("tl-server-292");
        String returnString = "";

        if ((returnString = com.CheckHomePage(driver)) != "") {
            R2.Failed("STEP1: Check that we are starting from the home page. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com.ClickNewWorkflowBtn(driver)) != "") {
            R2.Failed("STEP2: Click New Workflow button. " + returnString);
            R2.sendReport();
            return;
        }

        if ((returnString = com.CheckWorkflowsPage(driver)) != "") {
            R2.Failed("STEP3: Check navigated to the Workflow page. " + returnString);
            R2.sendReport();
            return;
        }

        driver.navigate().back();

        if ((returnString = com.CheckHomePage(driver)) != "") {
            R2.Failed("STEP4: Verify that we were returned to the home page. " + returnString);
            R2.sendReport();
            return;
        }
        R2.Passed("SUCCESS");
        R2.sendReport();
    }


    //tl-server-293
    @Test
    public void addFilesClicked() throws Exception {
        RunnableResult R2 = new RunnableResult("tl-server-293");
        String returnString = "";

        if ((returnString = com.CheckHomePage(driver)) != "") {
            R2.Failed("STEP1: Check that we are starting from the home page. " + returnString);
            R2.sendReport();
            return;
        }

        if ((returnString = com.ClickAddFilesBtn(driver)) != "") {
            R2.Failed("STEP2: Click add files button. " + returnString);
            R2.sendReport();
            return;
        }

        if ((returnString = com.CheckHomePage(driver)) != "") {
            R2.Failed("STEP3: Check that we are in the home page. " + returnString);
            R2.sendReport();
            return;
        }

        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ESCAPE);
        robot.keyRelease(KeyEvent.VK_ESCAPE);

        if ((returnString = com.CheckHomePage(driver)) != "") {
            R2.Failed("STEP4: Verify that we were returned to the home page. " + returnString);
            R2.sendReport();
            return;
        }

        R2.Passed("SUCCESS");
        R2.sendReport();
    }
}
