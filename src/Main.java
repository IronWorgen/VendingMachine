import domen.Bottle;
import domen.Product;
import machine.VendingMachine;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Product item1 = new Product("Lays", "Чипсы", 100, 15);
        item1.setPriceProduct(123);
        Product item2 = new Bottle("Cola", "Water", 60,15, 100);

        Product item3 = new Product("Масло", "Масло", 120, 3);

        VendingMachine machine = new VendingMachine(10, 9   );

        machine.addProduct(item1);
        machine.addProduct(item2);
        machine.addProduct(item3);

        GUI mainFrame = new GUI(machine);
        mainFrame.initialize();

    }
}