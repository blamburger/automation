package modules;

import org.openqa.selenium.By;

public class HeaderModule extends BaseModule{
    private By openBasket = new By.ByXPath("//div[@class='header state-retail']//a[@class='m-basket']");

    public void openBasket(){
        click(openBasket);
    }
}
