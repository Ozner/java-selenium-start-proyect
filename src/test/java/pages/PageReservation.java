package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class PageReservation {
	private WebDriver driver;
	private By titleText;
	private By passengersSelect;
	
	public PageReservation(WebDriver driver) {
		this.driver = driver;
		titleText = By.xpath("ddfdsfsdfsdf");
		passengersSelect = By.name("aaa");
	}
	
	public void selectPassengers(int cant) {
		Select passengerDrop = new Select(driver.findElement(passengersSelect));
		passengerDrop.deselectByVisibleText(Integer.toString(cant));
	}
}
