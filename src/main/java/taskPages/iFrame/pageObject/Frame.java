package taskPages.iFrame.pageObject;

import framework.elements.Input;
import framework.elements.Label;
import org.openqa.selenium.By;

public class Frame {
    private static String inputFieldLocator = "//body[@id = 'tinymce']";
    private static String strongText = "//strong";

    public Input getInput() {
        return new Input(By.xpath(inputFieldLocator), "input");
    }

    public String getStrongText() {
        return new Label(By.xpath(strongText), "strongText").getText();
    }
}
