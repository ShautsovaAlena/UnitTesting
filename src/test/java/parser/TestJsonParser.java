package parser;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import java.io.File;

public class TestJsonParser {
    private Cart srcCart;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        srcCart = new Cart("test-cart");

        RealItem car = new RealItem();
        car.setName("Audi");
        car.setPrice(32026.9);
        car.setWeight(1560);

        VirtualItem disk = new VirtualItem();
        disk.setName("Windows");
        disk.setPrice(11);
        disk.setSizeOnDisk(20000);

        srcCart.addRealItem(car);
        srcCart.addVirtualItem(disk);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        File file = new File("src/main/resources/" + srcCart.getCartName() + ".json");
        if (file.exists()) {
            file.delete();
        }
    }

    @Test(groups = "parser")
    public void json1Parser() {
        JsonParser parser = new JsonParser();
        parser.writeToFile(srcCart);

        Cart fromFileCart = parser.readFromFile(new File("src/main/resources/test-cart.json"));

        Assert.assertEquals(srcCart.getCartName(), fromFileCart.getCartName());
        Assert.assertEquals(srcCart.getTotalPrice(), fromFileCart.getTotalPrice());
    }

    @Test(expectedExceptions = FileNotFoundException.class,groups = "parser")
    public void json2Parser() {
        JsonParser parser = new JsonParser();
        parser.readFromFile(new File("src/main/resources/invalid-cart.json"));
    }

    @Test(groups = "parser")
    public void testValidCartName() {
        JsonParser parser = new JsonParser();
        Cart cart = new Cart(":;''^1234567890-+*&^%$#@!");
        parser.writeToFile(cart);
        Cart fromFileCart = parser.readFromFile(new File("src/main/resources/" + cart.getCartName() + ".json"));
        Assert.assertEquals(cart.getCartName(), fromFileCart.getCartName());
    }
}
