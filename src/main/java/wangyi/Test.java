package main.java.wangyi;

import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

public class Test {
	public static void main(String args[]) throws IOException
	{
		System.setProperty("phantomjs.binary.path", "C:\\phantomjs\\phantomjs.exe");
		
		
		
		WebDriver driver = new PhantomJSDriver();
		driver.get("http://study.163.com/course/introduction/382025.htm");
		java.io.File screenShotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenShotFile, new java.io.File("D:\\a2.png"));
		driver.quit();
	}
}
