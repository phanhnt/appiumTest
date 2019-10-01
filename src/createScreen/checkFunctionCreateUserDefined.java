package createScreen;

import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidElement;

import org.testng.annotations.BeforeTest;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;


public class checkFunctionCreateUserDefined {

	public setup app = new setup();
	public record tmp= new record();

	private String name; //default name of record 
		public void setName(String name) {
			this.name=name;
		}
		public String getName() {
			return this.name;
		}		
	
	@BeforeTest
	public void beforeTest() throws MalformedURLException, InterruptedException {
		app.start();
		WebElement recordButton = app.driver.findElementById("com.supereffect.voicechanger:id/recordButton");
		recordButton.click();
	}

	@AfterTest
	public void afterTest() {
		app.driver.navigate();
	}

	@Test
  public void createSteps() throws InterruptedException {
	  app.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	   
	  WebElement nameEdit = app.driver.findElementById("com.supereffect.voicechanger:id/recordNameEditText");
	  app.driver.hideKeyboard();
	  nameEdit.sendKeys("New");
	  app.driver.hideKeyboard();
	  
	  WebElement qualitySettingButton= app.driver.findElementById("com.supereffect.voicechanger:id/quality");
	  qualitySettingButton.click();  
	  
	  //popup setting	
	  WebElement file_format= app.driver.findElementById("com.supereffect.voicechanger:id/spinner");
	  file_format.click();
	  app.driver.findElementByXPath(formatXpath()).click();
	  app.driver.findElementByXPath(qualityRadioXpath()).click();;
	  app.driver.findElement(By.id("com.supereffect.voicechanger:id/ok")).click();
	  
	  //start recording

	  WebElement start_button =app.driver.findElementById("com.supereffect.voicechanger:id/recordButton");
	  start_button.click();
	  WebElement stop_button =app.driver.findElementById("com.supereffect.voicechanger:id/recordedStopButton");	  
	  //Thread.sleep(4000);
	  stop_button.click();
}

	
	
public String formatXpath() { 
	return "//android.widget.TextView[@text='" +pickFileType()+ "']"; 
	}
public String qualityRadioXpath() { 
	return "//android.widget.RadioButton[@text='"+pickQuality()+"']"; 
	}	 

//pick value from enum value of record class
public String pickFileType() {
	return tmp.format.OGG.toString();
}
//pick value from enum value of record class
public String pickQuality() {
	return tmp.quality.kbp96.getQuality();
}

@Test(priority = 2, enabled = true)
public void checkEOMoveScreen() {
	WebElement moveScrEdit = app.driver.findElementByXPath(
			"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.view.View/android.widget.TextView");
	
	WebElement name1= app.driver.findElementById("com.supereffect.voicechanger:id/trackTitle");
    
		System.out.println("name from create screen: "+ name1.getText());
		
		setName(name1.getText());
		System.out.println("added in variables: "+ getName());
		assertEquals(moveScrEdit.getText(), "Edit effects");
}

@Test(priority = 3, enabled = true)
public void checkEOSavedRecord() {
	app.driver.navigate().back();
	
	WebElement my_studio_button = app.driver.findElementById("com.supereffect.voicechanger:id/myStudioButton");
	my_studio_button.click();
	WebElement tabRecording = app.driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/android.widget.TextView[2]");
	tabRecording.click();	
	
	//C2: List<AndroidElement> itemClick = app.driver.findElementsById("com.supereffect.voicechanger:id/itemLayoutClick");
	List<AndroidElement> recording_list = app.driver.findElements(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.support.v4.view.ViewPager/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/*"));
	//System.out.println(recording_list.size()); //-> size thieu-> chi co 9 record
	
	//System.out.println(recording_list.get(0).findElementById("com.supereffect.voicechanger:id/title").getText());
	String latest_reocord_name = recording_list.get(0).findElementById("com.supereffect.voicechanger:id/title").getText();
	assertEquals(latest_reocord_name, getName());
	
}



}
