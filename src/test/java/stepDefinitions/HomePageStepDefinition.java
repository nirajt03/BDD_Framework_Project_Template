package stepDefinitions;

import static org.testng.Assert.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.Status;

import driverfactory.DriverFactory;
import helperTestUtility.ReportLogs;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjectModels.HomePage;
import pageObjectModels.LoginPage;

public class HomePageStepDefinition {
	
	public static final Logger logger = LogManager.getLogger(HomePageStepDefinition.class);
	LoginPage loginpage = new LoginPage(DriverFactory.getInstance().getDriver());
	HomePage homePage;

	@Given("User needs to navigate to home page")
	public void user_needs_to_navigate_to_home_page() {
		homePage = loginpage.moveToHomePage();
		ReportLogs.addLogWithScreenshot(Status.INFO,"Successfully moved to Home Page");
	}
	
	@When("Home Page must be visible to user")
	public void home_page_must_be_visible_to_user() {
	   homePage.waitForHomePageToBeVisible();
	}
	
	@Then("Verify Home page header text as {string}")
	public void verify_home_page_header_text(String expHomePageHeader) {
		String headerText =  homePage.getHomePageHeader();
		headerText = headerText.replaceAll("\\r\\n|\\r|\\n", " ");
		ReportLogs.addLogForStringComparison(headerText, expHomePageHeader,"Home Page Header Text");
		assertEquals(headerText, expHomePageHeader, "Failed to assert Home Page header text");
	}
	
	@Then("Verify Home page description text as {string}")
	public void verify_home_page_description_text(String expHomePageDesc) {
		String descriptionText =  homePage.getHomePageDescription();
		descriptionText = descriptionText.replaceAll("\\r\\n|\\r|\\n", " ");
		ReportLogs.addLogForStringComparison(descriptionText, expHomePageDesc,"Home Page Description Text");

		//assertEquals(descriptionText.contains(expHomePageDesc), "Failed to assert Home Page description text");
		assertEquals(descriptionText, expHomePageDesc, "Failed to assert Home Page description text");
		ReportLogs.addLogWithScreenshot(Status.INFO, "Home Page Verification");
	}
	
}
