package parser;

import com.google.gson.Gson;
import shop.Cart;

import java.io.*;
import java.io.FileNotFoundException;

public class JsonParser implements Parser {

    private Gson gson = new Gson();

    public void writeToFile(Cart cart) {
        try (FileWriter writer = new FileWriter("src/main/resources/" + cart.getCartName() + ".json")) {
            writer.write(gson.toJson(cart));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Cart readFromFile(File file) {
        BufferedReader reader = null;
        Cart cart = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            cart = gson.fromJson(reader.readLine(), Cart.class);
        } catch (FileNotFoundException ex) {
            throw new parser.FileNotFoundException(String.format("File %s.json not found!", file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cart;
    }
}
