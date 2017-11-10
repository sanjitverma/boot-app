package com.example.verma.bootapp.controller;

import com.example.verma.bootapp.BootAppApplication;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * Created by SANJIT on 09/11/17.
 */

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootAppApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServerWebTest {

    private static ChromeDriver chromeDriver;

    @Value("${local.server.port}")
    private int port;

    @BeforeClass
    public static void openBrowser() {

        final File file = new File("/Users/SANJIT/boot-app/src/test/java/resources/chromedriver");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        chromeDriver = new ChromeDriver();
        chromeDriver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
    }

    @Test
    public void testaddBookToList() {
        String baseURL = "http://localhost:" + port + "/sanjit";
        chromeDriver.get(baseURL);
        assertEquals("You have no books in your book list",
                chromeDriver.findElementByTagName("div").getText());
        chromeDriver.findElementByName("title").sendKeys("BOOK TITLE");
        chromeDriver.findElementByName("author")
                .sendKeys("BOOK AUTHOR");
        chromeDriver.findElementByName("isbn")
                .sendKeys("1234567890");
        chromeDriver.findElementByName("description")
                .sendKeys("DESCRIPTION");
        chromeDriver.findElementByTagName("form")
                .submit();

        WebElement dl = chromeDriver.findElementByCssSelector("dt.bookHeadline");
        assertEquals("BOOK TITLE by BOOK AUTHOR (ISBN: 1234567890)", dl.getText());
        WebElement dt = chromeDriver.findElementByCssSelector("dd.bookDescription");
        assertEquals("DESCRIPTION", dt.getText());
    }

    @AfterClass
    public static void closeBrowser() {
        chromeDriver.quit();
    }

}
