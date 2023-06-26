package testcases;

import java.io.IOException;
import java.util.ArrayList;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.HotelSearchPage;
import utilities.ReadTestData;


public class TC008_GuestAndRooms_Max40Guests_5Room extends BaseTest {

	@Test
	public static void guestsAndRoomsMax40Guests5Rooms() throws InterruptedException, IOException {
		
		ReadTestData readTD = new ReadTestData();
		String[] columnNames = { "Destination","CheckIn","CheckOut","Adults","Children","Rooms"};
		ArrayList<String> testdata = readTD.getTestData("Input_TestData", columnNames, "TC008");
		String inputDestinationName = testdata.get(0);
		String inputCheckInDay = testdata.get(1).substring(0,testdata.get(1).indexOf(","));
		String inputCheckInMonth= testdata.get(1).substring(testdata.get(1).indexOf(",")+2);
		String inputCheckOutDay = testdata.get(2).substring(0,testdata.get(2).indexOf(","));
		String inputCheckOutMonth= testdata.get(2).substring(testdata.get(2).indexOf(",")+2);
		int inputAdultCount = Integer.parseInt(testdata.get(3));
		int inputChildCount = Integer.parseInt(testdata.get(4));
		int inputRoomsCount = Integer.parseInt(testdata.get(5));
		
		int actualAdultCount = 0;
		int actualChildCount = 0;
		int actualRoomsCount = 0;
		
		HotelSearchPage hsr = PageFactory.initElements(driver, HotelSearchPage.class);
		hsr.navigateHotelSearch();
		hsr.destinationSpecificCity(inputDestinationName);
		hsr.checkIn(inputCheckInDay, inputCheckInMonth);
		hsr.checkOut(inputCheckOutDay, inputCheckOutMonth);
		for(int room=1;room<=inputRoomsCount;room++) {
			actualRoomsCount = hsr.roomsAdd(room);
			actualAdultCount = hsr.guestsAdultAdd(inputAdultCount + actualAdultCount, room);
			actualChildCount = hsr.guestsChildAdd(inputChildCount+ actualChildCount, room);			
		}
		hsr.clickGuestRoomApplyBtn();

		Assert.assertEquals((actualAdultCount+actualChildCount), 40);
		Assert.assertEquals(actualRoomsCount, 5);
		logger.info("Test Case Successfully executed");

	}
	}
