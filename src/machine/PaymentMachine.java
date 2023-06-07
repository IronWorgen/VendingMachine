package machine;

public class PaymentMachine implements Payment{

    @Override
    public boolean takeCash(double cash, double price) {
        if (price>cash){
            return false;
        }
        return true;
    }

    @Override
    public double giveChange(double cash, double price) {
        return cash - price;

    }
}
