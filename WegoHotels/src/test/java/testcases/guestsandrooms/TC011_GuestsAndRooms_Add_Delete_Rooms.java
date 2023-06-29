package testcases.guestsandrooms;

import java.io.IOException;
import java.util.ArrayList;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.HotelSearchPage;
import utilities.ReadTestData;

public class TC011_GuestsAndRooms_Add_Delete_Rooms extends BaseTest {

	@Test
	public static void addDeleteGuestsAndRooms() throws InterruptedException, IOException {
		
		ReadTestData readTD = new ReadTestData();
		String[] columnNames = { "Destination","CheckIn","CheckOut","Adults","Children","Rooms"};
		ArrayList<String> testdata = readTD.getTestData("Input_TestData", columnNames, "TC011");
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
		hsr.guestsAdultAdd(7, 1);
		hsr.guestsChildAdd(5, 1);
		hsr.roomsAdd(5);
		actualRoomsCount = hsr.roomsRemove(inputRoomsCount);
		hsr.clickGuestRoomApplyBtn();
		Assert.assertEquals(actualRoomsCount, inputRoomsCount);
		logger.info("Test Case Successfully executed");

	}
}
