public class TestlinkThreadManager {
    private static int activeThreads = 0;
    public static void addThread()
    {
        activeThreads++;
    }
    public static void removeThread()
    {
        activeThreads--;
        if(activeThreads < 0)
        {
            activeThreads = 0;
        }
    }
    public static boolean checkIfDone()
    {
        return (activeThreads <= 0);
    }
    public static void waitForThreadsToComplete()
    {
        for(int i=0;i<120;i++)
        {
            if(checkIfDone())
            {
                break;
            }
            else
            {
                try
                {
                    Thread.sleep(1000);
                }
                catch(Exception e)
                {

                }
            }
        }
    }
}
