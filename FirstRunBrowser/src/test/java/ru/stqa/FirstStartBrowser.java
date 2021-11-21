package ru.stqa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class FirstStartBrowser {

        private WebDriver driver;
        private WebDriverWait wait;

        @Before
        public void start() {
            driver = new ChromeDriver();
            wait = new WebDriverWait(driver, 20);
        }
        @Test
        public void FirstStartBrowser(){
            driver.get("http://www.google.com/");

            driver.findElement(By.name("q")).sendKeys("webdriver"  + "\n");
            //Добавил  + "\n", так как не находил элемент btnG, не смог победить
            //driver.findElement(By.name("btnG")).click();
            wait.until(titleIs("webdriver - Поиск в Google"));

        }
        @After
        public void stop() {
            driver.quit();
            driver = null;
                    }
}
