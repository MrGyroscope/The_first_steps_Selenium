package ru.stqa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;


import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class Shopping_basket_13 {
    private WebDriver driver;
    private WebDriverWait wait;

    private boolean SearchElement (By locator) {
        try {
            driver.findElement(locator);
            return true;
        }
        catch (NoSuchElementException ex){
            return false;
        }
    }

    @Before
    public void start(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,20);
    }

    @Test
    public void Shop (){
        for (int n=0; n<4;n=n+1){
            driver.get("http://localhost/litecart/en/");
            driver.findElement(By.cssSelector(".hover-light")).click();
            driver.findElement(By.name("add_cart_product")).click();
                   //желтая уточка, гори в аду
            if (SearchElement(By.cssSelector("[name='options[Size]']"))) {
                driver.findElement(By.cssSelector("[name='options[Size]']")).click();
                driver.findElement(By.cssSelector("[name='options[Size]']")).sendKeys(Keys.DOWN);
                driver.findElement(By.cssSelector("[name='options[Size]']")).sendKeys(Keys.ENTER);
                driver.findElement(By.name("add_cart_product")).click();
            }
            wait.until(ExpectedConditions.attributeContains(By.cssSelector("#cart .quantity"), "textContent", Integer.toString(n)));
            driver.navigate().back();
        }
        driver.get("http://localhost/litecart/en/checkout");
        wait.until(titleIs("Checkout | My Store"));

        WebElement Table = driver.findElement(By.cssSelector("#box-checkout-summary"));
        List <WebElement> Table_summary = Table.findElements(By.cssSelector("td.item"));
        int n = Table_summary.size();
        while (true){
            if(SearchElement(By.cssSelector("button[value=Remove]"))){
                WebElement Button = driver.findElement(By.cssSelector("button[value=Remove]"));
                wait.until(visibilityOf(Button));
                Button.click();
                wait.until(numberOfElementsToBe(By.cssSelector("td.item"), n-1));
                n=n-1;
                continue;
            }
            else{
                wait.until(ExpectedConditions.stalenessOf(Table));
                break;
            }
        }
     }
    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
        }

