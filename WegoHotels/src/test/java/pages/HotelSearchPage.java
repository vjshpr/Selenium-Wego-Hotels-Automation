package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BaseTest;

public class HotelSearchPage extends BaseTest{
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	int roomSize=1;
	@FindBy(xpath="//a[@href='/hotels']") WebElement hotelTab;
	@FindBy(how=How.CSS, using="#destinationSearchQuery") WebElement destination;
	@FindBy(xpath="//div[@class='wHTh6cwAapGfkWAeHKO0']/div/div[2]/div/div/div") List <WebElement> allSearchPlaces;
	@FindBy(xpath="//div[@class='DqKau3x4f__kxGIMdsjE']/div/div") List <WebElement> allPopularCities;
	@FindBy(xpath="//div[@class='wHTh6cwAapGfkWAeHKO0']/div/div") WebElement searchList;
	@FindBy(css=".FY9RtgdPmmBEwcA3Pfqz") WebElement checkInBox;
	@FindBy(css=".rEbH14PcQ_dr5fc01KJZ") WebElement checkOutBox;
	@FindBy(xpath="//div[contains(@class,'FY9RtgdPmmBEwcA3Pfqz')]//div[@class='vSZlELYMnb5m1ZWm5i8Y']") WebElement checkInBoxText;
	@FindBy(xpath="//div[contains(@class,'rEbH14PcQ_dr5fc01KJZ')]//div[@class='vSZlELYMnb5m1ZWm5i8Y']") WebElement checkOutBoxText;
	@FindBy(xpath="//div[@class='j0wcKAjAJNcOV6lKR2tM']/div/div[2]/div/div[3]/div[1]/div[1]") WebElement monthTitle;
	@FindBy(xpath="//div[@class='j0wcKAjAJNcOV6lKR2tM']/div/div[2]/div/div[2]") WebElement nextMonth;
	@FindBy(xpath="//div[@class='j0wcKAjAJNcOV6lKR2tM']/div/div[2]/div/div[1]") WebElement previousMonth;
	@FindBy(xpath="//div[@class='j0wcKAjAJNcOV6lKR2tM']/div/div[2]/div/div[3]") WebElement calendarSection;
	@FindBy(xpath="//div[@class='j0wcKAjAJNcOV6lKR2tM']/div/div[2]/div/div[3]/div[1]/div[3]/div") List <WebElement> allDates;
	@FindBy(css=".SYfbHoSRdiQcDSHSJnXZ") WebElement guestsAndRoomBtn;
	@FindBy(xpath="//div[@class='vtVp78TxrwXGkme_znUC']/div/div[2]/div[2]/div") List <WebElement> adultCount;
	@FindBy(xpath="//div[@class='vtVp78TxrwXGkme_znUC']/div[1]/div[2]/div[2]/button[2]") WebElement adultAdd;
	@FindBy(xpath="//div[@class='vtVp78TxrwXGkme_znUC']/div[1]/div[2]/div[2]/button[1]") WebElement adultRemove;
	@FindBy(xpath="//div[@class='vtVp78TxrwXGkme_znUC']/div/div[2]/div[4]/div") List <WebElement> childCount;
	@FindBy(xpath="//div[@class='vtVp78TxrwXGkme_znUC']/div[1]/div[2]/div[4]/button[2]") WebElement childAdd;
	@FindBy(xpath="//div[@class='vtVp78TxrwXGkme_znUC']/div[1]/div[2]/div[4]/button[1]") WebElement childRemove;
	@FindBy(xpath="//div[@class='vtVp78TxrwXGkme_znUC']/div/div[1]/div") List <WebElement> roomsCount;
	@FindBy(xpath="//div[@class='SQOdmOkvQkUo9dSrI2WA']/button[1]") WebElement addRoom;
	@FindBy(xpath="//div[@class='vtVp78TxrwXGkme_znUC']/div/div/button[contains(text(),'Remove')]") WebElement removeRoom;
	@FindBy(xpath="//div[@class='SQOdmOkvQkUo9dSrI2WA']/button[2]") WebElement applyBtn;
	@FindBy(xpath="//button[@type='submit']") WebElement searchBtn;
	@FindBy(xpath="//div[@class='bD3ZHYjLUdMwGUWftoRi']/div") WebElement freeCancellation;
	@FindBy(xpath="//div[@class='LgFGV5NNk2Kb0IYXu4GT']/div") WebElement withComparison;
	@FindBy(xpath="//*[contains(text(),'Booking.com')]") WebElement bookingDotComWeb;
	@FindBy(xpath="//div[@class='SPkmApjaoGraMIPsUOF7 q74k4FKULmYIfbeouAKs A9yZJJ9J6SGbQMxHYKmG']") WebElement freeCancellationCheckBox;
	@FindBy(xpath="//button[@type='submit']") WebElement searchResults;
	
	
	public String navigateHotelSearch() {
		hotelTab.click();
		logger.info("Navigated to hotels tab");
		return driver.getTitle();
	}
	
	public String destinationSpecificCity(String inputDestinationName) throws InterruptedException{
		destination.click();
		destination.clear();
		destination.sendKeys(inputDestinationName);
		logger.debug("Entered destination name");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@class='wHTh6cwAapGfkWAeHKO0']/div/div[2]/div[1]/div/div[1]"))));
		logger.debug("Waiting for destination textbox element");
		String destinationName = null;
		for (WebElement elem:allSearchPlaces) {
			logger.info("Searching correct destination.. " + "actual: "+ inputDestinationName + "current: "+elem.getText());
			String searchPlace=elem.getText();
			String finalSearchPlace[] = searchPlace.split("\n");
			if(finalSearchPlace[0].contains(inputDestinationName)) {
				destinationName = elem.getText();
				elem.click();
				logger.info("Found correct destination name and clicked successfully");
				break;
			}	
		}
		return destinationName;
	}


	public String destinationPopularCity(String inputDestinationName) throws InterruptedException{
		destination.click();
		destination.clear();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@class='DqKau3x4f__kxGIMdsjE']/div[1]"))));
		logger.debug("Entered destination name");

		String destinationName = null;
		for (WebElement elem:allPopularCities) {
			logger.info("Searching correct destination.. " + "actual: "+ inputDestinationName + "current: "+elem.getText());

			String searchPlace=elem.getText();
			String finalSearchPlace[] = searchPlace.split("\n");
			if(finalSearchPlace[0].contains(inputDestinationName)) {
				destinationName = elem.getText();
				elem.click();
				logger.info("Found correct destination name and clicked successfully");

				break;
			}	
		}
		return destinationName;
	}
	public String checkIn(String inputCheckInDay, String inputCheckInMonth) {
		checkInBox.click();
		String checkInDateEnabled = "Not Available";
		if(inputCheckInMonth!=null && inputCheckInDay!=null) {
		while(true) {
			if(monthTitle.getText().equalsIgnoreCase(inputCheckInMonth)) {
				logger.info("Found correct input Month");
				break;
			}
			else {
				nextMonth.click();
				logger.info("Input Month not found, clicking on second month");

			}
		}
		
		for(WebElement ele:allDates) {
			if(ele.getText().equalsIgnoreCase(inputCheckInDay)) {
				ele.click();
				if(checkInBoxText.getText().isBlank()) {
					checkInDateEnabled = "Date is disabled";
					logger.info("CheckIn field is blank");

					break;
				}
				else
				{
					checkInDateEnabled = "Date Selected";
					logger.info("Check in field is selected correctly");

					break;
				}
		}
		}
		}
		else {
			 checkInDateEnabled = "Not Avaialble";
				logger.info("Check In field is blank");

		}
		logger.info("Check Info returned: " + checkInDateEnabled);

		return checkInDateEnabled;
		
	}
	
	public String checkOut(String inputCheckOutDay, String inputCheckOutMonth) {
		checkOutBox.click();	
		String checkOutDateEnabled = "Not Avaialble";
		if(inputCheckOutDay!=null && inputCheckOutMonth!=null) {
		while(true) {
			if(monthTitle.getText().equalsIgnoreCase(inputCheckOutMonth)) {
				logger.info("CheckOut Month found");

				break;
			}
			else {
				logger.info("CheckOut month not found, clicking on next month");

				nextMonth.click();
			}
		}
		
		for(WebElement ele:allDates) {
			if(ele.getText().equalsIgnoreCase(inputCheckOutDay)) {
				ele.click();
				if(checkOutBoxText.getText().isBlank()) {
					checkOutDateEnabled = "Date is disabled";
					break;
				}
				else
				{
					checkOutDateEnabled = "Date Selected";
					break;
				}

			}

		}
		}
		else
		{
			checkOutDateEnabled = "Not Available";
			
		}
		return checkOutDateEnabled;
	}
	
	public int guestsAdultAdd(int inputAdultCount, int inputRoomsCount) {
		if(adultAdd.isDisplayed()==false) {
			guestsAndRoomBtn.click();
		}
		String addAdultXpath = "//div[@class='vtVp78TxrwXGkme_znUC']/div["+inputRoomsCount+"]/div[2]/div[2]/button[2]";
		WebElement adultAdd = driver.findElement(By.xpath(addAdultXpath));
		wait.until(ExpectedConditions.elementToBeClickable(adultAdd));
		
		int actualAdultCount = addCount(adultCount);
		while(actualAdultCount<inputAdultCount) {
			adultAdd.click();
			actualAdultCount = addCount(adultCount);
		}
		actualAdultCount = addCount(adultCount);
		////applyBtn.click();
		return actualAdultCount;
		
	}
	

	
	public int guestsAdultRemove(int inputAdultCount, int inputRoomsCount) {
		if(adultAdd.isDisplayed()==false) {
			guestsAndRoomBtn.click();
		}
		String addAdultXpath = "//div[@class='vtVp78TxrwXGkme_znUC']/div["+inputRoomsCount+"]/div[2]/div[2]/button[@class='SWDCPhuVHQoCVBy7_q8W']";
		WebElement adultRemove = driver.findElement(By.xpath(addAdultXpath));
		wait.until(ExpectedConditions.elementToBeClickable(adultRemove));
		
		int actualAdultCount = addCount(adultCount);
		while(actualAdultCount>inputAdultCount) {
			wait.until(ExpectedConditions.elementToBeClickable(adultRemove));
			adultRemove.click();
			actualAdultCount = addCount(adultCount);
		}
		actualAdultCount = addCount(adultCount);
		////applyBtn.click();
		return actualAdultCount;
		
	}
	
	public int guestsChildAdd(int inputChildCount, int inputRoomsCount) {
		if(childAdd.isDisplayed()==false) {
			guestsAndRoomBtn.click();
		}
		String addChildXpath = "//div[@class='vtVp78TxrwXGkme_znUC']/div["+inputRoomsCount+"]/div[2]/div[4]/button[2]";
		WebElement childAdd = driver.findElement(By.xpath(addChildXpath));
		
		wait.until(ExpectedConditions.elementToBeClickable(childAdd));
		int actualChildCount = addCount(childCount);		
		while(actualChildCount<inputChildCount) {
			childAdd.click();
			actualChildCount = addCount(childCount);
		}
		actualChildCount = addCount(childCount);
		return actualChildCount;	
	}
	
	public int guestsChildRemove(int inputChildCount, int inputRoomsCount) {
		if(childAdd.isDisplayed()==false) {
			guestsAndRoomBtn.click();
		}
		String addChildXpath = "//div[@class='vtVp78TxrwXGkme_znUC']/div["+inputRoomsCount+"]/div[2]/div[4]/button[1]";
		WebElement childRemove = driver.findElement(By.xpath(addChildXpath));
		
		wait.until(ExpectedConditions.elementToBeClickable(childRemove));
		int actualChildCount = addCount(childCount);		
		while(actualChildCount>inputChildCount) {
			childRemove.click();
			actualChildCount = addCount(childCount);
		}
		actualChildCount = addCount(childCount);
		return actualChildCount;		
	}
	
	public int roomsAdd(int inputRoomsCount) {
		if(addRoom.isDisplayed()==false) {
			guestsAndRoomBtn.click();
		}
		
		wait.until(ExpectedConditions.elementToBeClickable(addRoom));
		
		int currentRoomsCount = roomsCount.size();
		while(currentRoomsCount<inputRoomsCount) {
			addRoom.click();
			currentRoomsCount = roomsCount.size();
		}
		
		currentRoomsCount = roomsCount.size();
		return currentRoomsCount;
	}
	
	public int roomsRemove(int inputRoomsCount) {
		if(addRoom.isDisplayed()==false) {
			guestsAndRoomBtn.click();
		}
		wait.until(ExpectedConditions.elementToBeClickable(addRoom));
		
		String removeRoomXpath ; 
		WebElement removeRoom;
		int currentRoomsCount = roomsCount.size();
		while(currentRoomsCount>inputRoomsCount) {
			removeRoomXpath = "//div[@class='vtVp78TxrwXGkme_znUC']/div["+inputRoomsCount+"]/div/button[contains(text(),'Remove')]";
			removeRoom = driver.findElement(By.xpath(removeRoomXpath));
			removeRoom.click();
			currentRoomsCount = roomsCount.size();
		}
		currentRoomsCount = roomsCount.size();;
		return currentRoomsCount;
	}
	
	public boolean freeCancellation(String inputFreeCancellation) {
		
		wait.until(ExpectedConditions.elementToBeClickable(freeCancellation));
		
		if(inputFreeCancellation.equalsIgnoreCase("Yes")){
			
			String fcClassAttribute = freeCancellation.getAttribute("class");
			String[] fcString = fcClassAttribute.split(" ");
			
			if(fcString.length == 2) {
				freeCancellation.click();
				return true;
			}
		}
			else {
				return false;
			}
		return false;
			
	}
	
	public String withComparison(String withComparisonValue) {
		
		String withCompareValue = withComparison.getAttribute("class");
		String[] withCompareSplit = withCompareValue.split(" ");
		if (withComparison.isDisplayed()) {
			
		if(withCompareSplit.length==3 && withComparisonValue.equalsIgnoreCase("No")) {
			withComparison.click();
			withComparisonValue= "No";
		}
		else if(withCompareSplit.length==2 && withComparisonValue.equalsIgnoreCase("Yes") ) {
			withComparison.click();
			withComparisonValue = "Yes";
		}
		}
		else {
			withComparisonValue= "No";
		}
		return withComparisonValue;

	}
	
	public ArrayList<String> search() {
		searchBtn.click();
		
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		ArrayList<String> tabsUrl = new ArrayList<String>();
		if(tabs.size()>1) {
	    for(String popup :driver.getWindowHandles()) 
	    {
	    driver.switchTo().window(popup);
	    tabsUrl.add(driver.getCurrentUrl());
	    }
	   }		
		
		return tabsUrl;

		
	}
	
	public int addCount(List <WebElement> guestKey){
		int actualCount =0;
		for(WebElement ele:guestKey) {
			actualCount = Integer.parseInt(ele.getText())+actualCount;
		}
		return actualCount;
	}
	
	public void clickGuestRoomApplyBtn() {
		applyBtn.click();
	}
}
