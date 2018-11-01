package pages;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import helper.Helper;

public class PageLogin {
	private WebDriver driver;
	//uso de pagefactory para instanciar los web elements de la pagina
	// es una manera mas prolija de usar el patron page object 
	//usando algo propodionado por selenium, el @FindBy
	@FindBy(how=How.XPATH, using="//a[contains(@href,'login')]")
	WebElement loginLink;
	
	//private By loginLink; comento esta variable del tipo By
	private By userNameField;
	private By passwordField;
	private By loginButton;
	private WebDriverWait explicitWait;
	
	public PageLogin(WebDriver driver) {
		this.driver = driver;
		//loginLink = By.xpath("//a[contains(@href,'login')]"); comento la busqueda del WebElement ya que no es necesaria
		PageFactory.initElements(driver, this);// inicializar la page factory
		userNameField = By.name("login");
		passwordField = By.name("password");
		loginButton = By.name("commit");
		explicitWait = new WebDriverWait(driver,10); //esperar durante 10 segundo que aparezca un elemento, si aparece antes continua el flujo.
	}
	
	
	public void login(String userName, String password) {
		loginLink.click();
		this.ingresarUsuario(userName);
		this.ingresarPassword(password);
		this.enviarFormulario();	
	}
	
	public void ingresarUsuario(String userName) {
		WebElement userNameElement = explicitWait.until(ExpectedConditions.presenceOfElementLocated(userNameField));

		(userNameElement).click();
		(userNameElement).sendKeys(userName);
	}
	
	public void ingresarPassword(String password) {
		WebElement passwordElement = explicitWait.until(ExpectedConditions.presenceOfElementLocated(passwordField));
		
		passwordElement.click();
		passwordElement.sendKeys(password);
	}
	
	public void enviarFormulario() {
		driver.findElement(loginButton).click();
	}
}
