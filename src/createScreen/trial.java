package createScreen;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.annotations.Test;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;

public class trial{
	
@Test
public void main() {
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
		
		  String result = null;
		 File imageFile = new File("C:\\Users\\PA\\Downloads\\appiumTest-master\\screenshots\\04-Oct-2019__10_56_49AM.png");
		  System.out.println("Image name is :" + imageFile.toString());
		 
		  ITesseract instance = new Tesseract();
	
		  File tessDataFolder = LoadLibs.extractTessResources("tessdata"); // Extracts Tessdata folder from referenced tess4j jar for language support
		  //instance.setDatapath(tessDataFolder.getAbsolutePath()); // sets tessData path	  
	
		  instance.setDatapath("C:\\Users\\PA\\Downloads\\Tess4J-3.4.8-src\\Tess4J\\tessdata");
		  instance.setLanguage("eng");
		  
		  result = instance.doOCR(imageFile);
		  System.out.println(result);		
		 return result;		
	 }
	  
	/**
	  * Takes screenshot of active screen
	  * 
	  * @return ImageFileName
	  */
	/* public String takeScreenShot() {
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
	 }*/
	

}