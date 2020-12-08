import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

//This class contains all actions relevant to the login page
public class LoginOperations {
    //
    // Call this to log in to the website
    //
    // Arguments:
    //       driver: a reference to the driver
    //       username: the username to log in with
    //       password: the password to use for login
    //       checkbox: true or false to indicate if the checkbox should be checked
    //               True ->  check it
    //               False -> uncheck it
    //       url: the url of the login page to use
    //

    boolean login(WebDriver driver, String username, String password, boolean checkbox, String url) {
        //Navigate to the specified url
        driver.get(url);

        //retrieve the current url from the driver
        String currentURL = driver.getCurrentUrl();

        //check that the url does not end with "/files". The reason for this is that
        //if we are already logged in, the browser will automatically be redirected to
        // "/files" so we don't need to do the login actions.

        if (!currentURL.endsWith("/files")) {
            try {
                //Find the "username" textbox and fill in the specified username
                WebElement elem = driver.findElement(By.name("username"));
                elem.sendKeys(username);


                //Find the "password" textbox and fill in the specified password
                elem = driver.findElement(By.name("password"));
                elem.sendKeys(password);

                //find the "Remember Me" checkbox
                elem = driver.findElement(By.name("rememberMe"));

                //If we want the checkbox to be checked, check if it is NOT
                //checked and if so, click on it.
                if (checkbox) {
                    if (!elem.isSelected()) {
                        elem.click();
                    }
                }
                //If we do NOT want the checkbox to be checked, check if it is
                // //checked and if so, click on it
                else {
                    if (elem.isSelected()) {
                        elem.click();
                    }
                }
                //Find the submit button and click on it
                elem = driver.findElement(By.cssSelector("button.btn-primary"));
                elem.click();
                //Return true if we made it this far (no exceptions occurred along the way)
                return true;
            } catch (Exception e) {
                System.out.println("Problem logging in ");
            }
        }
        //If we made it here, there was a problem so return false
        return false;
    }

    //Check to see if we are currently logged in to the server
    boolean CheckIfLoggedIn(WebDriver driver) {
        try {
            //Find the Customer drop down (note, this only works on cloud and possibly workgroup)
            WebElement elem = driver.findElement(By.id("data-userLoggedIn"));
            //If the element was found then return true
            if (elem != null) {
                return true;
            }
        } catch (Exception e) {

        }
        //If we made it here, the element was not found so return false
        return false;
    }
}
