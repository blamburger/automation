import modules.BaseModule;
import modules.BasketModule;
import modules.HeaderModule;
import modules.ProductListModule;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class BasketItemsTest {

    private ProductListModule productListModule;
    private HeaderModule headerModule;
    private BasketModule basketModule;
    private BaseModule baseModule;

    @Before
    public void setUp(){
        productListModule = new ProductListModule();
        headerModule = new HeaderModule();
        basketModule = new BasketModule();
        baseModule = new BaseModule();
    }

    @After
    public void tearDown(){
        baseModule.quitDriver();
    }

    @Test
    public void addItemsToBasket(){
        baseModule.goToUrl("https://www.box.co.uk/gaming-laptops");
        String prod1Name = productListModule.addProductToBasket(1);
        String prod2Name = productListModule.addProductToBasket(2);
        headerModule.openBasket();
        List<String> basketItemTitles = basketModule.getAllItemTitles();
        Assert.assertTrue(basketItemTitles.contains(prod1Name));
        Assert.assertTrue(basketItemTitles.contains(prod2Name));
        basketModule.deleteItem(1);
        Assert.assertFalse(basketModule.getAllItemTitles().contains(prod1Name));
        basketModule.deleteItem(2);
        Assert.assertFalse(basketModule.getAllItemTitles().contains(prod2Name));
    }
}
