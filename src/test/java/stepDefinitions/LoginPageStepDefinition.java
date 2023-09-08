package stepDefinitions;

import pageObjectModels.LoginPage;
import driverfactory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginPageStepDefinition {
	LoginPage loginpage = new LoginPage(DriverFactory.getDriver());
	
	
	
	@Given("User launches the Pluralsight Clone Application using link {string}")
	public void user_launches_the_pluralsight_clone_application_using_link(String string) {
	   
	}

	@Given("Login form should be visible on launch application URL")
	public void login_form_should_be_visible_on_launch_application_url() {
	  
	}

	@When("User login as {string}")
	public void user_login_as(String string) {
	  
	}

	@Then("Verify Search page has displayed after logging in")
	public void verify_search_page_has_displayed_after_logging_in() {
	   
	}

	@Then("Logout from Pluralsight clone application")
	public void logout_from_pluralsight_clone_application() {
	   
	}
	
	@When("User should enter credentials as {string} and {string}")
	public void user_should_enter_credentials_as_and(String string, String string2) {
	    
		
	}
	
	@Then("Verify login error message as {string}")
	public void verify_login_error_message_as(String string) {
	   
	}
	
}
