import java.util.regex.Pattern;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.gargoylesoftware.htmlunit.javascript.host.file.File;
import java.util.concurrent.TimeUnit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Selenium {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  public static void read() {
      try {
          
          InputStream inputStream = new FileInputStream("读取的文件");
          Workbook workbook = null;
          
          workbook = new XSSFWorkbook(inputStream);
          
          Sheet sheet = workbook.getSheetAt(0);
          
          map = new HashMap<String,String>();
          
          for(Row r : sheet) {
              r.getCell(0).setCellType(1);
              r.getCell(1).setCellType(1);
              String key = r.getCell(0).getStringCellValue();
              String value = r.getCell(1).getStringCellValue();
              value = value.trim();
              map.put(key, value);
          }
          
          
      } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
      }
      
  }
  
  @Before
  public void setUp() throws Exception {
	System.setProperty("webdriver.gecko.driver", "C:/User/PPIE/workspace/selenium/geckodriver.exe");
    driver = new FirefoxDriver();
    baseUrl = "https://psych.liebes.top/st";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testJiaoben() throws Exception {
    driver.get(baseUrl + "/st");
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("3015218090");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("218090");
    driver.findElement(By.id("submitButton")).click();
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
