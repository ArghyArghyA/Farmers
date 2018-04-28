package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;
import java.util.function.Function;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import repository.EAgent;
import repository.EAgent.LogIn;
import repository.Home;

public class Utilities
{

	public static WebDriver driver = new EdgeDriver();
	public static WebDriverWait wait = new WebDriverWait(driver, 10);
	protected static Actions act = new Actions(driver);
	protected static Select select;
	protected static Alert alert;
	protected static JavascriptExecutor js = (JavascriptExecutor) driver;
	protected final boolean debugging = false;
	protected static HashMap<String, String> windows = new HashMap<String, String>();
	protected static Object[] windowshandles;
	protected HashMap<String, HashMap<String, String>[]> Data;
	public HashMap<String, String> Global = new HashMap<String, String>();
	protected static HashMap<String, Integer> CallingCounter = new HashMap<String, Integer>();
	protected final Properties ZIP = new Properties();
	protected final Properties Credential = new Properties();
	protected final Properties URL = new Properties();

	public Utilities() throws FileNotFoundException, IOException
	{
		super();
		CallingCounter.clear();
		ZIP.load(new FileInputStream(
				"C:/Users/Arghya/eclipse-workspace/Farmers/src/main/java/repository/ZIP.properties"));
		Credential.load(new FileInputStream(
				"C:/Users/Arghya/eclipse-workspace/Farmers/src/main/java/repository/Credential.properties"));
		URL.load(new FileInputStream(
				"C:/Users/Arghya/eclipse-workspace/Farmers/src/main/java/repository/URL.properties"));
	}

	/*
	 * launchBrowser launches safari browser. As of now no browser support is there
	 * 
	 * @parameter String env-required env
	 */

	EAgent E = new EAgent();

	public boolean launchBrowser(HashMap<String, String> Init)
	{
		try
		{
			System.out.println("launching browser");
			String env = Init.get("Env").toUpperCase();

			if (URL.getProperty(env) != null)
			{
				driver.get(URL.getProperty(env));
			} else
			{
				throw new InvalidArgumentException("Kinldy specify URL for " + env + "in URL.properties first");
			}
			// System.out.println(driver.getTitle());
			wait.until(ExpectedConditions.titleContains("Farmers Insurance: Login"));
			wait.until(ExpectedConditions.elementToBeClickable(By.name("USER")));
			// Thread.sleep(3000);
			System.out.println("browser launched");
		} catch (Exception e)
		{
			e.printStackTrace();
			if (!debugging)
				driver.quit();
			return false;
		}

		return true;

	}

	public boolean login(HashMap<String, String> Init)
	{
		WebElement ele;
		try
		{
			LogIn dom = E.LogIn;
			System.out.println("logging in");
			// fill user id
			ele = wait.until(ExpectedConditions.elementToBeClickable(dom.User));
			if (Init.containsKey("User"))
				ele.sendKeys(Init.get("User"));
			else
			{
				String user = Credential.getProperty(Init.get("State")).split("/")[0];
				ele.sendKeys(user);
			}
			// fill password
			ele = wait.until(ExpectedConditions.elementToBeClickable(dom.Password));
			if (Init.containsKey("Password"))
			{
				ele.sendKeys(Init.get("Password"));
			} else
			{
				String password = Credential.getProperty(Init.get("State")).split("/")[1];
				ele.sendKeys(password);
			}
			ele.submit();

			System.out.println("log in button clicked");
			wait.until(ExpectedConditions.titleContains("Environment URLs"));
			wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.tagName("a"), 2));
			// Thread.sleep(5000);
			System.out.println("links opened");
			System.out.println(driver.getTitle());

			String env = Init.get("Env");
			if (env.toLowerCase().equalsIgnoreCase("ma11"))
			{
				// driver.findElement(By.linkText("eAgent TA (MA11 AFT SALT)")).click();
				driver.findElements(By.tagName("a")).get(0).click();
			} else if (env.toLowerCase().equalsIgnoreCase("ra11"))
			{
				// driver.findElement(By.partialLinkText("RA11")).click();
				driver.findElements(By.tagName("a")).get(1).click();
			} else if (env.toLowerCase().equalsIgnoreCase("rs11"))
			{
				// driver.findElement(By.partialLinkText("RS11")).click();
				driver.findElements(By.tagName("a")).get(2).click();
			} else if (env.toLowerCase().equalsIgnoreCase("tsv1"))
			{
				// driver.findElement(By.partialLinkText("TSV1")).click();
				driver.findElements(By.tagName("a")).get(3).click();
			}

			wait.until(ExpectedConditions.titleContains("Agency Dashboard"));
			wait.until(ExpectedConditions.elementToBeClickable(By.className("button_indicator")));
			wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Personal")));

			System.out.println("logged in");
		} catch (Exception e)
		{
			e.printStackTrace();
			if (!debugging)
				driver.quit();
			return false;
		}

		return true;

	}

	public boolean closure()
	{
		try
		{
			System.out.println("closing browser session");

			driver.quit();
		} catch (Exception e)
		{
			e.printStackTrace();
			// driver.quit();
			return false;
		}
		System.out.println("browser session closed");
		return true;
	}

	protected void switchToTab(String string)
	{
		String current = driver.getWindowHandle();
		windowshandles = driver.getWindowHandles().toArray();
		boolean flag = true;
		for (Object object : windowshandles)
		{
			driver.switchTo().window((String) object);
			waitForPageToLoad();
			if (debugging)
				System.out.println(driver.getTitle());
			if (driver.getTitle().contains(string))
			{

				flag = false;
				break;
			}
		}
		// flag will be false if matching window in found after the above for loop
		if (flag)
		{
			driver.switchTo().window(current);
			throw new NoSuchWindowException("There is no matching window with title containing " + string);
		}
		driver.manage().window().maximize();
	}
	
	protected void closeAndSwitchToTab(String string)
	{
		String current = driver.getWindowHandle();
		String target = "";
		windowshandles = driver.getWindowHandles().toArray();
		boolean flag = true;
		for (Object object : windowshandles)
		{
			driver.switchTo().window((String) object);
			waitForPageToLoad();
			if (debugging)
				System.out.println(driver.getTitle());
			if (driver.getTitle().contains(string))
			{

				flag = false;
				target = driver.getWindowHandle();
				break;
			}
		}
		// flag will be false if matching window is found after the above for loop
		if (flag)
		{
			driver.switchTo().window(current);
			throw new NoSuchWindowException("There is no matching window with title containing " + string);
		}
		else
		{
			driver.switchTo().window(current).close();
			driver.switchTo().window(target);
			driver.manage().window().maximize();
		}
		
		
	}
	
	protected void readData()
	{

	}

	protected void waitForPageToLoad()
	{
		wait.until(new Function<WebDriver, Boolean>()
		{
			public Boolean apply(WebDriver driver)
			{
				return String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
						.equalsIgnoreCase("complete");
			}
		});

	}

	public void closeExistingSession()
	{
		try
		{
			Runtime.getRuntime().exec("TASKKILL /F /IM edge.exe");
			System.exit(0);

		} catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
	}

	public Alert getAlert()
	{
		return ExpectedConditions.alertIsPresent().apply(driver);
	}

	public int increaseCounter(String string)
	{
		if (CallingCounter.get(string) != null)
		{
			CallingCounter.replace(string, CallingCounter.get(string) + 1);
		} else
		{
			CallingCounter.put(string, 1);
		}
		return getCounter(string);
	}

	public int getCounter(String string)
	{
		return CallingCounter.get(string);
	}
}
