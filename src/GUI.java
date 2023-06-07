import domen.Product;
import machine.VendingMachine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class GUI {
    private VendingMachine vendingMachine;

    private JTextField positionProduct = new JTextField(2);
    private JTextField quantityField = new JTextField(2);

    private JTextField cash = new JTextField(5);

    private JButton buyButton = new JButton("Купить");


    public GUI(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }


    public void initialize() {
        int width = 800;
        int height = 500;

        JFrame mainWindow = new JFrame("Автомат");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setSize(width, height);


        //расположение товара и кнопки покупки
        Box box1 = Box.createHorizontalBox();


        //номера ячеек---------------------------------
        JPanel panelProduct = new JPanel();
        panelProduct.setBackground(new Color(0, 255, 255));


        panelProduct.setLayout(new GridLayout(vendingMachine.getSizeMachine() / groupProductInMachine(),
                groupProductInMachine(),
                20, 40));
        for (int i = 1; i <= vendingMachine.getSizeMachine(); i++) {
            JPanel labelPanel = new JPanel();
            labelPanel.setLayout(new BorderLayout());
            labelPanel.setBackground(new Color(255, 140, 0));
            JLabel label = new JLabel(String.format("%d", i));
            label.setHorizontalAlignment(JLabel.CENTER);

            labelPanel.add(label, BorderLayout.CENTER);
            panelProduct.add(labelPanel);
        }
        box1.add(panelProduct);
        box1.add(Box.createHorizontalStrut(10));

        //покупка товара --------------------------------------
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new GridLayout(5, 1, 10, 10));
        newPanel.setBackground(new Color(255, 250, 205));
        newPanel.add(new JLabel());

        JPanel positionProductPanel = new JPanel();
        positionProductPanel.setLayout(new FlowLayout());
        positionProductPanel.setBackground(new Color(255, 250, 205));
        positionProductPanel.add(new JLabel("Номер ячейки: "));
        positionProductPanel.add(positionProduct);
        newPanel.add(positionProductPanel);


        JPanel quantityProductPanel = new JPanel();
        quantityProductPanel.setLayout(new FlowLayout());
        quantityProductPanel.setBackground(new Color(255, 250, 205));

        quantityProductPanel.add(new JLabel("Количество: "));
        quantityProductPanel.add(quantityField);
        newPanel.add(quantityProductPanel);

        JPanel buyButtonProductPanel = new JPanel();
        buyButtonProductPanel.setLayout(new FlowLayout());
        buyButtonProductPanel.setBackground(new Color(255, 250, 205));

        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int position = Integer.parseInt( positionProduct.getText());
                int quantity = Integer.parseInt( quantityField.getText());

                String result = vendingMachine.CanTakeProducts(position, quantity);


                if (!result.equals("")){
                    JOptionPane.showMessageDialog(new JButton(),result, "Внимание",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                mainWindow.dispose();
                showPaymentWindow(position, quantity);
            }
        });

        buyButtonProductPanel.add(buyButton);
        newPanel.add(buyButtonProductPanel);

        newPanel.add(new JLabel());

        box1.add(newPanel);

        // список продукции ----------------------------------------------------------
        JPanel laPanel = new JPanel();
        laPanel.setBackground(new Color(222, 184, 135));
        laPanel.setLayout(new GridLayout(vendingMachine.getSizeMachine() + 1, 1));
        showListProduct(laPanel);


        Box mainBox = Box.createVerticalBox();
        mainBox.setBackground(new Color(222, 184, 135));
        mainBox.add(box1);
        mainBox.add(Box.createVerticalStrut(10));
        mainBox.add(laPanel);


        mainWindow.setContentPane(mainBox);


        mainWindow.setVisible(true);

    }

    /**
     * размещает на панели список продуктов в формате ("номер ячейки -> продукт и количество")
     *
     * @param panel
     */
    private void showListProduct(JPanel panel) {
        JLabel label = new JLabel("Продукты: ");
        label.setFont(new Font("Arial", Font.PLAIN, 18));
        panel.add(label);
        Map<Integer, Product> products = vendingMachine.getProducts();
        for (Integer item : products.keySet()) {
            JLabel newLabel = new JLabel(String.format("%d -> %s", item, products.get(item)));
            newLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            panel.add(newLabel);

        }
    }


    /**
     * вычислить сколько сделать столбцов из ячеек
     *
     * @return
     */
    private int groupProductInMachine() {
        int size = vendingMachine.getSizeMachine();
        for (int i = 3; i < 10; i++) {
            if (size % i == 0) {
                return i;
            }
        }
        return 3;
    }

    /**
     * открывает окно оплаты продукта
     */
    public void showPaymentWindow(int position, int quantity){
        int width = 600;
        int height = 400;

        JFrame paymentWindow = new JFrame("Оплата");
        paymentWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        paymentWindow.setSize(width, height);

        // Чек

        JPanel checkPanel =new JPanel();
        checkPanel.add(new JLabel(vendingMachine.getProducts().get(position).getNameProduct()));
        checkPanel.add(new JLabel(quantity+"шт."));
        double price = vendingMachine.getProducts().get(position).getPriceProduct()*quantity;
        checkPanel.add(new JLabel(Double.toString(price)+"руб."));


        //Ввод средств


        JPanel cashPanel =new JPanel();
        cashPanel.add(new JLabel("Внесите средства"));
        cashPanel.add(cash);

        //подтверждение оплаты

        JPanel buttonsPanel = new JPanel();

        JButton backButton = new JButton("Назад");
        JButton buy = new JButton("Оплатить");

        //кнопка Оплатить
        buy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!vendingMachine.canBuy(Double.parseDouble(cash.getText()), price)){
                    JOptionPane.showMessageDialog(new JButton(),
                            String.format("Нехватает %d руб.",(int)(price- Integer.parseInt(cash.getText()))),
                            "Внимание",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if(price<Double.parseDouble(cash.getText())){
                    JOptionPane.showMessageDialog(new JButton(),
                            String.format("Ваша сдача %d руб. ",(int)(Double.parseDouble(cash.getText())-price)),
                            "Внимание",
                            JOptionPane.WARNING_MESSAGE);

                }
                vendingMachine.takeProduct(position, quantity);
                paymentWindow.dispose();
                initialize();
            }
        });
        //кнопка назад
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paymentWindow.dispose();
                initialize();
            }
        });

        buttonsPanel.add(backButton);
        buttonsPanel.add(buy);


        Box mainBox = Box.createVerticalBox();

        mainBox.add(checkPanel);
        mainBox.add(cashPanel);
        mainBox.add(buttonsPanel);


        paymentWindow.setContentPane(mainBox);
        paymentWindow.setVisible(true);

    }
}
