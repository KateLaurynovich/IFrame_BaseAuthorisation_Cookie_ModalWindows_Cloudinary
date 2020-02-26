
import framework.logger.MyLogger;
import framework.utils.PropertiesManager;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import taskPages.baseAuthorisation.BaseAuthorization;
import taskPages.baseAuthorisation.ResultAuthorization;

import static org.testng.Assert.assertEquals;

public class BasicAuthorization {
    private static MyLogger LOGGER = new MyLogger();

    @BeforeTest
    private void setUp() {
        PropertiesManager.loadProperties("config");
    }

    @Test
    public void testBasicAuthorisation () {
        LOGGER.step(1, "Gp to the Page: https://httpbin.org/basic-auth/user/passwd");

        ResultAuthorization resultAuthorization = BaseAuthorization.authorization(PropertiesManager.getProperties("URL2"), PropertiesManager.getProperties("user"),
                PropertiesManager.getProperties("password"));
        assertEquals(resultAuthorization.getUser(), PropertiesManager.getProperties("user"), " users are not equals");
        assertEquals(resultAuthorization.getAuthenticated(), true, "result is false");
    }
}
