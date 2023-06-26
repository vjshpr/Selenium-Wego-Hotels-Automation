package testcases;


import org.apache.hc.core5.http.Method;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.HotelSearchPage;

public class TC001_Hotels_Navigate_To_Hotel_Search extends BaseTest {

	@Test
	public static void navigateToHotelSearch() throws InterruptedException {
		HotelSearchPage hsr = PageFactory.initElements(driver, HotelSearchPage.class);
		String pageTitle=hsr.navigateHotelSearch();
		Assert.assertTrue(pageTitle.toLowerCase().contains("hotel"));
		logger.info("Test Case Successfully executed");

	}
}
