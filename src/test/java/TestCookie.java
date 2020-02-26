import framework.browsers.BrowserActions;
import framework.cookies.CookieManager;
import framework.logger.MyLogger;
import framework.utils.PropertiesManager;
import org.openqa.selenium.Cookie;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TestCookie {
    private static MyLogger LOGGER = new MyLogger();

    @BeforeTest
    private void setUp() {
        PropertiesManager.loadProperties("config");
    }

    @AfterMethod
    private void afterTest(){
        BrowserActions.quitBrowser();
    }
    @Test
    public void testCookie() {
        LOGGER.step(1, "go to the Page : http://www.example.com");
        BrowserActions.getBrowser().get(PropertiesManager.getProperties("URL3"));

        LOGGER.step(2, "add three 3_cookie");
        Cookie cookie1 = new Cookie("example_key_1", "example_value_1");
        Cookie cookie2 = new Cookie("example_key_2", "example_value_2");
        Cookie cookie3 = new Cookie("example_key_3", "example_value_3");
        CookieManager.addCookie(cookie1);
        CookieManager.addCookie(cookie2);
        CookieManager.addCookie(cookie3);
        assertEquals(BrowserActions.getBrowser().manage().getCookieNamed("example_key_1"), cookie1,"cookie1 was not added");
        assertEquals(BrowserActions.getBrowser().manage().getCookieNamed("example_key_2"), cookie2,"cookie2 was not added");
        assertEquals(BrowserActions.getBrowser().manage().getCookieNamed("example_key_3"), cookie3,"cookie3 was not added");

        LOGGER.step(3, "delete 3_cookie with key example_key_1");
        CookieManager.deleteCookie("example_key_1");
        assertNull(CookieManager.getCookie("example_key_1"), "cookie1 was not deleted");

        LOGGER.step(4, "add value example_value_300 to 3_cookie with name example_key_3");
        Cookie cookie4 = new Cookie("example_key_3", "example_value_300");

        BrowserActions.getBrowser().manage().addCookie(cookie4);
        assertEquals(CookieManager.getCookie("example_key_3"), cookie4,"cookie3 was not changed");

        LOGGER.step(5, "delete all cookies");
        CookieManager.deleteAllCookie();
        assertTrue(BrowserActions.getBrowser().manage().getCookies().isEmpty(), "list cookies is not null");
    }

}
