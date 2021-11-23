package org.openmrs.kenyaemr.contrib.qaframework.automation;

import static org.junit.Assert.assertTrue;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openmrs.kenyaemr.contrib.qaframework.RunTest;
import org.openmrs.kenyaemr.contrib.qaframework.automation.page.HomePage;
import org.openmrs.kenyaemr.contrib.qaframework.automation.page.LoginPage;
import org.openmrs.kenyaemr.contrib.qaframework.automation.test.TestBase;

public class LoginSteps extends TestBase {
	
	private LoginPage loginPage;
	
	private HomePage homePage;
	
	@After(RunTest.HOOK.LOGIN)
	public void destroy() {
		quit();
	}
	
	@Before(RunTest.HOOK.LOGIN)
	public void setLoginPage() {
		System.out.println(".... User Login......");
		loginPage = new LoginPage(getWebDriver());
	}
	
	@Given("User visits login page")
	public void visitLoginPage() throws Exception {
		loginPage.go();
	}
	
	@When("User enters {string} username")
	public void anyUsername(String username) {
		loginPage.enterUsername(username);
	}
	
	@And("User enters {string} password")
	public void anyPassword(String password) {
		loginPage.enterPassword(password);
	}
	
	@And("User Logs in")
	public void userLogsIn() {
		loginPage.clickLoginButton();
	}
	
	@Then("System Evaluates Login {string}")
	public void evaluateLogin(String status) {
		homePage = new HomePage(loginPage);
		if (status.trim().endsWith("true")) {
			assertTrue(textExists("Log Out"));
		} else if (status.trim().endsWith("false")) {
			assertTrue(textExists("Home"));
		}
	}	
}