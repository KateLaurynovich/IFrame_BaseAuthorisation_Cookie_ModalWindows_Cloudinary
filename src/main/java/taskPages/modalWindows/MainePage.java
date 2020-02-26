package taskPages.modalWindows;

import framework.elements.JsAlert;
import framework.elements.Button;
import framework.elements.Label;
import org.openqa.selenium.By;

public class MainePage {
    private static String buttonLocator = "//button[@onclick='%s']";
    private static String resultLocator = "//p[@id = 'result']";
    private static String resultAlert = "You successfuly clicked an alert";
    private static String resultConfirm = "You clicked: Ok";
    private static String resultPrompt = "You entered: %s";

    public Button getBtn(ButtonCategory buttonCategory) {
        return new Button(By.xpath(String.format(buttonLocator, buttonCategory.getTitle())), buttonCategory.toString());
    }

    public JsAlert getAlert() {
        return new JsAlert();
    }

    public boolean getResultAlert() {
        return new Label(By.xpath(resultLocator), "result").getText().equals(resultAlert);
    }

    public boolean getResultPrompt(String text) {
        return new Label(By.xpath(resultLocator), "result").getText().equals(String.format(resultPrompt, text));
    }

    public boolean getResultConfirm() {
        return new Label(By.xpath(resultLocator), "result").getText().equals(resultConfirm);
    }
}
