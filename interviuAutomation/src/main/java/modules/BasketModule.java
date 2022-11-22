package modules;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class BasketModule extends BaseModule{
    private By getItemTitles = new By.ByXPath("//div[@class='delivery-group-product ']//td[@class='delivery-group-title']//h3/a");
    private String getSpecificItemTitle = "(//div[@class='delivery-group-product ']//td[@class='delivery-group-title']//h3/a)[%s]";
    private String deleteItem = "(//div[@class='delivery-group-product ']//td[@class='delivery-group-quantity']//p[text()='DELETE'])[%s]";

    public List<String> getAllItemTitles(){
        List<String> itemTitles = new ArrayList<>();
        List<WebElement> itemTitlesElements = driver.findElements(getItemTitles);
        for (WebElement elem: itemTitlesElements) {
            itemTitles.add(getText(elem));
        }
        return itemTitles;
    }

    public String deleteItem(int itemIndex){
        By specificItemTitle = new By.ByXPath(String.format(getSpecificItemTitle, itemIndex));
        By deleteSpecificItem = new By.ByXPath(String.format(deleteItem, itemIndex));
        String itemTitle = getText(specificItemTitle);
        click(deleteSpecificItem);
        return itemTitle;
    }
}
