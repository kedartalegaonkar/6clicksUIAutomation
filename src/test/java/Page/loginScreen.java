package Page;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;
import commons.Util;

public class loginScreen extends Util {

	WebDriver driver;
	public TreeMap<String, Object[]> resultMap = new TreeMap<String, Object[]>();

	// public static final Logger log=Logger.getLogger(loginScreen.class.getName());

	@FindBy(xpath = "//a[@class='LoginLinks']")
	public WebElement teamSelect;
	@FindBy(xpath = "//input[@name='TenancyName']")
	WebElement teamName;
	@FindBy(xpath = "//button[2]/span")
	WebElement saveBtn;
	@FindBy(name = "userNameOrEmailAddress")
	public WebElement userName;
	@FindBy(name = "password")
	WebElement pswd;

	@FindBy(xpath = "//button[@class='m-btn--pill btn loginBtn']")
	WebElement loginBtn;
	@FindBy(xpath = "//*[@id='forget-password']")
	WebElement forgetPsw;

	@FindBy(xpath = "//button[contains(text(),'New Assessment')]")
	public WebElement sendAssessmentBtn;
	@FindBy(id = "NewTemplate")
	WebElement newTemplate;
	@FindBy(name = "name")
	public WebElement templateName;

	@FindBy(id = "vendorlist")
	public WebElement templateType;
	@FindBy(xpath = "//span[@class='ModalSectionLabel']")
	public WebElement formElement;

	@FindBy(xpath = "//button/span[contains(text(),'Create')]")
	public WebElement createAsmtBtn;
	@FindBy(xpath = "//span[@class='ng-star-inserted']")
	WebElement currentTeam;
	@FindBy(xpath = "//div[@class='m-portlet__head-text m-portlet__head-text--separator']")
	public WebElement asseessmentName;

	@FindBy(xpath = "//img[@alt='6clicks']")
	WebElement logo;
	@FindBy(xpath = "//button[contains(text(),'Add Question')] ")
	WebElement questionbtn;

	@FindBy(xpath = "//div[@class='modal-header']")
	WebElement changeTeamHeader;

	// new assessment control
	@FindBy(id = "NewAssesment")
	public WebElement NewAssesment;
	@FindBy(xpath = "//input[@placeholder='Select or create new third-party']")
	public WebElement vendorType;
	@FindBy(xpath = "//input[@placeholder='Select engagement']")
	WebElement selEngagement;
	@FindBy(xpath = "//input[@placeholder='Select assessment type']")
	public WebElement typeAssessment;
	@FindBy(xpath = "//span[@class='m-section__sub ng-tns-c22-4 ng-star-inserted']")
	WebElement template; // change the property
	@FindBy(xpath = "//span[@class='m-section__sub ng-tns-c16-17 ng-star-inserted']")
	WebElement AssessmentDisplay;
	@FindBy(xpath = "//app-assessment-builder/div/div/div[1]/div[1]/span[2]")
	WebElement TemplateDisplay;

	@FindBy(id = "register-btn")
	WebElement createNewTeamlink;
	@FindBy(name = "tenancyName")
	WebElement newTeamName; //
	@FindBy(name = "adminEmailAddress")
	WebElement adminEmailAddress;
	@FindBy(name = "Password")
	WebElement createTeamPassword;
	@FindBy(name = "PasswordRepeat")
	WebElement createTeamPasswordRepeat;
	@FindBy(xpath = "//span[@class='ng-tns-c2-1']")
	WebElement termsandcondition;
	@FindBy(xpath = "//button[@class='btn btn-primary m-btn m-btn--pill m-btn--custom m-btn--air']")
	WebElement createteambutton;
	@FindBy(xpath = "//span[contains(text(),'Master Data')]")
	WebElement masterDatalink;
	@FindBy(xpath = "(//a/span[contains(text(),'Marketplace')])[1]")
	WebElement linkMarketplace;
	@FindBy(xpath = "(//span[contains(text(),'Add')])[2]")
	WebElement addMarketplacebtn;
	@FindBy(id = "gridname")
	WebElement gridMarketplace;
	@FindBy(id = "griddescription")
	WebElement gridMarketplacedesc;

	@FindBy(id = "tagGridname")
	WebElement tagMarketplace;
	@FindBy(id = "tagGriddescription")
	WebElement tagMarketplacedesc;

	@FindBy(xpath = "//input[@title='Click to select color']")
	WebElement selectColor;
	@FindBy(xpath = "(//span[contains(text(),'Update')])[2]")
	WebElement updateBtn;
	@FindBy(xpath = "(//span[contains(text(),'Delete')])[2]")
	WebElement deleteBtn;

	@FindBy(xpath = "//div[@class='e-tab-text' and contains(text(),'Design Assessment')]")
	public WebElement designAssessmenttab;
	@FindBy(xpath = "//div[@class='e-tab-text' and contains(text(),'Customise Assessment')]")
	WebElement customiseAssessmenttab;
	@FindBy(xpath = "//div[@class='e-tab-text' and contains(text(),'Versions')]")
	WebElement versionstab;
	@FindBy(xpath = "//div[@class='e-tab-text' and contains(text(),'Send Assessment')]")
	public WebElement sendAssessmenttab;
	@FindBy(xpath = "//div[@class='e-tab-text' and contains(text(),'Audit Trail')]")
	WebElement auditTraitab;
	@FindBy(xpath = "//span[@class='status']")
	WebElement asmtStatus;
	@FindBy(xpath = "//a[@class='m-link--state m-link--primary ' and contains(text(),'Preview')]")
	WebElement previewLink;
	@FindBy(xpath = "//a[@class='m-link--state m-link--primary ' and contains(text(),'Settings')]")
	WebElement settingsLink;
	@FindBy(xpath = "//a[contains(text(),'Save As')]")
	WebElement saveAsLink;
	@FindBy(xpath = "//button[@title='Default']")
	WebElement selectDomain;
	@FindBy(xpath = "//button[contains(text(),'Add Question')]")
	public WebElement btnAddQuestion;
	@FindBy(xpath = "//a[contains(text(),'Add Domain')]")
	WebElement btnAddDomain;

	@FindBy(xpath = "//perfect-scrollbar/div/div[1]/div[2]/div[1]/input")
	WebElement addDomaineditbox;
	//// input[@class='m-nav__link-text ng-pristine ng-valid ng-touched']
	@FindBy(xpath = "//perfect-scrollbar/div/div[1]/div[2]/div[3]/a")
	WebElement addDomainsavebtn; // *[@id=\"cdk-drop-list-1\"]/div[2]/div[3]/a
	@FindBy(xpath = "//span[contains(text(),'Assessment Domains')]")
	WebElement domainHeading;

	// ------ add questions
	@FindBy(name = "name")
	WebElement questiontitle; //
	@FindBy(id = "defaultRTE2_rte-edit-view")
	WebElement questionDesc;
	@FindBy(xpath = "//*[@id='yesnoradio0']/label")
	WebElement yesRadiobtn;
	@FindBy(xpath = "//span[@class='e-switch-handle']")
	public WebElement docAttach;
	@FindBy(xpath = "(//span[@class='e-switch-handle'])[2]")
	public WebElement docAttachSeller;
	@FindBy(xpath = "//button[contains(text(),'Create Question')]")
	WebElement btncreatequestion;
	@FindBy(xpath = "(//span[@class='e-input-group e-control-wrapper e-ddl e-lib e-keyboard'])[1]")
	WebElement questionType;
	@FindBy(xpath = "//input[@value='Radio (Custom)']")
	WebElement questionOption;
	@FindBy(xpath = "//button[@class='btn btn-sm btn-outline-secondary m-btn--pill']")
	WebElement addquestionpref;

	@FindBy(xpath = "(//input[@placeholder='Answer choice'])[2]")
	WebElement customquestionedit1; // input[@placeholder='Answer choice']
	@FindBy(xpath = "(//input[@placeholder='Answer choice'])[1]")
	WebElement customquestionedit2;
	@FindBy(xpath = "(//input[@name='customradio'])[1]")
	WebElement customradiobtn;
	@FindBy(xpath = "//div[@class='modal-title ng-star-inserted']")
	WebElement questionPageHeader;
	@FindBy(xpath = "//div[@class='modal-assessment-cards']/div/label")
	public List<WebElement> chooseTemplate;

	// ------- approve and publish

	@FindBy(xpath = "//button/span[2]")
	WebElement changestatusdropdown; // button/span[2] //span[@class='ng-tns-c19-3 ng-star-inserted']
	@FindBy(xpath = "(//div[@class='m-nav__link'])[1]")
	public WebElement approveAssessment;
	@FindBy(xpath = "(//div[@class='m-nav__link'])[2]")
	public WebElement publishAssessment;
	@FindBy(xpath = "//button[contains(text(),'Publish')]")
	public WebElement publishBtn;
	@FindBy(xpath = "//div[@class='timelineTitle']")
	WebElement timelineTitle;
	@FindBy(xpath = "//button[@class='swal2-confirm swal2-styled']")
	WebElement approveAssessmentconfirmation; // asmtStatus
	@FindBy(xpath = "(//span[@class='m-topbar__userpic'])[2]")
	public WebElement logoutImage; // chnage from 3 to 2
	@FindBy(xpath = "(//span[@class='m-topbar__userpic'])[3]")
	public WebElement logoutImageMod;
	@FindBy(xpath = "(//span[@class='m-topbar__userpic'])[1]")
	public WebElement logoutImageAdmin;

	@FindBy(xpath = "//button[contains(text(),'Logout')]")
	WebElement btnLogout;
	@FindBy(xpath = "//span[contains(text(),'Assessments')]")
	public WebElement leftAssessmentLink;
	@FindBy(xpath = "//*[@id='grid_125033839_0_content_table']/tbody/tr")
	List<WebElement> tableRows;

	@FindBy(xpath = "//button[@class=' btn btn-outline-secondary m-btn--pill btn-sm dropdown-toggle m-dropdown__toggle']")
	WebElement selectDomainassessment;
	@FindBy(xpath = "//span[@class='m-nav__link-text' and contains(text(),'New Domain')]")
	WebElement selectAllDomain; //// span[@class='m-nav__link-text' and contains(text(),'All')]

	@FindBy(xpath = "(//div/ejs-dropdownlist)[1]")
	public WebElement questionDropdown; // (//span[@class='e-input-group e-control-wrapper e-ddl e-lib e-keyboard'])[1]
	@FindBy(xpath = "(//div/ejs-dropdownlist)[2]")
	WebElement questionDropdown1;
	@FindBy(xpath = "(//button[@title='Browse'])[1]")
	public WebElement btnbrowseButton;
	@FindBy(xpath = "//button[contains(text(),'Submit')]")
	WebElement btnsubmit;
	@FindBy(xpath = "//button[@title='Upload']")
	public WebElement btnuploadDoc;
	@FindBy(xpath = "(//div[@class='e-content e-lib e-keyboard'])[1]")
	WebElement explanationEditbox;
	@FindBy(xpath = "(//div[@class='e-content e-lib e-keyboard'])[2]")
	WebElement explanationEditbox1;

	@FindBy(xpath = "(//div[@class='borderBottom m--padding-top-10 '])[1]")
	WebElement scrollBar;
	@FindBy(xpath = "(//div[@class='borderBottom m--padding-top-10 '])[2]")
	WebElement scrollBar1;
	@FindBy(xpath = "//button[contains(text(),'Yes')]")
	public WebElement submitConfirm;

	@FindBy(xpath = "//tbody/tr/td[8]/div/span")
	//*[@id="grid_1760974781_0_content_table"]/tbody/tr[1]/td[8]/div
	WebElement assessmentStatus;

	@FindBy(xpath = "(//table/tbody/tr)[2]/td[2]/a")
	WebElement assessmentLink;

	@FindBy(xpath = "//*[@id=\"appAssessmentCollectResponsesId\"]//div[2]/div[2]/button[1]")
	//					*[@id="appAssessmentCollectResponsesId"]/div/app-assessment-collectors/div/div/div/div[1]/div/div[2]/div[2]/button[1]
	WebElement sendAssessmentstatus;
	@FindBy(xpath = "//button[contains(text(),'Copy')]")
	public WebElement btnCopy;
	@FindBy(id = "searchBar")
	WebElement searchAssessment;

	@FindBy(xpath = "//*[@id='container']/ol[1]/li/article/div/div[3]/div")
	WebElement publishDetails;
	@FindBy(xpath = "//*[@id='container']/ol[2]/li/article/div/div[3]/div")
	WebElement ApproveDetails;
	@FindBy(xpath = "//*[@id='container']/ol[3]/li/article/div/div[3]/div")
	WebElement DraftDetails;

	@FindBy(xpath = "//a[@class='btn btn-outline-primary m-btn--pill btn-sm']")
	WebElement btnViewResult;
	@FindBy(xpath = "//button[@title='Attach and View Documents']")
	WebElement paperClip;
	@FindBy(xpath = "//*[@id='target']")
	WebElement assessmenttype;

	// @FindBy(xpath="//button[@title='View documents']") WebElement paperClip1;
	// View documents
	@FindBy(xpath = "//button[@title='View documents']")
	WebElement paperClipDoc;

	@FindBy(xpath = "//div[@class='freeze-ui']")
	WebElement freezeUI;

	/// ====================== add action and assign uset

	@FindBy(xpath = "//a[@class='LoginLinks']")
	public WebElement teamSelect1;
	@FindBy(xpath = "(//button[@title='Assign question to users'])[1]")
	public WebElement assignQuestion;
	@FindBy(xpath = "(//button[@title='Open action and remediation manager'])[1]")
	public WebElement addAction;
	@FindBy(xpath = "//div[@class='e-multi-select-wrapper']")
	public WebElement selectUser;
	@FindBy(xpath = "(//button[@class='close'])[4]")
	public WebElement closebutton;

	@FindBy(xpath = "//input[@name='task']")
	public WebElement itemName;
	@FindBy(xpath = "//span[@class='e-searcher e-multiselect-box']")
	public WebElement userName1;
	@FindBy(xpath = "//button[@title='Browse...']")
	public WebElement btnBrowse;
	@FindBy(xpath = "//span[@title='Delete file']")
	public WebElement btnDeleteFile;
	@FindBy(xpath = "(//button[@class='close m--padding-left-30'])[3]")
	public WebElement closeBtn;
	@FindBy(xpath = "//div[@class='e-tab-text' and contains(text(),'Remediation')]")
	public WebElement remediationTab;
	@FindBy(xpath = "//table/tbody/tr/td[1]/a")
	public WebElement remAction;
	@FindBy(xpath = "//table/tbody/tr/td[2][@class='e-rowcell']")
	public WebElement remQuestion;
	@FindBy(xpath = "//table/tbody/tr/td[3][@class='e-rowcell e-templatecell']")
	public WebElement remDomain;
	@FindBy(xpath = "//table/tbody/tr/td[4][@class='e-rowcell']")
	public WebElement remOwner;

	@FindBy(xpath = "//*[@id='target']/span")
	public WebElement expAnswer1;
	@FindBy(xpath = "//*[@id='cdk-drop-list-3']/div[1]/mat-card/div/div[1]/div[2]/div[2]/span")
	public WebElement prefAnswer1;
	@FindBy(xpath = "//*[@id='cdk-drop-list-3']/div[2]/mat-card/div/div[1]/div[2]/div[1]/span")
	public WebElement expAnswer2;
	@FindBy(xpath = "//*[@id='cdk-drop-list-3']/div[2]/mat-card/div/div[1]/div[2]/div[2]/span")
	public WebElement prefAnswer2;

	@FindBy(xpath = "(//mat-card/div/div[1]/div/div[1]/div/div[2]/div)[1]")
	public WebElement verifyAddedQuestion;
	@FindBy(xpath = "(//mat-card/div/div[1]/div/div[1]/div/div[2]/div)[2]")
	public WebElement verifyAddedQuestion1;

	@FindBy(xpath = "//span[@class='e-chipcontent']")
	public WebElement selectedUser;

	// ----------- Add users --------
	@FindBy(xpath = "//*[@id='menuItem']/a/span[contains(text(),'Tenants')]")
	public WebElement tenantLink;
	@FindBy(xpath = "//div[@class='m-portlet__head-tools']/a")
	public WebElement createTenant;
	@FindBy(xpath = "//input[@name='teamName']")
	public WebElement EditTeamName;
	@FindBy(id = "AdminEmailAddress")
	public WebElement AdminEmailAddress;
	@FindBy(xpath = "//form/div[2]/div[7]/label[1]/span")
	public WebElement checkBoxChangePassword;
	@FindBy(xpath = "//form/div[2]/div[7]/label[2]/span")
	public WebElement checkBoxActivationEmail;
	@FindBy(xpath = "//form/div[2]/div[7]/label[3]/span")
	public WebElement checkBoxActive;
	@FindBy(xpath = "//form/div[2]/div[7]/label[4]/span")
	public WebElement checkBoxPublish;
	@FindBy(xpath = "//form/div[2]/div[7]/label[5]/span")
	public WebElement checkBoxServiceProvider;
	@FindBy(xpath = "//form/div[2]/div[4]/label/span")
	public WebElement checkBoxSetPassword;
	@FindBy(id = "adminPassword")
	public WebElement editAdminPassword;
	@FindBy(id = "AdminPasswordRepeat")
	public WebElement editAdminPasswordRepeat;

	@FindBy(xpath = "//form/div[3]/button[2]/span")
	public WebElement createUserSaveBtn;
	@FindBy(id = "tenantGrid_searchbar")
	public WebElement tenantSearch;
	@FindBy(id = "tenantGrid_searchbutton")
	public WebElement tenantSearchBtn;
	@FindBy(xpath = "(//table/tbody/tr)[2]")
	public WebElement userRow;
	@FindBy(xpath = "//button[@id='Edit']")
	public WebElement editUserbtn;
	@FindBy(xpath = "(//label[@class='m-checkbox']/input)[1]")
	public WebElement editUserFormActiveChkBox; // form/div[2]/div[2]/label/span
	@FindBy(xpath = "(//label[@class='m-checkbox']/input)[2]")
	public WebElement editUserFormPublisherChkBox; // form/div[2]/div[3]/label/span
	@FindBy(xpath = "(//label[@class='m-checkbox']/input)[3]")
	public WebElement editUserFormServiceChkBox; // form/div[2]/div[4]/label/span
	@FindBy(xpath = "//button[contains(text(),'Cancel')]")
	public WebElement editUserFormcancelBtn;
	// =========
	@FindBy(xpath = "//ejs-dropdownlist[@placeholder='Select Question Type']")
	public WebElement drpDownQtype;
	@FindBy(xpath = "(//ejs-dropdownlist[@placeholder='Select results type'])[1]")
	public WebElement drpDownResultWithinDomain;
	@FindBy(xpath = "(//ejs-dropdownlist[@placeholder='Select results type'])[2]")
	public WebElement drpDownResultAcrossDomain;

	@FindBy(xpath = "//div[@class='e-upload e-lib e-keyboard']")
	public WebElement settingsScrollBar;
	@FindBy(xpath = "//form/div[8]/div/span/button")
	public WebElement settingsSaveBtn;

	@FindBy(id = "multiSelectTags")
	public WebElement selectTags;
	@FindBy(id = "weighting")
	public WebElement editBoxWeighting;

	// ========== Authorities and provisions

	@FindBy(xpath = "//a/span[contains(text(),'Master Data')]")
	public WebElement linkMasterData;
	@FindBy(xpath = "//a/span[contains(text(),'Authorities')]")
	public WebElement linkAuthorities; // form/div[2]/div[2]/label/span
	@FindBy(xpath = "//a/span[contains(text(),'Provisions')]")
	public WebElement linkProvisions; // form/div[2]/div[3]/label/span
	@FindBy(xpath = "//span[@class='e-tbar-btn-text' and contains(text(),'Add')]")
	public WebElement linkAddAuthorities; // form/div[2]/div[4]/label/span
	@FindBy(xpath = "(//span[@class='e-tbar-btn-text' and contains(text(),'Add')])[2]")
	public WebElement AddCustomField;
	@FindBy(name = "authority_name")
	public WebElement editAuthorityName;
	@FindBy(name = "authorityBodyName")
	public WebElement editAuthorityBodyName;
	@FindBy(name = "sectorName")
	public WebElement sectorNameAuthority;

	@FindBy(name = "authorityJurisdictionName")
	public WebElement editJurisName;
	@FindBy(name = "authority_url")
	public WebElement editAuthorityURL;
	@FindBy(xpath = "//span[@class='e-tbar-btn-text' and contains(text(),'Update')]")
	public WebElement linkUpdateAuthorities;
	@FindBy(xpath = "//span[@class='e-tbar-btn-text' and contains(text(),'Edit')]")
	public WebElement linkEditAuthorities;
	@FindBy(xpath = "(//span[@class='e-tbar-btn-text' and contains(text(),'Update')])[2]")
	public WebElement UpdateAuthorities;
	@FindBy(xpath = "//span[@class='e-tbar-btn-text' and contains(text(),'Cancel')]")
	public WebElement linkCancelAuthorities;

	@FindBy(xpath = "//app-control/div/div/div[1]/div[3]/div[1]/a")
	public WebElement btnAddProvisions;
	@FindBy(id = "reference")
	public WebElement editProvisionId;
	@FindBy(id = "name")
	public WebElement editText;
	@FindBy(xpath = "//table[1]/tbody/tr/td[2]")
	public List<WebElement> AuthtableRows;
	@FindBy(xpath = "//input[@name='name']")
	public WebElement editField;

	@FindBy(xpath = "//button[contains(text(),'Templates')]")
	public WebElement btnTemplate;
	@FindBy(xpath = "(//table)[2]/tbody/tr[1]/td[1]")
	public WebElement verifyTemplateName; // valid for both
	@FindBy(xpath = "(//table)[2]/tbody/tr[1]/td[2]/a")
	public WebElement verifyTemplateNamelink;

	@FindBy(xpath = "(//table)[2]/tbody/tr/td[8]")
	public WebElement verifyTemplateStatus;
	@FindBy(xpath = "(//table)[2]/tbody/tr/td[10]")
	public WebElement verifyTemplateType;

	@FindBy(xpath = "(//table)[2]/tbody/tr[1]/td[2]")
	public WebElement verifyTemplateVersion;
	@FindBy(xpath = "(//table)[2]/tbody/tr[1]/td[3]")
	public WebElement verifyTempStatus;
	@FindBy(xpath = "(//table)[2]/tbody/tr[1]/td[5]/a")
	public WebElement verifyTemplateviewLink;
	@FindBy(xpath = "(//table)[2]/tbody/tr[2]/td[5]/a")
	public WebElement verifyTemplateviewLinkAfter;
	@FindBy(xpath = "(//table)[2]/tbody/tr[2]/td[1]")
	public WebElement verifyTemplateNameAfter;
	@FindBy(xpath = "(//table)[2]/tbody/tr[2]/td[2]")
	public WebElement verifyTemplateVersionAfter;
	@FindBy(xpath = "(//table)[2]/tbody/tr[2]/td[3]")
	public WebElement verifyTempStatusAfter;

	@FindBy(xpath = "(//table)[2]/tbody/tr/td[1]/a")
	public WebElement verifyTemplateNameLink;
	@FindBy(xpath = "//button[contains(text(),'Add Provision')]")
	public WebElement btnCreateProvision;

	@FindBy(xpath = "//input[@placeholder='Headline']")
	WebElement editHeadLine;
	@FindBy(xpath = "//input[@placeholder='Price']")
	WebElement editPrice;
	@FindBy(id = "descriptionEditor_rte-edit-view")
	WebElement editShortDescription;

	@FindBy(xpath = "//span[contains(text(),'Marketplace')]")
	WebElement linkMarketPlace;
	@FindBy(xpath = "//div[@class='MarketplaceItemHeading']")
	List<WebElement> listMarketPlace;
	@FindBy(xpath = "//button[contains(text(),'Detail')]")
	WebElement btnDetail;

	@FindBy(xpath = "//a/span[contains(text(),'Editions')]")
	WebElement linkEditions;
	@FindBy(xpath = "//ng-component/div/div/div[1]/div[3]/a")
	WebElement btnCreateEditions;
	@FindBy(id = "EditionDisplayName")
	WebElement editionName;
	@FindBy(xpath = "//label[@for='EditEdition_IsPaid']")
	WebElement radioBtnPaid; // EditEdition_IsPaid
	@FindBy(id = "MonthlyPrice")
	WebElement editMonthlyPrice;
	@FindBy(id = "AnnualPrice")
	WebElement editAnnualPrice;
	@FindBy(xpath = "//button[@class='btn btn-primary']")
	WebElement BtnSaveEditions;
	@FindBy(id = "marketplaceitem")
	WebElement marketplaceitem;
	@FindBy(id = "serviceprovider")
	WebElement serviceprovider;

	@FindBy(xpath = "//span[@title='Delete file']")
	WebElement deleteFileLogo;

	// @FindBy(xpath="//span[contains(text(),'Marketplace')]") WebElement
	// linkMarketPlace;
	// label[@class='m-radio']/span
	@FindBy(xpath = "//button[contains(text(),'Buy now')]")
	WebElement btnBuyNow;
	@FindBy(xpath = "//label[@class='m-radio']/span")
	WebElement radioBtnPrice;
	@FindBy(xpath = "//button[contains(text(),'Checkout')]")
	WebElement btnCheckout;
	@FindBy(xpath = "//button[contains(text(),'Purchase')]")
	WebElement btnPurchase;
	@FindBy(name = "cardnumber")
	WebElement cardnumber;
	@FindBy(name = "exp-date")
	WebElement expdate;
	@FindBy(name = "cvc")
	WebElement cvcDetail;
	@FindBy(id = "stripeCheckout")
	WebElement stripeCheckout;
	@FindBy(xpath = "//div[@class='m-portlet__head-title']")
	WebElement titleSubscription;
	@FindBy(xpath = "//table[1]/tbody/tr/td[1]")
	List<WebElement> subscriptionInfo;
	@FindBy(xpath = "//div[contains(text(),'Payment history')]")
	WebElement paymentHistory;
	@FindBy(xpath = "//table[1]/tbody/tr/td[3]")
	List<WebElement> paymentHistoryInfo;

	// ---------- create tenant
	@FindBy(xpath = "//button/span[contains(text(),'Next')]")
	public WebElement nextBtn;
	@FindBy(xpath = "//button/span[contains(text(),'Finish')]")
	public WebElement FinishBtn;

	@FindBy(xpath = "//span[contains(text(),'Administration')]")
	public WebElement leftAdministrationLink;
	@FindBy(xpath = "(//span[contains(text(),'Client')])[1]")
	public WebElement leftClientsLink; // span[contains(text(),'Clients')]
	@FindBy(xpath = "//span[contains(text(),'Third-Parties')]")
	public WebElement leftThirdPartyLink;

	@FindBy(xpath = "//span[contains(text(),'Settings')]")
	public WebElement leftSettingsLink;
	@FindBy(xpath = "//div[contains(text(),'Customisation')]")
	public WebElement customizationTab;
	@FindBy(xpath = "//a[contains(text(),'Save all')]")
	public WebElement btnSaveAll;
	@FindBy(xpath = "//ng-component/div/div/div[1]/div[1]/div/div/span")
	public WebElement dashboardMessage;
	@FindBy(xpath = "//app-choicemodal/form/div[2]/div[4]/div[1]/span")
	public WebElement assessmentOption;
	@FindBy(name = "Password")
	public WebElement changePassword;
	@FindBy(name = "PasswordRepeat")
	public WebElement changePasswordRepeat;
	@FindBy(xpath = "//button[contains(text(),'Submit')]")
	public WebElement BtnSubmit;

	@FindBy(xpath = "//default-brand/div/a/img")
	public WebElement homePageLogo;
	@FindBy(xpath = "//ng-component/div/div[1]/img")
	public WebElement loginPageLogo;
	@FindBy(xpath = "//h2[@id='swal2-title']")
	public WebElement serverError;
	@FindBy(xpath = "//button[contains(text(),'Ok')]")
	public WebElement btnServerErrorOk;
	@FindBy(xpath = "//*[@id='mat-dialog-3']/app-question-dialog/form/div[1]/button")
	public WebElement btnclose;

	@FindBy(xpath = "(//button[@class='icon-only-assessment'])[1]")
	public WebElement btnEditQuestion;

	@FindBy(xpath = "//button[contains(text(),'Delete')]")
	public List<WebElement> btnDeleteQuest;
	@FindBy(xpath = "//app-question-dialog//button[contains(text(),'Edit')]")
	public List<WebElement> btnEditQuest;
	@FindBy(xpath = "//single-app-question-dialog/form/div[1]/button")
	public WebElement btnFormclose; // app-question-dialog//button[@class='close']
	@FindBy(xpath = "(//*[@class='e-rowcell e-templatecell']/span)[2]")
	public WebElement btnDeleteFileBtn;

	@FindBy(xpath = "//*[@class='mat-icon-button ng-star-inserted']/span")
	public WebElement btnDeleteFileBtnMain;

	@FindBy(xpath = "(//ejs-dropdownlist/span)[1]")
	public List<WebElement> questionDropdown2;
	@FindBy(xpath = "(//ejs-dropdownlist/span)[2]")
	List<WebElement> questionDropdown3;

	@FindBy(id = "answer_hidden")
	public WebElement selectAnswer;
	@FindBy(id = "radiocustomanswer_hidden")
	public WebElement selectRadioAnswer;

	@FindBy(xpath = "//div[1]/mat-card/div/div[1]/div[1]/div[1]/div[2]/div[1]")
	public WebElement Qusetion1Heading;
	@FindBy(xpath = "//div[1]/mat-card/div/div[1]/div[1]/div[1]/div[2]/div[2]")
	public WebElement Qusetion1Desc;
	@FindBy(xpath = "//div[2]/mat-card/div/div[1]/div[1]/div[1]/div[2]/div[1]")
	public WebElement Qusetion2Heading;
	@FindBy(xpath = "//div[2]/mat-card/div/div[1]/div[1]/div[1]/div[2]/div[2]")
	public WebElement Qusetion2Desc;

	@FindBy(xpath = "//div[1]/mat-card/div/div[2]/div[3]")
	public WebElement Qusetion1Domain;
	@FindBy(xpath = "//div[2]/mat-card/div/div[2]/div[3]")
	public WebElement Qusetion2Domain;
	@FindBy(xpath = "//perfect-scrollbar/div/div[1]/div/div/div[2]/div/div[1]/div[2]/div")
	public WebElement priceMktPlace;
	@FindBy(xpath = "//app-product-detail/div/div/div[2]/perfect-scrollbar/div/div[1]/div/div/div[1]/div/div[2]")
	public WebElement sellerTenantDetail;
	@FindBy(xpath = "//input[@name='FixedPrice']")
	public WebElement fixedPrice;
	@FindBy(xpath = "(//table)[4]/tbody/tr")
	List<WebElement> mktTable;
	@FindBy(xpath = "(//table)[4]/tbody/tr/td[2]")
	List<WebElement> mktTableRows;
	@FindBy(xpath = "//div[contains(text(),'Marketplace Tags')]")
	public WebElement MktTagsLink;
	@FindBy(xpath = "//div[1]/mat-card/div/div[1]/div[2]/div[1]/span")
	public WebElement tempWeightedAnswar1;
	@FindBy(xpath = "//div[1]/mat-card/div/div[1]/div[2]/div[2]/span")
	public WebElement tempWeightedAnswar2;
	@FindBy(xpath = "//div[2]/mat-card/div/div[1]/div[2]/div[1]/span")
	public WebElement tempWeightedAnswar3;
	@FindBy(xpath = "//div[2]/mat-card/div/div[1]/div[2]/div[2]/span")
	public WebElement tempWeightedAnswar4;
	@FindBy(xpath = "//input[@class='e-input']")
	public WebElement EditAnswer;
	@FindBy(xpath = "//div[@class='e-content e-lib e-keyboard']") ////div[@class='e-content e-lib e-keyboard']		//(//input[@class='e-input'])[2]
	public WebElement EditAnswer1;
	@FindBy(xpath = "(//ejs-richtexteditor)[1]")
	public WebElement richTextEditor;
	@FindBy(xpath = "(//ejs-richtexteditor)[2]")
	public WebElement richTextEditor1;

	public boolean isElementPresent(List<WebElement> ele) {
		try {
			driver.manage().timeouts().implicitlyWait(05, TimeUnit.SECONDS);
			if (ele.size() > 0) {
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				return true;
			} else {
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				return false;
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			return false;
		}
	}

	public void checkReadOnlyStatusCompletedPage(Map<String, String> map) {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", btnbrowseButton);
			resultMap.put("5",
					new Object[] { map.get("Scenario Name"),
							"Validation if upload button is disable after document submission",
							(compareBooleanResult(btnbrowseButton.isEnabled(), false)[0]),
							(compareBooleanResult(btnbrowseButton.isEnabled(), false)[1]) });
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", paperClip);
			paperClip.click();
			waitForElement(btnDeleteFileBtn);
			resultMap.put("7",
					new Object[] { map.get("Scenario Name"), "Validation if uploaded file can not delete after submit",
							(compareStringResult(btnDeleteFileBtn, "-")[0]),
							(compareStringResult(btnDeleteFileBtn, "-")[1]) });
			closebutton.click();
			visibilityOfElement(paperClip, 5);
			writeToExl(resultMap);
			resultMap.clear();
		} catch (Exception e) {
			resultMap.put("3", new Object[] { map.get("Scenario Name"),
					"Error for checking read only status on complete page.", "Failed", "Check for screenshot" });
			// checkForServerError(map);
			writeToExl(resultMap);
			resultMap.clear();
			System.out.println("Error: " + e);
			writeToExl(resultMap);
			resultMap.clear();
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name")
					+ "_Error_on checking read only status on complete page.jpg");
		}
	}

	public void checkReadOnlyStatus(Map<String, String> map) {
		try {
			resultMap.put("3",
					new Object[] { map.get("Scenario Name"),
							"Validation of 'Add Question' button disable after approval.",
							(compareBooleanResult(btnAddQuestion.isEnabled(), false)[0]),
							(compareBooleanResult(btnAddQuestion.isEnabled(), false)[1]) });
			btnEditQuestion.click();
			
			 visibilityOfElement(btnFormclose, 7);
			 resultMap.put("4", new Object[] {map.get("Scenario Name"), "Validation of 'Delete' button on edit question page.",
			 (compareBooleanResult(isElementPresent(btnDeleteQuest),false)[0]),(compareBooleanResult(isElementPresent(btnDeleteQuest),false)[1])});
			 resultMap.put("5", new Object[] {map.get("Scenario Name"), "Validation of 'Edit' button on edit question page.",
			 (compareBooleanResult(isElementPresent(btnEditQuest),false)[0]),(compareBooleanResult(isElementPresent(btnEditQuest),false)[1])});
			
			takeSnapShot(driver,
					"Screenshots/" + map.get("Scenario Name") + "_checking read only status on complete page.jpg");
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", btnFormclose);
			writeToExl(resultMap);
			resultMap.clear();
		} catch (Exception e) {
			resultMap.put("3", new Object[] { map.get("Scenario Name"), "Error for checking read only status.",
					"Failed", "Check for screenshot" });
			checkForServerError(map);
			System.out.println("Error: " + e);
			writeToExl(resultMap);
			resultMap.clear();
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_Error_on checking read only status.jpg");
		}
	}

	public void checkForServerError(Map<String, String> map) {
		try {
			System.out.println("Check for server error");
			visibilityOfElement(serverError, 7);
			System.out.println("Server error: " + serverError.getText());
			if (serverError.getText().contains("internal error")) {
				resultMap.put("4", new Object[] { map.get("Scenario Name"), "Application Error occur during operation",
						"Server Error", "" });
				takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_ServerError.jpg");
				btnServerErrorOk.click();
				/*
				 * waitForElement(btnclose); if(btnclose.isDisplayed()) btnclose.click();
				 */
				resultMap.put("5", new Object[] { map.get("Scenario Name"),
						"Skipping this test case due to application error", "Failed", "" });
				writeToExl(resultMap);
				resultMap.clear();
				throw new AssertionError("Skipping this test case due to server error");
			}
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}

	public void uploadBrandingImages(Map<String, String> map) {
		try {
			for (int i = 2; i <= 4; i++) {
				String btnBrowse = "(//button[@title='Browse...'])[" + i + "]";
				String btnupload = "(//button[@title='Upload'])[" + (i - 1) + "]";
				String btnDeleteFile = "(//span[@title='Delete file'])[" + (i - 1) + "]";
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
						driver.findElement(By.xpath(btnBrowse)));
				driver.findElement(By.xpath(btnBrowse)).click();

				String path = System.getProperty("user.dir") + "/Test Data/" + map.get("uploadFile");
				uploadFileWithRobot(path);

				// uploadFileWithRobot("E:/New Selenium Class/UIAutomation6Clicks/Test
				// Data/CIS.png");
				waitForElement(driver.findElement(By.xpath(btnupload)));
				driver.findElement(By.xpath(btnupload)).click();
				visibilityOfElement(driver.findElement(By.xpath(btnDeleteFile)), 5);
			}
			resultMap.put("4", new Object[] { map.get("Scenario Name"),
					"Uploaded the images for LoginScreen, Logo and Favicon", ("Information"), ("") });
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_Image upload on branding page.jpg");
			writeToExl(resultMap);
			resultMap.clear();
		} catch (Exception e) {
			resultMap.put("3", new Object[] { map.get("Scenario Name"), "Error for image upload on branding page.",
					"Failed", "Check for screenshot" });
			checkForServerError(map);
			System.out.println("Error: " + e);
			writeToExl(resultMap);
			resultMap.clear();
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_Error_on image upload.jpg");
		}
	}

	public void newTenantValidations(Map<String, String> map, WebElement eleLink, String value1, String Value2) {
		try {
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -document.body.scrollHeight);");
			if (eleLink.getText().contains("Client"))
				btnSaveAll.click();

			visibilityOfElement(sendAssessmentBtn, 8);
			resultMap.put("3", new Object[] { map.get("Scenario Name"),
					"Validation of '" + eleLink.getText() + "' link at left navigation pane.",
					(compareBooleanResultDisplay(eleLink, true)[0]), (compareBooleanResultDisplay(eleLink, true)[1]) });
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_link at left navigation pane.jpg");
			eleLink.click();
			visibilityOfElement(dashboardMessage, 5);
			resultMap.put("4",
					new Object[] { map.get("Scenario Name"), "Validation of 'Your " + value1 + "' on page.",
							(compareStringResult(dashboardMessage, value1)[0]),
							(compareStringResult(dashboardMessage, value1)[1]) });
			takeSnapShot(driver,
					"Screenshots/" + map.get("Scenario Name") + "_Validation of 'Your " + value1 + "' on page.jpg");
			visibilityOfElement(sendAssessmentBtn, 5);
			sendAssessmentBtn.click();
			waitForElement(assessmentOption);
			resultMap.put("5",
					new Object[] { map.get("Scenario Name"),
							"Validation of '" + Value2 + "' on create assessment page.",
							(compareStringResult(assessmentOption, Value2)[0]),
							(compareStringResult(assessmentOption, Value2)[1]) });
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_Validation of '" + Value2
					+ "' on create assessment page.jpg");
			writeToExl(resultMap);
			resultMap.clear();
			closebutton.click();
			waitForElement(sendAssessmentBtn);
		} catch (Exception e) {
			resultMap.put("3", new Object[] { map.get("Scenario Name"), "_Error in Validation of links", "Failed",
					"Check the screenshot" });
			System.out.println("Error: " + e);
			writeToExl(resultMap);
			resultMap.clear();
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_Error in Validation of links.jpg");
		}
	}

	public void changePassword(Map<String, String> map) {
		try {
			waitForElement(changePassword);
			changePassword.sendKeys("Welcome1");
			changePasswordRepeat.sendKeys("Welcome1");
			BtnSubmit.click();
			resultMap.put("4", new Object[] { map.get("Scenario Name"),
					"Password change for user '" + map.get("DefaultUserLogin") + "'.", ("Information"), ("") });
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_Password change.jpg");
			writeToExl(resultMap);
			resultMap.clear();
		} catch (Exception e) {
			resultMap.put("3", new Object[] { map.get("Scenario Name"), "Error in password change", "Failed",
					"Check the screenshot" });
			System.out.println("Error: " + e);
			writeToExl(resultMap);
			resultMap.clear();
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_Error_Password change.jpg");
		}
	}

	public void manageBranding(Map<String, String> map) {
		try {
			visibilityOfElement(nextBtn, 7);
			nextBtn.click();
			visibilityOfElement(FinishBtn, 5);
			FinishBtn.click();
			visibilityOfElement(leftAdministrationLink, 10);
			leftAdministrationLink.click();
			waitForElement(leftSettingsLink);
			leftSettingsLink.click();
			waitForElement(customizationTab);
			customizationTab.click();
			waitForElement(customizationTab);
			// waitForElement(btnBrowse);
			resultMap.put("4", new Object[] { map.get("Scenario Name"),
					"Navigated to upload images page for manage branding.", ("Information"), ("") });
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_Manage branding page.jpg");
			writeToExl(resultMap);
			resultMap.clear();
		} catch (Exception e) {
			resultMap.put("3", new Object[] { map.get("Scenario Name"), "Error on manage branding page.", "Failed",
					"Check the screenshot" });
			System.out.println("Error: " + e);
			writeToExl(resultMap);
			resultMap.clear();
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_Error_Manage branding page.jpg");
		}
	}

	public void publishAssessmentTemplate(Map<String, String> map) {
		try {
			visibilityOfElement(editHeadLine, 8);
			editHeadLine.sendKeys("HeadLine1");
			editPrice.sendKeys(map.get("sellerPrice"));
			waitForElement(btnBrowse);
			uploadFile(map, btnBrowse);
			waitForElement(btnBrowse);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", publishBtn);
			
			//visibilityOfElement(publishBtn, 7);
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_publish Assessment.jpg");
			//publishBtn.click();
			visibilityOfElement(designAssessmenttab, 8);
			resultMap.put("25",
					new Object[] { map.get("Scenario Name"), "check for status of assessment as 'Published'",
							(compareStringResult(asmtStatus, "Published")[0]),
							(compareStringResult(asmtStatus, "Published")[1]) });
			writeToExl(resultMap);
			resultMap.clear();
		} catch (Exception e) {
			resultMap.put("3", new Object[] { map.get("Scenario Name"), "Error on Publish assessment template",
					"Failed", "Check the screenshot" });
			System.out.println("Error: " + e);
			writeToExl(resultMap);
			resultMap.clear();
			takeSnapShot(driver, "Screenshots/_Error on Publish assessment template.jpg");
		}
	}

	public void createEditions(Map<String, String> map) {
		try {
			visibilityOfElementAdv(linkEditions, 7);
			linkEditions.click();
			visibilityOfElement(btnCreateEditions, 3);
			btnCreateEditions.click();
			editionName.sendKeys("FirstEdition");
			Select sel = new Select(marketplaceitem);
			sel.selectByVisibleText(map.get("TemplateName") + " (AutomationSellerTenant)"); // TemplateName
			/*
			 * Select sel1=new Select(serviceprovider);
			 * sel1.selectByVisibleText("sellerTenant");
			 */

			waitForElement(radioBtnPaid);
			visibilityOfElement(fixedPrice, 3);
			fixedPrice.click();
			String fixValStr = fixedPrice.getAttribute("value");
			resultMap.put("3",
					new Object[] { map.get("Scenario Name"), "Validation of seller price on create edition page.",
							(compareStringResultOld(fixValStr, map.get("sellerPrice"))[0]),
							(compareStringResultOld(fixValStr, map.get("sellerPrice"))[1]) });
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_edition creation.jpg");
			BtnSaveEditions.click();
			System.out.println("Test");
			resultMap.put("4",
					new Object[] { map.get("Scenario Name"),
							"Edition created successfully for template '" + map.get("TemplateName") + "'.",
							("Information"), ("") });
			writeToExl(resultMap);
			resultMap.clear();
		} catch (Exception e) {
			resultMap.put("3", new Object[] { map.get("Scenario Name"), "Error on edition creation", "Failed",
					"Check the screenshot" });
			System.out.println("Error: " + e);
			writeToExl(resultMap);
			resultMap.clear();
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_Error on edition creation.jpg");
		}
	}

	public void navigateMktPlace(Map<String, String> map) {
		try {
			visibilityOfElement(linkMarketPlace, 10);
			linkMarketPlace.click();
			visibilityOfElement(btnDetail, 5);

			for (int i = 1; i < listMarketPlace.size(); i++) {
				String ele = "(//div[@class='MarketplaceItemHeading'])[" + i + "]";
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
						driver.findElement(By.xpath(ele)));
				String btnDetail = "(//button[contains(text(),'Detail')])[" + i + "]";
				String tempDetails = driver.findElement(By.xpath(ele)).getText();
				if (tempDetails.trim().equals(map.get("TemplateName"))) // TemplateName
				{
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
							driver.findElement(By.xpath(ele)));
					waitForElement(driver.findElement(By.xpath(ele)));
					driver.findElement(By.xpath(btnDetail)).click();
					visibilityOfElement(btnBuyNow, 8);
					resultMap.put("3",
							new Object[] { map.get("Scenario Name"), "Validation of seller price on market place page.",
									(compareStringResult(priceMktPlace, "A$" + map.get("sellerPrice"))[0]),
									(compareStringResult(priceMktPlace, "A$" + map.get("sellerPrice"))[1]) });
					resultMap.put("4",
							new Object[] { map.get("Scenario Name"),
									"Validation of seller tenant on market place page.",
									(compareStringResult(sellerTenantDetail, Util.readProperty("sellerTeam"))[0]),
									(compareStringResult(sellerTenantDetail, Util.readProperty("sellerTeam"))[1]) });

					takeSnapShot(driver,
							"Screenshots/" + map.get("Scenario Name") + "_Elements on marketplace page.jpg");
					resultMap.put("5",
							new Object[] { map.get("Scenario Name"),
									"Clicked on link from market place page '" + map.get("TemplateName") + "'.",
									("Information"), ("") });
					writeToExl(resultMap);
					resultMap.clear();
					break;
				}
			}
		} catch (Exception e) {
			resultMap.put("3", new Object[] { map.get("Scenario Name"), "Error on marketplace page", "Failed",
					"Check for screenshot" });
			System.out.println("Error: " + e);
			writeToExl(resultMap);
			resultMap.clear();
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_Error on marketplace page.jpg");
			throw new AssertionError("Error on on marketplace page. Stoping the test");
		}
	}

	@SuppressWarnings("null")
	public boolean compareLists(ArrayList<String> listOne, ArrayList<String> listTwo, Map<String, String> map) {
		try {
			Collections.sort(listOne);
			Collections.sort(listTwo);
			boolean isEqual = listOne.equals(listTwo); // false
			resultMap.put("3", new Object[] { map.get("Scenario Name"), "Validation for market place items",
					(compareBooleanResult(isEqual, true)[0]), (compareBooleanResult(isEqual, true)[1]) });
			writeToExl(resultMap);
			resultMap.clear();
			return isEqual;
		} catch (Exception e) {
			resultMap.put("3", new Object[] { map.get("Scenario Name"), "Error in validation for market place items",
					"Failed", "" });
			System.out.println("Error: " + e);
			writeToExl(resultMap);
			resultMap.clear();
			return (Boolean) null;
		}
	}

	public ArrayList<String> getMarketPlaceList(Map<String, String> map) {
		try {
			visibilityOfElement(linkMarketPlace, 8);
			linkMarketPlace.click();
			visibilityOfElement(btnDetail, 5);
			ArrayList<String> MPlaceList = new ArrayList<String>();
			MPlaceList.clear();

			for (int i = 1; i <= listMarketPlace.size(); i++) {
				String ele = "(//div[@class='MarketplaceItemHeading'])[" + i + "]";
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
						driver.findElement(By.xpath(ele)));
				String tempDetails = driver.findElement(By.xpath(ele)).getText();
				MPlaceList.add(tempDetails);
			}
			resultMap.put("3", new Object[] { map.get("Scenario Name"),
					"Captured the marketplace list from the page '" + map.get("Validation") + "'", "Information", "" });
			writeToExl(resultMap);
			resultMap.clear();
			return MPlaceList;
		} catch (Exception e) {
			resultMap.put("3", new Object[] { map.get("Scenario Name"), "Error in capturing marketplace list", "Failed",
					"Check for screenshot" });
			System.out.println("Error: " + e);
			writeToExl(resultMap);
			resultMap.clear();
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_Error in capturing marketplace.jpg");
			return null;
		}
	}

	public void getLogoDetails(Map<String, String> map) {
		try {
			String homelogoDeatil = homePageLogo.getAttribute("src");
			String srcDetail = "https://legalregtechquestiondocs.blob.core.windows.net/marketplace/ABC";
			takeSnapShot(driver,
					"Screenshots/" + map.get("Scenario Name") + "_" + map.get("Validation") + "_Home page logo.jpg");
			logoutApp(logoutImageMod);
			visibilityOfElement(userName, 8);
			String loginlogoDetail = loginPageLogo.getAttribute("src");
			takeSnapShot(driver,
					"Screenshots/" + map.get("Scenario Name") + "_" + map.get("Validation") + "_Login page logo.jpg");
			resultMap.put("2",
					new Object[] { map.get("Scenario Name"),
							"Validation for logo on home page '" + map.get("Validation") + "'",
							(compareBooleanResult(homelogoDeatil.contains(srcDetail), true)[0]),
							(compareBooleanResult(homelogoDeatil.contains(srcDetail), true)[1]) });
			resultMap.put("3",
					new Object[] { map.get("Scenario Name"),
							"Validation for logo on login page '" + map.get("Validation") + "'",
							(compareBooleanResult(loginlogoDetail.contains(srcDetail), true)[0]),
							(compareBooleanResult(loginlogoDetail.contains(srcDetail), true)[1]) });
			writeToExl(resultMap);
			resultMap.clear();
		} catch (Exception e) {
			resultMap.put("3", new Object[] { map.get("Scenario Name"), "Error in Validation for logo", "Failed",
					"Check for screenshot" });
			System.out.println("Error: " + e);
			writeToExl(resultMap);
			resultMap.clear();
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_Error in Validation for logo.jpg");
		}
	}

	public void assessmentPayment(Map<String, String> map) {
		try {
			visibilityOfElement(btnBuyNow, 8);
			btnBuyNow.click();
			visibilityOfElement(radioBtnPrice, 8);
			radioBtnPrice.click();
			visibilityOfElement(btnCheckout, 6);
			btnCheckout.click();
			visibilityOfElement(btnPurchase, 5);
			btnPurchase.click();
			visibilityOfElement(stripeCheckout, 7);
			driver.switchTo().frame("__privateStripeFrame5");
			// visibilityOfElement(cardnumber, 5);
			visibilityOfElement(cardnumber, 5);
			cardnumber.sendKeys("4242424242424242");
			waitForElement(expdate);
			expdate.sendKeys("1021");
			waitForElement(cvcDetail);
			cvcDetail.sendKeys("111");
			driver.switchTo().defaultContent();
			waitForElement(stripeCheckout);
			stripeCheckout.click();
			visibilityOfElement(btnServerErrorOk, 8);
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_assessmentPayment.jpg");
			btnServerErrorOk.click();
			resultMap.put("4", new Object[] { map.get("Scenario Name"), "Assessment payment done successfully.",
					("Information"), ("") });
			writeToExl(resultMap);
			resultMap.clear();

			// =========
			try {
				Thread.sleep(60000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			// ==========
		} catch (Exception e) {
			resultMap.put("3", new Object[] { map.get("Scenario Name"), "Error on Assessment payment page", "Failed",
					"Check for screenshot" });
			System.out.println("Error: " + e);
			writeToExl(resultMap);
			resultMap.clear();
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_Error on Assessment payment page.jpg");
			throw new AssertionError("Error on Assessment payment page. Stoping the test");
		}
	}

	public void verifyTemplate(Map<String, String> map) {
		try {
			visibilityOfElement(leftAssessmentLink, 7);
			leftAssessmentLink.click();
			visibilityOfElement(btnTemplate, 5);
			btnTemplate.click();
			visibilityOfElement(searchAssessment, 10);
			searchAssessment.click();
			searchAssessment.sendKeys(map.get("TemplateName")); // map.get("created Assessment")
			visibilityOfElement(verifyTemplateNamelink, 7);
			resultMap.put("3",
					new Object[] { map.get("Scenario Name"), "Validation of status of template as 'Published'",
							(compareStringResult(verifyTemplateStatus, "Published")[0]),
							(compareStringResult(verifyTemplateStatus, "Published")[1]) });
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_Verify Template as Draft.jpg");
			verifyTemplateNamelink.click();
		} catch (Exception e) {
			resultMap.put("3", new Object[] { map.get("Scenario Name"),
					"Error on template status validation. Stoping the test", "Failed", "Check for screenshot" });
			System.out.println("Error: " + e);
			writeToExl(resultMap);
			resultMap.clear();
			takeSnapShot(driver,
					"Screenshots/" + map.get("Scenario Name") + "_Error on template status validation.jpg");
			throw new AssertionError("Error on Assessment payment page. Stoping the test");
		}
	}

	public void verifyTemplateOnVersions(Map<String, String> map) {
		try {
			visibilityOfElement(versionstab, 5);
			versionstab.click();
			visibilityOfElement(versionstab, 5);
			resultMap.put("4",
					new Object[] { map.get("Scenario Name"),
							"Validation of template name on versions tab as '" + map.get("TemplateName") + "'",
							(compareStringResult(verifyTemplateName, map.get("TemplateName"))[0]),
							(compareStringResult(verifyTemplateName, map.get("TemplateName"))[1]) });
			resultMap.put("5",
					new Object[] { map.get("Scenario Name"), "Validation of template vesion versions tab as '1'",
							(compareStringResult(verifyTemplateVersion, "1")[0]),
							(compareStringResult(verifyTemplateVersion, "1")[1]) });
			resultMap.put("6",
					new Object[] { map.get("Scenario Name"),
							"Validation of template status versions tab as 'Published'",
							(compareStringResult(verifyTempStatus, "Published")[0]),
							(compareStringResult(verifyTempStatus, "Published")[1]) });
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_Verify Template on version before.jpg");
			writeToExl(resultMap);
			resultMap.clear();
		} catch (Exception e) {
			resultMap.put("3", new Object[] { map.get("Scenario Name"), "Error on verifying template", "Failed",
					"Check for screenshot" });
			System.out.println("Error: " + e);
			writeToExl(resultMap);
			resultMap.clear();
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name")
					+ "_error on verifying template on version page before.jpg");
		}
	}

	public void verifyTemplateOnVersionsafter(Map<String, String> map) {
		try {
			visibilityOfElement(versionstab, 5);
			versionstab.click();
			visibilityOfElement(versionstab, 5);
			resultMap.put("4",
					new Object[] { map.get("Scenario Name"),
							"Validation of template name on versions tab after at row 1 as '" + map.get("TemplateName")
									+ "'",
							(compareStringResult(verifyTemplateName, map.get("TemplateName"))[0]),
							(compareStringResult(verifyTemplateName, map.get("TemplateName"))[1]) });
			resultMap.put("5",
					new Object[] { map.get("Scenario Name"), "Validation of template vesion versions tab after as '1'",
							(compareStringResult(verifyTemplateVersion, "1")[0]),
							(compareStringResult(verifyTemplateVersion, "1")[1]) });
			resultMap.put("6",
					new Object[] { map.get("Scenario Name"),
							"Validation of template status versions tab after as 'Deprecated'",
							(compareStringResult(verifyTempStatus, "Deprecated")[0]),
							(compareStringResult(verifyTempStatus, "Deprecated")[1]) });
			resultMap.put("7",
					new Object[] { map.get("Scenario Name"),
							"Validation of template name on versions tab after at row 2 as '" + map.get("TemplateName")
									+ "'",
							(compareStringResult(verifyTemplateNameAfter, map.get("TemplateName"))[0]),
							(compareStringResult(verifyTemplateNameAfter, map.get("TemplateName"))[1]) });
			resultMap.put("8",
					new Object[] { map.get("Scenario Name"), "Validation of template vesion versions tab after '2'",
							(compareStringResult(verifyTemplateVersionAfter, "2")[0]),
							(compareStringResult(verifyTemplateVersionAfter, "2")[1]) });
			resultMap.put("9",
					new Object[] { map.get("Scenario Name"),
							"Validation of template status versions tab after as 'Published'",
							(compareStringResult(verifyTempStatusAfter, "Published")[0]),
							(compareStringResult(verifyTempStatusAfter, "Published")[1]) });
			resultMap.put("10",
					new Object[] { map.get("Scenario Name"),
							"Validation of Raw Template details as 'Internal Template'",
							(compareStringResult(TemplateDisplay, "Internal Template")[0]),
							(compareStringResult(TemplateDisplay, "Internal Template")[1]) });
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_Verify Template on version after.jpg");
			writeToExl(resultMap);
			resultMap.clear();
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_templateVersionFinal.jpg");
		} catch (Exception e) {
			resultMap.put("3", new Object[] { map.get("Scenario Name"), "Error after creating version", "Failed",
					"Check for screenshot" });
			System.out.println("Error: " + e);
			writeToExl(resultMap);
			resultMap.clear();
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name")
					+ "_error on verifying template on version page after.jpg");
		}
	}

	public void createVersion(Map<String, String> map) {
		try {
			designAssessmenttab.click();
			waitForElement(selectDomainassessment);
			changeAssessmentStatus(approveAssessment, "Draft", map);
			createYesNoquestion(map);
			changeAssessmentStatus(approveAssessment, "Approved", map);
			changeAssessmentStatusPublish(publishAssessment, "Published", false, map);
			visibilityOfElement(versionstab, 8);
		} catch (Exception e) {
			resultMap.put("3", new Object[] { map.get("Scenario Name"), "Error while creating version", "Failed",
					"Check for screenshot" });
			System.out.println("Error: " + e);
			writeToExl(resultMap);
			resultMap.clear();
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_Error in creating Version.jpg");
		}
	}

	public void createProvision(Map<String, String> map) {
		try {

			visibilityOfElement(linkProvisions, 3);
			linkProvisions.click();
			visibilityOfElement(btnAddProvisions, 5);
			btnAddProvisions.click();
			waitForElement(editProvisionId);

			Actions build = new Actions(driver);
			build.moveToElement(questionDropdown).click().build().perform();
			waitForElement(editProvisionId);
			build.sendKeys(map.get("AuthorityName")).build().perform();

			visibilityOfElement(editProvisionId, 2);
			// waitForElement(editProvisionId);
			editProvisionId.sendKeys("test1");
			editText.sendKeys("Edit text");
			resultMap.put("3", new Object[] { map.get("Scenario Name"),
					"Created provision for authority '" + map.get("AuthorityName") + "'", "Information", "" });
			writeToExl(resultMap);
			resultMap.clear();
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_createProvision.jpg");
			btnCreateProvision.click();
		} catch (Exception e) {
			resultMap.put("3", new Object[] { map.get("Scenario Name"), "Error while creating Provision", "Failed",
					"Check for screenshot" });
			System.out.println("Error: " + e);
			writeToExl(resultMap);
			resultMap.clear();
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_Error in creating Provision.jpg");
		}

	}

	public void createAuthorities(Map<String, String> map) {
		try {

			visibilityOfElement(linkAuthorities, 2);
			linkAuthorities.click();
			waitForElement(linkAddAuthorities);
			linkAddAuthorities.click();
			waitForElement(editAuthorityName);

			editAuthorityName.sendKeys(map.get("AuthorityName"));
			editAuthorityBodyName.sendKeys(map.get("AuthorityBodyName"));
			sectorNameAuthority.sendKeys(map.get("sectorNameAuthority"));
			editJurisName.sendKeys(map.get("editJurisName"));
			editAuthorityURL.sendKeys(map.get("editAuthorityURL"));
			linkUpdateAuthorities.click();

			visibilityOfElement(linkAddAuthorities, 5);

			for (int i = 1; i <= AuthtableRows.size(); i++) {
				String AuthName = AuthtableRows.get(i).getText();
				if (AuthName.equals(map.get("AuthorityName"))) // map.get("AuthorityName")
				{
					userRow.click();
					System.out.println("value of i: " + i);
					String authval = "(//table/tbody/tr)[" + (i + 1) + "]/td[2]";
					// String authval="(//table/tbody/tr)["+(i+1)+"]";
					// String authval1="(//table/tbody/tr)["+(i+1)+"]"+"/td[2]";
					driver.findElement(By.xpath(authval)).click();
					WebElement AuthorityName = driver
							.findElement(By.xpath("(//table/tbody/tr)[" + (i + 1) + "]/td[2]"));
					WebElement AuthorityBody = driver
							.findElement(By.xpath("(//table/tbody/tr)[" + (i + 1) + "]/td[3]"));
					WebElement AuthoritySector = driver
							.findElement(By.xpath("(//table/tbody/tr)[" + (i + 1) + "]/td[4]"));
					WebElement AuthorityJuris = driver
							.findElement(By.xpath("(//table/tbody/tr)[" + (i + 1) + "]/td[5]"));
					WebElement AuthorityURL = driver.findElement(By.xpath("(//table/tbody/tr)[" + (i + 1) + "]/td[6]"));
					resultMap.put("3", new Object[] { map.get("Scenario Name"), "Validation of Authority Started",
							"Information", "" });
					resultMap.put("4",
							new Object[] { map.get("Scenario Name"),
									"Validation of Authority Name '" + map.get("AuthorityName") + "'",
									(compareStringResult(AuthorityName, map.get("AuthorityName"))[0]),
									(compareStringResult(AuthorityName, map.get("AuthorityName"))[1]) });
					resultMap.put("5",
							new Object[] { map.get("Scenario Name"),
									"Validation of Authority Body '" + map.get("AuthorityBodyName") + "'",
									(compareStringResult(AuthorityBody, map.get("AuthorityBodyName"))[0]),
									(compareStringResult(AuthorityBody, map.get("AuthorityBodyName"))[1]) });
					resultMap.put("6",
							new Object[] { map.get("Scenario Name"), "Validation of Authority sector",
									(compareStringResult(AuthoritySector, map.get("sectorNameAuthority"))[0]),
									(compareStringResult(AuthoritySector, map.get("sectorNameAuthority"))[1]) });
					resultMap.put("7",
							new Object[] { map.get("Scenario Name"), "Validation of Authority Jurisdiction",
									(compareStringResult(AuthorityJuris, map.get("editJurisName"))[0]),
									(compareStringResult(AuthorityJuris, map.get("editJurisName"))[1]) });
					resultMap.put("8",
							new Object[] { map.get("Scenario Name"), "Validation of Authority URL",
									(compareStringResult(AuthorityURL, map.get("editAuthorityURL"))[0]),
									(compareStringResult(AuthorityURL, map.get("editAuthorityURL"))[1]) });
					takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_AuthorityValidation.jpg");
					writeToExl(resultMap);
					resultMap.clear();
					// linkCancelAuthorities.click();
					break;
				}
			}

			waitForElement(linkAddAuthorities);
			for (int i = 1; i <= AuthtableRows.size(); i++) {
				String AuthName = AuthtableRows.get(i).getText();
				if (AuthName.equals(map.get("AuthorityName"))) // map.get("AuthorityName")
				{
					userRow.click();
					System.out.println("value of i: " + i);
					String authval = "(//table/tbody/tr)[" + (i + 1) + "]";
					driver.findElement(By.xpath(authval)).click();
					Actions builder = new Actions(driver);
					builder.doubleClick().build().perform();
					break;
				}
			}
			uploadFile(map, btnBrowse);
			resultMap.put("9",
					new Object[] { map.get("Scenario Name"), "Validation for file upload",
							(compareBooleanResultDisplay(deleteFileLogo, true)[0]),
							(compareBooleanResultDisplay(deleteFileLogo, true)[1]) });
			writeToExl(resultMap);
			resultMap.clear();
			AddCustomField.click();
			questionType.click();
			selectdropdown();
			editField.sendKeys("test");
			UpdateAuthorities.click();
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_createAuthority.jpg");
			closebutton.click();
		} catch (Exception e) {
			resultMap.put("3", new Object[] { map.get("Scenario Name"), "Error while creating Authority", "Failed",
					"Check for screenshot" });
			System.out.println("Error: " + e);
			writeToExl(resultMap);
			resultMap.clear();
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_Error in creating Authority.jpg");
		}
	}

	public void uploadFile(Map<String, String> map, WebElement btnDetail) {
		try {

			waitForElement(btnDetail);
			btnDetail.click();
			// uploadFileWithRobot("E:/New Selenium Class/UIAutomation6Clicks/Test
			// Data/"+map.get("uploadFile"));
			String path = System.getProperty("user.dir") + "/Test Data/" + map.get("uploadFile");
			uploadFileWithRobot(path);
			waitForElement(btnuploadDoc);
			btnuploadDoc.click();
			waitForElement(deleteFileLogo);
		} catch (Exception e) {
			resultMap.put("3", new Object[] { map.get("Scenario Name"), "Error while File upload", "Failed",
					"Check for screenshot" });
			System.out.println("Error: " + e);
			writeToExl(resultMap);
			resultMap.clear();
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_Error in file upload.jpg");
		}
	}

	public void createWeightedTemplate(Map<String, String> map) {
		try {
			waitForElement(settingsLink);
			settingsLink.click();
			visibilityOfElement(settingsScrollBar, 5);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", drpDownQtype);
			waitForElement(drpDownQtype);
			drpDownQtype.click();
			selectdropdownUp();
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
					drpDownResultWithinDomain);
			drpDownResultWithinDomain.click();
			
			selectdropdown();
			drpDownResultAcrossDomain.click();
			selectdropdown();
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_create weighted template page.jpg");
			settingsSaveBtn.click();
		} catch (Exception e) {
			resultMap.put("3", new Object[] { map.get("Scenario Name"), "Error on create weighted template page",
					"Failed", "Check for screenshot" });
			checkForServerError(map);
			System.out.println("Error: " + e);
			writeToExl(resultMap);
			resultMap.clear();
			takeSnapShot(driver,
					"Screenshots/" + map.get("Scenario Name") + "_Error create weighted template page.jpg");
		}
	}

	public void createUser(Map<String, String> map, String element1, String element2) {
		try {
			map.put("DefaultUser", map.get("DefaultUser") + randomNumber());
			map.put("DefaultUserLogin", map.get("DefaultUser"));
			visibilityOfElement(createTenant, 5);
			createTenant.click();
			waitForElement(EditTeamName);
			EditTeamName.sendKeys(map.get("DefaultUser"));
			AdminEmailAddress.sendKeys(map.get("EmailID"));
			checkBoxActivationEmail.click();
			// checkBoxChangePassword.click();

			if (element1.equals("Publish"))
				checkBoxPublish.click();
			if ((element2.equals("Service"))) {
				checkBoxServiceProvider.click();
				checkBoxSetPassword.click();
				waitForElement(editAdminPassword);
				editAdminPassword.sendKeys("Welcome1");
				editAdminPasswordRepeat.sendKeys("Welcome1");
			}

			waitForElement(createUserSaveBtn);
			createUserSaveBtn.click();
			waitForElement(tenantSearch);
			resultMap.put("3", new Object[] { map.get("Scenario Name"),
					"New tenant created with name as '" + map.get("DefaultUserLogin") + "'", "Information", "" });
			writeToExl(resultMap);
			resultMap.clear();
			// editUserFormcancelBtn.click();
		} catch (Exception e) {
			resultMap.put("3", new Object[] { map.get("Scenario Name"), "Error while creating tenant", "Failed",
					"Check for screenshot" });
			writeToExl(resultMap);
			resultMap.clear();
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_Error in tenant creation.jpg");
		}
	}

	public void verifyUser(Map<String, String> map) {
		try {
			visibilityOfElement(tenantSearch, 8);
			tenantSearch.clear();
			tenantSearch.sendKeys(map.get("DefaultUser"));
			tenantSearchBtn.click();
			visibilityOfElement(tenantSearch, 5);
			userRow.click();
			editUserbtn.click();
			visibilityOfElement(editUserFormcancelBtn, 5);
			// waitForElement(editUserFormcancelBtn);

			if ((editUserFormActiveChkBox.isSelected()) && (!(editUserFormPublisherChkBox.isSelected()))
					&& (!(editUserFormServiceChkBox.isSelected()))) {
				resultMap.put("2",
						new Object[] { map.get("Scenario Name"), "Validation of Default tenant creation",
								(compareBooleanResult(editUserFormActiveChkBox.isSelected(), true)[0]),
								(compareBooleanResult(editUserFormActiveChkBox.isSelected(), true)[1]) });
				takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "Default tenant Creation.jpg");
			}

			else if ((editUserFormActiveChkBox.isSelected()) && ((editUserFormPublisherChkBox.isSelected()))
					&& (!(editUserFormServiceChkBox.isSelected()))) {
				resultMap.put("3",
						new Object[] { map.get("Scenario Name"), "Validation of Seller tenant creation",
								(compareBooleanResult(editUserFormPublisherChkBox.isSelected(), true)[0]),
								(compareBooleanResult(editUserFormPublisherChkBox.isSelected(), true)[1]) });
				takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "Seller tenant Creation.jpg");
			}

			else if ((editUserFormActiveChkBox.isSelected()) && ((editUserFormPublisherChkBox.isSelected()))
					&& ((editUserFormServiceChkBox.isSelected()))) {
				resultMap.put("4",
						new Object[] { map.get("Scenario Name"), "Validation of Service provider tenant creation",
								(compareBooleanResult(editUserFormServiceChkBox.isSelected(), true)[0]),
								(compareBooleanResult(editUserFormServiceChkBox.isSelected(), true)[1]) });
				takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "Service provide tenant Creation.jpg");
			} else {
				resultMap.put("5", new Object[] { map.get("Scenario Name"), "Validation of tenant creation", "Failed",
						"User not created properly" });
				takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "Improper tenant Creation.jpg");
			}
			writeToExl(resultMap);
			resultMap.clear();
			editUserFormcancelBtn.click();
			map.put("DefaultUser", "Defaultuser");
		} catch (Exception e) {
			resultMap.put("3", new Object[] { map.get("Scenario Name"), "Error while verifying tenant", "Failed",
					"Check for screenshot" });
			writeToExl(resultMap);
			resultMap.clear();
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_Error while verifying tenant.jpg");
		}
	}

	public void createAction(Map<String, String> map) {
		try {
			visibilityOfElement(assignQuestion, 5);
			assignQuestion.click();
			visibilityOfElement(selectUser, 5);
			selectUser.click();
			// selectUser.clear();
			// selectUser.sendKeys("admin admin");
			selectuser();
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_createAction.jpg");
			// selectdropdown();
			closebutton.click();
			resultMap.put("2", new Object[] { map.get("Scenario Name"), "Select User for assessment complated",
					"Information", "" });
			writeToExl(resultMap);
			resultMap.clear();
		} catch (Exception e) {
			resultMap.put("3", new Object[] { map.get("Scenario Name"), "Error while create action", "Failed",
					"Check for screenshot" });
			System.out.println("Error: " + e);
			writeToExl(resultMap);
			resultMap.clear();
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_Error in create action.jpg");
		}
	}

	public void addAction(SoftAssert softAssertion, Map<String, String> map) {
		try {
			visibilityOfElement(addAction, 5);
			addAction.click();
			visibilityOfElement(addquestionpref, 5);
			addquestionpref.click();
			itemName.sendKeys(map.get("ActionName"));
			userName1.click();
			selectuser();
			map.put("Selected User", selectedUser.getText());
			uploadFile(map, btnBrowse);
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_addAction.jpg");
			closeBtn.click();
			resultMap.put("2",
					new Object[] { map.get("Scenario Name"), "Add action to user complated", "Information", "" });
			writeToExl(resultMap);
			resultMap.clear();
		} catch (Exception e) {
			resultMap.put("3", new Object[] { map.get("Scenario Name"), "Error while add action", "Failed",
					"Check for screenshot" });
			System.out.println("Error: " + e);
			writeToExl(resultMap);
			resultMap.clear();
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_Error in add action.jpg");
		}
	}

	public void validRemediation(SoftAssert softAssertion, Map<String, String> map) {
		try {
			remediationTab.click();
			waitForElement(remAction);
			resultMap.put("2",
					new Object[] { map.get("Scenario Name"), "Validation of action name on remediation",
							(compareStringResult(remAction, map.get("ActionName"))[0]),
							(compareStringResult(remAction, map.get("ActionName"))[1]) });
			resultMap.put("3",
					new Object[] { map.get("Scenario Name"), "Validation of domain name on remediation",
							(compareStringResult(remDomain, map.get("DomainName"))[0]),
							(compareStringResult(remDomain, map.get("DomainName"))[1]) });
			resultMap.put("4",
					new Object[] { map.get("Scenario Name"), "Validation of selected user on remediation",
							(compareStringResult(remOwner, map.get("Selected User"))[0]),
							(compareStringResult(remOwner, map.get("Selected User"))[1]) });
			writeToExl(resultMap);
			resultMap.clear();
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_validateRemediation.jpg");
		} catch (Exception e) {
			resultMap.put("3", new Object[] { map.get("Scenario Name"), "Error while validRemediation", "Failed",
					"Check for screenshot" });
			System.out.println("Error: " + e);
			writeToExl(resultMap);
			resultMap.clear();
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_Error in validRemediation.jpg");
		}
	}

	//////////////////

	// actionPage actPage=new actionPage();

	// public SoftAssert softAssertion= new SoftAssert();

	Logger log = Logger.getLogger("6Clicks -->");

	public loginScreen(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void createMplacecategory(Map<String, String> map) {
		try {
			visibilityOfElement(masterDatalink, 8);
			masterDatalink.click();
			waitForElement(linkMarketplace);
			linkMarketplace.click();
			visibilityOfElement(addMarketplacebtn, 5);
			addMarketplacebtn.click();
			visibilityOfElement(gridMarketplace, 5);
			gridMarketplace.sendKeys(map.get("MktName"));
			gridMarketplacedesc.sendKeys(map.get("MktDesc"));
			updateBtn.click();
			visibilityOfElement(addMarketplacebtn, 8);
			waitForElement(addMarketplacebtn);

			int rowNo = 0;
			for (int i = 0; i <= mktTable.size(); i++) {
				String AuthName = mktTableRows.get(i).getText();
				if (AuthName.equals(map.get("MktName"))) // map.get("AuthorityName")
				{
					System.out.println("value of i: " + i);
					String authval = "(//table)[4]/tbody/tr[" + (i + 1) + "]"; // (//table)[4]/tbody/tr/td[2]
					driver.findElement(By.xpath(authval)).click();
					Actions builder = new Actions(driver);
					builder.click().build().perform();
					rowNo = i;
					break;
				}
			}

			WebElement marketName = driver.findElement(By.xpath("(//table)[4]/tbody/tr[" + (rowNo + 1) + "]/td[2]"));
			WebElement marketDescription = driver
					.findElement(By.xpath("(//table)[4]/tbody/tr[" + (rowNo + 1) + "]/td[3]"));
			resultMap.put("3", new Object[] { map.get("Scenario Name"),
					"Validation of creation of market place Started", "Information", "" });
			resultMap.put("4",
					new Object[] { map.get("Scenario Name"),
							"Validation of Market place Name '" + map.get("MktName") + "'",
							(compareStringResult(marketName, map.get("MktName"))[0]),
							(compareStringResult(marketName, map.get("MktName"))[1]) });
			resultMap.put("5",
					new Object[] { map.get("Scenario Name"),
							"Validation of Market place description '" + map.get("MktDesc") + "'",
							(compareStringResult(marketDescription, map.get("MktDesc"))[0]),
							(compareStringResult(marketDescription, map.get("MktDesc"))[1]) });

			writeToExl(resultMap);
			resultMap.clear();

			marketName.click();
			waitForElement(deleteBtn);
			deleteBtn.click();
			waitForElement(submitConfirm);
			submitConfirm.click();
			visibilityOfElement(MktTagsLink, 5);
		} catch (Exception e) {
			resultMap.put("3", new Object[] { map.get("Scenario Name"), "Error while creating marketplace", "Failed",
					"Check for screenshot" });
			System.out.println("Error: " + e);
			writeToExl(resultMap);
			resultMap.clear();
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_Error while creating marketplace.jpg");
		}
	}

	public void createMarketTag(Map<String, String> map) {
		try {
			visibilityOfElement(addMarketplacebtn, 5);
			addMarketplacebtn.click();
			visibilityOfElement(tagMarketplace, 5);
			tagMarketplace.sendKeys(map.get("TagName"));
			tagMarketplacedesc.sendKeys(map.get("TagDesc"));
			updateBtn.click();
			visibilityOfElement(addMarketplacebtn, 8);

			int rowNo = 0;
			for (int i = 0; i <= mktTable.size(); i++) {
				String AuthName = mktTableRows.get(i).getText();
				if (AuthName.equals(map.get("TagName"))) // map.get("AuthorityName")
				{
					System.out.println("value of i: " + i);
					String authval = "(//table)[4]/tbody/tr[" + (i + 1) + "]"; // (//table)[4]/tbody/tr/td[2]
					driver.findElement(By.xpath(authval)).click();
					Actions builder = new Actions(driver);
					builder.click().build().perform();
					rowNo = i;
					break;
				}
			}

			WebElement marketName = driver.findElement(By.xpath("(//table)[4]/tbody/tr[" + (rowNo + 1) + "]/td[2]"));
			WebElement marketDescription = driver
					.findElement(By.xpath("(//table)[4]/tbody/tr[" + (rowNo + 1) + "]/td[3]"));

			resultMap.put("3", new Object[] { map.get("Scenario Name"), "Validation of Tag name creation started.",
					"Information", "" });
			resultMap.put("4",
					new Object[] { map.get("Scenario Name"), "Validation of Tag Name '" + map.get("TagName") + "'",
							(compareStringResult(marketName, map.get("TagName"))[0]),
							(compareStringResult(marketName, map.get("TagName"))[1]) });
			resultMap.put("5",
					new Object[] { map.get("Scenario Name"),
							"Validation of Tag description '" + map.get("TagDesc") + "'",
							(compareStringResult(marketDescription, map.get("TagDesc"))[0]),
							(compareStringResult(marketDescription, map.get("TagDesc"))[1]) });
			writeToExl(resultMap);
			resultMap.clear();

			marketName.click();
			waitForElement(deleteBtn);
			deleteBtn.click();
			waitForElement(submitConfirm);
			submitConfirm.click();
			visibilityOfElement(addMarketplacebtn, 5);
		} catch (Exception e) {
			resultMap.put("3", new Object[] { map.get("Scenario Name"), "Error while creating tag", "Failed",
					"Check for screenshot" });
			System.out.println("Error: " + e);
			writeToExl(resultMap);
			resultMap.clear();
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_Error while creating tag.jpg");
		}
	}

	public void validateCompleteStatus(Map<String, String> map, SoftAssert softAssertion) throws Exception {
		try {
			Reporter.log("Validate complete status started");
			visibilityOfElement(leftAssessmentLink, 8);
			leftAssessmentLink.click();
			visibilityOfElement(searchAssessment, 6);
			searchAssessment.click();
			searchAssessment.sendKeys(map.get("created Assessment"));
			visibilityOfElement(assessmentLink, 8);

			resultMap.put("3",
					new Object[] { map.get("Scenario Name"),
							"Validation of status of assessment on main page as 'Completed'",
							(compareStringResult(assessmentStatus, "Submitted")[0]),
							(compareStringResult(assessmentStatus, "Submitted")[1]) });
			assessmentLink.click();
			visibilityOfElement(asmtStatus, 8);
			resultMap.put("4",
					new Object[] { map.get("Scenario Name"), "Validation of status of assessment page as 'Completed'",
							(compareStringResult(asmtStatus, "Completed")[0]),
							(compareStringResult(asmtStatus, "Completed")[1]) });
			visibilityOfElement(sendAssessmenttab, 2);
			sendAssessmenttab.click();
			visibilityOfElement(auditTraitab, 3);
			resultMap.put("5",
					new Object[] { map.get("Scenario Name"),
							"Validation of status on Send assessment page as 'Completed'",
							(compareStringResult(sendAssessmentstatus, "Completed")[0]),
							(compareStringResult(sendAssessmentstatus, "Completed")[1]) });
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_CompleteAssessmentstatus.jpg");

			auditTraitab.click();
			waitForElement(timelineTitle);
			Reporter.log("Time Line title after publish: " + timelineTitle.getText());

			resultMap.put("6",
					new Object[] { map.get("Scenario Name"), "Validation of 'View Results' link",
							(compareStringResult(btnViewResult, "View Results")[0]),
							(compareStringResult(btnViewResult, "View Results")[1]) });
			btnViewResult.click();

			visibilityOfElement(selectDomainassessment, 8);
			selectDomainassessment.click();
			waitForElement(selectAllDomain);
			selectAllDomain.click();

			visibilityOfElement(Qusetion1Heading, 8);
			if (map.get("Scenario Name").contains("creatSubmitAssessmentAndVerifyResult")) {
				resultMap.put("7",
						new Object[] { map.get("Scenario Name"), "Validation of 'Question 1 Heading' link",
								(compareStringResult(Qusetion1Heading, map.get("Question1Heading"))[0]),
								(compareStringResult(Qusetion1Heading, map.get("Question1Heading"))[1]) });
				resultMap.put("8",
						new Object[] { map.get("Scenario Name"), "Validation of 'Question 1 Description' link",
								(compareStringResult(Qusetion1Desc, map.get("Question1Description"))[0]),
								(compareStringResult(Qusetion1Desc, map.get("Question1Description"))[1]) });
				resultMap.put("9",
						new Object[] { map.get("Scenario Name"), "Validation of 'Question 2 Heading' link",		// done
								(compareStringResult(Qusetion2Heading, map.get("Q2HeadTemp"))[0]),
								(compareStringResult(Qusetion2Heading, map.get("Q2HeadTemp"))[1]) });
				/*
				 * resultMap.put("10", new Object[] { map.get("Scenario Name"),							//done
				 * "Validation of 'Question 2 Description' link",
				 * (compareStringResult(Qusetion2Desc, map.get("Question2Description"))[0]),
				 * (compareStringResult(Qusetion2Desc, map.get("Question2Description"))[1]) });
				 */
				resultMap.put("13",
						new Object[] { map.get("Scenario Name"), "Validation of Answer given for Question 1",		// Done
								(compareStringResult(tempWeightedAnswar1, map.get("Option2"))[0]),
								(compareStringResult(tempWeightedAnswar1, map.get("Option2"))[1]) });
				/*
				 * resultMap.put("14", new Object[] { map.get("Scenario Name"),
				 * "Validation of preffered answer for Question 1",
				 * (compareStringResult(tempWeightedAnswar2, map.get("PrefAnswer2"))[0]),
				 * (compareStringResult(tempWeightedAnswar2, map.get("PrefAnswer2"))[1]) });
				 */
				resultMap.put("15",
						new Object[] { map.get("Scenario Name"), "Validation of Answer given for Question 2",
								(compareStringResult(tempWeightedAnswar3, map.get("Option1"))[0]),
								(compareStringResult(tempWeightedAnswar3, map.get("Option1"))[1]) });
				/*
				 * resultMap.put("16", new Object[] { map.get("Scenario Name"),
				 * "Validation of preffered answer for Question 2",
				 * (compareStringResult(tempWeightedAnswar4, map.get("Option2"))[0]),
				 * (compareStringResult(tempWeightedAnswar4, map.get("Option2"))[1]) });
				 */
			}

			if (map.get("Scenario Name").contains("createAssessmentTemplateAndAssessment")) {
				resultMap.put("7",
						new Object[] { map.get("Scenario Name"), "Validation of 'Question 1 Heading' link",
								(compareStringResult(Qusetion1Heading, map.get("Q1HeadTemp"))[0]),
								(compareStringResult(Qusetion1Heading, map.get("Q1HeadTemp"))[1]) });
				resultMap.put("8",
						new Object[] { map.get("Scenario Name"), "Validation of 'Question 1 Description' link",
								(compareStringResult(Qusetion1Desc, map.get("Q1DescTemp"))[0]),
								(compareStringResult(Qusetion1Desc, map.get("Q1DescTemp"))[1]) });
				resultMap.put("9",
						new Object[] { map.get("Scenario Name"), "Validation of 'Question 2 Heading' link",			//done
								(compareStringResult(Qusetion2Heading, map.get("Q2HeadTemp"))[0]),
								(compareStringResult(Qusetion2Heading, map.get("Q2HeadTemp"))[1]) });
				/*
				 * resultMap.put("10", new Object[] { map.get("Scenario Name"),
				 * "Validation of 'Question 2 Description' link",
				 * (compareStringResult(Qusetion2Desc, map.get("Q2DescTemp"))[0]),
				 * (compareStringResult(Qusetion2Desc, map.get("Q2DescTemp"))[1]) });
				 */
				resultMap.put("13",
						new Object[] { map.get("Scenario Name"), "Validation of Answer given for Question 1",		//done
								(compareStringResult(tempWeightedAnswar1,
										map.get("Option2"))[0]),
								(compareStringResult(tempWeightedAnswar1,
										map.get("Option2"))[1]) });
				resultMap.put("14",
						new Object[] { map.get("Scenario Name"), "Validation of preffered answer for Question 1",	//done
								(compareStringResult(tempWeightedAnswar2, map.get("TempWeight"))[0]),
								(compareStringResult(tempWeightedAnswar2, map.get("TempWeight"))[1]) });

				/*
				 * resultMap.put("15", new Object[] {map.get("Scenario Name"),
				 * "Validation of Answer given for Qusetion 2",(compareStringResult(
				 * tempWeightedAnswar3,
				 * map.get("Option2"))[0]),(compareStringResult(tempWeightedAnswar3,
				 * map.get("Option2"))[1])}); resultMap.put("16", new Object[]
				 * {map.get("Scenario Name"),
				 * "Validation of preffered answer for Qusetion 2",(compareStringResult(
				 * tempWeightedAnswar4,
				 * map.get("Option2"))[0]),(compareStringResult(tempWeightedAnswar4,
				 * map.get("Option2"))[1])});
				 */
			}

			resultMap.put("11",
					new Object[] { map.get("Scenario Name"), "Validation of 'Add domain for Question 1' link",		// done
							(compareStringResult(Qusetion1Domain, "Page: " + map.get("DomainName"))[0]),
							(compareStringResult(Qusetion1Domain, "Page: " + map.get("DomainName"))[1]) });
			resultMap.put("12",
					new Object[] { map.get("Scenario Name"), "Validation of 'Add domain for Question 2' link",		//done
							(compareStringResult(Qusetion2Domain, "Page: " + map.get("DomainName"))[0]),
							(compareStringResult(Qusetion2Domain, "Page: " + map.get("DomainName"))[1]) });
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_CompleteAssessmentQuestionStatus.jpg");
			paperClipDoc.click();
			waitForElement(btnDeleteFileBtnMain);
			resultMap.put("17",
					new Object[] { map.get("Scenario Name"), "Validation if uploaded file can not delete after submit",
							(compareStringResult(btnDeleteFileBtnMain, "-")[0]),
							(compareStringResult(btnDeleteFileBtnMain, "-")[1]) });
			closebutton.click();
			visibilityOfElement(paperClipDoc, 5);
			Reporter.log("Validate complete status ends here");
			writeToExl(resultMap);
			resultMap.clear();
		} catch (Exception e) {
			resultMap.put("3", new Object[] { map.get("Scenario Name"), "Error while checking completion status",
					"Failed", "Check for screenshot" });
			System.out.println("Error: " + e);
			writeToExl(resultMap);
			resultMap.clear();
			takeSnapShot(driver,
					"Screenshots/" + map.get("Scenario Name") + "_Error in checking completion status.jpg");
		}
	}

	public void validateDocument(SoftAssert softAssertion, Map<String, String> map) throws Exception {
		try {
			visibilityOfElement(selectDomainassessment, 8);

			selectDomainassessment.click();
			waitForElement(selectAllDomain);
			selectAllDomain.click();

			visibilityOfElement(btnbrowseButton, 8);

			EditAnswer.sendKeys(map.get("PrefAnswer1"));
			waitForElement(EditAnswer);
			waitForElement(questionDropdown);
			questionDropdown.click();
			selectdropdown();

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnbrowseButton);

			uploadFile(map, btnbrowseButton);
			visibilityOfElement(paperClip, 5);

			resultMap.put("3",
					new Object[] { map.get("Scenario Name"), "Validation of 'Paper Clip' ",
							(compareBooleanResultDisplay(paperClip, true)[0]),
							(compareBooleanResultDisplay(paperClip, true)[1]) });
			// paperClip.click();
			writeToExl(resultMap);
			resultMap.clear();

			//chaange below code
			
			  if(map.get("ValidationType").equals("Assessment")) 
			  { 
				  
				  ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", EditAnswer1);
			  waitForElement(EditAnswer1);
			  EditAnswer1.sendKeys(map.get("Option1"));
			  waitForElement(EditAnswer1);
			  
				/*
				 * questionDropdown1.click();
				 * 
				 * Actions build = new Actions(driver);
				 * build.sendKeys(Keys.ARROW_DOWN).build().perform(); Thread.sleep(2000);
				 */ }
			 

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnsubmit);
			resultMap.put("2",
					new Object[] { map.get("Scenario Name"), "Validation of 'Submit' button",
							(compareBooleanResultDisplay(btnsubmit, true)[0]),
							(compareBooleanResultDisplay(btnsubmit, true)[1]) });
			btnsubmit.click();
			waitForElement(submitConfirm);
			submitConfirm.click();
			visibilityOfElement(logoutImage, 5);
			checkReadOnlyStatusCompletedPage(map);
			visibilityOfElement(logoutImage, 5);

			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_validateDocument.jpg");
			logoutApp(logoutImage);
		} catch (Exception e) {
			resultMap.put("3", new Object[] { map.get("Scenario Name"), "Error while validating user", "Failed",
					"Check for screenshot" });
			System.out.println("Error: " + e);
			writeToExl(resultMap);
			resultMap.clear();
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_Error in user validation.jpg");
		}
	}

	public WebDriver init(String Url, String team) throws Exception {
		// resultMap.put("1", new Object[] {"TC_Name", "Description", "Status",
		// "Comment"});
		selectBrowser();
		geturl(Url);
		Reporter.log("Naviagating to the URL.");
		loginToApp(driver, team, Util.readProperty("UserName"), Util.readProperty("Password"));
		// loginToApp(driver);
		return driver;
	}

	public void selectBrowser() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		log.info("Creating object of chrome browser");
		Reporter.log("Creating object of chrome browser");
	}

	public void geturl(String url) {
		driver.get(url);
		log.info("Navigating to " + url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}

	public void loginToApp(WebDriver driver, String teamname, String UserName, String Password)

	{
		try {
			PageFactory.initElements(driver, this);
			visibilityOfElement(teamSelect, 3);
			System.out.println("Team name: " + teamname);
			if (!(teamname.contains("AdminName"))) {
				teamSelect.click();
				waitForElement(changeTeamHeader);
				changeTeamHeader.click();
				teamName.clear();
				waitForElement(teamName);
				teamName.sendKeys(teamname);
				saveBtn.click();
				visibilityOfElement(forgetPsw, 15);
			}
			userName.sendKeys(UserName);
			pswd.sendKeys(Password);
			waitForElement(loginBtn);
			loginBtn.click();
		} catch (Exception e) {
			resultMap.put("2", new Object[] { "Login page Loading Error", "Page not loaded for login. Stoping the test",
					"Failed", "Check for screenshot" });
			writeToExl(resultMap);
			resultMap.clear();
			takeSnapShot(driver, "Screenshots/_Error for login.jpg");
			throw new AssertionError("Page not loaded for login. Stooping the test");
		}
	}

	public int randomNumber() {
		Random objGenerator = new Random();
		int randomNumber = objGenerator.nextInt(10000);
		return randomNumber;
	}

	public void createNewTeam(WebDriver driver) {
		// try {
		PageFactory.initElements(driver, this);
		createNewTeamlink.click();
		newTeamName.sendKeys("Myteamname");
		adminEmailAddress.sendKeys("kedartalegaonkar@gmail.com");
		createTeamPassword.sendKeys("Welcome1");
		createTeamPasswordRepeat.sendKeys("Welcome1");
		termsandcondition.click();

		/*
		 * } catch (Exception e) { System.out.println(e); }
		 */
	}

	public void createTemplate(Map<String, String> map, SoftAssert softAssertion) {
		try {
			visibilityOfElementAdv(sendAssessmentBtn, 10);
			String reportNm = map.get("TemplateName") + randomNumber();
			map.put("TemplateName", reportNm);
			// String templatetype=map.get("TemplateType");
			sendAssessmentBtn.click();
			waitForElement(templateName);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", newTemplate);
			newTemplate.click();
			waitForElement(templateName);
			templateName.click();
			templateName.sendKeys(reportNm);
			templateType.click();
			selectdropdown();
			createAsmtBtn.click();
			visibilityOfElement(asseessmentName, 8);

			resultMap.put("2",
					new Object[] { map.get("Scenario Name"),
							"Validation of Template creation version number as '" + reportNm + "'.",
							(compareStringResult(asseessmentName, reportNm)[0]),
							(compareStringResult(asseessmentName, reportNm)[1]) });
			resultMap.put("3",
					new Object[] { map.get("Scenario Name"), "Validation of Raw Template details",
							(compareStringResult(TemplateDisplay, "Template")[0]),
							(compareStringResult(TemplateDisplay, "Template")[1]) });
			resultMap.put("4", new Object[] { map.get("Scenario Name"), "Validation of status of template as 'Draft'",
					(compareStringResult(asmtStatus, "Draft")[0]), (compareStringResult(asmtStatus, "Draft")[1]) });
			resultMap.put("5",
					new Object[] { map.get("Scenario Name"), "Validation of 'Settings' Link on UI",
							(compareBooleanResultDisplay(settingsLink, true)[0]),
							(compareBooleanResultDisplay(settingsLink, true)[1]) });
			resultMap.put("6",
					new Object[] { map.get("Scenario Name"), "Validation of 'Design Assessment' tab on UI",
							(compareBooleanResultDisplay(designAssessmenttab, true)[0]),
							(compareBooleanResultDisplay(designAssessmenttab, true)[1]) });
			resultMap.put("7",
					new Object[] { map.get("Scenario Name"), "Validation of 'Versions' tab on UI",
							(compareBooleanResultDisplay(versionstab, true)[0]),
							(compareBooleanResultDisplay(versionstab, true)[1]) });
			resultMap.put("8",
					new Object[] { map.get("Scenario Name"), "Validation of 'Customise Assessment' tab on UI",
							(compareBooleanResultDisplay(customiseAssessmenttab, true)[0]),
							(compareBooleanResultDisplay(customiseAssessmenttab, true)[1]) });

			writeToExl(resultMap);
			resultMap.clear();
			Reporter.log("New template created successfully with name: " + asseessmentName.getText());
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_createVersionTemplate.jpg");

		} catch (Exception e) {
			resultMap.put("2", new Object[] { map.get("Scenario Name"),
					"Error while creating template.Stoping the test", "Failed", "Check for screenshot" });
			System.out.println("Error: " + e);
			writeToExl(resultMap);
			resultMap.clear();
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_Error for template.jpg");
			throw new AssertionError("Error while creating template.Stoping the test");
		}
	}

	public void createDomain(String domainName, Map<String, String> map) {
		try {

			visibilityOfElement(selectDomain, 5);
			selectDomain.click();
			waitForElement(btnAddDomain);
			btnAddDomain.click();
			waitForElement(domainHeading);
			domainHeading.click();
			waitForElement(selectDomain);
			selectDomain.click();
			waitForElement(addDomaineditbox);
			addDomaineditbox.sendKeys(domainName);
			domainHeading.click();
			waitForElement(selectDomain);
			selectDomain.click();
			waitForElement(addDomainsavebtn);
			addDomainsavebtn.click();
			/*
			 * waitForElement(selectDomain); selectDomain.click();
			 */
			map.put("DomainName", map.get("DomainName") + domainName);
			resultMap.put("2", new Object[] { map.get("Scenario Name"),
					"Domain is created on page with name as 'New Domain" + domainName + "'", "Information", "" });
			writeToExl(resultMap);
			resultMap.clear();
		} catch (Exception e) {
			resultMap.put("2", new Object[] { map.get("Scenario Name"), "Error while creating domain.", "Failed",
					"Check for screenshot" });
			System.out.println("Error: " + e);
			writeToExl(resultMap);
			resultMap.clear();
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_Error in domain.jpg");
		}
	}

	public void selectdropdownUp()

	{
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Actions build = new Actions(driver);
		build.sendKeys(Keys.ARROW_UP).build().perform();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		build.sendKeys(Keys.ENTER).build().perform();
	}
	
	public void selectdropdown()

	{
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Actions build = new Actions(driver);
		build.sendKeys(Keys.ARROW_DOWN).build().perform();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		build.sendKeys(Keys.ENTER).build().perform();
	}

	public void selectuser()

	{
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Actions build = new Actions(driver);
		build.sendKeys("admin admin").build().perform();
		build.sendKeys(Keys.ENTER).build().perform();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		build.sendKeys(Keys.ENTER).build().perform();
	}

	public void selectRadioBtn(WebElement btn) {
		Actions build = new Actions(driver);
		build.moveToElement(customradiobtn).moveByOffset(9, 9).click().build().perform();
	}

	public void createYesNoquestion(Map<String, String> map) {
		try {
			visibilityOfElement(btnAddQuestion, 5);
			btnAddQuestion.click();
			visibilityOfElement(questiontitle, 5);
			// waitForElement(questiontitle);
			questiontitle.sendKeys(map.get("Question1Heading"));
			questionDesc.sendKeys(map.get("Question1Description"));

			/*
			 * Actions build = new Actions(driver);
			 * build.moveToElement(yesRadiobtn).moveByOffset(9, *
			 * 9).click().build().perform();
			 */

			docAttach.click();

			customquestionedit1.sendKeys(map.get("Option1"));
			customquestionedit2.sendKeys(map.get("Option2"));

			((JavascriptExecutor) driver).executeScript("arguments[0].click();", btncreatequestion);
			// takeSnapShot(driver,"Screenshots/YesNoquestion_Page.jpg");
			// btncreatequestion.click();
			visibilityOfElement(btnAddQuestion, 3);
			verifyAddedQuestion.getText();
			resultMap.put("25",
					new Object[] { map.get("Scenario Name"), "Add Yes or No Question",
							(compareStringResult(verifyAddedQuestion, "Yes No Question1")[0]),
							(compareStringResult(verifyAddedQuestion, "Yes No Question1")[1]) });
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_YesNoquestion_Page.jpg");
			writeToExl(resultMap);
			resultMap.clear();
		} catch (Exception e) {
			resultMap.put("25", new Object[] { map.get("Scenario Name"), "Error while creating Yes or No question",
					"Failed", "Check for screenshot" });
			checkForServerError(map);
			System.out.println("Error: " + e);
			writeToExl(resultMap);
			resultMap.clear();
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_Error in YesNo question.jpg");
		}
	}

	public void createCustomquestion(Map<String, String> map) {
		try {
			visibilityOfElement(btnAddQuestion, 5);
			btnAddQuestion.click();
			visibilityOfElement(questiontitle, 5);
			questiontitle.sendKeys(map.get("Question2Heading"));
			questionDesc.sendKeys(map.get("Question2Description"));
			waitForElement(questionType);
			questionType.click();
			selectdropdown();
			
			
			/*
			 * questionPageHeader.click(); waitForElement(addquestionpref); //
			 * addquestionpref.click(); // waitForElement(addquestionpref); //
			 * addquestionpref.click(); // waitForElement(questionPageHeader);
			 * questionPageHeader.click(); waitForElement(questionPageHeader);
			 * customquestionedit1.sendKeys(map.get("Option1"));
			 * customquestionedit2.sendKeys(map.get("Option2"));
			 * selectRadioBtn(customradiobtn);
			 */
			// waitForElement(btncreatequestion);
			// ((JavascriptExecutor)
			// driver).executeScript("arguments[0].scrollIntoView(true);",
			// btncreatequestion);
			// btncreatequestion.click();
			
			
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", btncreatequestion);
			resultMap.put("25",
					new Object[] { map.get("Scenario Name"), "Add Custom Question",
							(compareStringResult(verifyAddedQuestion1, "Custom Question1")[0]),
							(compareStringResult(verifyAddedQuestion1, "Custom Question1")[1]) });
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_Customquestion_Page.jpg");
			writeToExl(resultMap);
			resultMap.clear();
		} catch (Exception e) {
			resultMap.put("25", new Object[] { map.get("Scenario Name"), "Error while creating custom question",
					"Failed", "Check for Screenshot" });
			checkForServerError(map);
			System.out.println("Error: " + e);
			writeToExl(resultMap);
			resultMap.clear();
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_Error in Custom question.jpg");
		}
	}

	public void createResponceOnlyQuestion(Map<String, String> map) {
		try {
			visibilityOfElement(btnAddQuestion, 5);
			btnAddQuestion.click();
			visibilityOfElement(questiontitle, 8);
			questionType.click();
			selectdropdown();
			waitForElement(questiontitle);
			questiontitle.sendKeys(map.get("Q2HeadTemp"));
			questionDesc.sendKeys(map.get("Q2DescTemp"));

			/*
			 * selectTags.click(); selectdropdown();
			 */

			// waitForElement(btncreatequestion);
			// ((JavascriptExecutor)
			// driver).executeScript("arguments[0].scrollIntoView(true);",
			// btncreatequestion);
			// btncreatequestion.click();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", btncreatequestion);
			resultMap.put("25",
					new Object[] { map.get("Scenario Name"), "Add Responce only question for template",
							(compareStringResult(verifyAddedQuestion1, "Responce question for template")[0]),
							(compareStringResult(verifyAddedQuestion1, "Responce question for template")[1]) });
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_ResponceOnly_Page.jpg");
			writeToExl(resultMap);
			resultMap.clear();
		} catch (Exception e) {
			resultMap.put("25", new Object[] { map.get("Scenario Name"),
					"Error while creating responce only question for template", "Failed", "Check for screenshot" });
			checkForServerError(map);
			System.out.println("Error: " + e);
			writeToExl(resultMap);
			resultMap.clear();
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_Error in responce only question.jpg");
		}
	}

	public void createCustomquestionTemplate(Map<String, String> map) {
		try {
			visibilityOfElement(btnAddQuestion, 5);
			btnAddQuestion.click();
			visibilityOfElement(questiontitle, 8);
			questiontitle.sendKeys(map.get("Q1HeadTemp"));
			questionDesc.sendKeys(map.get("Q1DescTemp"));
			selectTags.click();
			selectdropdown();
			docAttach.click();

			
			/*
			 * waitForElement(addquestionpref); // addquestionpref.click(); //
			 * waitForElement(addquestionpref); // addquestionpref.click(); //
			 * waitForElement(questionPageHeader); questionPageHeader.click();
			 * waitForElement(questionPageHeader);
			 * customquestionedit1.sendKeys(map.get("Option1"));
			 * customquestionedit2.sendKeys(map.get("Option2"));
			 * editBoxWeighting.sendKeys(map.get("TempWeight"));
			 */
			// waitForElement(btncreatequestion);

			// JavascriptExecutor executor = (JavascriptExecutor) driver;
			// executor.executeScript("arguments[0].click();", btncreatequestion);
			
			customquestionedit1.sendKeys(map.get("Option1"));
			customquestionedit2.sendKeys(map.get("Option2"));
			
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", editBoxWeighting);
			
			editBoxWeighting.sendKeys(map.get("TempWeight"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", btncreatequestion);
			resultMap.put("25",
					new Object[] { map.get("Scenario Name"), "Add Custom Question for template",
							(compareStringResult(verifyAddedQuestion, "Custom Question1 for template")[0]),
							(compareStringResult(verifyAddedQuestion, "Custom Question1 for template")[1]) });
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_Customquestion_Page_Template.jpg");
			writeToExl(resultMap);
			resultMap.clear();
		} catch (Exception e) {
			resultMap.put("25", new Object[] { map.get("Scenario Name"),
					"Error while creating custom question for template", "Failed", "Check for screenshot" });
			System.out.println("Error: " + e);
			writeToExl(resultMap);
			resultMap.clear();
			takeSnapShot(driver,
					"Screenshots/" + map.get("Scenario Name") + "_Error in Custom question for template.jpg");
		}
	}

	public void logoutApp(WebElement ele) {
		try {
			visibilityOfElement(ele, 5);
			ele.click();
			waitForElement(btnLogout);
			btnLogout.click();
			waitForElement(teamSelect);
		} catch (Exception e) {
			resultMap.put("25", new Object[] { "Logout Page", "Error while logout", "Failed", "Check for screenshot" });
			System.out.println("Error: " + e);
			writeToExl(resultMap);
			resultMap.clear();
			takeSnapShot(driver, "Screenshots/Logout Page_Error while logout.jpg");
		}
	}

	public void changeAssessmentStatus(WebElement btn, String validString, Map<String, String> map)

	{
		try {
			visibilityOfElement(changestatusdropdown, 8);
			changestatusdropdown.click();
			waitForElement(btn);
			btn.click();
			waitForElement(approveAssessmentconfirmation);
			approveAssessmentconfirmation.click();
			visibilityOfElement(changestatusdropdown, 8);
			resultMap.put("4",
					new Object[] { map.get("Scenario Name"),
							"Validation of status of template as '" + validString + "'",
							(compareStringResult(asmtStatus, validString)[0]),
							(compareStringResult(asmtStatus, validString)[1]) });
			writeToExl(resultMap);
			resultMap.clear();
		} catch (Exception e) {
			resultMap.put("25", new Object[] { map.get("Scenario Name"), "Error while changing assessment stutus",
					"Failed", "Check for screenshot" });
			System.out.println("Error: " + e);
			writeToExl(resultMap);
			resultMap.clear();
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_Error Staus change.jpg");
		}
	}

	public void changeAssessmentStatusPublish(WebElement btn, String validString, boolean publish,
			Map<String, String> map)

	{
		try {
			visibilityOfElement(changestatusdropdown, 8);
			changestatusdropdown.click();
			waitForElement(btn);
			btn.click();
			waitForElement(approveAssessmentconfirmation);
			approveAssessmentconfirmation.click();
			visibilityOfElement(changestatusdropdown, 8);
			if (publish == true) {
				docAttach.click();
				waitForElement(publishBtn);
			}
			publishBtn.click();
			visibilityOfElement(changestatusdropdown, 8);
			resultMap.put("4",
					new Object[] { map.get("Scenario Name"),
							"Validation of status of template as '" + validString + "'",
							(compareStringResult(asmtStatus, validString)[0]),
							(compareStringResult(asmtStatus, validString)[1]) });
			writeToExl(resultMap);
			resultMap.clear();
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_CreateVersionPublishStatus.jpg");
		} catch (Exception e) {
			resultMap.put("25", new Object[] { map.get("Scenario Name"),
					"Error while changing assessment stutus to publish", "Failed", "Check for screenshot" });
			System.out.println("Error: " + e);
			writeToExl(resultMap);
			resultMap.clear();
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_Error Staus change publish.jpg");
		}
	}

	public void createExcel() throws IOException {
		try {
			FileOutputStream fis = new FileOutputStream(new File("Output/Execution Result.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("Result");
			FileOutputStream out = new FileOutputStream("Output/Execution Result.xlsx");
			workbook.write(out);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void createResultHeading() {
		try {
			resultMap.put("1", new Object[] { "TC_Name", "Description", "Status", "Comment" });
			writeToExl(resultMap);
			resultMap.clear();
		} catch (Exception e) {

		}
	}

	public void visibilityofTemplate(Map<String, String> map) {
		try {
			visibilityOfElement(sendAssessmentBtn, 5);
			sendAssessmentBtn.click();
			waitForElement(NewAssesment);
			String formtemplate = null;
			for (int i = 1; i <= chooseTemplate.size(); i++) {
				String str = "(//div[@class='modal-assessment-cards']/div/label)[" + i + "]";
				System.out.println(driver.findElement(By.xpath(str)).getText());
				formtemplate = driver.findElement(By.xpath(str)).getText();
				if (formtemplate.equals(map.get("TemplateName"))) {
					driver.findElement(By.xpath(str)).click();
					resultMap.put("4", new Object[] { map.get("Scenario Name"),
							"Template present on create assessment page ", "Information", "" });
					takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_visibility of template.jpg");
					writeToExl(resultMap);
					resultMap.clear();
				}
			}
		} catch (Exception e) {
			resultMap.put("25", new Object[] { map.get("Scenario Name"), "Error while visibility of template", "Failed",
					"Check for screenshot" });
			System.out.println("Error: " + e);
			writeToExl(resultMap);
			resultMap.clear();
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_Error while visibility of template.jpg");
		}
	}

	public void createAssessmentUsingWgtTemp(Map<String, String> map) {
		try {
			visibilityOfElement(sendAssessmentBtn, 5);
			sendAssessmentBtn.click();
			waitForElement(NewAssesment);
			String formtemplate = null;
			for (int i = 1; i <= chooseTemplate.size(); i++) {
				String str = "(//div[@class='modal-assessment-cards']/div/label)[" + i + "]";
				System.out.println(driver.findElement(By.xpath(str)).getText());
				formtemplate = driver.findElement(By.xpath(str)).getText();
				if (formtemplate.equals(map.get("TemplateName")))
					driver.findElement(By.xpath(str)).click();
			}
			String AssessmentNm = map.get("AssessmentName") + randomNumber();
			map.put("created Assessment", AssessmentNm);

			waitForElement(templateName);
			templateName.clear();
			templateName.sendKeys(AssessmentNm);
			vendorType.sendKeys(map.get("VendorName")); // select vendor here
			formElement.click();
			createAsmtBtn.click();
			visibilityOfElement(asseessmentName, 8);
			resultMap.put("3",
					new Object[] { map.get("Scenario Name"),
							"Creation of assessment using weighted template for '" + AssessmentNm + "'",
							(compareStringResult(asseessmentName, AssessmentNm)[0]),
							(compareStringResult(asseessmentName, AssessmentNm)[1]) });
			changeAssessmentStatus(approveAssessment, "Approved", map);
			changeAssessmentStatus(publishAssessment, "Published", map);
			writeToExl(resultMap);
			resultMap.clear();
			sendAssessmenttab.click();
			visibilityOfElement(btnCopy, 3);
			// waitForElement(btnCopy);
			btnCopy.click();
		} catch (Exception e) {
			resultMap.put("25", new Object[] { map.get("Scenario Name"), "Error while createAssessmentUsingWgtTemp",
					"Failed", "Check for screenshot" });
			System.out.println("Error: " + e);
			writeToExl(resultMap);
			resultMap.clear();
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_Error createAssessmentUsingWgtTemp.jpg");
		}
	}

	public void visibilityOfElement(WebElement element, int timeinSecond) {
		System.out.println("Inside");
		WebDriverWait wait = new WebDriverWait(driver, 60);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			System.out.println("Waiting tims: " + timeinSecond * 1000 + " For element :" + element);
			Thread.sleep(timeinSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void visibilityOfElementAdv(WebElement element, int timeinSecond) {
		try {
			System.out.println("Inside");
			WebDriverWait wait = new WebDriverWait(driver, 60);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(element));
				System.out.println("Waiting tims: " + timeinSecond * 1000 + " For element :" + element);
				Thread.sleep(timeinSecond * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			resultMap.put("2", new Object[] { "Page Load Error",
					"Page loading takes lot of time, hence stopping this test", "Failed", "Check for screenshot" });
			writeToExl(resultMap);
			resultMap.clear();
			takeSnapShot(driver, "Screenshots/Error for page load.jpg");
			throw new AssertionError("Page not loaded for login. Stooping the test");
		}
	}

	public void waitForElement(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void navigateToAuditTab() {
		visibilityOfElement(auditTraitab, 3);
		auditTraitab.click();
		waitForElement(timelineTitle);
	}

	public void verifyAuditTab(Map<String, String> map, String str) {
		resultMap.put("2", new Object[] { map.get("Scenario Name"),
				"Time Line title '" + str + "' is '" + timelineTitle.getText() + "'", "Information", "" });
		writeToExl(resultMap);
		resultMap.clear();
	}

	public void createAssessment(Map<String, String> map, SoftAssert softAssertion) throws Exception {
		try {
			visibilityOfElementAdv(sendAssessmentBtn, 10);
			String vendorTp = map.get("VendorName");// "TestVendor";
			String templatetype = map.get("TemplateType");
			String AssessmentNm = map.get("AssessmentName") + randomNumber();
			map.put("created Assessment", AssessmentNm);
			sendAssessmentBtn.click();
			visibilityOfElement(NewAssesment, 5);

			waitForElement(templateName);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", NewAssesment);
			NewAssesment.click();

			waitForElement(templateName);
			templateName.sendKeys(AssessmentNm);

			vendorType.sendKeys(map.get("VendorName")); // select vendor here
			typeAssessment.sendKeys(templatetype);
			formElement.click();
			createAsmtBtn.click();
			visibilityOfElementAdv(asseessmentName, 8);

			resultMap.put("3",
					new Object[] { map.get("Scenario Name"),
							"Validation of Assessment creation wiith Number as '" + AssessmentNm + "'",
							(compareStringResult(asseessmentName, AssessmentNm)[0]),
							(compareStringResult(asseessmentName, AssessmentNm)[1]) });
			resultMap.put("4",
					new Object[] { map.get("Scenario Name"),
							"Validation of Raw Assessment details as '" + vendorTp + "'",
							(compareStringResult(assessmenttype, vendorTp)[0]),
							(compareStringResult(assessmenttype, vendorTp)[1]) });
			resultMap.put("5",
					new Object[] { map.get("Scenario Name"), "Validation of 'Design Assessment' tab on UI",
							(compareBooleanResultDisplay(designAssessmenttab, true)[0]),
							(compareBooleanResultDisplay(designAssessmenttab, true)[1]) });
			resultMap.put("6",
					new Object[] { map.get("Scenario Name"), "Validation of 'Send Assessment' tab on UI",
							(compareBooleanResultDisplay(sendAssessmenttab, true)[0]),
							(compareBooleanResultDisplay(sendAssessmenttab, true)[1]) });
			resultMap.put("7",
					new Object[] { map.get("Scenario Name"), "Validation of 'Audio trail' tab on UI",
							(compareBooleanResultDisplay(auditTraitab, true)[0]),
							(compareBooleanResultDisplay(auditTraitab, true)[1]) });
			resultMap.put("8",
					new Object[] { map.get("Scenario Name"), "Validation of 'Preview Link' Link on UI",
							(compareBooleanResultDisplay(previewLink, true)[0]),
							(compareBooleanResultDisplay(previewLink, true)[1]) });
			resultMap.put("9",
					new Object[] { map.get("Scenario Name"), "Validation of 'Settings' Link on UI",
							(compareBooleanResultDisplay(settingsLink, true)[0]),
							(compareBooleanResultDisplay(settingsLink, true)[1]) });
			resultMap.put("10",
					new Object[] { map.get("Scenario Name"), "Validation of 'Save As' Link on UI",
							(compareBooleanResultDisplay(saveAsLink, true)[0]),
							(compareBooleanResultDisplay(saveAsLink, true)[1]) });
			resultMap.put("11", new Object[] { map.get("Scenario Name"), "Validation of status of template as 'Draft'",
					(compareStringResult(asmtStatus, "Draft")[0]), (compareStringResult(asmtStatus, "Draft")[1]) });
			writeToExl(resultMap);
			designAssessmenttab.click();
			resultMap.clear();
			takeSnapShot(driver,
					"Screenshots/" + map.get("Scenario Name") + "_CreateAssessmentExistingVendor_Page.jpg");
		} catch (Exception e) {
			resultMap.put("25", new Object[] { map.get("Scenario Name"),
					"Error while creating assessment.Stoping the test", "Failed", "Check the screenshot" });
			System.out.println("Error: " + e);
			writeToExl(resultMap);
			resultMap.clear();
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_Error createAssessment.jpg");
			throw new AssertionError("Error while creating assessment. Stoping the test");
		}
	}

	public void copyLink(Map<String, String> map) {
		try {
			sendAssessmenttab.click();
			visibilityOfElement(btnCopy, 5);
			btnCopy.click();
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_PublishAssessmentAndCopy.jpg");
		} catch (Exception e) {
			resultMap.put("25", new Object[] { map.get("Scenario Name"), "Error while send assessment and copy",
					"Failed", "Check the screenshot" });
			System.out.println("Error: " + e);
			writeToExl(resultMap);
			resultMap.clear();
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_Error copy link.jpg");
		}
	}

	public void validateProof(Map<String, String> map, int valNumber, String proofType, String requiredString) {
		try {
			for (int i = 1; i < 4; i++) {
				String approveval = "//*[@id='container']/ol[" + i + "]/li/article/div/div[3]/div[" + valNumber + "]";
				String Aval = driver.findElement(By.xpath(approveval)).getText();
				if ((valNumber == 1) || ((valNumber == 2)))
					resultMap.put("3",
							new Object[] { map.get("Scenario Name"), proofType + " for number " + i,
									(compareBooleanResult(Aval.equals(requiredString), false)[0]),
									(compareBooleanResult(Aval.equals(requiredString), false)[1]) });
				else
					resultMap.put("4", new Object[] { map.get("Scenario Name"), "Validation status for number" + i,
							(compareStringResult(driver.findElement(By.xpath(approveval)), "Validation: true")[0]),
							(compareStringResult(driver.findElement(By.xpath(approveval)), "Validation: true")[1]) });
				writeToExl(resultMap);
				resultMap.clear();
				takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_AuditTrailDetails.jpg");
			}
		} catch (Exception e) {
			resultMap.put("25", new Object[] { map.get("Scenario Name"), "Error while prrof validation", "Failed",
					"Check the screenshot" });
			System.out.println("Error: " + e);
			writeToExl(resultMap);
			resultMap.clear();
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_Error proof validation.jpg");
		}
	}

	public String getclipboardcontent(Map<String, String> map) throws UnsupportedFlavorException, IOException {
		try {
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Clipboard clipboard = toolkit.getSystemClipboard();
			String result = (String) clipboard.getData(DataFlavor.stringFlavor);
			System.out.println("String from Clipboard:" + result);
			resultMap.put("3",
					new Object[] { map.get("Scenario Name"), "URL to complete the assessment ", result, "" });
			writeToExl(resultMap);
			resultMap.clear();
			return result;
		} catch (Exception e) {
			resultMap.put("25", new Object[] { map.get("Scenario Name"), "Error copy clipboard content", "Failed",
					"Check the screenshot" });
			System.out.println("Error: " + e);
			writeToExl(resultMap);
			resultMap.clear();
			takeSnapShot(driver, "Screenshots/" + map.get("Scenario Name") + "_Error clipboard content.jpg");
			return null;
		}
	}

}
