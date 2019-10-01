package editEffect;

import org.testng.annotations.Test;

import createScreen.setup;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;

import org.testng.annotations.BeforeTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import createScreen.record;

public class editRecordingSimpleEffect {
	public setup app = new setup();
	private record rec = new record();
	private String name; 
	private String effect;
	
	public void setName(String name) {
		this.name=name;
		rec.setName(name);
	}
	public String getName() {
		setName("ddddd");
		return name;
	}	
	
	public void setEffect(String name) {
		this.name=name;
	}
	public String getEffect() {
		setName("Giant");
		return this.name;
	}	
	
	@BeforeTest
	public void beforeTest() throws MalformedURLException, InterruptedException {
		app.start();
		WebElement my_studio_button = app.driver.findElementById("com.supereffect.voicechanger:id/myStudioButton");
		my_studio_button.click();
		WebElement tab_recording = app.driver.findElementByXPath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/android.widget.TextView[2]");
		tab_recording.click();
		// List<AndroidElement> recording_list =
		// app.driver.findElements(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.support.v4.view.ViewPager/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/*"));
		MobileElement el = (MobileElement) app.driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(" 
				+ "new UiSelector().scrollable(true)).scrollIntoView("
				+ "new UiSelector().textContains(\""+getName()+"\"));"));
		el.click();	
		System.out.println(el.getText());
		MobileElement icon = app.driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.support.v4.view.ViewPager/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.FrameLayout[7]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.ImageView[2]"));
		icon.click();
        Thread.sleep(1000);
		WebElement option = app.driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[1]");
		option.click();
	}

	@AfterTest
	public void afterTest() {
		app.driver.navigate();
	}
	
	@Test (priority=1, enabled=true) 
	public void chooseSimpleEffect() throws InterruptedException {
		List<AndroidElement> frame1 = app.driver.findElements(By.id("com.supereffect.voicechanger:id/effectLayout")); 
		System.out.println("frame2: "+ frame1.size());
		/* <Cách lấy list khác vẫn work>
		 * List<AndroidElement> frame2 = app.driver.findElementsByXPath(
		 * "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.support.v7.widget.RecyclerView/*"); 
		 * List<AndroidElement> frame2 =
		 * app.driver.findElementsByXPath("//android.support.v7.widget.RecyclerView/*");
		 */
		//scroll to specific element in lists.   
		String endScr =  frame1.get(frame1.size()-1).findElementById("com.supereffect.voicechanger:id/title").getText();
		MobileElement el = (MobileElement) app.driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(" 
				+ "new UiSelector().scrollable(true)).scrollIntoView("
				+ "new UiSelector().textContains(\""+ getEffect() +"\"));"));
		el.click();	
		//app.driver.findElement(MobileBy.AndroidUIAutomator(uiautomatorText))
		MobileElement child = app.driver.findElements(MobileBy.id("com.supereffect.voicechanger:id/saveButton")).get(4);
		child.click();	
		
		//handle pop-up by click ok
		app.driver.findElement(By.id("android:id/button1")).click();
	 
		//wait processing
		/*
		 * WebElement popup=
		 * app.driver.findElementById("com.supereffect.voicechanger:id/action_bar_root")
		 * ; if (popup.isDisplayed()) { app.driver.wait(); }
		 */
		Thread.sleep(8000);
		
		//scroll from top to bottom by coordinate-> works!		 
		/*Dimension size = app.driver.manage().window().getSize();
		int fromY= (int) (size.height*0.9);
		int toY = (int) (size.height*0.1);
		int x = (int) size.width/2;
		TouchAction touch= new TouchAction(app.driver);
		touch.press(PointOption.point(x, fromY)).waitAction().moveTo(PointOption.point(x, toY)).release().perform();*/	
	}
	
	@Test (priority=2, enabled=true) 
	public void checkEOEffectedChanged() throws InterruptedException {
		//handle popup after saving
		//frane layout : resource-id: android:id/content ; class: android.widget.FrameLayout
		// later button: com.supereffect.voicechanger:id/btn_later
		// No button: com.supereffect.voicechanger:id/btn_no
		//rate button: com.supereffect.voicechanger:id/btn_five_stars
		// class of buttons: android.widget.TextView
		//Thread.sleep(2000);
		List<AndroidElement> buttonAds = app.driver.findElements(By.id("com.supereffect.voicechanger:id/btn_later"));
		if (buttonAds.isEmpty()) {
			WebElement back_button = app.driver.findElementByAccessibilityId("Navigate up");
			back_button.click();
			Thread.sleep(1000);
		}else {
			app.driver.findElement(By.id("com.supereffect.voicechanger:id/btn_later")).click();
			Thread.sleep(1000);
		}
		WebElement tab_effected = app.driver.findElementByXPath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/android.widget.TextView[1]");
		tab_effected.click();
		
		List<AndroidElement> effected_list = app.driver.findElements(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.support.v4.view.ViewPager/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/*"));
		//System.out.println(recording_list.size()); //-> size thieu-> chi co 9 record
		
		//System.out.println(recording_list.get(0).findElementById("com.supereffect.voicechanger:id/title").getText());
		String saved_record = effected_list.get(0).findElementById("com.supereffect.voicechanger:id/title").getText();
		 String name_record = getName()+ "_"+ getEffect();
		System.out.println(saved_record);
		System.out.println(name_record);
		assertEquals(saved_record, name_record);
		}
	
	
	/*
	 * //@Test(priority=1, enabled=true)-> hàm này cho check screen Mtudio thì đúng
	 * hơn. public void selectARecord(){ WebElement tmp =
	 * app.driver.findElementsByXPath("//android.support.v7.widget.RecyclerView/*").
	 * get(0); tmp.click();
	 * 
	 * WebElement miniBoxPlaying=
	 * app.driver.findElementById("com.supereffect.voicechanger:id/miniBoxLayout");
	 * assertFalse(miniBoxPlaying.isDisplayed()); }
	 * 
	 * 
	 * @Test (priority=2, enabled=true) public void checkTotalCount() { WebElement
	 * option_icon=
	 * app.driver.findElementById("com.supereffect.voicechanger:id/buttonMore");
	 * option_icon.click();
	 * 
	 * WebElement option= app.driver.
	 * findElementByXPath("//android.widget.ListView[@text='Edit effects']");
	 * option.click(); }
	 * 
	 */
}
