import parser.JsonParser;
import shop.*;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Cart andrewCart = new Cart("andrew-cart");

        RealItem car = new RealItem();
        car.setName("Audi");
        car.setPrice(32026.9);
        car.setWeight(1560);

        VirtualItem disk = new VirtualItem();
        disk.setName("Windows");
        disk.setPrice(11);
        disk.setSizeOnDisk(20000);

        andrewCart.addRealItem(car);
        andrewCart.addVirtualItem(disk);
        andrewCart.showItems();

        JsonParser parser = new JsonParser();
        parser.writeToFile(andrewCart);

        Cart eugenCart = parser.readFromFile(new File("src/main/resources/eugen-cart.json"));
        eugenCart.showItems();
    }
}
