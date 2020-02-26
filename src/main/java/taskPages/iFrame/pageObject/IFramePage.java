package taskPages.iFrame.pageObject;

import taskPages.iFrame.pageObject.editMenu.EditMenu;
import framework.elements.IFrame;
import framework.elements.Label;
import org.openqa.selenium.By;

public class IFramePage {
    private static String exampleLocator = "//div[@class = 'example']//h3";
    private static String iFrameLocator = "//iframe[@id = 'mce_0_ifr']";
    private static String title = "An iFrame containing the TinyMCE WYSIWYG Editor";

    public boolean isIFrame() {
        return new Label(By.xpath(exampleLocator), "application.1_iFrame title").getText().equals(title);
    }

    public IFrame getIFrame () {
        return new IFrame(By.xpath(iFrameLocator), "application.1_iFrame field");
    }

    public Frame getFrame () {
        return new Frame();
    }

    public EditMenu getEditMenu(){
        return new EditMenu();
    }
}
