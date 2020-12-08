import org.hamcrest.CoreMatchers;
import org.junit.*;
import org.junit.internal.TextListener;
import org.junit.rules.ErrorCollector;
import org.junit.runner.JUnitCore;
import org.openqa.selenium.WebDriver;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;


//We need to create a class to hold our "Test Suite"
//Here, I've called this class "LoginPageTests" as it
//will hold the tests relevant to the login page
public class LoginPageTests extends Thread {
    //Create an error collector so that we can accumulate errors
    //but keep the tests running (the main reason I'm using this
    //is so that the "postTestActions" still get run even after a
    //failed assertion.  If we were to use regular asserts, the code
    //for each test would stop directly after the assert failed and
    //the browser would remain open.
    @Rule
    public ErrorCollector collector = new ErrorCollector();

    //All of the login page operation are enclosed in the "LoginOperations"
    //class, so we want to create an instance of it that we can use for
    //these tests.
    LoginOperations loginOps = new LoginOperations();
    CommonMethod com = new CommonMethod();

    //The driver setup actions are enclosed in the "DriverSetup" class,
    //so we want to create an instance of that for use with these tests
//    DriverSetup driverSetup = new DriverSetup();

    //Here is the test data for these tests (usernames and whatnot)

    String testUser = "desktopuser1@typefi.aws";
    String testPw = "-lL3eOmdO$";

    String user0 = "admin";
    String pass0 = "admin";
    String title0 = "Administrator";

    String user1 = "";
    String pass1 = "";
    String title1 = "1 DesktopUser";

    String user2 = "nperera@typefi.com";
    String pass2 = "1=1";
    String title4 = "Desktop OR workgroup";

    String user3 = "1=1";
    String pass3 = "-jBcCfBci5g";
    String title5 = "Desktop OR workgroup";


    String errorMsg = "Invalid username or password";


    String LoginPageUrl = Configuration.currentServerURL + "/login";


    //Here is out driver for controlling the browser
    WebDriver driver;

    //Actions that should be run before each test
    @Before
    public void preTestActions() {
//        com.setDriver(driver);
        //Set up the web driver (this step should actually open the web browser)
        DriverSetup.currentDriverType = DriverSetup.DriverTypes.Chrome;
        driver = DriverSetup.SetupWebDriver();
    }


    @After
    public void postTestActions() {
        try {
            //Try to close the web browser (so that we don't end up with a milliion open browsers)
            driver.close();
        } catch (Exception e) {
            System.out.println("Problem closing browser");
        }
    }

    @AfterClass
    public static void GlobalShutdown()
    {
        TestlinkThreadManager.waitForThreadsToComplete();
    }

    //tl-server-269
    @Test
    public void testValidLogin() throws Exception {
        RunnableResult R2 = new RunnableResult("tl-server-269");
        String returnString = "";
        try {
            //Try to log in to the website and asset that there was not an explicit failure
            if (!loginOps.login(driver, testUser, testPw, false, LoginPageUrl)) {
                R2.Failed("STEP1: \"Failure while trying to log in to '\" + LoginPageUrl + \"' with username: '\" + " +
                        "testUser + \"' and password: '\" + testPw + \"' browser: '\" + DriverSetup.currentDriverType + \"'\"");
                R2.sendReport();
                return;
            }

            //Check that we are actually logged in to the server
            if (!loginOps.CheckIfLoggedIn(driver)) {
                R2.Failed("STEP2: Was not allowed to log in with valid credentials. Browser: '" + DriverSetup.currentDriverType + "'");
                R2.sendReport();
                return;
            }

            if ((returnString = com.CheckHomePage(driver)) != "") {
                R2.Failed("STEP3: Check that we are successfully logged. " + returnString);
                R2.sendReport();
                return;
            }
            R2.Passed("SUCCESS");
            R2.sendReport();

        } catch (Exception e) {
            R2.Failed("STEP4: Unknown ExceptionCaught" + e.getMessage());
            R2.sendReport();
            return;
        }
    }

    //tl-server-270
    @Test
    public void testInvalidLogin1() throws Exception {
        RunnableResult R2 = new RunnableResult("tl-server-270");
        String returnString = "";

        try {
            //Try to log in to the website and asset that there was not an explicit failure
            if (!loginOps.login(driver, user0, pass0, false, LoginPageUrl)) {
                R2.Failed("STEP1: \"Failure while trying to log in to '\" + LoginPageUrl + \"' with username: '\" + " +
                        "testUser + \"' and password: '\" + testPw + \"' browser: '\" + DriverSetup.currentDriverType + \"'\"");
                R2.sendReport();
                return;
            }

            if (loginOps.CheckIfLoggedIn(driver)) {
                R2.Failed("STEP2: Was allowed to log in with invalid credentials. Browser: '" + DriverSetup.currentDriverType + "'");
                R2.sendReport();
                return;
            }

            if ((returnString = com.CheckLoginPage(driver)) != "") {
                R2.Failed("STEP3: Check that we are not logged in to the server. " + returnString);
                R2.sendReport();
                return;
            }

            if ((returnString = com.CheckErrorMsg(driver)) != "") {
                R2.Failed("STEP4: Check that 'Invalid username or password' message is correct. " + returnString);
                R2.sendReport();
                return;
            }
            R2.Passed("SUCCESS");
            R2.sendReport();

        } catch (Exception e) {
            R2.Failed("STEP5: Unknown ExceptionCaught" + e.getMessage());
            R2.sendReport();
            return;
        }
    }


    //tl-server-271
    @Test
    public void testInvalidLogin2() throws Exception {
        RunnableResult R2 = new RunnableResult("tl-server-271");
        String returnString = "";

        try {
            //Try to log in to the website and asset that there was not an explicit failure
            if (!loginOps.login(driver, user1, pass1, false, LoginPageUrl)) {
                R2.Failed("STEP1: \"Failure while trying to log in to '\" + LoginPageUrl + \"' with username: '\" + " +
                        "testUser + \"' and password: '\" + testPw + \"' browser: '\" + DriverSetup.currentDriverType + \"'\"");
                R2.sendReport();
                return;
            }

            //Check that we are actually logged in to the server
            if (loginOps.CheckIfLoggedIn(driver)) {
                R2.Failed("STEP2: Was allowed to log in with invalid credentials. Browser: '" + DriverSetup.currentDriverType + "'");
                R2.sendReport();
                return;
            }

            if ((returnString = com.CheckLoginPage(driver)) != "") {
                R2.Failed("STEP3: Check that we are not logged in to the server. " + returnString);
                R2.sendReport();
                return;
            }

            if ((returnString = com.CheckErrorMsg(driver)) != "") {
                R2.Failed("STEP4: Check that 'Invalid username or password' message is correct. " + returnString);
                R2.sendReport();
                return;
            }

            R2.Passed("SUCCESS");
            R2.sendReport();

        } catch (Exception e) {
            R2.Failed("STEP5: Unknown ExceptionCaught" + e.getMessage());
            R2.sendReport();
            return;
        }
    }

    //tl-server-272
    @Test
    public void testInvalidLogin3() throws Exception {
        RunnableResult R2 = new RunnableResult("tl-server-272");
        String returnString = "";

        try {
            //Try to log in to the website and asset that there was not an explicit failure
            if (!loginOps.login(driver, testUser, pass0, false, LoginPageUrl)) {
                R2.Failed("STEP1: \"Failure while trying to log in to '\" + LoginPageUrl + \"' with username: '\" + " +
                        "testUser + \"' and password: '\" + testPw + \"' browser: '\" + DriverSetup.currentDriverType + \"'\"");
                R2.sendReport();
                return;
            }

            //Check that we are actually logged in to the server
            if (loginOps.CheckIfLoggedIn(driver)) {
                R2.Failed("STEP2: Was allowed to log in with invalid credentials. Browser: '" + DriverSetup.currentDriverType + "'");
                R2.sendReport();
                return;
            }

            if ((returnString = com.CheckLoginPage(driver)) != "") {
                R2.Failed("STEP3: Check that we are not logged in to the server. " + returnString);
                R2.sendReport();
                return;
            }

            if ((returnString = com.CheckErrorMsg(driver)) != "") {
                R2.Failed("STEP4: Check that 'Invalid username or password' message is correct. " + returnString);
                R2.sendReport();
                return;
            }

            R2.Passed("SUCCESS");
            R2.sendReport();

        } catch (Exception e) {
            R2.Failed("STEP5: Unknown ExceptionCaught" + e.getMessage());
            R2.sendReport();
            return;
        }
    }

    //tl-server-273
    @Test
    public void testInvalidLogin4() throws TestLinkAPIException {
        RunnableResult R2 = new RunnableResult("tl-server-273");
        String returnString = "";

        try {
            //Try to log in to the website and asset that there was not an explicit failure
            if (!loginOps.login(driver, user1, testPw, false, LoginPageUrl)) {
                R2.Failed("STEP1: \"Failure while trying to log in to '\" + LoginPageUrl + \"' with username: '\" + " +
                        "testUser + \"' and password: '\" + testPw + \"' browser: '\" + DriverSetup.currentDriverType + \"'\"");
                R2.sendReport();
                return;
            }

            //Check that we are actually logged in to the server
            if (loginOps.CheckIfLoggedIn(driver)) {
                R2.Failed("STEP2: Was allowed to log in with invalid credentials. Browser: '" + DriverSetup.currentDriverType + "'");
                R2.sendReport();
                return;
            }

            if ((returnString = com.CheckLoginPage(driver)) != "") {
                R2.Failed("STEP3: Check that we are not logged in to the server. " + returnString);
                R2.sendReport();
                return;
            }

            if ((returnString = com.CheckErrorMsg(driver)) != "") {
                R2.Failed("STEP4: Check that 'Invalid username or password' message is correct. " + returnString);
                R2.sendReport();
                return;
            }

            R2.Passed("SUCCESS");
            R2.sendReport();

        } catch (Exception e) {
            R2.Failed("STEP5: Unknown ExceptionCaught" + e.getMessage());
            R2.sendReport();
            return;
        }
    }

    //tl-server-274
    @Test
    public void testInvalidLogin5() throws TestLinkAPIException {
        RunnableResult R2 = new RunnableResult("tl-server-274");
        String returnString = "";

        try {
            //Try to log in to the website and asset that there was not an explicit failure
            if (!loginOps.login(driver, testUser, pass2, false, LoginPageUrl)) {
                R2.Failed("STEP1: \"Failure while trying to log in to '\" + LoginPageUrl + \"' with username: '\" + " +
                        "testUser + \"' and password: '\" + testPw + \"' browser: '\" + DriverSetup.currentDriverType + \"'\"");
                R2.sendReport();
                return;
            }

            //Check that we are actually logged in to the server
            if (loginOps.CheckIfLoggedIn(driver)) {
                R2.Failed("STEP2: Was allowed to log in with invalid credentials. Browser: '" + DriverSetup.currentDriverType + "'");
                R2.sendReport();
                return;
            }

            if ((returnString = com.CheckLoginPage(driver)) != "") {
                R2.Failed("STEP3: Check that we are not logged in to the server. " + returnString);
                R2.sendReport();
                return;
            }

            if ((returnString = com.CheckErrorMsg(driver)) != "") {
                R2.Failed("STEP4: Check that 'Invalid username or password' message is correct. " + returnString);
                R2.sendReport();
                return;
            }

            R2.Passed("SUCCESS");
            R2.sendReport();

        } catch (Exception e) {
            R2.Failed("STEP5: Unknown ExceptionCaught" + e.getMessage());
            R2.sendReport();
            return;
        }
    }

    //tl-server-276
    @Test
    public void testInvalidLogin6() throws TestLinkAPIException {
        RunnableResult R2 = new RunnableResult("tl-server-276");
        String returnString = "";

        try {

            //Try to log in to the website and asset that there was not an explicit failure
            if (!loginOps.login(driver, user2, testPw, false, LoginPageUrl)) {
                R2.Failed("STEP1: \"Failure while trying to log in to '\" + LoginPageUrl + \"' with username: '\" + " +
                        "testUser + \"' and password: '\" + testPw + \"' browser: '\" + DriverSetup.currentDriverType + \"'\"");
                R2.sendReport();
                return;
            }

            //Check that we are actually logged in to the server
            if (loginOps.CheckIfLoggedIn(driver)) {
                R2.Failed("STEP2: Was allowed to log in with invalid credentials. Browser: '" + DriverSetup.currentDriverType + "'");
                R2.sendReport();
                return;
            }

            if ((returnString = com.CheckLoginPage(driver)) != "") {
                R2.Failed("STEP3: Check that we are not logged in to the server. " + returnString);
                R2.sendReport();
                return;
            }

            if ((returnString = com.CheckErrorMsg(driver)) != "") {
                R2.Failed("STEP4: Check that 'Invalid username or password' message is correct. " + returnString);
                R2.sendReport();
                return;
            }

            R2.Passed("SUCCESS");
            R2.sendReport();

        } catch (Exception e) {
            R2.Failed("STEP5: Unknown ExceptionCaught" + e.getMessage());
            R2.sendReport();
            return;
        }
    }

    //tl-server-277
    @Test
    public void testInvalidLogin7() throws TestLinkAPIException {
        RunnableResult R2 = new RunnableResult("tl-server-277");
        String returnString = "";

        try {
            //Try to log in to the website and asset that there was not an explicit failure
            if (!loginOps.login(driver, user0, testPw, false, LoginPageUrl)) {
                R2.Failed("STEP1: \"Failure while trying to log in to '\" + LoginPageUrl + \"' with username: '\" + " +
                        "testUser + \"' and password: '\" + testPw + \"' browser: '\" + DriverSetup.currentDriverType + \"'\"");
                R2.sendReport();
                return;
            }

            //Check that we are actually logged in to the server
            if (loginOps.CheckIfLoggedIn(driver)) {
                R2.Failed("STEP2: Was allowed to log in with invalid credentials. Browser: '" + DriverSetup.currentDriverType + "'");
                R2.sendReport();
                return;
            }

            if ((returnString = com.CheckLoginPage(driver)) != "") {
                R2.Failed("STEP3: Check that we are not logged in to the server. " + returnString);
                R2.sendReport();
                return;
            }

            if ((returnString = com.CheckErrorMsg(driver)) != "") {
                R2.Failed("STEP4: Check that 'Invalid username or password' message is correct. " + returnString);
                R2.sendReport();
                return;
            }

            R2.Passed("SUCCESS");
            R2.sendReport();

        } catch (Exception e) {
            R2.Failed("STEP5: Unknown ExceptionCaught" + e.getMessage());
            R2.sendReport();
            return;
        }
    }

    //tl-server-278
    @Test
    public void testInvalidLogin8() throws TestLinkAPIException {
        RunnableResult R2 = new RunnableResult("tl-server-278");
        String returnString = "";

        try {
            //Try to log in to the website and asset that there was not an explicit failure
            if (!loginOps.login(driver, user3, testPw, false, LoginPageUrl)) {
                R2.Failed("STEP1: \"Failure while trying to log in to '\" + LoginPageUrl + \"' with username: '\" + " +
                        "testUser + \"' and password: '\" + testPw + \"' browser: '\" + DriverSetup.currentDriverType + \"'\"");
                R2.sendReport();
                return;
            }

            //Check that we are actually logged in to the server
            if (loginOps.CheckIfLoggedIn(driver)) {
                R2.Failed("STEP2: Was allowed to log in with invalid credentials. Browser: '" + DriverSetup.currentDriverType + "'");
                R2.sendReport();
                return;
            }

            if ((returnString = com.CheckLoginPage(driver)) != "") {
                R2.Failed("STEP3: Check that we are not logged in to the server. " + returnString);
                R2.sendReport();
                return;
            }

            if ((returnString = com.CheckErrorMsg(driver)) != "") {
                R2.Failed("STEP4: Check that 'Invalid username or password' message is correct. " + returnString);
                R2.sendReport();
                return;
            }

            R2.Passed("SUCCESS");
            R2.sendReport();

        } catch (Exception e) {
            R2.Failed("STEP5: Unknown ExceptionCaught" + e.getMessage());
            R2.sendReport();
            return;
        }
    }

    //tl-server-279
    @Test
    public void checkBoxTest1() throws TestLinkAPIException {
        RunnableResult R2 = new RunnableResult("tl-server-279");
        String returnString = "";

        try {
            //Try to log in to the website and asset that there was not an explicit failure
            if (!loginOps.login(driver, testUser, testPw, false, LoginPageUrl)) {
                R2.Failed("STEP1: \"Failure while trying to log in to '\" + LoginPageUrl + \"' with username: '\" + " +
                        "testUser + \"' and password: '\" + testPw + \"' browser: '\" + DriverSetup.currentDriverType + \"'\"");
                R2.sendReport();
                return;
            }

            //Check that we are actually logged in to the server
            if (!loginOps.CheckIfLoggedIn(driver)) {
                R2.Failed("STEP2: Was not allowed to log in with valid credentials remember unchecked.. Browser: '" + DriverSetup.currentDriverType + "'");
                R2.sendReport();
                return;
            }

            if ((returnString = com.CheckHomePage(driver)) != "") {
                R2.Failed("STEP3: Check that we are successfully logged. " + returnString);
                R2.sendReport();
                return;
            }
            R2.Passed("SUCCESS");
            R2.sendReport();

        } catch (Exception e) {
            R2.Failed("STEP4: Unknown ExceptionCaught" + e.getMessage());
            R2.sendReport();
            return;
        }
    }

    //tl-server-280
    @Test
    public void checkBoxTest2() throws TestLinkAPIException {
        RunnableResult R2 = new RunnableResult("tl-server-280");
        String returnString = "";

        try {
            //Try to log in to the website and asset that there was not an explicit failure
            if (!loginOps.login(driver, testUser, testPw, true, LoginPageUrl)) {
                R2.Failed("STEP1: \"Failure while trying to log in to '\" + LoginPageUrl + \"' with username: '\" + " +
                        "testUser + \"' and password: '\" + testPw + \"' browser: '\" + DriverSetup.currentDriverType + \"'\"");
                R2.sendReport();
                return;
            }

            //Check that we are actually logged in to the server
            if (!loginOps.CheckIfLoggedIn(driver)) {
                R2.Failed("STEP2: Was not allowed to log in with valid credentials and remember checked. Browser: '" + DriverSetup.currentDriverType + "'");
                R2.sendReport();
                return;
            }

            if ((returnString = com.CheckHomePage(driver)) != "") {
                R2.Failed("STEP3: Check that we are successfully logged. " + returnString);
                R2.sendReport();
                return;
            }
            R2.Passed("SUCCESS");
            R2.sendReport();

        } catch (Exception e) {
            R2.Failed("STEP4: Unknown ExceptionCaught" + e.getMessage());
            R2.sendReport();
            return;
        }
    }

    @Test
    public void checkBoxTest3() {
        TestJava testJava = new TestJava();
        testJava.test();

    }


}