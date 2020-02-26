package taskPages.baseAuthorisation;

import framework.api.HttpConnection;
import com.fasterxml.jackson.databind.ObjectMapper;
import framework.logger.MyLogger;
import framework.utils.PropertiesManager;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.StringReader;

public class BaseAuthorization {
    private static final MyLogger LOGGER = new MyLogger();
    public static ResultAuthorization authorization(String url, String user, String password) {
        JSONObject jsonObject = HttpConnection.getJSONObject(HttpConnection.getResponseAuth(url, user, password));
        StringReader reader = new StringReader(jsonObject.toString());
        ResultAuthorization resultAuthorization = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            resultAuthorization = mapper.readValue(reader, ResultAuthorization.class);
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }
        LOGGER.info("caseResult  = ", resultAuthorization);
        return resultAuthorization;
    }
}
