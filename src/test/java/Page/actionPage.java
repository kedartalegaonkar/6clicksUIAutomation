package Page;

import java.util.Map;
import commons.Util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

public class actionPage {
	WebDriver driver;
	/*public loginScreen(WebDriver driver)
	{
		 PageFactory.initElements(driver, this);
	}*/
	
	public actionPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		//super();
		// TODO Auto-generated constructor stub
	}
	//loginScreen loginpage=new loginScreen(driver);
	@FindBy(xpath="//*[@id='menuItem']/a/span[contains(text(),'Tenants')]") public WebElement tenantLink;
	@FindBy(xpath="//div[@class='m-portlet__head-tools']/a") public WebElement createTenant;
	@FindBy(xpath="//input[@name='teamName']") public WebElement EditTeamName;
	@FindBy(id="AdminEmailAddress") public WebElement AdminEmailAddress;
	@FindBy(xpath="//form/div[2]/div[7]/label[1]/span") public WebElement checkBoxChangePassword;
	@FindBy(xpath="//form/div[2]/div[7]/label[2]/span") public WebElement checkBoxActivationEmail;
	@FindBy(xpath="//form/div[2]/div[7]/label[3]/span") public WebElement checkBoxActive;
	@FindBy(xpath="//form/div[2]/div[7]/label[4]/span") public WebElement checkBoxPublish;
	@FindBy(xpath="//form/div[2]/div[7]/label[5]/span") public WebElement checkBoxServiceProvider;
	
	@FindBy(xpath="//form/div[2]/div[4]/label/span") public WebElement checkBoxSetPassword;
	@FindBy(id="adminPassword") public WebElement editAdminPassword;
	@FindBy(id="AdminPasswordRepeat") public WebElement editAdminPasswordRepeat;
	
	//------------
	loginScreen loginpage=new loginScreen(driver);
	public void createUser(Map<String, String> map, String element1,String element2)
	{	PageFactory.initElements(driver, this);
		 map.put("DefaultUser",map.get("DefaultUser")+loginpage.randomNumber());
		 map.put("DefaultUserLogin",map.get("DefaultUser"));
		 
		 //visibilityOfElement(tenantLink, 7);
		 //tenantLink.click();
		 loginpage.visibilityOfElement(createTenant, 5);
		 createTenant.click();
		 loginpage.waitForElement(EditTeamName);
		 EditTeamName.sendKeys(map.get("DefaultUser"));
		 AdminEmailAddress.sendKeys(map.get("EmailID"));
		 checkBoxActivationEmail.click();		 
		 if(element1.equals("Publish"))
			 checkBoxPublish.click();
		 if(element2.equals("Service"))
			 {
			 	checkBoxServiceProvider.click();
			 	checkBoxSetPassword.click();
			 	loginpage.waitForElement(editAdminPassword);
				editAdminPassword.sendKeys("Welcome1");
				editAdminPasswordRepeat.sendKeys("Welcome1");
			 }
		 loginpage.waitForElement(loginpage.createUserSaveBtn);
		 loginpage.createUserSaveBtn.click();
		 loginpage.waitForElement(loginpage.tenantSearch);
	}	
	
	
	
	

	@FindBy(xpath="//a[@class='LoginLinks']") public WebElement teamSelect;
	@FindBy(xpath="(//button[@title='Assign question to users'])[1]") public WebElement assignQuestion;
	@FindBy(xpath="(//button[@title='Open action and remediation manager'])[1]") public WebElement addAction;
	
	@FindBy(xpath="//div[@class='e-multi-select-wrapper']") public WebElement selectUser;
	@FindBy(xpath="//*[@id='mat-dialog-2']/add-user-dialog/form/div[1]/button") public WebElement closebutton;
	
	@FindBy(xpath="//input[@name='task']") public WebElement itemName;
	@FindBy(xpath="//span[@class='e-searcher e-multiselect-box']") public WebElement userName;
	@FindBy(xpath="//button[@title='Browse...']") public WebElement btnBrowse;
	@FindBy(xpath="//span[@title='Delete file']") public WebElement btnDeleteFile;
	@FindBy(xpath="//*[@id='mat-dialog-2']/app-question-manager/div[1]/div/div[2]/button[3]") public WebElement closeBtn;	
	
	/*public void createAction()
	{
		waitForElement(selectAllDomain);
		//Thread.sleep(2000);
		selectAllDomain.click();
		waitForElement(assignQuestion);
		assignQuestion.click();
		selectUser.click();
		selectUser.sendKeys("admin admin");
		selectdropdown();
		closebutton.click();
	}
	
	public void addAction( SoftAssert softAssertion)
	{
		waitForElement(addAction );
		addAction.click();
		visibilityOfElement(addquestionpref, 5);
		addquestionpref.click();
		itemName.sendKeys("ActionName1");
		userName.click();
		userName.sendKeys("admin admin");
		selectdropdown();
		softAssertion.assertFalse(btnDeleteFile.isDisplayed(), "Delete file button");
		btnBrowse.click();
		//Thread.sleep(3000);
		//E:\New Selenium Class\UIAutomation6Clicks\Test Data\UploadFile.txt
		uploadFileWithRobot("E:/New Selenium Class/UIAutomation6Clicks/Test Data/UploadFile1.txt");
		//Thread.sleep(2000);
		waitForElement(btnuploadDoc);
		btnuploadDoc.click();
		waitForElement(btnDeleteFile);
		softAssertion.assertTrue(btnDeleteFile.isDisplayed(), "Delete file button");
		closeBtn.click();
	}	*/

}
