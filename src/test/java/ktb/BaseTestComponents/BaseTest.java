package ktb.BaseTestComponents;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.beust.jcommander.internal.Console;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

//import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.github.bonigarcia.wdm.WebDriverManager;
import kt.pageobjects.LandingPage;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {
	public WebDriver d1;
	public LandingPage landingPage;
	public WebDriver initializeDriver() throws IOException
	{
		// properties class // here entire framewrok will run on chrome and we can control the complete framework execution.
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//kt//resources//GlobalData.properties");
		// we don't want to use local system pah, so in selenium system.getproperty give this location dynamically
		//local system path can not work in any other system, here user.dir will automatically get the project path
		// above objwct wil convert file to InputStream object.
		prop.load(fis ); // here file should be in stream.
		// now we can get any properies which we want to use from the file
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");
		//String browserName = prop.getProperty("browser");
		
		if(browserName.contains("chrome")) // if broser is chrome invoke this browser
		{
			//ChromeOptions options = new ChromeOptions(); // for headless options
		    WebDriverManager.chromedriver().setup();
		   // if(browserName.contains("headless"))
		  //  {
		 //   	 options.addArguments("headless"); // set headless
		 //   }
		   
		   // d1 = new ChromeDriver(options); // pass to driver
		    d1 = new ChromeDriver();
		 //  d1.manage().window().setSize(new Dimension(1440,900)); // standard size for browser full screen
		  //  d1.manage().window().setSize(new Dimension(1024,768)); // import dimension from selenium
		}
		else if (browserName.contains("firefox"))
		{
			// write code to invoke the firefox
			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\karis\\Downloads\\geckodriver-v0.34.0-win64\\geckodriver.exe");
			d1 = new FirefoxDriver();
		}
		else if (browserName.contains("edge"))
		{
			// write code to invoke the edge
			System.setProperty("webdriver.edge.driver", "C:\\Users\\karis\\Downloads\\edgedriver_win64\\edgedriver.exe");
			d1 = new EdgeDriver();
		}
		d1.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));  // implicit wait
		//d1.manage().window().maximize();
		return d1;
	}
	
	public List<HashMap<String, String>> getJasonDataToMap(String filePath) throws IOException
	{   // read json to string conert json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		//StandardCharsets.UTF_8 return our string to encoding format on how to convert into string
		//convert string to HashMap we need to use dependency Jackson Databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
		});
		//This data is a list with two arguments. In 1st argument will hv one hashmap,2nd hv another hashmap
		return data;
		
	}
	
	public File getScreenshot(String testCaseName, WebDriver d1) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)d1;
		File source = ts.getScreenshotAs(OutputType.FILE);
	 // we hv to givr output type in which we want ss
		File file = new File(System.getProperty("user.dir") + "//Reports//" + testCaseName +".png");
		FileUtils.copyFile(source, file);// store file ss in local, detination path as file object
		return file;
	}

	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		d1 = initializeDriver();
		landingPage = new LandingPage(d1); // created object for landing page
		landingPage.goTo();
		return landingPage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown()throws IOException
	{
		d1.quit();
	}

}
