package createScreen;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.internal.Utils;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;



public class RecordNameIsHTMLtags{
	public setup app = new setup();
	private WebDriverWait wait;
	static String scrShotDir = "screenshots";
	  File scrFile;
	  static File scrShotDirPath = new java.io.File("./"+ scrShotDir+ "//");
	  String destFile;
	private String HTML = "<b>bold</b>  ins<br>ali"; //   <a href=\"https://www.w3schools.com\">Visit W3Schools.com!</a>  ins<br>ali   ";
	
	@BeforeTest
	public void beforeTest() throws MalformedURLException, InterruptedException {
		app.start();
		// WebElement popup = app.driver.findElementByLinkText("I Agree");
		WebElement recordButton = app.driver.findElementById("com.supereffect.voicechanger:id/recordButton");
		recordButton.click();
	}

	@AfterTest
	public void afterTest() {
		app.driver.quit();
	}

	@Test(priority = 1, enabled = true)
	public void testNameIsEmpty() {
		app.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		WebElement name_textbox= app.driver.findElementById("com.supereffect.voicechanger:id/recordNameEditText");
		name_textbox.sendKeys(HTML);  
		app.driver.hideKeyboard();
		WebElement start_button= app.driver.findElementById("com.supereffect.voicechanger:id/recordButton");
		start_button.click();
		
		WebDriverWait wait = new WebDriverWait(app.driver, 10);
		String image;
		try {
			image = readToastMessage();
			//System.out.println(image);
			//assertEquals(image.contains("Invalid file name!"), true);
		} catch (TesseractException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//app.driver.findElementById("com.supereffect.voicechanger:id/recordToggleButton").click();
		//System.out.print(name_textbox);
		//assertEquals("<b>bold</b>  ins<br>ali", app.driver.findElementById("com.supereffect.voicechanger:id/recordNameEditText").getText());
		
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