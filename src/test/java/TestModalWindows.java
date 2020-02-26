import taskPages.modalWindows.ButtonCategory;
import taskPages.modalWindows.MainePage;
import framework.browsers.BrowserActions;
import framework.logger.MyLogger;
import framework.utils.PropertiesManager;
import framework.utils.RandomGeneration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TestModalWindows {
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
    public void testModalWindows() {
        LOGGER.step(0, "Gp to the Page: http://the-internet.herokuapp.com/javascript_alerts");
        BrowserActions.goToPage(PropertiesManager.getProperties("URL4"));

        LOGGER.step(1, "click on button  'Click for JS Alert'");
        MainePage mainePage = new MainePage();
        mainePage.getBtn(ButtonCategory.ALERT).clickElement();
        assertEquals(mainePage.getAlert().getText(), "I am a JS Alert", "text in alert JS Alert is not equals");

        LOGGER.step(2, "In Alert, click 'OK'");
        mainePage.getAlert().accept();
        assertTrue(mainePage.getResultAlert(), "result is failed");

        LOGGER.step(3, "click on button 'Click for JS Confirm'");
        mainePage.getBtn(ButtonCategory.CONFIRM).clickElement();
        assertEquals(mainePage.getAlert().getText(), "I am a JS Confirm", "text in alert JS Confirm is not equals");

        LOGGER.step(4, "In Alert, click 'OK'");
        mainePage.getAlert().accept();

        LOGGER.step(5, "click on button 'Click for JS Prompt'");
        assertTrue(mainePage.getResultConfirm(), "You successfully clicked an confirm");
        mainePage.getBtn(ButtonCategory.PROMPT).clickElement();

        LOGGER.step(6, "Enter randomly generated text in the input field. Click 'OK'");
        String text = RandomGeneration.randomString(10);
        mainePage.getAlert().sendKeys(text);
        assertTrue(mainePage.getResultPrompt(text), "JS prompt text is not equals");
    }
}
