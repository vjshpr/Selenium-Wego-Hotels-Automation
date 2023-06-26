package testcases;

import java.io.IOException;
import java.util.ArrayList;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.HotelSearchPage;
import utilities.ReadTestData;

public class TC002_Destination_WithSpecificCity extends BaseTest {

	@Test
	public void destinationWithSpecificCity() throws IOException, InterruptedException {
		ReadTestData readTD = new ReadTestData();
		String[] columnNames = { "Destination" };
		ArrayList<String> testdata = readTD.getTestData("Input_TestData", columnNames, "TC002");
		String inputDestinationName = testdata.get(0);
		HotelSearchPage hsr = PageFactory.initElements(driver, HotelSearchPage.class);
		hsr.navigateHotelSearch();
		String actualDestinationName = hsr.destinationSpecificCity(inputDestinationName);
		Assert.assertTrue(actualDestinationName.contains(inputDestinationName));

	}

}
