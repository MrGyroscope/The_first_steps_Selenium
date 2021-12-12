package ru.stqa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class sort_and_geozone_9 {
    private WebDriver driver;
    private WebDriverWait wait;

    private boolean isElementPresent(By locator) {
    ///https://coderoad.ru/13909600/В-чем-же-заключается-альтернатива-selenium-isElementPresent-в-Webdriver
        try {
            driver.findElement(locator);
            return true;
        }
        catch (NoSuchElementException ex){
            return false;
        }
    }

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
    }

    @Test
    public void CheckСountries () {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("My Store"));
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        List<WebElement> Count_name = driver.findElements(By.cssSelector("tr.row a:not([title])"));
        ArrayList<String> Count_name_list = new ArrayList<>();
        for (int n1=0; n1<Count_name.size(); n1=n1+1){
            Count_name_list.add(n1, Count_name.get(n1).getText());
        //    System.out.println(Count_name_list.get(n));
        }
        ArrayList<String> Count_name_sort = new ArrayList<>();
        Count_name_sort = (ArrayList<String>) Count_name_list.clone();
        Collections.sort(Count_name_sort);

        if (Count_name_list.equals(Count_name_sort) == true){
            System.out.println("Страны в списке расположены в алфавитном порядке");
        }
        else         {
            System.out.println("Страны в списке расположены не в алфавитном порядке");
        }
        List <WebElement> Country = driver.findElements(By.cssSelector("tr.row"));
        for(int n2 = 0; n2 < Country.size(); n2=n2+1){
            if(Country.get(n2).getAttribute("textContent").contains("0") == false){
                Country.get(n2).findElement(By.cssSelector("a:not([title])")).click();
                assertTrue(isElementPresent(By.cssSelector("#table-zones")));
                List <WebElement> Zone_name = driver.findElements(By.cssSelector("td [name*=name][name*=zones]"));
                ArrayList <String> Zone_name_list = new ArrayList<>();
                for(int n3 = 0; n3 < Zone_name.size(); n3=n3+1){
                    Zone_name_list.add(n3, Zone_name.get(n3).getText());
                }
                ArrayList <String> Zone_name_sort = new ArrayList<>();
                Zone_name_sort = (ArrayList<String>) Zone_name_list.clone();
                Collections.sort(Zone_name_sort);
                if(Zone_name_list.equals(Zone_name_sort) == false){
                    System.out.println("Зоны расположены не в алфавитном порядке");
                }
                driver.navigate().back();
                Country = driver.findElements(By.cssSelector("tr.row"));
                }
        }
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        List <WebElement> GeoZones = driver.findElements(By.cssSelector("[name=geo_zones_form] td a:not([title])"));
        for (int n4 = 0; n4<GeoZones.size(); n4=n4+1){
            driver.findElements(By.cssSelector("[name=geo_zones_form] td a:not([title])")).get(n4).click();
            assertTrue(isElementPresent(By.cssSelector("#table-zones")));
            List<WebElement> GeoZones_name = driver.findElements(By.cssSelector("select[name*=zone_code]"));
            ArrayList<String> GeoZones_list = new ArrayList<>();
            for (int n5=0;n5<GeoZones_name.size();n5=n5+1) {
                GeoZones_list.add(GeoZones_name.get(n5).getText());
            }
            ArrayList<String> GeoZone_sort = new ArrayList<>();
            GeoZone_sort=(ArrayList<String>) GeoZones_list.clone();
            Collections.sort(GeoZone_sort);
            if (GeoZones_list.equals(GeoZone_sort)==false){
                System.out.println("Зоны расположены не в алфавитном порядке");
            }
            driver.navigate().back();
        }

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
