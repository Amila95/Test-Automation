import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import sun.security.krb5.Config;

import java.util.List;


//--Nuwan Sampath--
public class CommonMethod {

    public void waitForLoad(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
    }

    public boolean waitUntilElementAppears(WebDriver driver, By locator, int timeOut) {
        try {
            WebElement element = new WebDriverWait(driver, timeOut).until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (Exception ex) {
            System.err.println("Timeout occurred while waiting for element to appear in CommonMethod.java");
        }
        return false;
    }
    public boolean waitUntilElementIsVisible(WebDriver driver, By locator, int timeOut) {
        try {
            WebElement element = new WebDriverWait(driver, timeOut).until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (Exception ex) {
            System.err.println("Timeout occurred while waiting for element to be visible in CommonMethod.java");
        }
        return false;
    }
    public boolean waitUntilElementAppearsAndIsVisible(WebDriver driver, By locator, int timeOut)
    {
        if(waitUntilElementAppears(driver,locator,timeOut))
        {
            return waitUntilElementIsVisible(driver, locator, timeOut);
        }
        return false;
    }
    //Login page related

    public String CheckLoginPage(WebDriver driver) throws Exception {
        try {
            String current = driver.getCurrentUrl();
            System.out.println("Reported current url is " + current);
            if (!current.equals(Configuration.currentServerURL + "/login")) {
                System.out.println("Login page was not found");
                return "Login page was not found";
            } else
                return "";

        } catch (Exception ex) {
            return "Exception Occurred: " + ex.getMessage();
        }
    }

    public String CheckErrorMsg(WebDriver driver) throws Exception {
        try {
            if (!waitUntilElementAppears(driver, By.xpath("/html/body/div[1]/div/div/div/div[2]/form/p/strong"), 20)) {
                throw new NoSuchElementException("The error message was not found");
            }

            WebElement error = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[2]/form/p/strong"));
            System.out.println(error.getAttribute("innerHTML"));

            if (!error.getAttribute("innerHTML").equals("Invalid username or password")) {
                System.out.println("The error message mismatch");
                return "The error message mismatch";
            } else if ((!error.isDisplayed())) {
                System.out.println("The error message was either not present nor displayed");
                return "The error message was either not present nor displayed";
            } else
                return "";
        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }
    }

    //Files page related

    public String CheckHomePage(WebDriver driver) throws Exception {
        try {
            String current = driver.getCurrentUrl();
            System.out.println("Reported current url is " + current);
            if (!current.equals(Configuration.currentServerURL + "/files")) {
                System.out.println("Home page was not found");
                return "Home page was not found";
            } else
                return "";

        } catch (Exception ex) {
            return "Exception Occurred: " + ex.getMessage();
        }
    }

    public String ClickFilesTab(WebDriver driver) throws Exception {
        try {
            if (!waitUntilElementAppears(driver, By.xpath("//body/div[1]/div[1]/nav[1]/div[1]/div[2]/ul[1]/li[1]/a[2]"), 20)) {
                throw new NoSuchElementException("The Files tab button was not found");
            }
            WebElement ele = driver.findElement(By.xpath("//body/div[1]/div[1]/nav[1]/div[1]/div[2]/ul[1]/li[1]/a[2]"));

            if (ele == null) {
                System.out.println("The Files tab button return null value");
                return "The Files tab button return null value";
            } else if (!ele.isDisplayed()) {
                System.out.println("The Files tab Button is either not present nor displayed");
                return "The Files tab Button is either not present nor displayed";
            } else if (!ele.isEnabled()) {
                System.out.println("The Files tab Button is disabled");
                return "The Files tab Button is disabled";
            } else
                ele.click();
            return "";

        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }
    }

    public String ClickNewFolderBtn(WebDriver driver) throws Exception {
        try {
            if (!waitUntilElementAppears(driver, By.id("typefi-files-new-folder"), 20)) {
                throw new NoSuchElementException("The New Project button was not found");
            }
            WebElement ele = driver.findElement(By.id("typefi-files-new-folder"));

            if (ele == null) {
                System.out.println("The New Folder button return null value");
                return "The New Folder button return null value";
            } else if (!ele.isDisplayed()) {
                System.out.println("The New Folder Button is either not present nor displayed");
                return "The New Folder Button is either not present nor displayed";
            } else if (!ele.isEnabled()) {
                System.out.println("The New Folder Button is disabled");
                return "The New Folder Button is disabled";
            } else
                ele.click();
            return "";

        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }
    }

    public String ClickNewProjectBtn(WebDriver driver) throws Exception {
        try {
            if (!waitUntilElementAppears(driver, By.id("typefi-files-new-project"), 20)) {
                throw new NoSuchElementException("The New Project button was not found");
            }
            WebElement ele = driver.findElement(By.id("typefi-files-new-project"));

            if (ele == null) {
                System.out.println("The New Project button return null value");
                return "The New Project button return null value";
            } else if (!ele.isDisplayed()) {
                System.out.println("The New Project Button is either not present nor displayed");
                return "The New Project Button is either not present nor displayed";
            } else if (!ele.isEnabled()) {
                System.out.println("The New Project Button is disabled");
                return "The New Project Button is disabled";
            } else
                ele.click();
            return "";

        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }
    }

    public String ClickNewWorkflowBtn(WebDriver driver) throws Exception {
        try {
            if (!waitUntilElementAppears(driver, By.id("typefi-files-new-workflow"), 20)) {
                throw new NoSuchElementException("The New workflow button was not found");
            }
            WebElement ele = driver.findElement(By.id("typefi-files-new-workflow"));

            if (ele == null) {
                System.out.println("The New workflow button return null value");
                return "The New workflow button return null value";
            } else if (!ele.isDisplayed()) {
                System.out.println("The New workflow Button is either not present nor displayed");
                return "The New workflow Button is either not present nor displayed";
            } else if (!ele.isEnabled()) {
                System.out.println("The New workflow Button is disabled");
                return "The New workflow Button is disabled";
            } else
                ele.click();
            return "";

        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }
    }

    public String ClickAddFilesBtn(WebDriver driver) throws Exception {
        try {
            if (!waitUntilElementAppears(driver, By.id("typefi-files-add"), 20)) {
                throw new NoSuchElementException("The Add Files button was not found");
            }
            WebElement ele = driver.findElement(By.id("typefi-files-add"));

            if (ele == null) {
                System.out.println("The Add Files button return null value");
                return "The Add Files button return null value";
            } else if (!ele.isDisplayed()) {
                System.out.println("The Add Files Button is either not present nor displayed");
                return "The Add Files Button is either not present nor displayed";
            } else if (!ele.isEnabled()) {
                System.out.println("The Add Files Button is disabled");
                return "The Add Files Button is disabled";
            } else
                ele.click();
            return "";

        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }
    }
    //New Folder page related

    public String NewFolderDialogAppears(WebDriver driver) {
        try {
            if (!waitUntilElementAppears(driver, By.xpath("//*[@id=\"newFolderModal\"]/div/div/div[1]"), 20)) {
                throw new NoSuchElementException("Choose Workflow dialog was not found");
            }

            WebElement dialog = driver.findElement(By.xpath("//*[@id=\"newFolderModal\"]/div/div/div[1]"));
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

    //New Project page related

    public String CheckProjPage(WebDriver driver) throws Exception {
        try {
            if (!waitUntilElementAppears(driver, By.id("form-create-project"), 20)) {
                throw new NoSuchElementException("New Project text field was not found");
            }
            WebElement pageID = driver.findElement(By.id("form-create-project"));
            String current = driver.getCurrentUrl();
            //if (!pageID.equals("New Project") && !current.equals(DriverSetup.currentServerURL+"/projects")) {
            if (!(pageID.getAttribute("id").equals("form-create-project") && current.equals(Configuration.currentServerURL + "/projects"))) {
                System.out.println("Navigated to wrong page");
                return "Navigated to wrong page";
            } else
                return "";
        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }
    }

    public String ProjectNameTxtBox(WebDriver driver) throws Exception {
        try {
            if (!waitUntilElementAppears(driver, By.id("projectName"), 20)) {
                throw new NoSuchElementException("Project Name field was not found");
            }
            WebElement ele = driver.findElement(By.id("projectName"));

            if (ele == null) {
                System.out.println("Project Name field return null value");
                return "Project Name field return null value";
            } else if (!ele.isDisplayed()) {
                System.out.println("Project Name field is either not present nor displayed");
                return "Project Name field is either not present nor displayed";
            } else if (!ele.isEnabled()) {
                System.out.println("Project Name field is disabled");
                return "Project Name field is disabled";
            } else
                return "";
        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }
    }

    public String ChooseWorkflow(WebDriver driver) throws Exception {
        try {
            if (!waitUntilElementAppears(driver, By.xpath("//body/div[1]/div[1]/div[2]/form[1]/fieldset[2]/div[1]/div[1]/div[1]/div[1]/div[1]/button[1]"), 20)) {
                throw new NoSuchElementException("Choose workflow button was not found");
            }
            WebElement ele = driver.findElement(By.xpath("//body/div[1]/div[1]/div[2]/form[1]/fieldset[2]/div[1]/div[1]/div[1]/div[1]/div[1]/button[1]"));

            if (ele == null) {
                System.out.println("Choose workflow button return null value");
                return "Choose workflow button return null value";
            } else if (!ele.isDisplayed()) {
                System.out.println("Choose workflow Button is either not present nor displayed");
                return "Choose workflow Button is either not present nor displayed";
            } else if (!ele.isEnabled()) {
                System.out.println("Choose workflow Button is disabled");
                return "Choose workflow Button is disabled";
            } else
                ele.click();
            return "";
        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }
    }

    public String ChooseWFDialogAppears(WebDriver driver) {
        try {
            if (!waitUntilElementAppears(driver, By.xpath("//body/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]"), 20)) {
                throw new NoSuchElementException("Choose Workflow dialog was not found");
            }

            WebElement dialog = driver.findElement(By.xpath("//body/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]"));
            WebElement dialogName = dialog.findElement(By.id("typefi-file-chooser-title"));
            if (!dialogName.getAttribute("innerHTML").equals("Choose Workflow")) {
                System.out.println("Choose Workflow dialog was not found");
                return "Choose Workflow dialog was not found";
            } else {
                return "";
            }
        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }
    }


    public String AddWorkflow(WebDriver driver) throws Exception {
        try {
            if (!waitUntilElementAppears(driver, By.id("typefi-projects-add-workflow"), 20)) {
                throw new NoSuchElementException("Add workflow button was not found");
            }
            WebElement ele = driver.findElement(By.id("typefi-projects-add-workflow"));

            if (ele == null) {
                System.out.println("Add workflow button return null value");
                return "Add workflow button return null value";
            } else if (!ele.isDisplayed()) {
                System.out.println("Add workflow Button is either not present nor displayed");
                return "Add workflow Button is either not present nor displayed";
            } else if (!ele.isEnabled()) {
                System.out.println("Add workflow Button is disabled");
                return "Add workflow Button is disabled";
            } else
                ele.click();
            return "";

        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }
    }


    public String ClickSaveBtn(WebDriver driver) throws Exception {
        try {
            if (!waitUntilElementAppears(driver, By.id("typefi-projects-save"), 30)) {
                throw new NoSuchElementException("Save button was not found");
            }
            WebElement ele = driver.findElement(By.id("typefi-projects-save"));

            if (ele == null) {
                System.out.println("Save button return null value");
                return "Save button return null value";
            } else if (!ele.isDisplayed()) {
                System.out.println("Save Button is either not present nor displayed");
                return "Save Button is either not present nor displayed";
            } else if (!ele.isEnabled()) {
                System.out.println("Save Button is disabled");
                return "Save Button is disabled";
            } else
                ele.click();
            return "";

        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }
    }

    public String ClickCancelBtn(WebDriver driver) throws Exception {
        try {
            if (!waitUntilElementAppears(driver, By.id("typefi-cancel"), 20)) {
                throw new NoSuchElementException("The Cancel button was not found");
            }
            WebElement ele = driver.findElement(By.id("typefi-cancel"));

            if (ele == null) {
                System.out.println("Cancel button return null value");
                return "Cancel button return null value";
            } else if (!ele.isDisplayed()) {
                System.out.println("Cancel Button is either not present nor displayed");
                return "Cancel Button is either not present nor displayed";
            } else if (!ele.isEnabled()) {
                System.out.println("Cancel Button is disabled");
                return "Cancel Button is disabled";
            } else
                ele.click();
            return "";

        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }
    }

    public String DeleteWorkflow1(WebDriver driver) throws Exception {
        try {
            if (!waitUntilElementAppears(driver, By.name("delete-workflow"), 20)) {
                throw new NoSuchElementException("Delete button was not found");
            }
            WebElement ele = driver.findElement(By.name("delete-workflow"));

            if (ele == null) {
                System.out.println("Delete button return null value");
                return "Delete button return null value";
            } else if (!ele.isDisplayed()) {
                System.out.println("Delete Button is either not present nor displayed");
                return "Delete Button is either not present nor displayed";
            } else if (!ele.isEnabled()) {
                System.out.println("Delete Button is disabled");
                return "Delete Button is disabled";
            } else
                ele.click();
            return "";
        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }
    }

    //Workflows page related

    public String CheckQAFolder(WebDriver driver) throws Exception {
        try {
            if (!CheckLocationExists(driver, Configuration.getQAFolderFullPath()))
            {
                //The QA folder does not exist... so create it.
                if(!CreateNewFolderAtPath(driver, Configuration.getQAFolderParent(), Configuration.getQAFolderName()).equals(("")))
                {
                    //if there was a problem creating the folder return an error
                    return "problem #1 creating the QA folder in CheckQAFolder method";
                }
                if(!CheckLocationExists(driver,Configuration.getQAFolderFullPath()))
                {
                    return "problem #2 creating the QA folder in CheckQAFolder method";
                }
            }
            return "";

        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }
    }
    //CREATE A NEW FOLDER
    public String CreateNewFolderAtPath(WebDriver driver, String parentFolder, String newFolderName)
    {
        driver.navigate().to(Configuration.currentServerURL + "/files" + parentFolder);
        waitForLoad(driver);

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("typefi-files-new-folder"))).click();

        if (!waitUntilElementAppears(driver, By.xpath("//*[@id=\"newFolderModal\"]/div/div/div[1]"), 20)) {
            throw new NoSuchElementException("Choose Workflow dialog was not found");
        }

        WebElement dialog = driver.findElement(By.xpath("//*[@id=\"newFolderModal\"]/div/div/div[1]"));
        WebElement dialogName = dialog.findElement(By.id("myModalLabel"));

        System.out.println(dialogName.getAttribute("innerHTML"));
        if (!dialogName.getAttribute("innerHTML").equals("New Folder")) {
            System.out.println("New Folder dialog was not found");
            return "New Folder dialog was not found";
        } else {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("folderName")));
            driver.findElement(By.name("folderName")).sendKeys(newFolderName);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("typefi-files-new-folder-submit"))).click();
            return "";
        }
    }
    //CHECK IF A FILE OR FOLDER EXISTS
    public boolean CheckLocationExists(WebDriver driver, String path)
    {
        driver.navigate().to(Configuration.currentServerURL +"/files"+ path);
        System.err.println("WAITING FOR LOAD");
        waitForLoad(driver);

        System.err.println("LOADED");
        String returnMessage = CheckTypefiErrorPanl(driver);
        System.err.println("CHECKED ERRORPANEL");
        if(!returnMessage.equals(""))
        {
            System.err.print(returnMessage);
        }
        //If error page found it mean location does not exist and return false.
        return (!returnMessage.equals(""));

    }
    public String CheckWorkflowsPage(WebDriver driver) throws Exception {
        try {
            String current = driver.getCurrentUrl();
            if (!(current.equals(Configuration.currentServerURL + "/workflows"+Configuration.getQAFolderFullPath())))
            {
                System.out.println("Navigated to wrong page");
                return "Navigated to wrong page";
            } else
                return "";
        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }
    }
    //DELETE A FILE AT RANDOM
    public String DeleteFile(WebDriver driver, String parentPath, String fileName)
    {
        try
        {
            if(CheckLocationExists(driver, parentPath+"/"+fileName)) {
                driver.navigate().to(Configuration.currentServerURL + "/files" + parentPath);
                waitForLoad(driver);
                /*
                WebDriverWait wait = new WebDriverWait(driver, 30);
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"deleteFilesForm\"]/div/table/tbody/tr/td[1]/input")));
                */
                boolean fileFound = false;
                List<WebElement> table = driver.findElements(By.xpath("//*[@id=\"deleteFilesForm\"]/div/table/tbody/tr"));
                for (int i = 0; i < table.size(); i++) {
                    System.out.println("CHECKING ROW: "+table.get(i).getAttribute(("innerHTML")));
                    WebElement item = table.get(i).findElement(By.className("typefi-files-name"));
                    if (item.isDisplayed()) {
                        System.err.println("COMPARING: "+item.getAttribute("data-file-name-including-extension-lower-case").toLowerCase()+" WITH: "+fileName.toLowerCase());
                        if (item.getAttribute("data-file-name-including-extension-lower-case").toLowerCase().equals(fileName.toLowerCase()))
                        {
                            System.err.println("FOUND THE ONE!!!");
                            fileFound = true;
                            WebElement checkBox = table.get(i).findElement(By.xpath(".//input"));
                            System.err.print("OUTER HTML: "+checkBox.getAttribute("outerHTML"));
                            if(!checkBox.isSelected()) {
                                checkBox.click();
                                waitForLoad(driver);
                            }
                            DeleteWorkflow2(driver);

                            DeleteDialogAppears(driver);
                            //I SUSPECT THERE'S SOME JAVASCRIPT IN THE BACKGROUND FILLING IN SOME VALUE WHICH WE NEED
                            //TO WAIT FOR SO I'VE ADDED TWO EXPLICIT WAITS... ideally we'd out what it is we're actually
                            //waiting on and wait specifically for that.
                            Thread.sleep(500);
                            System.err.println(ConfirmDelete(driver));
                            Thread.sleep(500);
                            break;
                        }
                    }
                }
            }
            else
            {
                System.out.println("File did not exist so no need to delete it");
            }
        }
        catch(Exception e)
        {
            System.err.println("Exception occured whe trying to delete file in methos DeleteFile");
        }
        return "";
    }

    public String WorkflowNameTxtBox(WebDriver driver) throws Exception {
        try {
            if (!waitUntilElementAppears(driver, By.id("typefi-workflows-name"), 20)) {
                throw new NoSuchElementException("Workflow Name field was not found");
            }
            WebElement ele = driver.findElement(By.id("typefi-workflows-name"));

            if (ele == null) {
                System.out.println("Workflow Name field return null value");
                return "Workflow Name field return null value";
            } else if (!ele.isDisplayed()) {
                System.out.println("Workflow Name field is either not present nor displayed");
                return "Workflow Name field is either not present nor displayed";
            } else if (!ele.isEnabled()) {
                System.out.println("Workflow Name field is disabled");
                return "Workflow Name field is disabled";
            } else
                return "";
        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }
    }

    public String ClickWorkflowSaveBtn(WebDriver driver) throws Exception {
        try {
            if (!waitUntilElementAppears(driver, By.id("typefi-workflows-save"), 30)) {
                throw new NoSuchElementException("Save button was not found");
            }
            WebElement ele = driver.findElement(By.id("typefi-workflows-save"));

            if (ele == null) {
                System.out.println("Save button return null value");
                return "Save button return null value";
            } else if (!ele.isDisplayed()) {
                System.out.println("Save Button is either not present nor displayed");
                return "Save Button is either not present nor displayed";
            } else if (!ele.isEnabled()) {
                System.out.println("Save Button is disabled");
                return "Save Button is disabled";
            } else
                ele.click();
            return "";

        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }
    }

    public String DeleteWorkflow2(WebDriver driver) throws Exception {
        try {
            if (!waitUntilElementAppearsAndIsVisible(driver, By.id("typefi-files-delete"), 20)) {
                throw new NoSuchElementException("Delete button was not found");
            }
            WebElement ele = driver.findElement(By.id("typefi-files-delete"));

            if (ele == null) {
                System.out.println("Delete button return null value");
                return "Delete button return null value";
            } else if (!ele.isDisplayed()) {
                System.out.println("Delete Button is either not present nor displayed");
                return "Delete Button is either not present nor displayed";
            } else if (!ele.isEnabled()) {
                System.out.println("Delete Button is disabled");
                return "Delete Button is disabled";
            } else
                ele.click();
            return "";
        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }
    }

    public String DeleteDialogAppears(WebDriver driver) {
        try {
            if (!waitUntilElementAppearsAndIsVisible(driver, By.xpath("//*[@id=\"confirmDeleteModal\"]/div/div/div[1]"), 20)) {
                throw new NoSuchElementException("Delete confirmation model was not found");
            }

            WebElement modelHeader = driver.findElement(By.xpath("//*[@id=\"confirmDeleteModal\"]/div/div/div[1]"));
            WebElement modelName = modelHeader.findElement(By.id("myModalLabel"));
//            System.out.println(dialogName.getAttribute("innerHTML"));
            if (!modelName.getAttribute("innerHTML").equals("Delete")) {
                System.out.println("Delete confirmation model was not found");
                return "Delete confirmation model was not found";
            } else {
                return "";
            }

        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }
    }

    public String ConfirmDelete(WebDriver driver) throws Exception {
        try {
            if (!waitUntilElementAppearsAndIsVisible(driver, By.id("typefi-files-delete-files-submit"), 20)) {
                throw new NoSuchElementException("Delete button was not found");
            }

            WebElement ele = driver.findElement(By.id("typefi-files-delete-files-submit"));


            if (ele == null) {
                System.out.println("Add Action button return null value");
                return "Delete button return null value";
            } else if (!ele.isDisplayed()) {
                System.out.println("Add Action Button is either not present nor displayed");
                return "Delete button is either not present nor displayed";
            } else if (!ele.isEnabled()) {
                System.out.println("Add Action Button is disabled");
                return "Delete button is disabled";
            } else
                ele.click();
            return "";
        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }
    }

    public String AddAction(WebDriver driver) throws Exception {
        try {
            if (!waitUntilElementAppears(driver, By.id("typefi-workflows-add-action-browse-new-workflow"), 20)) {
                throw new NoSuchElementException("Add Action button was not found");
            }
            WebElement ele = driver.findElement(By.id("typefi-workflows-add-action-browse-new-workflow"));

            if (ele == null) {
                System.out.println("Add Action button return null value");
                return "Add Action button return null value";
            } else if (!ele.isDisplayed()) {
                System.out.println("Add Action Button is either not present nor displayed");
                return "Add Action Button is either not present nor displayed";
            } else if (!ele.isEnabled()) {
                System.out.println("Add Action Button is disabled");
                return "Add Action Button is disabled";
            } else
                ele.click();
            return "";
        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }
    }

    public String NewActionDialogAppears(WebDriver driver) {
        try {
            if (!waitUntilElementAppears(driver, By.xpath("//*[@id=\"typefi-actions-add-action-modal\"]/div/div"), 20)) {
                throw new NoSuchElementException("New Action dialog was not found");
            }

            WebElement dialog = driver.findElement(By.xpath("//*[@id=\"typefi-actions-add-action-modal\"]/div/div"));
            WebElement dialogName = dialog.findElement(By.id("typefi-actions-add-action-modal-label"));
            if (!dialogName.getAttribute("innerHTML").equals("New Action")) {
                System.out.println("New Action dialog was not found");
                return "New Action dialog was not found";
            } else {
                return "";
            }
        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }
    }


    //Common

    public String CheckTypefiErrorPanl(WebDriver driver) {
        try {
            waitForLoad(driver);

            if (!waitUntilElementAppears(driver, By.xpath("//*[@id=\"typefi-main-panel\"]/*[@id=\"typefi-error-panel\"]"), 1)) {
                throw new NoSuchElementException("Typefi error panel was not found");
            }

            WebElement page = driver.findElement(By.xpath("//*[@id=\"typefi-main-panel\"]/*[@id=\"typefi-error-panel\"]"));

            WebElement errorTitle = page.findElement(By.id("title-big"));

//            System.out.println(errorTitle.getAttribute("innerHTML"));
            if (!errorTitle.getAttribute("innerHTML").equals("'Curiouser and curiouser!' cried Alice.")) {
                System.out.println("Error page was not found");
                return "Error page was not found";
            } else {
                return "";
            }
        } catch (NoSuchElementException | TimeoutException tx) {
            System.out.println(tx.getMessage());
            return tx.getMessage();
        }
    }
}
