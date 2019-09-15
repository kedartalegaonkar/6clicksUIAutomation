package tests;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import Page.actionPage;
import Page.loginScreen;
import commons.ExcelReader;
import commons.Util;

public class driverScript {
	WebDriver driver;
	Logger log = Logger.getLogger("6Clicks -->");
	ExcelReader excel=new ExcelReader();		
	private loginScreen loginpage=new loginScreen(driver);
	public String userDetail;
	//private actionPage actionpg;
	public TreeMap<String, String> Testdata = new TreeMap<String, String>();
	
	public driverScript() {
		
	}	
	SoftAssert softAssertion= new SoftAssert();

	@BeforeSuite()
	public void setup() throws Exception

	{	
		FileUtils.cleanDirectory(new File("Screenshots"));
		loginpage.createExcel();
		loginpage.createResultHeading();
		
		//loginpage.createExcel();
		//loginpage=PageFactory.initElements(driver, loginScreen.class);
		//actionpg=PageFactory.initElements(driver, actionPage.class);
	}

	@Parameters({ "Team" })
	@BeforeMethod	
	public void login(@Optional("Team") String Team,Object[] testArgs) throws Exception
	{
		//userDetail=Team;
		this.driver=loginpage.init(Util.readProperty("URL"),Util.readProperty(Team));  //"Team"		
	}

	@AfterMethod
	public void logout() throws Exception
	{		
		driver.close();		
	}
	
	@Parameters({ "Team" })
	@Test(dataProvider = "data")  
	public void createMplacecategory(Map<String, String> map) throws Exception
	{			
		map.put("Scenario Name","createMplacecategory");
		loginpage.createMplacecategory(map);
		loginpage.MktTagsLink.click();
		loginpage.createMarketTag(map);		 
	}

	@Parameters({ "Team" })
	@Test(dataProvider = "data")  // tested with excel and screenshot  //priority=1,
	public void createUser(Map<String, String> map) throws Exception
	{		 
		loginpage.visibilityOfElement(loginpage.tenantLink, 10);
		map.put("Scenario Name","createUser");		
		loginpage.tenantLink.click();
		
		loginpage.createUser(map,"","");  // Default User Creation
		loginpage.verifyUser(map);

		loginpage.createUser(map,"Publish",""); // Seller User Creation
		loginpage.verifyUser(map);		

		 loginpage.createUser(map,"Publish","Service"); // Service provider user creation.
		 loginpage.verifyUser(map);		 
	}

	@Test(dataProvider = "data")  // tested with excel and screenshot -- covers both authority and provision
	public void CreateAuthorityAndProvisionFromHost(Map<String, String> map,ITestContext context)
	{		
		map.put("Scenario Name","CreateAuthorityAndProvisionFromHost");
		loginpage.visibilityOfElementAdv(loginpage.linkMasterData, 8);
		loginpage.linkMasterData.click();
		loginpage.createAuthorities(map);			
		loginpage.createProvision(map);
	}

	@Test(dataProvider = "data") // login with admin first -- tested with excel and screenshot
	public void CustomBrandingForServiceProvider_1(Map<String, String> map) throws Exception
	{	
		map.put("Scenario Name", "CustomBrandingForServiceProvider_1");	
		loginpage.visibilityOfElementAdv(loginpage.tenantLink, 10);		 
		loginpage.tenantLink.click();
		loginpage.createUser(map,"","Service");
		loginpage.verifyUser(map);
		loginpage.logoutApp(loginpage.logoutImageAdmin);		 
		loginpage.loginToApp(driver,map.get("DefaultUserLogin"),Util.readProperty("UserName"),Util.readProperty("Password"));		 
		loginpage.changePassword(map);
		loginpage.manageBranding(map);
		loginpage.uploadBrandingImages(map);		 
		loginpage.newTenantValidations(map,loginpage.leftClientsLink,"Clients","Client:");		
	}

	@Test(dataProvider = "data")  //login with Defaultuser3664  -- tested with excel and screenshot
	public void CustomBrandingForServiceProvider_2(Map<String, String> map) throws Exception
	{	
		map.put("Scenario Name", "CustomBrandingForServiceProvider_2");
		map.put("Validation", "for seller tenant");
		ArrayList<String> list=loginpage.getMarketPlaceList(map);		 
		loginpage.getLogoDetails(map);		 
		loginpage.loginToApp(driver,"Team1ForCIS",Util.readProperty("UserName"),Util.readProperty("Password"));
		map.put("Validation", "for client");
		ArrayList<String> listClient=loginpage.getMarketPlaceList(map);
		loginpage.newTenantValidations(map,loginpage.leftThirdPartyLink,"Third-Parties","Assessee:");		 
		loginpage.getLogoDetails(map);
		loginpage.compareLists(list, listClient,map);	 
	}	 


	@Test(dataProvider = "data") // tested with excel and screenshot--Login: sellertenant, create edition: admin and payment and other validation: testtenant
	public void CreateMarketplaceAssessmentTemplate(Map<String, String> map) throws Exception   
	{		

		map.put("Scenario Name", "CreateMarketplaceAssessmentTemplate");
		loginpage.createTemplate(map,softAssertion);
		loginpage.createDomain("4",map);				

		loginpage.createYesNoquestion(map);	  
		//loginpage.createCustomquestion(map);

		loginpage.changeAssessmentStatus(loginpage.approveAssessment,"Approved",map);
		loginpage.changeAssessmentStatus(loginpage.publishAssessment,"Approved",map);

		loginpage.visibilityOfElement(loginpage.docAttachSeller, 8);
		loginpage.docAttachSeller.click();
		loginpage.publishAssessmentTemplate(map);
		
		loginpage.checkReadOnlyStatus(map);		
		driver.close();
		
		this.driver=loginpage.init(Util.readProperty("URL"),Util.readProperty("AdminTeam"));				  
		loginpage.createEditions(map);
		loginpage.logoutApp(loginpage.logoutImageAdmin);

		loginpage.loginToApp(driver,Util.readProperty("Team"),Util.readProperty("UserName"),Util.readProperty("Password"));
		loginpage.navigateMktPlace(map);
		loginpage.assessmentPayment(map);
		loginpage.verifyTemplate(map);

		//loginpage.changeAssessmentStatus(loginpage.approveAssessment,"Approved",map);
		//loginpage.changeAssessmentStatusPublish(loginpage.publishAssessment,"Published",true,map);		 		 
		loginpage.visibilityofTemplate(map);		 
	}	 

	@Test(dataProvider = "data")  // tested with excel and screenshot User: testTenant
	public void CreateVersionOfAssessmentTemplate(Map<String, String> map) throws Exception   

	{
		map.put("Scenario Name", "CreateVersionOfAssessmentTemplate");
		loginpage.createTemplate(map,softAssertion);
		loginpage.createDomain("4",map);				

		loginpage.createYesNoquestion(map);	  
		loginpage.createCustomquestion(map);

		loginpage.changeAssessmentStatus(loginpage.approveAssessment,"Approved",map);
		loginpage.changeAssessmentStatusPublish(loginpage.publishAssessment,"Published",true,map);

		loginpage.verifyTemplateOnVersions(map);		 
		loginpage.createVersion(map);
		loginpage.verifyTemplateOnVersionsafter(map);			
	}

	@Test(dataProvider = "data")  // // tested with excel and screenshot pls Use: testTenant and test
	public void createAssessmentTemplateAndAssessment(Map<String, String> map) throws Exception 	
	{	
		map.put("Scenario Name","createAssessmentTemplateAndAssessment");
		loginpage.createTemplate(map,softAssertion);
		loginpage.createWeightedTemplate(map);	  
		loginpage.createDomain("3",map);

		loginpage.createCustomquestionTemplate(map);	  
		loginpage.createResponceOnlyQuestion(map);

		loginpage.changeAssessmentStatus(loginpage.approveAssessment,"Approved",map);
		loginpage.checkReadOnlyStatus(map);
		
		loginpage.changeAssessmentStatusPublish(loginpage.publishAssessment,"Published",true,map);
		loginpage.createAssessmentUsingWgtTemp(map);

		String Url=loginpage.getclipboardcontent(map);
		Reporter.log("URL for completion: "+Url);	    

		loginpage.geturl(Url);
		loginpage.visibilityOfElement(loginpage.submitConfirm, 7);

		loginpage.submitConfirm.click();
		loginpage.visibilityOfElement(loginpage.teamSelect, 10);

		loginpage.loginToApp(driver,map.get("VendorName"),Util.readProperty("UserName"),Util.readProperty("Password"));
		map.put("ValidationType","Template");
		loginpage.validateDocument(softAssertion,map);

		loginpage.geturl(Util.readProperty("URL"));	  
		loginpage.loginToApp(driver,Util.readProperty("Team"),Util.readProperty("UserName"),Util.readProperty("Password"));

		loginpage.validateCompleteStatus(map,softAssertion);
		loginpage.waitForElement(loginpage.logoutImage);	 	
	}

	@Test(dataProvider = "data")  // // tested with excel and screenshot pls Use: testTenant and test
	public void creatSubmitAssessmentAndVerifyResult(Map<String, String> map) throws Exception

	{	
		map.put("Scenario Name","creatSubmitAssessmentAndVerifyResult");
		loginpage.createAssessment(map,softAssertion);
		loginpage.createDomain("2",map);		
		loginpage.createYesNoquestion(map);			
		loginpage.createCustomquestion(map);	  
		loginpage.navigateToAuditTab();
		loginpage.verifyAuditTab(map,"before approve");
		loginpage.changeAssessmentStatus(loginpage.approveAssessment,"Approved",map);
		
		loginpage.designAssessmenttab.click();
		loginpage.visibilityOfElement(loginpage.btnEditQuestion, 3);
		loginpage.checkReadOnlyStatus(map);
		
		loginpage.navigateToAuditTab();
		loginpage.verifyAuditTab(map,"before publish");
		loginpage.changeAssessmentStatus(loginpage.publishAssessment,"Published",map);
		loginpage.verifyAuditTab(map,"after publish");
		
		loginpage.designAssessmenttab.click();
		loginpage.visibilityOfElement(loginpage.btnEditQuestion, 3);
		loginpage.navigateToAuditTab();
		
		loginpage.validateProof(map, 2,"Full proof","Full Proof:   ");  
		loginpage.validateProof(map, 1,"Partial proof","Partial Proof:   ");
		loginpage.validateProof(map, 3,"","");

		loginpage.copyLink(map);
		loginpage.logoutApp(loginpage.logoutImage);

		String Url=loginpage.getclipboardcontent(map);
		Reporter.log("URL for completion: "+Url);	    

		loginpage.geturl(Url);
		loginpage.visibilityOfElement(loginpage.submitConfirm, 7);

		loginpage.submitConfirm.click();
		loginpage.visibilityOfElement(loginpage.teamSelect, 10);

		loginpage.loginToApp(driver,map.get("VendorName"),Util.readProperty("UserName"),Util.readProperty("Password"));
		
		map.put("ValidationType","Assessment");
		loginpage.validateDocument(softAssertion,map);

		loginpage.geturl(Util.readProperty("URL"));	  
		loginpage.loginToApp(driver,Util.readProperty("Team"),Util.readProperty("UserName"),Util.readProperty("Password"));   //Util.readProperty("Team")

		loginpage.validateCompleteStatus(map,softAssertion);

		loginpage.createAction(map);
		loginpage.addAction(softAssertion,map);
		loginpage.validRemediation(softAssertion,map);

		loginpage.waitForElement(loginpage.logoutImage);

		Reporter.log("---Test case ends---");
		softAssertion.assertAll();	  
	}

	@DataProvider(name = "data")
	public Object[][] dataSupplier() throws IOException {

		File file = new File("Test Data/MasterSheet.xlsx");
		FileInputStream fis = new FileInputStream(file);

		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);
		wb.close();
		int lastRowNum = sheet.getLastRowNum() ;
		int lastCellNum = sheet.getRow(0).getLastCellNum();
		Object[][] obj = new Object[lastRowNum][1];

		for (int i = 0; i < lastRowNum; i++) {
			Map<Object, Object> datamap = new HashMap<Object, Object>();
			for (int j = 0; j < lastCellNum; j++) {
				datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i+1).getCell(j).toString());
			}
			obj[i][0] = datamap;

		}
		return  obj;
	} 

}



/*	@Test(priority=201,dataProvider = "data")  // included in authority 
public void createNewprovision(Map<String, String> map)

{
	map.put("Scenario Name","createNewprovision");
	System.out.println("create Provision");
	loginpage.visibilityOfElement(loginpage.linkMasterData, 8);
	//loginpage.linkMasterData.click();
	loginpage.createProvision(map);		
}
 */

/*@AfterTest
public void signout()
{
  driver.close();
  log.info("Browser closed");
  Reporter.log("Browser closed");	
}*/

/*@Test(priority=1,dataProvider = "data")
public void createNewTeam()
{
	loginpage.createNewTeam();
}*/
