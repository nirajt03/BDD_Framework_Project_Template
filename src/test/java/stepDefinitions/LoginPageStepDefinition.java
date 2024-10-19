package stepDefinitions;

import static org.testng.Assert.assertEquals;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.Status;

import driverfactory.DriverFactory;
import helperTestUtility.ReportLogs;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjectModels.LoginPage;
import pageObjectModels.SearchPage;

public class LoginPageStepDefinition {

	public static final Logger logger = LogManager.getLogger(LoginPageStepDefinition.class);
	LoginPage loginpage = new LoginPage(DriverFactory.getInstance().getDriver());
	SearchPage searchpage;

	@Given("User launches the Pluralsight Clone Application using link {string}")
	public void user_launches_the_pluralsight_clone_application_using_link(String url) {
		DriverFactory.getInstance().getDriver().get(url);
	}

	@Given("Login form should be visible on launch application URL")
	public void login_form_should_be_visible_on_launch_application_url() {
		loginpage.waitForLoginFormToBeVisible();
		ReportLogs.addLog(Status.INFO, "Login Form is Visible");
	}

	@When("User login as {string}")
	public void user_login_as(String authority) {
		searchpage = loginpage.loginToPluralsightApplication(authority);
		ReportLogs.addLog(Status.INFO, "User Logged in as "+authority);
	}

	@Then("Verify Search page has displayed after login and verify search box text as {string}")
	public void verify_search_page_has_displayed_after_login_and_verify_search_box_text_as(String expSearchText) {
		String searchText = loginpage.getSearchPlaceholderText();
		ReportLogs.addLogForStringComparison(searchText, expSearchText,"Search Text Box Text");
		//Assert.assertFalse(true);
		assertEquals(searchText, expSearchText,"Failed to assert Search Text of Search Box placeholder");
		ReportLogs.addLogWithScreenshot(Status.INFO,"Verified login functionality");
	}

	@Then("Logout from Pluralsight clone application")
	public void logout_from_pluralsight_clone_application() {
		loginpage = loginpage.logoutFromPluralsightApplication();
		ReportLogs.addLog(Status.INFO,"Successfully logged out of Pluralsight Application");	
	}

	@When("User should enter credentials as {string} and {string}")
	public void user_should_enter_credentials_as_and(String username, String password) {
		loginpage.checkNegativeLoginScenarios(username, password);
		ReportLogs.addLogWithScreenshot(Status.INFO, "Checked Negative Login Scenario for Pluralsight Application");

	}

	@Then("Verify login error message as {string}")
	public void verify_login_error_message_as(String errorMessage) {
		String loginErrorMsg = loginpage.getLoginErrorText();
		ReportLogs.addLogForStringComparison(loginErrorMsg, errorMessage,"Validated login error message");
		//Assert.assertFalse(true);
		assertEquals(loginErrorMsg, errorMessage, "Failed to assert Login Error message for nagative scenario");
	}

//	@Then("User should enter other credentials as Username and Password")
//	public void user_should_enter_other_credentials_as_username_and_password(DataTable userCredentials) {
//		for(Map<String, String> data : userCredentials.asMaps(String.class, String.class)) {
//			loginpage.checkNegativeLoginScenarios(data.get("Username"), data.get("Password"));
//			ReportLogs.addLogWithScreenshot(Status.INFO, "Checked Negative Login Scenario for Pluralsight Application");
//		}
//	}
//	
//	@Then("Verify login error message as Error Message")
//	public void verify_login_error_message_as_error_message(DataTable errorMessages) {
//		for(Map<String, String> data : errorMessages.asMaps(String.class, String.class)) {
//			String loginErrorMsg = loginpage.getLoginErrorText();
//			ReportLogs.addLogForStringComparison(loginErrorMsg, data.get("Error Message"),"Validated login error message");
//			assertEquals(loginErrorMsg, data.get("Error Message"), "Failed to assert Login Error message for nagative scenario");
//		}
//	}
	
	@Then("User should enter other credentials as Username and Password, verify login error message as Error Message")
	public void user_should_enter_other_credentials_as_username_and_password_verify_login_error_message_as_error_message(DataTable userCredentials) {
		for(Map<String, String> data : userCredentials.asMaps(String.class, String.class)) {
			loginpage.checkNegativeLoginScenarios(data.get("Username"), data.get("Password"));
			ReportLogs.addLogWithScreenshot(Status.INFO, "Checked Negative Login Scenario for Pluralsight Application");
			String loginErrorMsg = loginpage.getLoginErrorText();
			ReportLogs.addLogForStringComparison(loginErrorMsg, data.get("Error Message"),"Validated login error message");
			assertEquals(loginErrorMsg, data.get("Error Message"), "Failed to assert Login Error message for nagative scenario");
		}
	}


}
