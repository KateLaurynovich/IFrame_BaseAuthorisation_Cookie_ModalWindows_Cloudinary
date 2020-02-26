package taskPages.iFrame.pageObject.editMenu;

import framework.elements.Button;
import org.openqa.selenium.By;

public class EditMenu {

    private static String categoryBtn = "//div[@id = 'mceu_%d']";

    public Button getCategoryBtn (EditMenuCategory editMenuCategory) {
        return new Button(By.xpath(String.format(categoryBtn,  editMenuCategory.getTitle())), editMenuCategory.toString());
    }
}
