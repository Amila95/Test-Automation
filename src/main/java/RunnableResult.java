import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;

public class RunnableResult implements Runnable {

    private Thread t;
    private String threadName;
    private String executionNote;
    private String result;

    RunnableResult(String testName)
    {
        this.result = TestLinkAPIResults.TEST_BLOCKED;
        threadName = testName;
        executionNote = "";
    }
    RunnableResult(String result, String testName)
    {
        this.result = result;
        threadName = testName;
        executionNote = "";
    }

    RunnableResult(String result, String testName, String executionNote)
    {
        this.result = result;
        threadName = testName;
        this.executionNote = executionNote;
    }

    public void Passed(String executionNote)
    {
        this.executionNote = executionNote;
        this.result = TestLinkAPIResults.TEST_PASSED;
    }
    public void Passed() { this.result = TestLinkAPIResults.TEST_PASSED; }

    public void Failed(String executionNote)
    {
        this.executionNote = executionNote;
        this.result = TestLinkAPIResults.TEST_FAILED;
    }
    public void Failed()
    {
        this.result = TestLinkAPIResults.TEST_FAILED;
    }

    public void Blocked(String executionNote)
    {
        this.executionNote = executionNote;
        this.result = TestLinkAPIResults.TEST_BLOCKED;
    }
    public void Blocked()
    {
        this.result = TestLinkAPIResults.TEST_BLOCKED;
    }

    public void sendReport()
    {
        switch(Configuration.currentTestlinkReportMode)
        {
            case none:
                System.out.println("No Testlink Report Sent: "+threadName+" : "+executionNote);
                break;
            case synchronous:
                TestlinkThreadManager.addThread();
                this.run();
                break;
            case asynchronous:
                TestlinkThreadManager.addThread();
                this.start();
                break;
            default:
                System.err.println("Invalid report mode "+Configuration.currentTestlinkReportMode+ "found in RunnableResult");
        }
    }
    public void run()
    {
        try {
            System.out.println("Running " +  threadName );
            System.err.println("TEST "+threadName+" RESULT: "+(result == TestLinkAPIResults.TEST_FAILED?"FAILED":(result == TestLinkAPIResults.TEST_PASSED?"PASSED":"BLOCKED")) +"... "+executionNote);

            TestLinkIntegration.updateResults(threadName, executionNote, result);
            System.out.println("Completed " +threadName);
        } catch (TestLinkAPIException e) {
            System.out.println("Thread " +  threadName + " interrupted.");
        }
        TestlinkThreadManager.removeThread();
    }

    public void start () {
        System.out.println("Starting " +  threadName );
        if (t == null) {
            t = new Thread (this, threadName);
            t.start ();
        }
    }
}