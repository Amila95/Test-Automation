import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import javax.crypto.Mac;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DriverSetup {
//    public static String driverPath = "/Users/udarak/IdeaProjects2/automation-example/webdrivers/mac/";
//    public static String driverPath = "/Volumes/Virtual/Typefi Automation/automation-example/webdrivers/mac/";
//    public static String driverPath = "webdrivers/mac/";
//    public static String driverPath = "webdrivers/linux/";
    public static DriverTypes currentDriverType;

    public enum DriverTypes {
        Chrome,
        Edge,
        Safari,
        Firefox
    }

    /**
     * types of Operating Systems
     */
    public enum OSType {
        Windows, MacOS, Linux, Other
    };

    public static Map<OSType,String> firefoxDriver = new HashMap<OSType, String>() {{
        put(OSType.Windows,"geckodriver64.exe");
        put(OSType.Linux,"geckodriver");
        put(OSType.MacOS,"geckodriver");
        put(OSType.Other,"geckodriver");
    }};
    public static Map<OSType,String> chromeDriver = new HashMap<OSType, String>() {{
        put(OSType.Windows,"chromedriver.exe");
        put(OSType.Linux,"chromedriver");
        put(OSType.MacOS,"chromedriver");
        put(OSType.Other,"chromedriver");
    }};
    public static Map<OSType,String> edgeDriver = new HashMap<OSType, String>() {{
        put(OSType.Windows,"msedgedriver.exe");
        put(OSType.Linux,"msedgedriver");
        put(OSType.MacOS,"msedgedriver");
        put(OSType.Other,"msedgedriver");
    }};
    public static Map<OSType,String> safariDriver = new HashMap<OSType, String>() {{
        put(OSType.Windows,"safaridriver.exe");
        put(OSType.Linux,"safaridriver");
        put(OSType.MacOS,"safaridriver");
        put(OSType.Other,"safaridriver");
    }};
    public static Map<OSType,String> osDriverPath = new HashMap<OSType, String>() {{
        put(OSType.Windows,"webdrivers/win/");
        put(OSType.Linux,"webdrivers/linux/");
        put(OSType.MacOS,"webdrivers/mac/");
        put(OSType.Other,"webdrivers/mac/");
    }};
    // cached result of OS detection
    protected static OSType detectedOS;

    /**
     * detect the operating system from the os.name System property and cache
     * the result
     *
     * @returns - the operating system detected
     */
    public static OSType getOperatingSystemType() {
        if (detectedOS == null) {
            String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
            if ((OS.indexOf("mac") >= 0) || (OS.indexOf("darwin") >= 0)) {
                detectedOS = OSType.MacOS;
            } else if (OS.indexOf("win") >= 0) {
                detectedOS = OSType.Windows;
            } else if (OS.indexOf("nux") >= 0) {
                detectedOS = OSType.Linux;
            } else {
                detectedOS = OSType.Other;
            }
        }
        return detectedOS;
    }

    //Call this static method to set the driver type
    public static void ChangeDriverType(DriverTypes newDriverType) {
        System.out.println("Changing driver type to " + newDriverType.toString());
        currentDriverType = newDriverType;
    }

    //Call this static method to get a new driver of the type selected by "ChangeDriverType"
    public static WebDriver SetupWebDriver() {
        getOperatingSystemType();
        System.out.println("Current operating system is: "+(detectedOS.equals(OSType.Linux)?"Linux":(detectedOS.equals(OSType.Windows)?"Windows":(detectedOS.equals(OSType.MacOS)?"Mac":"Other"))));
        System.out.println("Current driver is " + currentDriverType);
        switch (currentDriverType) {
            case Chrome:
                //Set the path to the driver you wish to use
                System.setProperty("webdriver.chrome.driver", osDriverPath.get(detectedOS) + chromeDriver.get(detectedOS));

                //Set up the web driver (this step should actually open the web browser)
                return (new ChromeDriver());
            case Firefox:
                //Set the path to the driver you wish to use
                System.setProperty("webdriver.gecko.driver", osDriverPath.get(detectedOS) + firefoxDriver.get(detectedOS));
                //Set up the web driver (this step should actually open the web browser)
                return (new FirefoxDriver());
            case Safari:
               System.setProperty("webdriver.safari.driver", osDriverPath.get(detectedOS) + safariDriver.get(detectedOS));
                return (new SafariDriver());
           case Edge:
                System.setProperty("webdriver.edge.driver", osDriverPath.get(detectedOS) + chromeDriver.get(detectedOS));
                return (new EdgeDriver());

            default:
                return null;
        }
    }
}






