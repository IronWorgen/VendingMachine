package machine;

public interface Payment {
    /**
     * принять деньги, проверить достаточно ли внесенной суммы для покупки
     * @param cash - полученная сумма
     * @param price - стоимость товара
     * @return
     */
    boolean takeCash(double cash, double price);

    /**
     * вернуть сдачу
     * @param cash - полученная сумма
     * @param price - стоимость товара
     * @return
     */
    double giveChange(double cash, double price);
}
