package testcases;

import java.io.IOException;
import java.util.ArrayList;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.HotelSearchPage;
import utilities.ReadTestData;

public class TC013_Search_ComparisonWithOtherWebsites extends BaseTest {

	@Test
	public static void ComparisonWithOtherWebsites() throws InterruptedException, IOException {
		
		ReadTestData readTD = new ReadTestData();
		String[] columnNames = { "Destination","CheckIn","CheckOut","Adults","Children","Rooms","FreeCancellation","WithComparison" };
		ArrayList<String> testdata = readTD.getTestData("Input_TestData", columnNames, "TC013");
		String inputDestinationName = testdata.get(0);
		String inputCheckInDay = testdata.get(1).substring(0,testdata.get(1).indexOf(","));
		String inputCheckInMonth= testdata.get(1).substring(testdata.get(1).indexOf(",")+2);
		String inputCheckOutDay = testdata.get(2).substring(0,testdata.get(2).indexOf(","));
		String inputCheckOutMonth= testdata.get(2).substring(testdata.get(2).indexOf(",")+2);
		int inputAdultCount = Integer.parseInt(testdata.get(3));
		int inputChildCount = Integer.parseInt(testdata.get(4));
		int inputRoomsCount = Integer.parseInt(testdata.get(5));
		String inputFreeCancellation = testdata.get(6);
		String inputWithComparison = testdata.get(7);
		
		int actualAdultCount = 0;
		int actualChildCount = 0;
		int actualRoomsCount = 0;
		HotelSearchPage hsr = PageFactory.initElements(driver, HotelSearchPage.class);
		hsr.navigateHotelSearch();
		hsr.destinationSpecificCity(inputDestinationName);
		hsr.checkIn(inputCheckInDay, inputCheckInMonth);
		hsr.checkOut(inputCheckOutDay, inputCheckOutMonth);
		actualRoomsCount = hsr.roomsAdd(inputRoomsCount);
		actualAdultCount = hsr.guestsAdultAdd(inputAdultCount, 1);
		actualChildCount = hsr.guestsChildAdd(inputChildCount, 1);			
		hsr.clickGuestRoomApplyBtn();
		boolean actualFreeCancellation = hsr.freeCancellation(inputFreeCancellation);
		String actualWithComparison = hsr.withComparison(inputWithComparison);
		ArrayList<String> acutalWindowsUrl = hsr.search();
		Assert.assertEquals(acutalWindowsUrl.size(), 2);
		logger.info("Test Case Successfully executed");

	}
}
