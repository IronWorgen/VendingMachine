package machine;

import domen.Product;

import java.util.Map;

public class Dispenser implements  ProductDispenser{
    /**
     * извлечь указанное количество продукта из машины
     * @param position - номер ячейки из которой надо извлечь продукт
     * @param quantity - количество продукта
     * @param products - карта ячеек машины
     */
    @Override
    public void retrieveProduct(int position, int quantity, Map<Integer, Product> products) {
        Product product = products.get(position);
        if (product.getQuantityProduct()<quantity){
            System.out.println(String.format("Недостаточно товара. В наличии только %d ед. товара", product.getQuantityProduct()));


        }else {
            product.setQuantityProduct(product.getQuantityProduct()-quantity);
            setPositionEmpty(position, products);
        }


    }


    /**
     * продукт добавляется в ячейку к продукту с таким же названием
     * если в машине нет такого продукта, он помещается в пустую ячейку
     * @param product - продукт, который надо разместить в машине
     * @param volume - вместимость ячейки машины
     * @param products - карта ячеек машины
     */
    @Override
    public void addProduct(Product product,int volume, Map<Integer, Product> products) {
        String name = product.getNameProduct();
        int quantity = product.getQuantityProduct();
        for (int item : products.keySet()) {
            if (quantity<=0){return;}
            if (products.get(item).getNameProduct().equals(name)){
                if (products.get(item).getQuantityProduct()<volume){
                    int freeSpace = volume-products.get(item).getQuantityProduct();
                    if (freeSpace>quantity){
                        products.get(item).setQuantityProduct(products.get(item).getQuantityProduct()+quantity);
                        return;
                    }else {
                        quantity = quantity - freeSpace;
                        products.get(item).setQuantityProduct(volume);
                    }
                }
            } else if (products.get(item).getNameProduct().equals("")) {
                if ( quantity<=volume){
                    products.put(item, product.CloneThis(quantity));
                    quantity = 0;
                }else {
                    products.put(item, product.CloneThis(volume));
                    quantity = quantity-volume;
                }
            }
        }
        if (quantity > 0) {
            System.out.println("Нет свободных ячеек " );
        }

    }

    /**
     * если в ячейке закончился товар сделать еe пустой
     * @param position - номер ячейки
     */
    private void setPositionEmpty(int position, Map<Integer, Product> products) {
        if ( products.get(position).getQuantityProduct()==0){
            products.get(position).setNameProduct("");
        }
    }
}
