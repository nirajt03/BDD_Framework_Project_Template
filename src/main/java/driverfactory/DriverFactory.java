package driverfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	public WebDriver driver;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	/**
	 * This method is used to initialize the thradlocal driver on the basis of given
	 * browser
	 * 
	 * @param browser
	 * @return this will return tldriver.
	 */
	public WebDriver init_driver(String browser) {

		System.out.println("browser value is: " + browser);

		WebDriver driver = null;
		if (browser.equalsIgnoreCase("Chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			options.addArguments("--incognito");
			//options.addArguments("--remote-allow-origins=*");	
			options.setBinary("116");
			//options.setBrowserVersion("116");

			//Map<String, Object> prefs = new HashMap<>();
			// enable/disable mic or camera permissions
			//prefs.put("profile.default_content_setting_values.media_stream_mic", 2);
			//value "1" is used for allowing the option, "2" -- for blocking.
			//prefs.put("profile.default_content_setting_values.media_stream_camera", 1);
			//options.setExperimentalOption("prefs", prefs);

			driver = new ChromeDriver(options);
		} else if (browser.equalsIgnoreCase("Firefox")) {
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("-private");
			driver = new FirefoxDriver(options);
		} else if (browser.equalsIgnoreCase("Edge")) {
			EdgeOptions options = new EdgeOptions();
			options.addArguments("-private");
			driver = new EdgeDriver(options);
		} else {
			System.out.println("Please pass the correct browser value. Browser passed was : " + browser);
		}

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		return driver;

	}

	/**
	 * this is used to get the driver with ThreadLocal
	 * 
	 * @return
	 */
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
}
