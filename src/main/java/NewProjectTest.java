import org.hamcrest.CoreMatchers;
import org.junit.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.rules.ErrorCollector;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testlink.api.java.client.TestLinkAPIResults;

import java.util.concurrent.CompletableFuture;


//--Nuwan Sampath--
public class NewProjectTest extends Thread {

    private static WebDriver driver;
    LoginOperations loginOps = new LoginOperations();
    //DriverSetup driversetup = new DriverSetup();
    CommonMethod com = new CommonMethod();


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




    // No of testcases = 10

    //tl-server-301
    @Test
    public void cancelProj() throws Exception {
        RunnableResult R2 = new RunnableResult("tl-server-301");
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
        if ((returnString = com.ClickCancelBtn(driver)) != "") {
            R2.Failed("STEP4: Click the Cancel button. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com.CheckHomePage(driver)) != "") {
            R2.Failed("STEP5: Verify that we were returned to the home page. " + returnString);
            R2.sendReport();
            return;
        }
        R2.Passed("SUCCESS");
        R2.sendReport();
    }

    //tl-server-303
    @Test
    public void cancelProjWithAll() throws Exception {
        RunnableResult R2 = new RunnableResult("tl-server-303");
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
        if ((returnString = com.ProjectNameTxtBox(driver)) != "") {
            R2.Failed("STEP4: Verify the project name text box. " + returnString);
            R2.sendReport();
            return;
        }
        driver.findElement(By.id("projectName")).sendKeys("Test123");

        if ((returnString = com.ClickCancelBtn(driver)) != "") {
            R2.Failed("STEP5: Click the Cancel button. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com.CheckHomePage(driver)) != "") {
            R2.Failed("STEP6: Verify that we were returned to the home page. " + returnString);
            R2.sendReport();
            return;
        }
        R2.Passed("SUCCESS");
        R2.sendReport();
    }

    //tl-server-305
    @Test
    public void addWorkflow() throws Exception {
        RunnableResult R2 = new RunnableResult("tl-server-305");
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
        if ((returnString = com.AddWorkflow(driver)) != "") {
            R2.Failed("STEP4: Click the Add Workflow button. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com.CheckProjPage(driver)) != "") {
            R2.Failed("STEP5: Verify that we are now on the New Project page. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com.AddWorkflow(driver)) != "") {
            R2.Failed("STEP6: Click the Add Workflow button. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com.CheckProjPage(driver)) != "") {
            R2.Failed("STEP7: Verify that we are now on the New Project page. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com.ClickCancelBtn(driver)) != "") {
            R2.Failed("STEP8: Click the Cancel button. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com.CheckHomePage(driver)) != "") {
            R2.Failed("STEP9: Verify that we were returned to the home page. " + returnString);
            R2.sendReport();
            return;
        }
        R2.Passed("SUCCESS");
        R2.sendReport();
    }

    //tl-server-306
    @Test
    public void deleteWorkflow() throws Exception {
        RunnableResult R2 = new RunnableResult("tl-server-306");
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
        if ((returnString = com.AddWorkflow(driver)) != "") {
            R2.Failed("STEP4: Click the Add Workflow button. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com.CheckProjPage(driver)) != "") {
            R2.Failed("STEP5: Verify that we are now on the New Project page. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com.DeleteWorkflow1(driver)) != "") {
            R2.Failed("STEP6: Click the Delete workflow button. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com.CheckProjPage(driver)) != "") {
            R2.Failed("STEP7: Verify that we are now on the New Project page. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com.ClickCancelBtn(driver)) != "") {
            R2.Failed("STEP8: Click the Cancel button. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com.CheckHomePage(driver)) != "") {
            R2.Failed("STEP9: Verify that we were returned to the home page. " + returnString);
            R2.sendReport();
            return;
        }
        R2.Passed("SUCCESS");
        R2.sendReport();
    }


    //tl-server-308
    @Test
    public void createProjWithName() throws Exception {
        RunnableResult R2 = new RunnableResult("tl-server-308");
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
        if ((returnString = com.ProjectNameTxtBox(driver)) != "") {
            R2.Failed("STEP4: Verify the project name text box. " + returnString);
            R2.sendReport();
            return;
        }
        driver.findElement(By.id("projectName")).sendKeys("NewProject");

        if ((returnString = com.ClickSaveBtn(driver)) != "") {
            R2.Failed("STEP5: Click the Save button. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com.CheckHomePage(driver)) != "") {
            R2.Failed("STEP6: Verify that we were returned to the home page. " + returnString);
            R2.sendReport();
            return;
        }
        R2.Passed("SUCCESS");
        R2.sendReport();
    }


    //tl-server-311
    @Test
    public void cancelProjEmtName() throws Exception {
        RunnableResult R2 = new RunnableResult("tl-server-311");
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
        if ((returnString = com.ProjectNameTxtBox(driver)) != "") {
            R2.Failed("STEP4: Verify the project name text box. " + returnString);
            R2.sendReport();
            return;
        }
        driver.findElement(By.id("projectName")).sendKeys("");

        if ((returnString = com.ClickCancelBtn(driver)) != "") {
            R2.Failed("STEP5: Click the Cancel button. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com.CheckHomePage(driver)) != "") {
            R2.Failed("STEP6: Verify that we were returned to the home page. " + returnString);
            R2.sendReport();
            return;
        }
        R2.Passed("SUCCESS");
        R2.sendReport();
    }


    //tl-server-312
    @Test
    public void creProjWthExName() throws Exception {
        RunnableResult R2 = new RunnableResult("tl-server-312");
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
        if ((returnString = com.ProjectNameTxtBox(driver)) != "") {
            R2.Failed("STEP4: Verify the project name text box. " + returnString);
            R2.sendReport();
            return;
        }
        driver.findElement(By.id("projectName")).sendKeys("NewProject");
        if ((returnString = com.ClickSaveBtn(driver)) != "") {
            R2.Failed("STEP5: Click the Save button. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com.CheckHomePage(driver)) != "") {
            R2.Failed("STEP6: Verify that we were returned to the home page. " + returnString);
            R2.sendReport();
            return;
        }
        R2.Passed("SUCCESS");
        R2.sendReport();
    }

    //tl-server-314
    @Test
    public void creProjWthAll() throws Exception {
        RunnableResult R2 = new RunnableResult("tl-server-314");
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
        if ((returnString = com.ProjectNameTxtBox(driver)) != "") {
            R2.Failed("STEP4: Verify the project name text box. " + returnString);
            R2.sendReport();
            return;
        }
        driver.findElement(By.id("projectName")).sendKeys("TestProject");
        if ((returnString = com.ChooseWorkflow(driver)) != "") {
            R2.Failed("STEP5: Click the Choose workflow button. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com.CheckProjPage(driver)) != "") {
            R2.Failed("STEP6: Verify that we are now on the New Project page. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com.ChooseWFDialogAppears(driver)) != "") {
            R2.Failed("STEP7: Verify that Choose workflow dialog appears. " + returnString);
            R2.sendReport();
            return;
        }
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Nuwan')]"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Testworkflow')]"))).click();

        if ((returnString = com.CheckProjPage(driver)) != "") {
            R2.Failed("STEP8: Verify that we are now on the New Project page. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com.ClickSaveBtn(driver)) != "") {
            R2.Failed("STEP9: Click the Save button. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com.CheckHomePage(driver)) != "") {
            R2.Failed("STEP10: Verify that we were returned to the home page. " + returnString);
            R2.sendReport();
            return;
        }
        R2.Passed("SUCCESS");
        R2.sendReport();
    }

    //tl-server-315
    @Test
    public void chooseWorkflow() throws Exception {
        RunnableResult R2 = new RunnableResult("tl-server-315");
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
        if ((returnString = com.ChooseWorkflow(driver)) != "") {
            R2.Failed("STEP4: Click the Choose workflow button. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com.CheckProjPage(driver)) != "") {
            R2.Failed("STEP5: Verify that we are now on the New Project page. " + returnString);
            R2.sendReport();
            return;
        }
        if ((returnString = com.ChooseWFDialogAppears(driver)) != "") {
            R2.Failed("STEP6: Verify that Choose workflow dialog appears. " + returnString);
            R2.sendReport();
            return;
        }
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'AAA')]"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Testworkflow')]"))).click();
        Thread.sleep(2000);

        if ((returnString = com.CheckProjPage(driver)) != "") {
            R2.Failed("STEP7: Verify that we are now on the New Project page. " + returnString);
            R2.sendReport();
            return;
        }
        R2.Passed("SUCCESS");
        R2.sendReport();
    }


    //tl-server-316
    @Test
    public void backToHome() throws Exception {
        RunnableResult R2 = new RunnableResult("tl-server-316");
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
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"typefi-main-panel\"]/ul/li/a"))).click();
        if ((returnString = com.CheckHomePage(driver)) != "") {
            R2.Failed("STEP4: Verify that we were returned to the home page. " + returnString);
            R2.sendReport();
            return;
        }
        R2.Passed("SUCCESS");
        R2.sendReport();
    }
}






