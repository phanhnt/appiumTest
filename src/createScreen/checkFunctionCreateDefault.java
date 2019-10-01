package createScreen;

import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.BeforeTest;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;

public class checkFunctionCreateDefault {

	public setup app = new setup();
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

	public String def_name() {
		WebElement def_name= app.driver.findElementById("com.supereffect.voicechanger:id/recordNameEditText");
		return def_name.getText();
	}
	
	@Test(priority = 1, enabled = true)
	public void createSteps() throws InterruptedException {
		app.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		List<AndroidElement> buttonAds = app.driver.findElements(By.id("com.android.packageinstaller:id/permission_deny_button"));
		if (buttonAds.isEmpty()) {
			WebElement back_button = app.driver.findElementById("com.supereffect.voicechanger:id/recordButton");
			back_button.click();
			Thread.sleep(1000);
		}else {
			app.driver.findElement(By.id("com.android.packageinstaller:id/permission_deny_button")).click();
			Thread.sleep(1000);
		}
		AndroidTouchAction startStop = new AndroidTouchAction(app.driver);
		startStop.tap(TapOptions.tapOptions().withElement(
		ElementOption.element(app.driver.findElementById("com.supereffect.voicechanger:id/recordButton"))));
		startStop.perform();
		// AndroidTouchAction stop = new AndroidTouchAction(app.driver) -> wait for
		// 30-40s to load find and run pauseButton
	     //pause Button (Optional)
		 startStop .tap(TapOptions.tapOptions() .withElement(ElementOption.element(
		 app.driver.findElementById("com.supereffect.voicechanger:id/recordToggleButton")))) .perform();
		 
		WebElement stop_button= app.driver.findElementById("com.supereffect.voicechanger:id/recordedStopButton");
		stop_button.click();
		
		/*startStop
				.tap(TapOptions.tapOptions()
						.withElement(ElementOption.element(
								app.driver.findElementById("com.supereffect.voicechanger:id/recordedStopButton"))))
				.perform().waitAction();
		*/
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
