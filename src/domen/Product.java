package domen;

public class Product {

    private String nameProduct;
    private String categoryProduct;
    private double priceProduct;
    private int quantityProduct;


    public Product(String nameProduct, String categoryProduct, double priceProduct, int quantityProduct ) {
        this.nameProduct = nameProduct;
        this.categoryProduct = categoryProduct;
        this.priceProduct = priceProduct;
        this.quantityProduct=quantityProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public String getCategoryProduct() {
        return categoryProduct;
    }

    public Double getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(double priceProduct) {
        if (priceProduct <= 0) {
            throw new IllegalStateException(String.format("Цена указана не корректно"));
        }
        this.priceProduct = priceProduct;
    }


    public int getQuantityProduct() {return this.quantityProduct;}

    public void setQuantityProduct(int quantityProduct) {this.quantityProduct = quantityProduct;}

    /**
     * создает копию этого класса, с указанным кол-вом продукта
     * @param quantity - количество продукта
     * @return
     */
    public Product CloneThis(int quantity){
        Product cloneProduct = new Product(nameProduct, categoryProduct, priceProduct,quantity);

        return cloneProduct;
    }

    @Override
    public String toString() {
        return nameProduct.equals("")? "Пусто" : nameProduct +

                " - " +quantityProduct+ "шт. " + " Цена: " + priceProduct;
    }

}
