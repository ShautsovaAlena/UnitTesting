package shop;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import parser.JsonParser;

import java.io.File;


public class TestShop {

    @BeforeClass
    public void setUp(){

    }

    @Test(groups = "item")
    public void testRealitemWeight(){
        double weight = 12.3;
        RealItem item = new RealItem();
        item.setWeight(weight);

        Assert.assertEquals(weight, item.getWeight());
    }

    @Test(groups = "item")
    public void testVirtualItem(){
        double size = 1024;
        VirtualItem item = new VirtualItem();
        item.setSizeOnDisk(size);

        Assert.assertEquals(size, item.getSizeOnDisk());
    }

    @Test(groups = "shop")
    public void testCartItem(){
        Cart cart = new Cart("test-cart");

        RealItem car = new RealItem();
        car.setName("Audi");
        car.setPrice(1000);
        car.setWeight(1560);

        cart.addRealItem(car);

        Assert.assertEquals(cart.getTotalPrice(), 1000 + 1000*0.2);
    }

    @Test(groups = "shop")
    public void testCartItemOperations(){
        Cart cart = new Cart("test-cart");

        RealItem car = new RealItem();
        car.setName("Audi");
        car.setPrice(1000);
        car.setWeight(1560);

        cart.addRealItem(car);
        cart.deleteRealItem(car);

        Assert.assertEquals(cart.getTotalPrice(), 0.0);
    }
}
