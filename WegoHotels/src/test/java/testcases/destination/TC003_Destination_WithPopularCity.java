package testcases.destination;

import java.io.IOException;
import java.util.ArrayList;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.HotelSearchPage;
import utilities.ReadTestData;

public class TC003_Destination_WithPopularCity extends BaseTest {

	@Test
	public void destinationWithPopularCity() throws IOException, InterruptedException {
		
		ReadTestData readTD = new ReadTestData();
		String[] columnNames = { "Destination" };
		ArrayList<String> testdata = readTD.getTestData("Input_TestData", columnNames, "TC003");
		String inputDestinationName = testdata.get(0);
		HotelSearchPage hsr = PageFactory.initElements(driver, HotelSearchPage.class);
		hsr.navigateHotelSearch();
		String actualDestinationName = hsr.destinationPopularCity(inputDestinationName);
		Assert.assertTrue(actualDestinationName.contains(inputDestinationName));
	
		logger.info("Test Case Successfully executed");

	}
}