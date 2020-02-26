import framework.browsers.BrowserActions;
import framework.cloudinary.CloudPhoto;
import framework.cloudinary.CloudinaryRes;
import framework.files.CompareFile;
import framework.files.EqualsPicture;
import framework.files.FileManager;
import framework.logger.MyLogger;
import framework.utils.PropertiesManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TestCloudinary {
    private static MyLogger LOGGER = new MyLogger();

    @BeforeTest
    private void setUp() {
        PropertiesManager.loadProperties("config");
        FileManager.deleteDownloadFile(PropertiesManager.getProperties("path2"));
        FileManager.deleteDownloadFile(PropertiesManager.getProperties("path"));
    }

    @AfterMethod
    private void afterTest() {
        BrowserActions.quitBrowser();
    }

    @Test
    public void testCloud() {

        LOGGER.step(1, "Organize the launch of any WEB-UI autotest (test task).");
        new TestIFrame().testIFrame();

        LOGGER.step(2, "Cloudinary take a screenshot and upload it to the service");
        BrowserActions.takeScreenshot(PropertiesManager.getProperties("path"));
        CloudPhoto cloudPhoto = CloudinaryRes.getResultUploadFile(PropertiesManager.getProperties("path"), PropertiesManager.getProperties("CloudName"),
                PropertiesManager.getProperties("APIKey"), PropertiesManager.getProperties("APISecret"));

        LOGGER.step(3, "Check that the uploaded and uploaded images are identical.");
        FileManager.saveImg(cloudPhoto.getUrl(), PropertiesManager.getProperties("path2"));
        assertEquals(EqualsPicture.compareImages(PropertiesManager.getProperties("path2"), PropertiesManager.getProperties("path")), CompareFile.FULL.getTitle(),
                "screenshots are not equals");
    }
}
