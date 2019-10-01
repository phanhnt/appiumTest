package createScreen;

import java.util.*;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.interactions.touch.TouchActions;
import org.testng.annotations.AfterTest;
import io.appium.java_client.*;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.ActionOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

public class checkUI {
	public setup app = new setup();

	public ArrayList<String> def_value = new ArrayList<String>(); // array save default value of unit test in GUI

	private Object executeScript;
	
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

	@Test (priority = 1, enabled = true)
	public void testButtonDefaultQualiy() {
		 WebElement quality_button= app.driver.findElementById("com.supereffect.voicechanger:id/quality");
		 def_value.add(quality_button.getText());
		
		 System.out.println(def_value.get(0));
		 assertEquals(def_value.get(0), "Medium Quality, MP3-128 kbps");
	}
	
	@Test (priority = 2, enabled = true) //case này lỗi syntex hh:mm đang để hh.mm-> nhưng tên k chứa ký tự đặc biệt -> để 1 . thôi
	public void testDefaultName() {
		WebElement def_name= app.driver.findElementById("com.supereffect.voicechanger:id/recordNameEditText");
		def_value.add(def_name.getText());
		//System.out.println(def_value.get(1));		 
		Calendar cal = Calendar.getInstance();
		String currentTime = new SimpleDateFormat("MMM dd, hh.mm a").format(cal.getTime());	//type 12h: h >< type 24h: H	  
		assertEquals(def_value.get(1), currentTime);
		
	}
	
	
		
	@Test (priority = 3, enabled=true)
	public void TestDefaultTimeRecord() {
		WebElement def_time_record= app.driver.findElementById("com.supereffect.voicechanger:id/tickText");
		def_value.add(def_time_record.getText());
		System.out.println(def_value.get(2));
		assertEquals(def_value.get(2), "00:00:00");
	}
	
	@Test (priority = 4, enabled=false)
	public void testIconSetting() {
		WebElement def_time_record= app.driver.findElementById("com.supereffect.voicechanger:id/settingButton");
		def_time_record.click();
		WebElement title_popup= app.driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.TextView[1]");
		String popup_name= title_popup.getText();
		System.out.println(popup_name);
		assertEquals(popup_name, "Recording quality"); // thế này thì TC sau nó bắt buộc phải tắt popup đi, vậy thì thứ tự thực hiện TC sẽ bị đóng khung cái này và cái sau, nếu thay thứ tự -> không linh hoạt??? => pp luận: sắp xếp trình tự TC thì mới chạy tối ưu nhất. trình tự thực hiện TC có thể ảnh hưởng đến hiệu quả test.
	}
	
	@Test (priority = 5, enabled=false)
	public void testButtonStart() throws InterruptedException {
		 app.driver.findElementById("com.supereffect.voicechanger:id/ok").click();	
		WebElement start_button= app.driver.findElementById("com.supereffect.voicechanger:id/recordButton");
		WebElement time_running= app.driver.findElementById("com.supereffect.voicechanger:id/tickText");
		start_button.click();
		Thread.sleep(3000);
	   	assertEquals(timeRun("com.supereffect.voicechanger:id/tickText", "00:00:00"), false);
		System.out.println(time_running.getText());
	}
	
	public boolean timeRun (String x, String y) {
		WebElement time_running = app.driver.findElementById(x);
		if (time_running.getText().equals(y))
			return true;
		else 
			return false;		
	}
	
	@Test (priority = 6, enabled=false)
	public void buttonPause() {
		
	}
	
	@Test (priority = 7, enabled=false)
	public void testDoubleClickQualityButton() {
		// WebElement quality_button= app.driver.findElementById("com.supereffect.voicechanger:id/quality");
				 //def_value.add(quality_button.getText()); t�?a độ [147,300][392,363]
		 AndroidElement x = app.driver.findElementById("com.supereffect.voicechanger:id/quality");
		 
		 //??? -> https://github.com/appium/java-client/blob/master/src/test/java/io/appium/java_client/android/AndroidTouchTest.java
		AndroidTouchAction a1 = new AndroidTouchAction(app.driver);
		AndroidTouchAction a2 = new AndroidTouchAction(app.driver);
		a1.tap(PointOption.point(x.getLocation().getX(),x.getLocation().getY())).release().perform();
		a2.tap(PointOption.point(x.getLocation().getX(),x.getLocation().getY())).release().perform();
	
		//.tap(PointOption.point(x.getLocation().getX(),x.getLocation().getY())).release().perform();
		//a1.press(PointOption.point(x.getLocation().getX(), x.getLocation().getY())).waitAction(WaitOptions.waitOptions(Duration.ofMillis(100))).release().perform().press(PointOption.point(x.getLocation().getX(), x.getLocation().getY())).release().perform();
		//a1.press(PointOption.point(x.getLocation().getX(), x.getLocation().getY())).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1))).release().perform();
		//a2.press(PointOption.point(x.getLocation().getX(), x.getLocation().getY())).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1))).release().perform();
		
		//a1.press(PointOption.point(x.getLocation().getX(), x.getLocation().getY())).waitAction(WaitOptions.waitOptions(Duration.ofMillis(100))).release().perform();
		

		 /*HashMap<String, String> tapObject = new HashMap<String,String>();
		 tapObject.put("tapCount", "2.0");
		 tapObject.put("duration", "0.0");
		 tapObject.put("element", x.getId());
		 JavascriptExecutor js = (JavascriptExecutor) app.driver;	 
		  js.executeScript("mobile: shell", tapObject);*/
	}
	
	
}
