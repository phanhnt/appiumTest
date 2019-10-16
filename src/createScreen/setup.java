package createScreen;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class setup {
	
	 public AppiumDriver<AndroidElement> driver;
	 
	 
  @Test
  public void start() throws MalformedURLException, InterruptedException {
	 
		
		//Set the Desired Capabilities
		DesiredCapabilities caps = new DesiredCapabilities();
		//real device
		caps.setCapability("deviceName", "Nemo");
		caps.setCapability("udid", "fe0ecacb"); //Give Device ID of your mobile phone "ce01171198b9463e02"
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "5.0.2");
		caps.setCapability("automatioName", "UiAutomator2");
		caps.setCapability("app", "C:\\Users\\PA\\Downloads\\VoiceChangerAudioEffects_v1.4.1_apkpure.com.apk");
		   
		//caps.setCapability("appPackage", "com.supereffect.voicechanger");
		//caps.setCapability("appActivity", "com.supereffect.voicechanger2.UI.activity.MainActivity");
		
	/*
		//emulator
		caps.setCapability("deviceName", "Samsung Galaxy S6");
		caps.setCapability("udid", "192.168.45.101:5555"); //Give Device ID of your mobile phone "ce01171198b9463e02"
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "6.0");
		caps.setCapability("app", "H:\\VoiceChangerAudioEffects_v1.4.1_apkpure.com.apk");
		
		caps.setCapability("automatioName", "UiAutomator2");   
		//caps.setCapability("appPackage", "com.supereffect.voicechanger");
		//caps.setCapability("appActivity", "com.supereffect.voicechanger2.UI.activity.MainActivity");
		caps.setCapability("fullReset", "false");
		caps.setCapability("noReset", "true");
		caps.setCapability("newCommandTimeout", 60*10);
		//caps.setCapability("autoAcceptAlerts", true); - just for iOS
		caps.setCapability("autoGrantPermissions", true);
		//caps.SetCapability("autoDismissAlerts", true);
		//Instantiate Appium Driver
		
		*/
		driver = new AndroidDriver<AndroidElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);		
		/*if (driver.findElementById("android:id/button1").isDisplayed())
		{
			driver.findElementById("android:id/button1").click();
		}*/
		
  }
}
