package createScreen;

import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import org.testng.annotations.BeforeTest;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;

public class draftEditScreen {
	
	public setup app = new setup(); 
	
  /*@Test(priority=1)
  public void setting() {
	     //screenAlwaysTurnOnButton
		 WebElement sreenTurnOnButton = app.driver.findElementById("com.supereffect.voicechanger:id/alwayOnButton");
		 sreenTurnOnButton.click();
		 
		 WebElement settingButton = app.driver.findElementById("com.supereffect.voicechanger:id/settingButton");
		 settingButton.click();		
		 //check selectBox FileFormat
		
		  WebElement fileType =
		  app.driver.findElementById("com.supereffect.voicechanger:id/spinner");
		  fileType.click();
		  WebElement pick = app.driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[2]"));
		 pick.click();
		 
		
		 //check radio selection of Quality
		 WebElement quality = app.driver.findElementById("com.supereffect.voicechanger:id/kbps32");
		 quality.click();
		 WebElement OKbutton =app.driver.findElementById("com.supereffect.voicechanger:id/ok");
		 OKbutton.click();
		 		
  }*/
	
  @Test (priority = 1)
public void createName() throws InterruptedException {
	//Home screen   
		
		 WebElement nameEdit = app.driver.findElementById("com.supereffect.voicechanger:id/recordNameEditText");
		 app.driver.hideKeyboard();
		 nameEdit.sendKeys("new");
		
		 app.driver.hideKeyboard();
		 
		 WebElement startButton = app.driver.findElementById("com.supereffect.voicechanger:id/recordButton");
		  startButton.click(); 
		 // mất 20s chạy câu lệnh trc		
		  /*
		 * WebElement pauseButton = app.driver.findElementById(
		 * "com.supereffect.voicechanger:id/recordToggleButton"); pauseButton.click();
		 */
	     Thread.sleep(5000);
		  
		 WebElement stopButton =
		 app.driver.findElementById("com.supereffect.voicechanger:id/recordedStopButton");
				 stopButton.click(); 	  
  }
  
  @Test(priority = 2)
  public void setEffect()throws InterruptedException {
	  //after click Stop -> move to screen My studio Record
	 Thread.sleep(2000);
	  WebElement pauseButton= app.driver.findElement(By.id("com.supereffect.voicechanger:id/toggleButton"));
	  pauseButton.click();
	  
	 /* List<WebElement> effectList= new ArrayList<WebElement>();
		 for (int i=1; i<= 5; i++ ) {
			 WebElement index = app.driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.support.v7.widget.RecyclerView/android.widget.FrameLayout["+i+"]/android.widget.LinearLayout");
		     effectList.add(index);
		 }
		 System.out.println(effectList.get(1).getText());
		// .click();
		 * 
		 */
	  WebElement pickEffect = app.driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.support.v7.widget.RecyclerView/android.widget.FrameLayout[2]/android.widget.LinearLayout");
	  pickEffect.click();
	  
	  WebElement buttonNormal_effect =  app.driver.findElement(By.id("com.supereffect.voicechanger:id/btn_normal"));
	  
	  WebElement buttonDefault_effect =  app.driver.findElement(By.id("com.supereffect.voicechanger:id/btn_default"));
	  buttonDefault_effect.click();
  }
  
  @BeforeTest
  public void beforeTest() throws MalformedURLException, InterruptedException {
	 app.start();
	 WebElement recordButton = app.driver.findElementById("com.supereffect.voicechanger:id/recordButton");
	 recordButton.click();
	 
  }

  @AfterTest
  public void afterTest() {
	  app.driver.closeApp();  }

}
