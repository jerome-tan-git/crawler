package main.java.xuetangx;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class xuetanglist {
	private static String phantomJsPath = "c:\\phantomjs\\phantomjs.exe";
	private static WebDriver driver = null;
	private static BufferedWriter bWriter = null;

	public static void main(String args[]) {
//		System.setProperty("phantomjs.binary.path", phantomJsPath);
//		DesiredCapabilities cap = DesiredCapabilities.phantomjs();
//		cap.setCapability("phantomjs.page.settings.userAgent","Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.90 Safari/537.36");
//		driver = new PhantomJSDriver(cap);
//		driver = new PhantomJSDriver();
		System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
		driver = new ChromeDriver();
		try {
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
//			driver.manage().window().setSize(new Dimension(1000,1000));
//			driver.manage().window().maximize();
			driver.get("http://www.xuetangx.com/courses");
			Thread.sleep(5000);
			
//			new WebDriverWait(driver, 60).until(new  ExpectedCondition<Boolean>() {
//
//				public Boolean apply(WebDriver input) {
//					System.out.println(input.findElement(By.tagName("body")).getText());
//					return input.findElement(By.tagName("body")).getText().contains("¼´½«¿ª¿Î");
//				}
//
//			  });
			WebElement element = null;
			while((element = driver.findElement(By.id("show_more"))).isDisplayed())
			{
				if (element.findElement(By.className("btn1")).isDisplayed())
				{
					try
					{
						element.findElement(By.className("btn1")).click();
					}catch(Exception e)
					{
						
					}
				
				}
				Thread.sleep(2000);
			}
			System.out.println("end");
			FileWriter fw = new FileWriter("./xuetangx.url");
			PrintWriter pw = new PrintWriter(fw);
			List<WebElement> els = driver.findElements(By.tagName("h3"));
			System.out.println(els.size());
			for(WebElement e : els)
			{
				System.out.println(e.findElement(By.tagName("a")).getAttribute("href"));
				pw.println(e.findElement(By.tagName("a")).getAttribute("href"));
				pw.flush();
			}
			pw.close();
			fw.close();
			
//			java.io.File screenShotFile = ((TakesScreenshot) driver)
//					.getScreenshotAs(OutputType.FILE);
//			FileUtils.copyFile(screenShotFile, new java.io.File("D:\\a3.png"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}

	}
}
