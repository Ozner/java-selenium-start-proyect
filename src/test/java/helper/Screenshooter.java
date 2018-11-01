package helper;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshooter {
	
	public static void takeScreenshoot(String screenName, WebDriver driver) {
		String directorio = "Evidencia\\";		 
		
		File myScreenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	
		try {
			FileUtils.copyFile(myScreenShot, new File(directorio + "LOGIN " + System.currentTimeMillis() + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
