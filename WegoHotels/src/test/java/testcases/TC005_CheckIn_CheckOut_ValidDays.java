package testcases;

import java.io.IOException;
import java.util.ArrayList;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.HotelSearchPage;
import utilities.ReadTestData;

public class TC005_CheckIn_CheckOut_ValidDays extends BaseTest {

	@Test
	public static void checkInCheckOutValidDays() throws IOException, InterruptedException {
		
		ReadTestData readTD = new ReadTestData();
		String[] columnNames = { "Destination","CheckIn","CheckOut" };
		ArrayList<String> testdata = readTD.getTestData("Input_TestData", columnNames, "TC005");
		String inputDestinationName = testdata.get(0);
		String inputCheckInDay = testdata.get(1).substring(0,testdata.get(1).indexOf(","));
		String inputCheckInMonth= testdata.get(1).substring(testdata.get(1).indexOf(",")+2);
		String inputCheckOutDay = testdata.get(2).substring(0,testdata.get(2).indexOf(","));
		String inputCheckOutMonth= testdata.get(2).substring(testdata.get(2).indexOf(",")+2);
		
		HotelSearchPage hsr = PageFactory.initElements(driver, HotelSearchPage.class);
		hsr.navigateHotelSearch();
		hsr.destinationSpecificCity(inputDestinationName);
		String checkInDateFound = hsr.checkIn(inputCheckInDay, inputCheckInMonth);
		String checkOutDateFound = hsr.checkOut(inputCheckOutDay, inputCheckOutMonth);
		Assert.assertTrue(checkInDateFound.equals("Date Selected"));
		Assert.assertTrue(checkOutDateFound.equals("Date Selected"));
		logger.info("Test Case Successfully executed");

	}
	
	
}
