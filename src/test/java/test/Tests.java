package test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import helper.Helper;
import pages.PageLogin;
import pages.PageLogon;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.Assert;


public class Tests {
	//private WebDriver driverFirefox;
	private WebDriver driverChrome;
	ArrayList<String> tabs;
	
	@BeforeTest
	public void setUp(){
		System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		driverChrome = new ChromeDriver(options);
		driverChrome.manage().window().maximize();
		//Pruebas de cambiar tamanio y mover el browser
		//driverChrome.manage().window().setSize(new Dimension(200, 200));
		//driverChrome.manage().window().setPosition(new Point(800, 200));
		driverChrome.navigate().to("https://github.com/");
		
		//usar gecko para firefox
		/*System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe");
		WebDriver driverFirefox = new FirefoxDriver();
		driverFirefox.get("http://www.google.com");*/
			
		JavascriptExecutor jsExecutor = ((JavascriptExecutor) driverChrome);
		String googleWindow = "window.open('http://www.google.com')";
		jsExecutor.executeScript(googleWindow);
		
		//todos los tabs de un navegador
		tabs = new ArrayList<String> (driverChrome.getWindowHandles());
	
		Helper helper = new Helper();
		helper.sleepSecond(5);
	}
	
	@Test(priority = 1)
	public void pruebaUno() {
		try {
			PageLogin pagelogin = new PageLogin(driverChrome);
			pagelogin.login("rnzparente@gmail.com", "ingenieria2013github");
			Assert.assertTrue(driverChrome.findElement(By.xpath("//*[@id=\"js-pjax-container\"]/div[1]/div/div/h2")).getText().contains("Learn Git and GitHub without any code!"), "No se encontro el texto: Learn Git and GitHub without any code!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test(priority = 3)
	public void pruebaDos() {
		try {
			PageLogon pagelogon = new PageLogon(driverChrome);
			pagelogon.logon();
			//asegurar que se desloguea
			pagelogon.assertLogonPage();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(priority = 2)
	public void pruebaTres() {
		//abrir otra pestania y navegar en algun sitio.
		driverChrome.switchTo().window(tabs.get(1)).navigate().to("http://codigoenorbita.com");
	}
	
	@AfterTest
	public void tearDown() {
		//ITestResult result, parametro que no me funciona
		/*if(!result.isSuccess()) {
			//instanciar Screenshooter para 
		}*/
		driverChrome.switchTo().window(tabs.get(1)).close();
		driverChrome.switchTo().window(tabs.get(0)).close();
		//driverChrome.close();
	}
}
