package domen;

public class Bottle extends Product {
    private int volume;

    public Bottle(String nameProduct, String categoryProduct, double priceProduct, int quantityProduct, int volume) {
        super(nameProduct, categoryProduct, priceProduct, quantityProduct);
        this.volume = volume;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }


    /**
     * создает копию этого класса, с указанным кол-вом продукта
     * @param quantity - количество продукта
     * @return
     */
    @Override
    public Product CloneThis(int quantity){
        Product cloneProduct = new Bottle(super.getNameProduct(), super.getCategoryProduct(), super.getPriceProduct(),
                quantity, volume);

        return cloneProduct;
    }

    @Override
    public String toString() {
        return super.getNameProduct().equals("")? "Пусто" :
                super.getNameProduct()+ " - " +
                super.getQuantityProduct()+ " шт., "+
                volume+" л."+   "  Цена: "+super.getPriceProduct();

    }
}
