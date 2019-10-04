package createScreen;

import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import org.testng.annotations.BeforeTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.lept.PIX;
import org.bytedeco.javacpp.tesseract.TessBaseAPI;

import static org.bytedeco.javacpp.lept.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.interactions.touch.TouchActions;
import org.testng.annotations.AfterTest;
import io.appium.java_client.*;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.functions.ExpectedCondition;
import io.appium.java_client.touch.ActionOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;

public class RecordNameIsNull{
	public setup app = new setup();
	private WebDriverWait wait;
	 static String scrShotDir = "screenshots";
	  File scrFile;
	  static File scrShotDirPath = new java.io.File("./"+ scrShotDir+ "//");
	  String destFile;
	
	
	@BeforeTest
	public void beforeTest() throws MalformedURLException, InterruptedException {
		app.start();
		// WebElement popup = app.driver.findElementByLinkText("I Agree");
		WebElement recordButton = app.driver.findElementById("com.supereffect.voicechanger:id/recordButton");
		recordButton.click();
	}

	@AfterTest
	public void afterTest() {
		app.driver.navigate();
	}

	@Test(priority = 1, enabled = true)
	public void testNameIsNull() {
		WebElement name_textbox= app.driver.findElementById("com.supereffect.voicechanger:id/recordNameEditText");
		name_textbox.sendKeys("");  
		app.driver.hideKeyboard();
		WebElement start_button= app.driver.findElementById("com.supereffect.voicechanger:id/recordButton");
		start_button.click();		
		//WebElement alertt = app.driver.findElementById("com.supereffect.voicechanger:id/actionMidText");
		//assertEquals("Please enter the file name!",AlertText);
		String image;
		try {
			image = readToastMessage();
			System.out.println(image);
			//assertEquals(image.contains("Please enter the file name!"), true);
		} catch (TesseractException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	 public String readToastMessage() throws TesseractException{
		 String imgName = takeScreenShot();
		  String result = null;
		 File imageFile = new File(scrShotDirPath, imgName);
		  System.out.println("Image name is :" + imageFile.toString());
		 // File imageFile = new File("C:\\Users\\PA\\Downloads\\appiumTest-master\\screenshots\\03-Oct-2019__03_19_50PM.png");
		  ITesseract instance = new Tesseract();
	
		  File tessDataFolder = LoadLibs.extractTessResources("tessdata"); // Extracts Tessdata folder from referenced tess4j jar for language support
		  //instance.setDatapath(tessDataFolder.getAbsolutePath()); // sets tessData path	  
	
		  instance.setDatapath("C:\\Users\\PA\\Downloads\\Tess4J-3.4.8-src\\Tess4J\\tessdata");
		  instance.setLanguage("eng(2)");
		  
		  result = instance.doOCR(imageFile);
		  System.out.println(result);		
		 return result;		
	 }
	  
	/**
	  * Takes screenshot of active screen
	  * 
	  * @return ImageFileName
	  */
	 public String takeScreenShot() {
	  File scrFile = app.driver.getScreenshotAs(OutputType.FILE); 

	  SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
	  new File(scrShotDir).mkdirs(); // Create folder under project with name "screenshots" if doesn't exist
	  destFile = dateFormat.format(new Date()) + ".png"; // Set file name using current date time.
	  try {
	   FileUtils.copyFile(scrFile, new File(scrShotDir + "/" + destFile)); // Copy paste file at destination folder location
	  } catch (IOException e) {
	   System.out.println("Image not transfered to screenshot folder");
	   e.printStackTrace();
	  }
	  return destFile;
	 }
	

}
