package demo;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.AWTException;
import java.util.logging.Level;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;
import org.openqa.selenium.support.ui.WebDriverWait; 
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */
    @Test
    public void testCase01() throws InterruptedException{
        System.out.println("Start TestCase01");
        String url = "https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform";
        Wrappers.navigate(driver,url);
        Thread.sleep(5000);

      // WebElement name =  driver.findElement(By.xpath("//div[@class='rFrNMe k3kHxc RdH0ib yqQS1 zKHdkd']//input"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // wait 1
        WebElement name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='rFrNMe k3kHxc RdH0ib yqQS1 zKHdkd']//input")));
        
       String keys = "Crio Learner";
       Wrappers.sendKeys(name, keys);
       Thread.sleep(1000);

        WebElement question =  driver.findElement(By.xpath("//div[@class='Pc9Gce Wic03c']//textarea"));
        long epochTime = System.currentTimeMillis() / 1000;
        String answer = "I want to be the best QA Engineer! "+epochTime;
        System.out.println(answer);
        Wrappers.sendKeys(question,answer);
        Thread.sleep(1000);

        WebElement radiobtn = driver.findElement(By.xpath("//div[@id='i19']//div[contains(@class,'AB7Lab Id5V1')]"));
        Wrappers.click(radiobtn,driver);
        Thread.sleep(1000);

        WebElement checkbox= driver.findElement(By.xpath("//div[@id='i37']//div[contains(@class,'uHMk6b fsHoPb')]"));
        Wrappers.click(checkbox,driver);
        Thread.sleep(1000);

        WebElement dropdown = driver.findElement(By.xpath("//span[normalize-space()='Choose']"));
        Wrappers.click(dropdown, driver);
         Thread.sleep(2000);
         driver.findElement(By.xpath("(//span[@class='vRMGwf oJeWuf'][normalize-space()='Mr'])[2]")).click();
        //  Actions actions = new Actions(driver);
        // actions.sendKeys(dropdown, Keys.ARROW_DOWN).perform();
        // actions.sendKeys(Keys.ENTER).perform();
        Thread.sleep(4000);

        // try {
        //     Robot robot = new Robot();
        //     robot.keyPress(KeyEvent.VK_DOWN);
        //     robot.keyRelease(KeyEvent.VK_DOWN);
        //     robot.keyPress(KeyEvent.VK_ENTER);
        //     robot.keyRelease(KeyEvent.VK_ENTER);
        //     Thread.sleep(6000);
            
        // } catch (AWTException e) {
        //     e.printStackTrace();
        // }

        
       WebElement date =  driver.findElement(By.xpath("//input[@type='date']"));
       LocalDate currentDate = LocalDate.now();
        LocalDate dateMinus7Days = currentDate.minusDays(7);
        String formattedDate = dateMinus7Days.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        // Input the dynamically calculated date into the field
        date.sendKeys(formattedDate);
       Thread.sleep(1000);

        WebElement hour =  driver.findElement(By.xpath("//input[@aria-label='Hour']"));
       String hourkeys = "07";
       Wrappers.sendKeys(hour, hourkeys);
        WebElement minute =  driver.findElement(By.xpath("//input[@aria-label='Minute']"));
       String minutekeys = "30";
        Wrappers.sendKeys(minute, minutekeys);
       Thread.sleep(2000);

       WebElement submitbtn = driver.findElement(By.xpath("//span[contains(text(),'Submit')]"));
       Wrappers.click(submitbtn,driver);
         Thread.sleep(3000);

         WebElement successMessage = driver.findElement(By.xpath("//div[contains(text(),'Thanks for your response')]"));

            String messageText = successMessage.getText();
            System.out.println("Success Message: " + messageText);

      
        System.out.println("End TestCase01");


    }
     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}