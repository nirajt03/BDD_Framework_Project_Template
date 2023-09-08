package testrunners;

import static AppHooks.ApplicationHooks.dateMonthYear;
import static AppHooks.ApplicationHooks.driver;
import static AppHooks.ApplicationHooks.monthYear;
import static AppHooks.ApplicationHooks.year;

import java.awt.Desktop;
import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.Comparator;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.service.ExtentService;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
		features=".//src/test/resources/features/",
		//features=".//src/test/resources/features/04 SearchPage.feature",
		glue={"stepDefinitions"},
		dryRun=true,
		//tags="@Custom",
		monochrome = true,
		plugin = {"pretty",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
			}
		)

public class Run extends AbstractTestNGCucumberTests{

	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() {
		try {
			//int thresholdDays = 10;
			//String testClassName = getClassName();
				//ScreenRecorderUtil.startRecord(TypeOfScreen.RegularScreen,testClassName);
				//ScreenRecorderUtil.deleteOlderFilesAndDirectories(thresholdDays, TimeUnit.DAYS,".avi");	
			System.out.println("Screen Recording Started ..!!");
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}

	@BeforeClass
	public void beforeClass() {
		ExtentService.getInstance().setSystemInfo("Application", "Pluralsight");
		ExtentService.getInstance().setSystemInfo("User Name", System.getProperty("user.name"));
		ExtentService.getInstance().setSystemInfo("Environment", "QA");
		ExtentService.getInstance().setSystemInfo("URL", "");
		ExtentService.getInstance().setSystemInfo("OS", System.getProperty("os.name"));
		ExtentService.getInstance().setSystemInfo("OS Version", System.getProperty("os.version"));
		ExtentService.getInstance().setSystemInfo("OS Arch", System.getProperty("os.arch"));
	}

	//	@BeforeMethod
	//	public void beforeMethod() {
	//		System.out.println("In Before Method");
	//	}

	//
	//	@AfterMethod
	//	public void afterMethod() {
	//		System.out.println("In After Method");
	//	}

	@AfterClass
	public void afterClass() {
		System.out.println("In After Class");
		ExtentService.getInstance().setSystemInfo("Browser", getBrowser());
		ExtentService.getInstance().setSystemInfo("Browser Version", getBrowserVersion());
	}

	@AfterSuite(alwaysRun = true)
	public void afterSuite() {
		try {
			//ScreenRecorderUtil.stopRecord();
			//System.out.println("Screen Recording Stopped ..!!");

			//Gets the latest execution report
			File latestHTMLReport = getPathForLatestReport();
			String latestHTMLReportPath = latestHTMLReport.getPath();

			//Preload report on default browser of the system
			Desktop.getDesktop().browse(new File(latestHTMLReportPath).toURI());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get Path For Latest Report
	 * @return  File 
	 */
	public File getPathForLatestReport() {
		String reportPath = System.getProperty("user.dir")+"\\Reports\\ "+year+"\\"+monthYear+"\\"+dateMonthYear+"\\";
		File latestDirectory = getLatestDir(reportPath);
		String latestDirStr = latestDirectory.getPath();
		File latestReport = getTheNewestFile(latestDirStr+"\\","html");		
		return latestReport;
	}

	/**
	 * Get The Newest File : returns latest html report
	 * @param filePath
	 * @param ext
	 * @return  File
	 */
	public File getTheNewestFile(String filePath, String ext) {
		File theNewestFile = null;
		File dir = new File(filePath);
		FileFilter fileFilter = new WildcardFileFilter("*." + ext);
		File[] files = dir.listFiles(fileFilter);
		if (files.length > 0) {
			Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
			theNewestFile = files[0];
		}else {
			throw new NullPointerException("No Files found in the given path");
		}
		return theNewestFile;
	}

	/**
	 * Get Latest Directory
	 * @param dirPath
	 * @return File
	 */
	public File getLatestDir(String dirPath) {		
		File latestDirectory = new File(dirPath);
		File[] listOfFiles = latestDirectory.listFiles();
		File lastModified = Arrays.stream(listOfFiles).filter(File::isDirectory).max(Comparator.comparing(File::lastModified)).orElse(null);
		return lastModified;
	}

	/**
	 * Get Class Name
	 * @return
	 */
	public String getClassName() {
		String packageName = this.getClass().getPackage().getName().trim();
		return packageName;
	}
	
	/**
	 * Returns Browser Name of the Test Environment
	 * @return Browser Name
	 */
	public String getBrowser() {
		Capabilities browserCap = ((RemoteWebDriver) driver).getCapabilities();
		String browserName = browserCap.getBrowserName();
		return browserName;
	}

	/**
	 * Returns Browser version of the Test Environment
	 * @return Browser Version
	 */
	public String getBrowserVersion() {
		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		String version = cap.getBrowserVersion().toString();
		return version;
	}

}