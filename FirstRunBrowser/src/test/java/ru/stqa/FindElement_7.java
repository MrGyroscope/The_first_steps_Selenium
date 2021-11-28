package ru.stqa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class FindElement_7 {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
    }

    @Test
    public void RoundClick () {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("My Store"));

        for (int n1=0; n1 < driver.findElements(By.cssSelector("#box-apps-menu #app-")).size(); n1=n1+1) {
            driver.findElements(By.cssSelector("#box-apps-menu #app-")).get(n1).click();
            driver.findElement(By.cssSelector("h1"));
            if (driver.findElements(By.cssSelector("#box-apps-menu #app- .docs")).size() != 0) {
                for (int n2 = 0; n2 < driver.findElements(By.cssSelector("#box-apps-menu #app- .docs li")).size(); n2=n2+1) {
                    driver.findElements(By.cssSelector("#box-apps-menu #app- .docs li")).get(n2).click();
                    driver.findElement(By.cssSelector("h1"));
                }
            }
        }
    }
    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
