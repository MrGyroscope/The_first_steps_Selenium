package ru.stqa;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.lang3.RandomStringUtils;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class RegistrationNewUser_11 {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
    }

    @Test
    public void Registration() {
     //   String r = RandomStringUtils.randomAlphabetic(5);
        //  System.out.println(r);
        driver.get("http://localhost/litecart/en/create_account");
        driver.findElement(By.name("firstname")).sendKeys(RandomStringUtils.randomAlphabetic(5));
        driver.findElement(By.name("lastname")).sendKeys(RandomStringUtils.randomAlphabetic(5));
        driver.findElement(By.name("address1")).sendKeys(RandomStringUtils.randomAlphabetic(5));
        driver.findElement(By.name("address1")).sendKeys(RandomStringUtils.randomAlphabetic(5));
        driver.findElement(By.name("postcode")).sendKeys(RandomStringUtils.randomNumeric(5));
        driver.findElement(By.name("city")).sendKeys(RandomStringUtils.randomAlphabetic(5));
        driver.findElement(By.cssSelector(".select2-selection__arrow")).click();
        driver.findElement(By.cssSelector(".select2-search__field")).sendKeys("United States" + Keys.ENTER);
        String Email = RandomStringUtils.randomAlphabetic(5)+"@mail.com";
        driver.findElement(By.name("email")).sendKeys(Email);
        driver.findElement(By.name("phone")).sendKeys(RandomStringUtils.randomNumeric(7));
        String Password = RandomStringUtils.randomAlphanumeric(9);
        driver.findElement(By.name("password")).sendKeys(Password);
        driver.findElement(By.name("confirmed_password")).sendKeys(Password);
        driver.findElement(By.name("create_account")).click();
        wait.until(titleIs("Online Store | My Store"));
        driver.get("http://localhost/litecart/en/logout");
        driver.findElement(By.name("email")).sendKeys(Email);
        driver.findElement(By.name("password")).sendKeys(Password);
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("Online Store | My Store"));
        driver.get("http://localhost/litecart/en/logout");
            }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
