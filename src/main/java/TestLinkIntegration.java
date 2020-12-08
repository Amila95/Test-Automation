import testlink.api.java.client.TestLinkAPIClient;
import testlink.api.java.client.TestLinkAPIException;

public class TestLinkIntegration{
    //public static final String TESTLINK_KEY = "00e133645bf6a61448a2986feffc3df3";
    public static final String TESTLINK_KEY = "01464f5fbcb9517d630997ecdffbcd4f";  //This is the key for the new, dedicated automation user
    public static final String TESTLINK_URL = "https://testlink.typefi.com/lib/api/xmlrpc/v1/xmlrpc.php";
    public static final String TEST_PROJECT_NAME = "server";
    public static final String TEST_PLAN_NAME = "Automation Test Plan";
    //public static final String TEST_CASE_NAME = "tl-server-290";
    public static final String BUILD_NAME = "1-chrome";



    public static void updateResults(String TEST_CASE_NAME, String exception, String results) throws TestLinkAPIException{
        TestLinkAPIClient testLink = new TestLinkAPIClient(TESTLINK_KEY,TESTLINK_URL);
        testLink.reportTestCaseResult(TEST_PROJECT_NAME,TEST_PLAN_NAME,TEST_CASE_NAME,BUILD_NAME,exception,results);
    }


}
