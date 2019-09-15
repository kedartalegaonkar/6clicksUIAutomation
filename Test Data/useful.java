// Publish Assessment Template
login as 'sellerTenant'

status:
1) create assessment : 90% complete
2) Create weighted template : need tp test
3) 

((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(ele)));

@FindBy(xpath="//input[@placeholder='Headline']") WebElement editHeadLine;
@FindBy(xpath="//input[@placeholder='Price']") WebElement editPrice;
SelectDropdown // for tags
@FindBy(id="descriptionEditor_rte-edit-view") WebElement editShortDescription;
@FindBy(xpath="//a/span[contains(text(),'Editions')]") WebElement linkEditions;
@FindBy(xpath="//ng-component/div/div/div[1]/div[3]/a") WebElement btnCreateEditions;
@FindBy(id="EditionDisplayName") WebElement editionName;
@FindBy(id="EditEdition_IsPaid") WebElement radioBtnPaid; 
@FindBy(id="MonthlyPrice") WebElement editMonthlyPrice;
@FindBy(id="AnnualPrice") WebElement editAnnualPrice;
@FindBy(xpath="//button[@class='btn btn-primary']") WebElement BtnSaveEditions;
@FindBy(xpath="//div[@class='MarketplaceItemCardHead']") List<WebElement> listMarketPlace;

Payment history


@FindBy(xpath="//span[contains(text(),'Marketplace')]") WebElement linkMarketPlace;
@FindBy(xpath="//button[contains(text(),'Buy now')]") WebElement btnBuyNow;  
@FindBy(xpath="//button[contains(text(),'Checkout')]") WebElement btnCheckout;  
@FindBy(xpath="//button[contains(text(),'Purchase')]") WebElement btnPurchase;
@FindBy(name="cardnumber") WebElement cardnumber;
@FindBy(name="exp-date") WebElement expdate;
@FindBy(name="cvc") WebElement cvcDetail;
@FindBy(id="stripeCheckout") WebElement stripeCheckout;
@FindBy(xpath="//div[@class='m-portlet__head-title']") WebElement titleSubscription;
@FindBy(xpath="//table[1]/tbody/tr/td[1]") List<WebElement> subscriptionInfo;
@FindBy(xpath="//div[contains(text(),'Payment history')]") WebElement paymentHistory;
@FindBy(xpath="//table[1]/tbody/tr/td[3]") List<WebElement> paymentHistoryInfo;

@FindBy(name="Password") changePassword;	
@FindBy(name="PasswordRepeat") changePasswordRepeat;

Defaultuser3664  - service user ---> Team1ForCIS  --> 
Defaultuser3664
Defaultuser6743
myTemplate8272  -- Team2ForCIS

Defaultuser7829\

Extra time on adduser and add action after click on view result --- warning
javascript executor on documnet completion --- warning
domain name on remediation -- warning

Clients,Client: , (Third Parties,Assessee:)

//*[@id="mat-dialog-0"]/app-choicemodal/form/div[2]/div[4]/div[1]/span

/html/body/app-root/ng-component/div/default-layout/div/div/div[3]/ng-component/div/div/div[1]/div[1]/div/div/span
//*[@id="gridpayment_content_table"]/tbody/tr[3]/td[3]

resultMap.put("3", new Object[] {"Create Template", "Validation of status of template",(compareStringResult(asmtStatus.getText(), "Draft")[0]),(compareStringResult(asmtStatus.getText(), "Draft")[1])});

resultMap.put("2", new Object[] {"Create User", "Validation of Default user creation", (compareBooleanResult(editUserFormActiveChkBox.isSelected(),true)[0]),(compareBooleanResult(editUserFormActiveChkBox.isSelected(),true)[1])});

writeToExl(resultMap);
resultMap.clear();


@FindBy(xpath="//span[contains(text(),'Tenants')]") WebElement teanantslink;
@FindBy(xpath="//a[contains(text(),'Create new tenant')]") WebElement btnCreateNewTenant;  
use 
'tenantLink'  for create new tenant linkEditions
EditTeamName   for team name

/html/body/app-root/ng-component/div/default-layout/div/div/div[3]/ng-component/div/createtenantmodal/div/div/div/

//*[@id="menuItem"]/a/span[contains(text(),'Settings')]  Defaultuser6270

//*[@id="e-content_1"]/div/div[3]/div[2]/div/div/div[1]/div/div[4]/input
//form/div[2]/div[4]/label/span
//form/div[2]/div[7]/label[1]/span





/html/body/app-root/ng-component/div/default-layout/div/div/div[3]/ng-component/div/div/div[1]/div[3]/a






 

(//div[@class='MarketplaceItemHeading'])[1]
	  /*loginpage.visibilityOfElement(loginpage.sendAssessmentBtn,5);	  
	  loginpage.sendAssessmentBtn.click();
	  
	  loginpage.waitForElement(loginpage.NewAssesment);  
	  
	  String formtemplate=null;
	  for (int i = 1; i <= loginpage.chooseTemplate.size(); i++) 
	  {
		  String str="(//div[@class='modal-assessment-cards']/div/label)["+i+"]";
		  System.out.println(driver.findElement(By.xpath(str)).getText());
		  formtemplate=driver.findElement(By.xpath(str)).getText();
		  if(formtemplate.equals(map.get("TemplateName")))		  
		  driver.findElement(By.xpath(str)).click();	  
	  }
	  String AssessmentNm=map.get("AssessmentName")+loginpage.randomNumber();
	  map.put("created Assessment", AssessmentNm);
		 
		 loginpage.waitForElement(loginpage.templateName);
		 loginpage.templateName.clear();
		 loginpage.templateName.sendKeys(AssessmentNm);
		
	  	loginpage.vendorType.sendKeys(map.get("VendorName"));  // select vendor here	  	
	  	loginpage.formElement.click();		
	  	loginpage.createAsmtBtn.click();
	  	
	  	loginpage.visibilityOfElement(loginpage.asseessmentName,8); 	
	  	
	  	loginpage.changeAssessmentStatus(loginpage.approveAssessment,"Approved");
		loginpage.changeAssessmentStatus(loginpage.publishAssessment,"Published" );
		
		loginpage.sendAssessmenttab.click();
		loginpage.waitForElement(loginpage.btnCopy);

		loginpage.btnCopy.click();







