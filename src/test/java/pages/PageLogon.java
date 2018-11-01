package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class PageLogon {
	private WebDriver driver;
	private By loginLink = null;
	private By logonLink = null;
	private WebDriverWait explicitWait;
	
	public PageLogon(WebDriver driver) {
		this.driver = driver;
		loginLink = By.xpath("//a[contains(@href,'login')]");
		logonLink = By.xpath("//*[@id=\"user-links\"]/li[3]/details/details-menu/ul/li[10]/form/button");
		explicitWait = new WebDriverWait(driver,10);
	}
	
	public void assertLogonPage() {
		WebElement singInElement = explicitWait.until(ExpectedConditions.presenceOfElementLocated(loginLink));
		Assert.assertTrue(singInElement.getText().contains("Sign in"), "No se pudo desloguear");
	}
	
	public void logon() throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"user-links\"]/li[3]/details/summary")).click();
		WebElement singOutElement = explicitWait.until(ExpectedConditions.presenceOfElementLocated(logonLink));		
		singOutElement.click();
	}
}
