package ru.stqa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class FindStiker_8 {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
    }

    @Test
    public void CheckSticker() {
        driver.get("http://localhost/litecart/en/");
        List<WebElement> Ducks = driver.findElements(By.cssSelector("li.product.column.shadow.hover-light"));
        for (int n = 0; n < Ducks.size(); n = n + 1) {
            WebElement AnySticker = Ducks.get(n);
            int m=n+1;
            if (AnySticker.findElements(By.cssSelector(".sticker")).size() != 1) {
                System.out.println("У " + m + " по счету товара на странице нет стикера, либо присутствует более одного");
            } else {
                System.out.println("У " + m + " по счету товара на странице один стикер");
            }
        }
    }
    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}