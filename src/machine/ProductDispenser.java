package machine;

import domen.Product;

import java.util.Map;

public interface ProductDispenser {

    /**
     * Уменьшить количество продуктов на позиции position, на количество quantity.
     * В случае нехватки продукта оповестить об этом.
     * @param position
     * @param quantity
     */
    void retrieveProduct(int position, int quantity, Map<Integer, Product> products);

    /**
     * добавить продукт в автомат
     * @param product
     */
    void addProduct(Product product,int volume, Map<Integer, Product> products);




}
