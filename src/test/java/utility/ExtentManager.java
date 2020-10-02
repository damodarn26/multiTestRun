package utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentManager {

	private static ExtentReports report;

	public static ExtentReports createInstance(String fileName) {
		
		ExtentHtmlReporter extent = new ExtentHtmlReporter(fileName);
        report = new ExtentReports();
        report.attachReporter(extent);

		return report;
	}

}
