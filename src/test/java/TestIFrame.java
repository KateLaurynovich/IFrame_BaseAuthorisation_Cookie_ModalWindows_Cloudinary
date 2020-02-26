import taskPages.iFrame.pageObject.editMenu.EditMenuCategory;
import taskPages.iFrame.models.TextIFame;
import taskPages.iFrame.pageObject.IFramePage;
import framework.browsers.BrowserActions;
import framework.files.FileManager;
import framework.logger.MyLogger;
import framework.utils.PropertiesManager;
import framework.utils.RandomGeneration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TestIFrame {
    private static MyLogger LOGGER = new MyLogger();

    @BeforeTest
    private void setUp() {
        PropertiesManager.loadProperties("config");
        FileManager.deleteDownloadFile(PropertiesManager.getProperties("path2"));
    }

    @AfterMethod
    private void afterTest(){
        BrowserActions.quitBrowser();
    }

    @Test
    public void testIFrame() {
        LOGGER.step(1, "Gp to the Page: http://the-internet.herokuapp.com/iframe");
        BrowserActions.goToPage(PropertiesManager.getProperties("URL"));
        IFramePage iFramePage = new IFramePage();
        assertTrue(iFramePage.isIFrame(), "id is not application.1_iFrame page");

        LOGGER.step(2, "Clear input field and enter randomly generated text");
        iFramePage.getIFrame().switchToIFrame();
        iFramePage.getFrame().getInput().clearInput();
        TextIFame textIFame = new TextIFame(RandomGeneration.randomString(20));
        iFramePage.getFrame().getInput().submitInput(textIFame.getText());
        assertEquals(textIFame.getText(), iFramePage.getFrame().getInput().getText(), "text is not equals");

        LOGGER.step(3, "Highlight the entered text and press the 'B' button");
        iFramePage.getFrame().getInput().doubleClick();
        BrowserActions.switchToDefaultContent();
        iFramePage.getEditMenu().getCategoryBtn(EditMenuCategory.BOLD).clickElement();
        iFramePage.getIFrame().switchToIFrame();
        assertEquals(iFramePage.getFrame().getStrongText(), textIFame.getText(), " text is not strong");
    }
}
