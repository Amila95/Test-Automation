import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//Uthpali
public class CommonMethods2 {

    public boolean waitUntilElementAppears(WebDriver driver, By locator, int timeOut) {
        try {
            WebElement element = new WebDriverWait(driver, timeOut).until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (Exception ex) {
            System.err.println("Timeout occurred while waiting for element to appear in CommonMethod.java");
        }
        return false;
    }

    public String CheckErrorMsg(WebDriver driver)throws Exception{
        try{
            if(!waitUntilElementAppears(driver,By.xpath("//body/div[1]/div[1]/div[1]"),20)){
                throw new NoSuchElementException("The login panel was not found");
            }
            WebElement panel = driver.findElement(By.xpath("//body/div[1]/div[1]/div[1]"));
            WebElement error = panel.findElement(By.xpath("//strong"));
            System.out.println(error.getText());

            if((!error.getText().equals("Invalid username or password"))){
                System.out.println("The error message was not found");
                return "The error message was not found";
            }else
                return "";
        }catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }
    }


    public String CheckLoginPage(WebDriver driver) throws Exception {
        try {
            String current = driver.getCurrentUrl();
            System.out.println("Reported current url is "+current);
            if (!current.equals(Configuration.currentServerURL+"/login")) {
                System.out.println("Login page was not found");
                return "Login page was not found";
            } else
                return "";

        } catch (Exception ex) {
            return "Exception Occurred: "+ ex.getMessage();
        }
    }

    public String CheckHomePage(WebDriver driver) throws Exception {
        try {
            String current = driver.getCurrentUrl();
            System.out.println("Reported current url is "+current);
//            Assert.assertEquals(current, DriverSetup.currentServerURL+"/files");
            if (!current.equals(Configuration.currentServerURL+"/files")) {
                System.out.println("Home page was not found");
                return "Home page was not found";
            } else
                return "";

        } catch (Exception ex) {
            return "Exception Occurred: "+ ex.getMessage();
        }
    }



    public String CheckToolbar(WebDriver driver) throws Exception {
        try {
            if (!waitUntilElementAppears(driver, By.xpath("//*[@id=\"typefi-bottom-navbar\"]/div[2]/div"), 20)) {
                throw new NoSuchElementException("The tool bar was not found");
            }
            WebElement ele = driver.findElement(By.xpath("//*[@id=\"typefi-bottom-navbar\"]/div[2]/div"));

            if (ele == null) {
                System.out.println("The tool bar return null value");
                return "The tool bar return null value";
            } else if (!ele.isDisplayed()) {
                System.out.println("The tool bar is either not present nor displayed");
                return "The tool bar is either not present nor displayed";
            } else if (!ele.isEnabled()) {
                System.out.println("The tool bar is disabled");
                return "The tool bar is disabled";
            } else
                ele.isDisplayed();
            return "";

        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }
    }

    public String ClickCopybutton(WebDriver driver) throws Exception {
        try {
            if (!waitUntilElementAppears(driver, By.id("typefi-files-copy"), 20)) {
                throw new NoSuchElementException("The copy button was not found");
            }
            WebElement ele = driver.findElement(By.id("typefi-files-copy"));

            if (ele == null) {
                System.out.println("The copy button return null value");
                return "The copy button return null value";
            } else if (!ele.isDisplayed()) {
                System.out.println("The copy Button is either not present nor displayed");
                return "The copy Button is either not present nor displayed";
            } else if (!ele.isEnabled()) {
                System.out.println("The copy Button is disabled");
                return "The copy Button is disabled";
            } else
                ele.click();
            return "";

        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }
    }

    public String CopyToDialogAppears(WebDriver driver) {
        try {
            if (!waitUntilElementAppears(driver, By.xpath("//*[@id=\"fileChooserModal\"]/div/div"), 20)) {
                throw new NoSuchElementException("Copy to dialog was not found");
            }

            WebElement dialog = driver.findElement(By.xpath("//*[@id=\"fileChooserModal\"]/div/div"));
            WebElement dialogName = dialog.findElement(By.id("typefi-file-chooser-title"));
            System.out.println(dialogName.getAttribute("innerHTML"));
            if (!dialogName.getAttribute("innerHTML").equals("Copy To")) {
                System.out.println("copy to dialog was not found");
                return "copy to dialog was not found";
            } else {
                return "";
            }

        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }
    }


    public String DialogCopybutton(WebDriver driver) throws Exception {
        try {
            if (!waitUntilElementAppears(driver, By.id("typefi-btn-folder-chooser"), 20)) {
                throw new NoSuchElementException("The copy button was not found");
            }
            //WebElement dialog = driver.findElement(By.id("footer-file-chooser"));
            WebElement copybtn = driver.findElement(By.id("typefi-btn-folder-chooser"));

            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOf(copybtn));

            if (copybtn == null) {
                System.out.println("The copy button return null value");
                return "The copy button return null value";
            } else if (!copybtn.isDisplayed()) {
                System.out.println("The copy Button is either not present nor displayed");
                //Thread.sleep(100000);
                return "The copy Button is either not present nor displayed";
            } else if (!copybtn.isEnabled()) {
                System.out.println("The copy Button is disabled");
                return "The copy Button is disabled";
            } else
                copybtn.click();
            try {
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return "";
        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }
    }

    public String CheckDetailsPage(WebDriver driver) throws Exception {
        try {
            String current = driver.getCurrentUrl();

            if (!(current.equals(Configuration.currentServerURL+"/details"))) {
                System.out.println("Navigated to wrong page");
                return "Navigated to wrong page";
            } else
                return "";
        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();


        }

    }
    public String DialogCancelbutton(WebDriver driver) throws Exception {
        try {
            if (!waitUntilElementAppears(driver, By.xpath("//*[@id=\"footer-file-chooser\"]/button[2]"), 20)) {
                throw new NoSuchElementException("The cancel button was not found");
            }
            //WebElement dialog = driver.findElement(By.id("footer-file-chooser"));
            WebElement cancelbtn = driver.findElement(By.xpath("//*[@id=\"footer-file-chooser\"]/button[2]"));

            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOf(cancelbtn));

            if (cancelbtn == null) {
                System.out.println("The cancel button return null value");
                return "The cancel button return null value";
            } else if (!cancelbtn.isDisplayed()) {
                System.out.println("The cancel Button is either not present nor displayed");
                //Thread.sleep(100000);
                return "The cancel Button is either not present nor displayed";
            } else if (!cancelbtn.isEnabled()) {
                System.out.println("The cancel Button is disabled");
                return "The cancel Button is disabled";
            } else
                cancelbtn.click();
            try {
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return "";
        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }
    }


    public String ClickDropdownArrow(WebDriver driver) throws Exception {
        try {
            if (!waitUntilElementAppears(driver, By.xpath("//body/div[1]/div[1]/div[2]/nav[1]/div[2]/div[1]/div[1]/button[2]"), 20)) {
                throw new NoSuchElementException("The dropdown button was not found");
            }
            WebElement ele = driver.findElement(By.xpath("//body/div[1]/div[1]/div[2]/nav[1]/div[2]/div[1]/div[1]/button[2]"));
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOf(ele));

            if (ele == null) {
                System.out.println("The dropdown arrow button return null value");
                return "The dropdown button return null value";
            } else if (!ele.isDisplayed()) {
                System.out.println("The dropdown arrow Button is either not present nor displayed");
                return "The dropdown arrow Button is either not present nor displayed";
            } else if (!ele.isEnabled()) {
                System.out.println("The dropdown arrow Button is disabled");
                return "The dropdown arrow Button is disabled";
            } else
                ele.click();
            try {
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return "";

        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }
    }
    public String CheckDropdownAppear(WebDriver driver) {
        try {
            if (!waitUntilElementAppears(driver, By.xpath("//*[@id=\"typefi-files-copy-duplicate\"]/ul"), 20)) {
                throw new NoSuchElementException("The dropdown was not found");
            }
            WebElement ele = driver.findElement(By.xpath("//*[@id=\"typefi-files-copy-duplicate\"]/ul"));

            if (ele == null) {
                System.out.println("The dropdown return null value");
                return "The dropdown return null value";
            } else if (!ele.isDisplayed()) {
                System.out.println("The dropdown is either not present nor displayed");
                return "The dropdown is either not present nor displayed";
            } else if (!ele.isEnabled()) {
                System.out.println("The dropdown is disabled");
                return "The dropdown is disabled";
            } else
                ele.isDisplayed();
            return "";

        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }
    }

    public String ClickDropdownCopyBtn(WebDriver driver) throws Exception {
        try {
            if (!waitUntilElementAppears(driver, By.xpath("//a[@id='typefi-files-copy']"), 20)) {
                throw new NoSuchElementException("The copy button was not found");
            }
            WebElement ele = driver.findElement(By.xpath("//a[@id='typefi-files-copy']"));

            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOf(ele));


            if (ele == null) {
                System.out.println("The copy button return null value");
                return "The copy button return null value";
            } else if (!ele.isDisplayed()) {
                System.out.println("The copy Button is either not present nor displayed");
                return "The copy Button is either not present nor displayed";
            } else if (!ele.isEnabled()) {
                System.out.println("The copy Button is disabled");
                return "The copy Button is disabled";
            } else
                ele.click();
            try {
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return "";

        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }
    }

    public String ClickDropdownDuplicateBtn(WebDriver driver) throws Exception {
        try {
            if (!waitUntilElementAppears(driver, By.id("typefi-files-duplicate"), 20)) {
                throw new NoSuchElementException("The copy button was not found");
            }
            WebElement ele = driver.findElement(By.id("typefi-files-duplicate"));

            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOf(ele));


            if (ele == null) {
                System.out.println("The duplicate button return null value");
                return "The duplicate button return null value";
            } else if (!ele.isDisplayed()) {
                System.out.println("The duplicate Button is either not present nor displayed");
                return "The duplicate Button is either not present nor displayed";
            } else if (!ele.isEnabled()) {
                System.out.println("The duplicate Button is disabled");
                return "The duplicate Button is disabled";
            } else
                ele.click();
            try {
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return "";

        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }
    }
    public String ClickRenamebutton(WebDriver driver) throws Exception {
        try {
            if (!waitUntilElementAppears(driver, By.id("typefi-files-rename"), 20)) {
                throw new NoSuchElementException("The rename button was not found");
            }
            WebElement ele = driver.findElement(By.id("typefi-files-rename"));

            if (ele == null) {
                System.out.println("The rename button return null value");
                return "The rename button return null value";
            } else if (!ele.isDisplayed()) {
                System.out.println("The rename Button is either not present nor displayed");
                return "The rename Button is either not present nor displayed";
            } else if (!ele.isEnabled()) {
                System.out.println("The rename Button is disabled");
                return "The rename Button is disabled";
            } else
                ele.click();
            try {
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return "";

        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }

    }
    public String RenameFileDialogAppears(WebDriver driver) {
        try {
            if (!waitUntilElementAppears(driver, By.xpath("//*[@id=\"renameFolderModal\"]/div"), 20)) {
                throw new NoSuchElementException("Rename file dialog was not found");
            }

            WebElement dialog = driver.findElement(By.xpath("//*[@id=\"renameFolderModal\"]/div"));
            WebElement dialogName = dialog.findElement(By.id("typefi-file-rename-title"));
            System.out.println(dialogName.getAttribute("innerHTML"));
            if (!dialogName.getAttribute("innerHTML").equals("Rename File")) {
                System.out.println("Rename file dialog was not found");
                return "Rename file dialog was not found";
            } else {
                return "";
            }

        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }
    }

    public String CheckReNameTxtBox(WebDriver driver) throws Exception {
        try {
            if (!waitUntilElementAppears(driver, By.id("renameFolderName"), 20)) {
                throw new NoSuchElementException("textbox was not found");
            }
            WebElement ele = driver.findElement(By.id("renameFolderName"));

            if (ele == null) {
                System.out.println("Textbox return null value");
                return "Textbox return null value";
            } else if (!ele.isDisplayed()) {
                System.out.println("Textbox  is either not present nor displayed");
                return "Textbox  is either not present nor displayed";
            } else if (!ele.isEnabled()) {
                System.out.println("Textbox  is disabled");
                return "Textbox  is disabled";
            } else
                return "";
        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }
    }

        public String ClearTextInRenameTextBox(WebDriver driver) throws Exception {
            try {
                if (!waitUntilElementAppears(driver, By.xpath("//input[@id='renameFolderName']"), 20)) {
                    throw new NoSuchElementException("The text  was not found");
                }
                WebElement ele = driver.findElement(By.xpath("//input[@id='renameFolderName']"));
                WebDriverWait wait = new WebDriverWait(driver, 30);
                wait.until(ExpectedConditions.visibilityOf(ele));

                if (ele == null) {
                    System.out.println("The text return null value");
                    return "The text return null value";
                } else if (!ele.isDisplayed()) {
                    System.out.println("The text is either not present nor displayed");
                    return "The text is either not present nor displayed";
                } else if (!ele.isEnabled()) {
                    System.out.println("The text is disabled");
                    return "The text is disabled";
                } else
                    ele.clear();
                try {
                    Thread.sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                return "";

            } catch (NoSuchElementException | TimeoutException tx) {
                System.out.println(tx.getMessage());
                return tx.getMessage();
            }
    }

    public String DialogRenamebutton(WebDriver driver) throws Exception {
        try {
            if (!waitUntilElementAppears(driver, By.id("typefi-files-rename-folder-submit"), 20)) {
                throw new NoSuchElementException("The rename button was not found");
            }

            WebElement renamebtn = driver.findElement(By.id("typefi-files-rename-folder-submit"));

            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOf(renamebtn));

            if (renamebtn == null) {
                System.out.println("The rename button return null value");
                return "The rename button return null value";
            } else if (!renamebtn.isDisplayed()) {
                System.out.println("The rename Button is either not present nor displayed");
                //Thread.sleep(100000);
                return "The rename Button is either not present nor displayed";
            } else if (!renamebtn.isEnabled()) {
                System.out.println("The rename Button is disabled");
                return "The rename Button is disabled";
            } else
                renamebtn.click();
            try {
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return "";
        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }
    }

    public String RenameDialogCancelbutton(WebDriver driver) throws Exception {
        try {
            if (!waitUntilElementAppears(driver, By.xpath("//*[@id=\"renameFolderModal\"]/div/div/div[3]/button[1]"), 20)) {
                throw new NoSuchElementException("The cancel button was not found");
            }
            //WebElement dialog = driver.findElement(By.id("footer-file-chooser"));
            WebElement cancelbtn = driver.findElement(By.xpath("//*[@id=\"renameFolderModal\"]/div/div/div[3]/button[1]"));

            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOf(cancelbtn));

            if (cancelbtn == null) {
                System.out.println("The cancel button return null value");
                return "The cancel button return null value";
            } else if (!cancelbtn.isDisplayed()) {
                System.out.println("The cancel Button is either not present nor displayed");
                //Thread.sleep(100000);
                return "The cancel Button is either not present nor displayed";
            } else if (!cancelbtn.isEnabled()) {
                System.out.println("The cancel Button is disabled");
                return "The cancel Button is disabled";
            } else
                cancelbtn.click();
            try {
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return "";
        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }
    }

    public String RenameDialogClosebutton(WebDriver driver) throws Exception {
        try {
            if (!waitUntilElementAppears(driver, By.xpath("//*[@id=\"renameFolderModal\"]/div/div/div[1]/button"), 20)) {
                throw new NoSuchElementException("The close button was not found");
            }
            //WebElement dialog = driver.findElement(By.id("footer-file-chooser"));
            WebElement closebtn = driver.findElement(By.xpath("//*[@id=\"renameFolderModal\"]/div/div/div[1]/button"));

            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOf(closebtn));

            if (closebtn == null) {
                System.out.println("The close button return null value");
                return "The close button return null value";
            } else if (!closebtn.isDisplayed()) {
                System.out.println("The close Button is either not present nor displayed");
                //Thread.sleep(100000);
                return "The close Button is either not present nor displayed";
            } else if (!closebtn.isEnabled()) {
                System.out.println("The close Button is disabled");
                return "The close Button is disabled";
            } else
                closebtn.click();
            try {
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return "";
        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }
    }

    public String ClickMovebutton(WebDriver driver) throws Exception {
        try {
            if (!waitUntilElementAppears(driver, By.id("typefi-files-move"), 20)) {
                throw new NoSuchElementException("The move button was not found");
            }
            WebElement ele = driver.findElement(By.id("typefi-files-move"));

            if (ele == null) {
                System.out.println("The move button return null value");
                return "The move button return null value";
            } else if (!ele.isDisplayed()) {
                System.out.println("The move Button is either not present nor displayed");
                return "The move Button is either not present nor displayed";
            } else if (!ele.isEnabled()) {
                System.out.println("The move Button is disabled");
                return "The move Button is disabled";
            } else
                ele.click();
            try {
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return "";

        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }

    }
    public String MoveFileDialogAppears(WebDriver driver) {
        try {
            if (!waitUntilElementAppears(driver, By.xpath("//*[@id=\"fileChooserModal\"]/div/div"), 20)) {
                throw new NoSuchElementException("Move file dialog was not found");
            }

            WebElement dialog = driver.findElement(By.xpath("//*[@id=\"fileChooserModal\"]/div/div"));
            WebElement dialogName = dialog.findElement(By.id("typefi-file-chooser-title"));
            System.out.println(dialogName.getAttribute("innerHTML"));
            if (!dialogName.getAttribute("innerHTML").equals("Move To")) {
                System.out.println("Move file dialog was not found");
                return "Move file dialog was not found";
            } else {
                return "";
            }

        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }
    }

    public String DialogMovebutton(WebDriver driver) throws Exception {
        try {
            if (!waitUntilElementAppears(driver, By.id("typefi-btn-folder-chooser"), 20)) {
                throw new NoSuchElementException("The move button was not found");
            }

            WebElement movebtn = driver.findElement(By.id("typefi-btn-folder-chooser"));

            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOf(movebtn));

            if (movebtn == null) {
                System.out.println("The move button return null value");
                return "The move button return null value";
            } else if (!movebtn.isDisplayed()) {
                System.out.println("The move Button is either not present nor displayed");
                //Thread.sleep(100000);
                return "The move Button is either not present nor displayed";
            } else if (!movebtn.isEnabled()) {
                System.out.println("The move Button is disabled");
                return "The move Button is disabled";
            } else
                movebtn.click();
            try {
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return "";
        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }
    }

    public String ErrorMsgChecking(WebDriver driver)throws Exception{
        try{
            if(!waitUntilElementAppears(driver,By.xpath("//*[@id=\"modal-footer-warn\"]"),20)){
                throw new NoSuchElementException("The error message was not found");
            }

            WebElement error = driver.findElement(By.xpath("//*[@id=\"modal-footer-warn\"]"));
            System.out.println(error.getAttribute("innerHTML"));

            if(!error.getAttribute("innerHTML").equals("The destination folder is the same folder")) {
                System.out.println("The error message mismatch");
                return "The error message mismatch";
            }else if((!error.isDisplayed())) {
                System.out.println("The error message was either not present nor displayed");
                return "The error message was either not present nor displayed";
            } else
                return "";
        }catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }
    }
    public String MoveDialogCancelbutton(WebDriver driver) throws Exception {
        try {
            if (!waitUntilElementAppears(driver, By.xpath("//*[@id=\"footer-file-chooser\"]/button[2]"), 20)) {
                throw new NoSuchElementException("The cancel button was not found");
            }
            WebElement ele = driver.findElement(By.xpath("//*[@id=\"footer-file-chooser\"]/button[2]"));

            if (ele == null) {
                System.out.println("The cancel button return null value");
                return "The cancel  button return null value";
            } else if (!ele.isDisplayed()) {
                System.out.println("The cancel  Button is either not present nor displayed");
                return "The cancel  Button is either not present nor displayed";
            } else if (!ele.isEnabled()) {
                System.out.println("The cancel  Button is disabled");
                return "The cancel Button is disabled";
            } else
                ele.click();
            try {
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return "";

        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }

    }
    public String MoveDialogClosebutton(WebDriver driver) throws Exception {
        try {
            if (!waitUntilElementAppears(driver, By.xpath("//*[@id=\"fileChooserModal\"]/div/div/div[1]/button/span[1]"), 20)) {
                throw new NoSuchElementException("The close button was not found");
            }

            WebElement closebtn = driver.findElement(By.xpath("//*[@id=\"fileChooserModal\"]/div/div/div[1]/button/span[1]"));

            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOf(closebtn));

            if (closebtn == null) {
                System.out.println("The close button return null value");
                return "The close button return null value";
            } else if (!closebtn.isDisplayed()) {
                System.out.println("The close Button is either not present nor displayed");
                //Thread.sleep(100000);
                return "The close Button is either not present nor displayed";
            } else if (!closebtn.isEnabled()) {
                System.out.println("The close Button is disabled");
                return "The close Button is disabled";
            } else
                closebtn.click();
            try {
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return "";
        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }
    }

    public String ClickDownloadbutton(WebDriver driver) throws Exception {
        try {
            if (!waitUntilElementAppears(driver, By.xpath("//*[@id=\"typefi-files-export\"]"), 20)) {
                throw new NoSuchElementException("The download button was not found");
            }
            WebElement ele = driver.findElement(By.xpath("//*[@id=\"typefi-files-export\"]"));

            if (ele == null) {
                System.out.println("The download button return null value");
                return "The download button return null value";
            } else if (!ele.isDisplayed()) {
                System.out.println("The download Button is either not present nor displayed");
                return "The download Button is either not present nor displayed";
            } else if (!ele.isEnabled()) {
                System.out.println("The download Button is disabled");
                return "The download Button is disabled";
            } else
                ele.click();
            try {
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return "";

        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }
    }

        public String ClickDeletebutton(WebDriver driver) throws Exception {
            try {
                if (!waitUntilElementAppears(driver, By.id("typefi-files-delete"), 20)) {
                    throw new NoSuchElementException("The Delete button was not found");
                }
                WebElement ele = driver.findElement(By.id("typefi-files-delete"));

                if (ele == null) {
                    System.out.println("The Delete button return null value");
                    return "The Delete button return null value";
                } else if (!ele.isDisplayed()) {
                    System.out.println("The Delete Button is either not present nor displayed");
                    return "The Delete Button is either not present nor displayed";
                } else if (!ele.isEnabled()) {
                    System.out.println("The Delete Button is disabled");
                    return "The Delete Button is disabled";
                } else
                    ele.click();
                try {
                    Thread.sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                return "";

            } catch (NoSuchElementException | TimeoutException tx) {
                System.out.println(tx.getMessage());
                return tx.getMessage();
            }

        }

    public String DeleteDialogAppears(WebDriver driver) {
        try {
            if (!waitUntilElementAppears(driver, By.xpath("//*[@id=\"confirmDeleteModal\"]/div/div"), 20)) {
                throw new NoSuchElementException("Delete file dialog was not found");
            }

            WebElement dialog = driver.findElement(By.xpath("//*[@id=\"confirmDeleteModal\"]/div/div"));
            WebElement dialogName = dialog.findElement(By.id("myModalLabel"));
            System.out.println(dialogName.getAttribute("innerHTML"));
            if (!dialogName.getAttribute("innerHTML").equals("Delete")) {
                System.out.println("Delete dialog was not found");
                return "Delete dialog was not found";
            } else {
                return "";
            }

        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }
    }
    public String DialogDeletebutton(WebDriver driver) throws Exception {
        try {
            if (!waitUntilElementAppears(driver, By.id("typefi-files-delete-files-submit"), 20)) {
                throw new NoSuchElementException("The delete button was not found");
            }

            WebElement deletebtn = driver.findElement(By.id("typefi-files-delete-files-submit"));

            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOf(deletebtn));

            if (deletebtn == null) {
                System.out.println("The delete button return null value");
                return "The  delete button return null value";
            } else if (!deletebtn.isDisplayed()) {
                System.out.println("The  delete Button is either not present nor displayed");
                //Thread.sleep(100000);
                return "The  delete Button is either not present nor displayed";
            } else if (!deletebtn.isEnabled()) {
                System.out.println("The  delete Button is disabled");
                return "The  delete Button is disabled";
            } else
                deletebtn.click();
            try {
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return "";
        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }
    }

    public String DeleteDialogCancelbutton(WebDriver driver) throws Exception {
        try {
            if (!waitUntilElementAppears(driver, By.xpath("//*[@id=\"confirmDeleteModal\"]/div/div/div[4]/button[1]"), 20)) {
                throw new NoSuchElementException("The cancel button was not found");
            }
            WebElement ele = driver.findElement(By.xpath("//*[@id=\"confirmDeleteModal\"]/div/div/div[4]/button[1]"));

            if (ele == null) {
                System.out.println("The cancel button return null value");
                return "The cancel  button return null value";
            } else if (!ele.isDisplayed()) {
                System.out.println("The cancel  Button is either not present nor displayed");
                return "The cancel  Button is either not present nor displayed";
            } else if (!ele.isEnabled()) {
                System.out.println("The cancel  Button is disabled");
                return "The cancel Button is disabled";
            } else
                ele.click();
            try {
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return "";

        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }

    }
    public String DeleteDialogClosebutton(WebDriver driver) throws Exception {
        try {
            if (!waitUntilElementAppears(driver, By.xpath("//*[@id=\"confirmDeleteModal\"]/div/div/div[1]/button"), 20)) {
                throw new NoSuchElementException("The close button was not found");
            }

            WebElement closebtn = driver.findElement(By.xpath("//*[@id=\"confirmDeleteModal\"]/div/div/div[1]/button"));

            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOf(closebtn));

            if (closebtn == null) {
                System.out.println("The close button return null value");
                return "The close button return null value";
            } else if (!closebtn.isDisplayed()) {
                System.out.println("The close Button is either not present nor displayed");
                //Thread.sleep(100000);
                return "The close Button is either not present nor displayed";
            } else if (!closebtn.isEnabled()) {
                System.out.println("The close Button is disabled");
                return "The close Button is disabled";
            } else
                closebtn.click();
            try {
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return "";
        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }
    }

    public String ClickNewFoldrButton(WebDriver driver) throws Exception {
        try {
            if (!waitUntilElementAppears(driver, By.id("typefi-files-new-folder"), 20)) {
                throw new NoSuchElementException("The NewFoldrButton was not found");
            }
            WebElement ele = driver.findElement(By.id("typefi-files-new-folder"));

            if (ele == null) {
                System.out.println("The NewFoldrButton return null value");
                return "The NewFoldrButton return null value";
            } else if (!ele.isDisplayed()) {
                System.out.println("The NewFoldrButton is either not present nor displayed");
                return "The NewFoldrButton is either not present nor displayed";
            } else if (!ele.isEnabled()) {
                System.out.println("The NewFoldrButton is disabled");
                return "The NewFoldrButton is disabled";
            } else
                ele.click();
            return "";

        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }
    }
    public String NewFolderDialogAppears(WebDriver driver) {
        try {
            if (!waitUntilElementAppears(driver, By.xpath("//*[@id=\"newFolderModal\"]/div/div"), 20)) {
                throw new NoSuchElementException("New Folder dialog was not found");
            }

            WebElement dialog = driver.findElement(By.xpath("//*[@id=\"newFolderModal\"]/div/div"));
            WebElement dialogName = dialog.findElement(By.id("myModalLabel"));
            System.out.println(dialogName.getAttribute("innerHTML"));
            if (!dialogName.getAttribute("innerHTML").equals("New Folder")) {
                System.out.println("New Folder dialog was not found");
                return "New Folder dialog was not found";
            } else {
                return "";
            }

        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }

    }
    public String CheckNeFolderTxtBox(WebDriver driver) throws Exception {
        try {
            if (!waitUntilElementAppears(driver, By.xpath("//*[@id=\"newFolderModal\"]/div/div"), 20)) {
                throw new NoSuchElementException("textbox was not found");
            }

            WebElement dialog = driver.findElement(By.xpath("//*[@id=\"newFolderModal\"]/div/div"));
            WebElement ele = dialog.findElement(By.id("folderName"));

            if (ele == null) {
                System.out.println("Textbox return null value");
                return "Textbox return null value";
            } else if (!ele.isDisplayed()) {
                System.out.println("Textbox is either not present nor displayed");
                return "Textbox is either not present nor displayed";
            } else if (!ele.isEnabled()) {
                System.out.println("Textbox  is disabled");
                return "Textbox is disabled";
            } else
                return "";
        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }
    }

    public String NewFolderDialogCreatebutton(WebDriver driver) throws Exception {
        try {
            if (!waitUntilElementAppears(driver, By.xpath("//*[@id=\"typefi-files-new-folder-submit\"]"), 20)) {
                throw new NoSuchElementException("The cancel button was not found");
            }
            WebElement ele = driver.findElement(By.xpath("//*[@id=\"typefi-files-new-folder-submit\"]"));

            if (ele == null) {
                System.out.println("The create button return null value");
                return "The create button return null value";
            } else if (!ele.isDisplayed()) {
                System.out.println("The create  Button is either not present nor displayed");
                return "The create  Button is either not present nor displayed";
            } else if (!ele.isEnabled()) {
                System.out.println("The create  Button is disabled");
                return "The create Button is disabled";
            } else
                ele.click();
            try {
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return "";

        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }

    }
    public String NewFolderDialogCancelbutton(WebDriver driver) throws Exception {
        try {
            if (!waitUntilElementAppears(driver, By.xpath("//*[@id=\"confirmDeleteModal\"]/div/div/div[4]/button[1]"), 20)) {
                throw new NoSuchElementException("The cancel button was not found");
            }
            WebElement ele = driver.findElement(By.xpath("//*[@id=\"confirmDeleteModal\"]/div/div/div[4]/button[1]"));

            if (ele == null) {
                System.out.println("The create button return null value");
                return "The create button return null value";
            } else if (!ele.isDisplayed()) {
                System.out.println("The create  Button is either not present nor displayed");
                return "The create  Button is either not present nor displayed";
            } else if (!ele.isEnabled()) {
                System.out.println("The create  Button is disabled");
                return "The cancel Button is disabled";
            } else
                ele.click();
            try {
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return "";

        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }

    }
    public String NewFolderDialogClosebutton(WebDriver driver) throws Exception {
        try {
            if (!waitUntilElementAppears(driver, By.xpath("//*[@id=\"confirmDeleteModal\"]/div/div/div[1]/button"), 20)) {
                throw new NoSuchElementException("The close button was not found");
            }

            WebElement closebtn = driver.findElement(By.xpath("//*[@id=\"confirmDeleteModal\"]/div/div/div[1]/button"));

            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOf(closebtn));

            if (closebtn == null) {
                System.out.println("The close button return null value");
                return "The close button return null value";
            } else if (!closebtn.isDisplayed()) {
                System.out.println("The close Button is either not present nor displayed");
                //Thread.sleep(100000);
                return "The close Button is either not present nor displayed";
            } else if (!closebtn.isEnabled()) {
                System.out.println("The close Button is disabled");
                return "The close Button is disabled";
            } else
                closebtn.click();
            try {
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return "";
        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }
    }




}

