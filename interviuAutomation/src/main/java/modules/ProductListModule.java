package modules;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class ProductListModule extends BaseModule{

    private String addProductToBasket = "(//div[@class='product-list-item ']//a[@title='Add To Basket'])[%s]";
    private String getProductTitle = "(//div[@class='product-list-item ']//div[@class='p-list-title-wrapper']/h3/a)[%s]";
    private By continueShopping = new By.ByXPath("//div[@class='basket-addons2-right']//div[@class='close-pop']");

    public String addProductToBasket(int productIndex){
        By productTitle = new By.ByXPath(String.format(getProductTitle, productIndex));
        By addProductToBasket = new By.ByXPath(String.format(this.addProductToBasket, productIndex));
        String title = getText(productTitle);
        click(addProductToBasket);
        click(continueShopping);
        return title;
    }
}
