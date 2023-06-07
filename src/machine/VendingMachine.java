package machine;

import domen.Product;

import java.util.HashMap;
import java.util.Map;

public class VendingMachine  {
    /** максимальная вместимость одной ячейки     */
    private int volume;
    /** количество ячеек     */
    private int sizeMachine;

    /** расположение продуктов в ячейках    */
    private Map<Integer, Product> products;

    /** модуль размещения продукции в автомате     */
     private ProductDispenser dispenser;
    /** модуль оплаты товара  */
    private  Payment payment;


    public VendingMachine(int volume, int sizeMachine/*, Map<Integer, Product> products*/) {
        products = new HashMap<>();
        this.volume = volume;
        this.sizeMachine = sizeMachine;
        //this.products = products;
        this.dispenser = new Dispenser();
        payment = new PaymentMachine();

        for (int i = 1; i <=sizeMachine; i++) {
            products.put(i, new Product("", "", 0, 0));
        }

    }

    /**
     * добавить продукт в автомат
     * @param product - продукт, который надо добавить
     */
    public void  addProduct(Product product){
        dispenser.addProduct(product,volume, products);
    }

    /**
     * Получить товар
     * @param position - название ячейки
     * @param quantity - количество товара
     */

    public void takeProduct(int position, int quantity){
        dispenser.retrieveProduct(position, quantity, products);
    }



    /**
     * проверка есть ли в наличии такое количество товара
     * @param position
     * @param quantity
     * @return пустую строку если есть, иначе вернуть сообщение (сколько в наличии)
     */
    public String CanTakeProducts(int position, int quantity){

        if (position>sizeMachine){
            return "Неверный номер ячейки";
        }
        Product product = products.get(position);
        if (product.getQuantityProduct()<quantity){
           return String.format("Недостаточно товара. В наличии только %d ед. товара", product.getQuantityProduct());
        }
        return "";
    }

    /**
     * проверка хвaтает ли внесенных денег для покупки
     * @return
     */
    public boolean canBuy(double cash, double price){
        if(payment.takeCash(cash,price)){
            return true;
        }
        return false;
    }

    /**
     * вернуть сдачу
     * @param cash
     * @param price
     * @return
     */

    public  double  giveCashBack(double cash, double price){
        return payment.giveChange(cash,price);
    }


    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getSizeMachine() {
        return sizeMachine;
    }

    public void setSizeMachine(int sizeMachine) {
        this.sizeMachine = sizeMachine;
    }

    public Map<Integer, Product> getProducts() {
        for (Integer integer : products.keySet()) {
            System.out.println(products.get(integer).toString());
        }
        return products;
    }

    public void setProducts(Map<Integer, Product> products) {
        this.products = products;
    }
}
