package testcases.checkincheckout;

import java.io.IOException;
import java.util.ArrayList;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.HotelSearchPage;
import utilities.ReadTestData;

public class TC004_CheckIn_PastDays extends BaseTest {

	@Test
	public static void checkInPastDays() throws InterruptedException, IOException {
		ReadTestData readTD = new ReadTestData();
		String[] columnNames = { "Destination","CheckIn","CheckOut" };
		ArrayList<String> testdata = readTD.getTestData("Input_TestData", columnNames, "TC004");
		String inputDestinationName = testdata.get(0);
		String inputCheckInDay = testdata.get(1).substring(0,testdata.get(1).indexOf(","));
		String inputCheckInMonth= testdata.get(1).substring(testdata.get(1).indexOf(",")+2);
		
		HotelSearchPage hsr = PageFactory.initElements(driver, HotelSearchPage.class);
		hsr.navigateHotelSearch();
		hsr.destinationSpecificCity(inputDestinationName);
		String checkInDateFound = hsr.checkIn(inputCheckInDay, inputCheckInMonth);
		Assert.assertTrue(checkInDateFound.equals("Date is disabled"));
		logger.info("Test Case Successfully executed");

	}

}
