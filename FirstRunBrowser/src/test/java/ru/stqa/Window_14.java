package ru.stqa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class Window_14 {
    private WebDriver driver;
    private WebDriverWait wait;

        public ExpectedCondition<String> thereIsWindowOtherThan(final Set<String> oldW) //на вход получим множество окон до клика
    {
        return new ExpectedCondition<String>() {
            public String apply(WebDriver input) {
                Set<String> newW = driver.getWindowHandles();   //получим множество окон после клика
                newW.removeAll(oldW);                           //вычтем из нового множества старое
                if(newW.size()>0) {                             //если множества различны (т.е. после клика появились новые окна), вернем первое, иначе - NULL
                    return newW.iterator().next();
                }else
                {
                    return null;
                }
            }
        };
    }

    @Before
    public void start(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,20);
    }
    @Test
    public void open_link (){
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("My Store"));
        driver.get("http://localhost/litecart/admin/?app=countries&doc=edit_country");
        wait.until(titleIs("Add New Country | My Store"));

        String mainWindow = driver.getWindowHandle();
        Set<String> oldWindows = driver.getWindowHandles();
        List<WebElement> Links = driver.findElements(By.cssSelector("td#content i.fa.fa-external-link"));
        for (int n=0; n<Links.size(); n=n+1){
            Links.get(n).click();
            String newWindow = wait.until(thereIsWindowOtherThan(oldWindows));
            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(mainWindow);
                    }
    }
    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
