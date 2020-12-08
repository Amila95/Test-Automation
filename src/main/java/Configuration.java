public class Configuration {

    public static String currentServerURL = "https://v9.typefi.com";

    public enum TestlinkReportMode
    {
        none,
        synchronous,
        asynchronous
    }
    public static TestlinkReportMode currentTestlinkReportMode = TestlinkReportMode.none;
    private static String qaFolderName = "QA-Folder";
    private static String qaFolderParent = "/";

    public static String getQAFolderFullPath()
    {
        return qaFolderParent+qaFolderName;
    }
    public static String getQAFolderName()
    {
        return qaFolderName;
    }
    public static String getQAFolderParent()
    {
        return qaFolderParent;
    }
}
