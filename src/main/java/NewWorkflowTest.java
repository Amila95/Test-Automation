import org.hamcrest.CoreMatchers;
import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;

import java.io.File;
import java.util.List;

//--Nuwan Sampath--
public class NewWorkflowTest {

    private static WebDriver driver;
    LoginOperations loginOps = new LoginOperations();
    DriverSetup driversetup = new DriverSetup();
    CommonMethod com = new CommonMethod();

    @Rule
    public ErrorCollector collector = new ErrorCollector();

    String testUser = "desktopuser1@typefi.aws";
    String testPw = "-lL3eOmdO$";

    String LoginPageUrl = Configuration.currentServerURL + "/login";

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
            //Make sure that the QA folder exists before running tests
            if(!com.CheckQAFolder(driver).equals(""))
            {
                throw new Exception("Problem creating the QA Folder");
            }
            driver.navigate().to(Configuration.currentServerURL+"/files");
            com.waitForLoad(driver);
        } catch (Exception e) {
            System.out.println("login failed: "+ e.getMessage());
        }

    }

    @After
    //Actions that should be run after each test
    public void postTestActions() {
        try {
            //Try to close the web browser (so that we don't end up with a milliion open browsers)
            driver.close();
        } catch (Exception e) {
            System.out.println("Problem closing browser");
        }
    }


    @AfterClass
    public static void GlobalShutdown() {
        TestlinkThreadManager.waitForThreadsToComplete();
    }

    //tl-server-327
    @Test
    public void backToHome() throws Exception {
        RunnableResult R2 = new RunnableResult("tl-server-327");
        String returnString = "";

        if ((returnString = com.CheckHomePage(driver)) != "") {
            R2.Failed("STEP1: Check that we are starting from the home page. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com.ClickNewWorkflowBtn(driver)) != "") {
            R2.Failed("STEP2: Click new workflow button. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com.CheckWorkflowsPage(driver)) != "") {
            R2.Failed("STEP3: Click that we are in workflows page. " + returnString);
            R2.sendReport();
            return;
        }
        driver.findElement(By.xpath("//*[@id=\"typefi-fixed-header\"]/ul/li/a")).click();

        if ((returnString = com.CheckHomePage(driver)) != "") {
            R2.Failed("STEP4: Verify that we were returned to the home page. " + returnString);
            R2.sendReport();
            return;
        }

        R2.Passed("SUCCESS");
        R2.sendReport();
    }


    //tl-server-328
    @Test
    public void addActionClicked() throws Exception {
        RunnableResult R2 = new RunnableResult("tl-server-328");
        String returnString = "";

        if ((returnString = com.CheckHomePage(driver)) != "") {
            R2.Failed("STEP1: Check that we are starting from the home page. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com.ClickNewWorkflowBtn(driver)) != "") {
            R2.Failed("STEP2: Click new workflow button. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com.CheckWorkflowsPage(driver)) != "") {
            R2.Failed("STEP3: Click that we are in workflows page. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com.AddAction(driver)) != "") {
            R2.Failed("STEP4: Click the Add Action button. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com.NewActionDialogAppears(driver)) != "") {
            R2.Failed("STEP5: Check the New Action Dialog appears. " + returnString);
            R2.sendReport();
            return;
        }

        R2.Passed("SUCCESS");
        R2.sendReport();

    }

    //tl-server-329
    @Test
    public void createWFWithName() throws Exception {
        RunnableResult R2 = new RunnableResult("tl-server-329");
        String returnString = "";
        String testWorkflowName = "TestWorkflow.typefi_workflow";

        String current = Configuration.currentServerURL + "/details" + Configuration.getQAFolderFullPath()+"/"+testWorkflowName;

        if ((returnString = com.CheckHomePage(driver)) != "") {
            R2.Failed("STEP1: Check that we are starting from the home page. " + returnString);
            R2.sendReport();
            return;
        }

        if(!com.CheckLocationExists(driver, Configuration.getQAFolderFullPath()))
        {
            R2.Failed("STEP2: The QA Folder does not exist.  Aborting test.");
            R2.sendReport();
            return;
        }
        com.DeleteFile(driver, Configuration.getQAFolderParent() + Configuration.getQAFolderName(), "TestWorkflow.typefi_workflow");

        driver.navigate().to(Configuration.currentServerURL+"/files"+Configuration.getQAFolderFullPath());
        com.waitForLoad(driver);

        if ((returnString = com.ClickNewWorkflowBtn(driver)) != "") {
            R2.Failed("STEP7: Click new workflow button. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com.CheckWorkflowsPage(driver)) != "") {
            R2.Failed("STEP8: Click that we are in workflows page. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com.WorkflowNameTxtBox(driver)) != "") {
            R2.Failed("STEP9: Verify the Workflow name text box. " + returnString);
            R2.sendReport();
            return;
        }
        driver.findElement(By.id("typefi-workflows-name")).sendKeys("TestWorkflow");

        if ((returnString = com.ClickWorkflowSaveBtn(driver)) != "") {
            R2.Failed("STEP10: Click the workflow save button. " + returnString);
            R2.sendReport();
            return;
        }

        if(!com.CheckLocationExists(driver,Configuration.getQAFolderFullPath()+"/"+testWorkflowName))
        {
            R2.Failed("STEP11: An error page appeared when trying to verify that the new workflow exists. " + returnString);
            R2.sendReport();
            return;
        }
        R2.Passed("SUCCESS");
        R2.sendReport();
    }


    //tl-server-330
    @Test
    public void exportWorkflow_1() throws Exception {

    }


    @Test
    public void saveWorkflow() throws Exception {
        RunnableResult R2 = new RunnableResult("tl-server-329");
        String returnString = "";
        String testWorkflowName = "TestWorkflow2.typefi_workflow";

        String current = Configuration.currentServerURL + "/details" + Configuration.getQAFolderFullPath()+"/"+testWorkflowName;

        if ((returnString = com.CheckHomePage(driver)) != "") {
            R2.Failed("STEP1: Check that we are starting from the home page. " + returnString);
            R2.sendReport();
            return;
        }

        if(!com.CheckLocationExists(driver, Configuration.getQAFolderFullPath()))
        {
            R2.Failed("STEP2: The QA Folder does not exist.  Aborting test.");
            R2.sendReport();
            return;
        }
        com.DeleteFile(driver, Configuration.getQAFolderParent() + Configuration.getQAFolderName(), testWorkflowName);

        driver.navigate().to(Configuration.currentServerURL+"/files"+Configuration.getQAFolderFullPath());
        com.waitForLoad(driver);

        if ((returnString = com.ClickNewWorkflowBtn(driver)) != "") {
            R2.Failed("STEP7: Click new workflow button. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com.CheckWorkflowsPage(driver)) != "") {
            R2.Failed("STEP8: Click that we are in workflows page. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com.WorkflowNameTxtBox(driver)) != "") {
            R2.Failed("STEP9: Verify the Workflow name text box. " + returnString);
            R2.sendReport();
            return;
        }
        driver.findElement(By.id("typefi-workflows-name")).sendKeys("TestWorkflow2");

        if ((returnString = com.AddAction(driver)) != "") {
            R2.Failed("STEP4: Click the Add Action button. " + returnString);
            R2.sendReport();
            return;
        }

        if ((returnString = com.NewActionDialogAppears(driver)) != "") {
            R2.Failed("STEP5: Check the New Action Dialog appears. " + returnString);
            R2.sendReport();
            return;
        }
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"typefi-actions-add-action-modal-list\"]/a[5]"))).click();


        if ((returnString = com.ClickWorkflowSaveBtn(driver)) != "") {
            R2.Failed("STEP10: Click the workflow save button. " + returnString);
            R2.sendReport();
            return;
        }

        if(!com.CheckLocationExists(driver,Configuration.getQAFolderFullPath()+"/"+testWorkflowName))
        {
            R2.Failed("STEP11: An error page appeared when trying to verify that the new workflow exists. " + returnString);
            R2.sendReport();
            return;
        }
        R2.Passed("SUCCESS");
        R2.sendReport();

    }

}
