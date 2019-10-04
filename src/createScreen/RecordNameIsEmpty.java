package createScreen;

import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import org.testng.annotations.BeforeTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
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
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.functions.ExpectedCondition;
import io.appium.java_client.touch.ActionOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;

public class RecordNameIsEmpty{
	public setup app = new setup();
	private WebDriverWait wait;

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
	public void testNameIsEmpty() {
		app.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		WebElement name_textbox= app.driver.findElementById("com.supereffect.voicechanger:id/recordNameEditText");
		name_textbox.sendKeys("                                ");  
		app.driver.hideKeyboard();
		WebElement start_button= app.driver.findElementById("com.supereffect.voicechanger:id/recordButton");
		start_button.click();
		
		WebElement stop_button =app.driver.findElementById("com.supereffect.voicechanger:id/recordedStopButton");	  
		  //Thread.sleep(4000);
		  stop_button.click();
		wait.until(ExpectedConditions.alertIsPresent());
		assertEquals(ExpectedConditions.alertIsPresent(), false);
	}
}
		