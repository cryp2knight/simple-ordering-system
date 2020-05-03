/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderingsystem;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dell
 */
public class OrderingSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
            UIManager.setLookAndFeel(info.getClassName());
            break;
        }
    }
} catch (Exception e) {
    // If Nimbus is not available, you can set the GUI to another look and feel.
}
        
        ArrayList<Meal> meals = new ArrayList<Meal>();
        meals.add(new Meal("Regular Burger",45));
        meals.add(new Meal("Burger with Cheese",55));
        meals.add(new Meal("Burger with egg and cheese",50));
        meals.add(new Meal("Burger with ham",60));
        meals.add(new Meal("Burger with ham and cheese",70));
        meals.add(new Meal("Regular Fries",35));
        meals.add(new Meal("Medium Fries",45));
        meals.add(new Meal("Large Fries",55));
        meals.add(new Meal("Choco Sundae",28));
        meals.add(new Meal("Caramel Sundae",28));
        meals.add(new Meal("Strawberry Sundae",28));
        meals.add(new Meal("Regular Coke",25));
        meals.add(new Meal("Large Coke",35));
        meals.add(new Meal("Regular Sprite",28));
        meals.add(new Meal("Large Sprite",38));
        
        ArrayList<Order> orders = new ArrayList<Order>();
        ArrayList<SalesReport> sales = new ArrayList<SalesReport>();
        
        //password part
        Password p = new Password("admin");
        String[] options = new String[]{"Cancel", "Ok"};
        PasswordPanel pnlPass = new PasswordPanel();

        while (true){
            int option = JOptionPane.showOptionDialog(null, pnlPass, "Login",
                                 JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                                 null, options, options[1]);
            if(option == 1){
                String password = pnlPass.jPasswordField1.getText();
                if (!password.equals(p.getPassword())){
                    p.tryAgain();
                    if (p.getTries() <= 0){
                        System.exit(0);
                    }
                }else{
                    break;
                }
            }        
        }
        System.out.print("Hello");
        
        while (true){
        MenuPanel m = new MenuPanel();
        int menu = JOptionPane.showOptionDialog(null, m, "Menu",
                                 JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                                 null, options, options[1]);
        int menuChoice = m.jComboBox1.getSelectedIndex();
        if(menu == 1){
            if (menuChoice == 0){
                OrderMealPanel op = new OrderMealPanel();
                //converting arraylist to 2d array
                Object[][] tbdata = new Object[meals.size()][3];
                for (int i = 0; i < meals.size(); i++){
                    tbdata[i][0] = i;
                    tbdata[i][1] = meals.get(i).getMeal();
                    tbdata[i][2] = meals.get(i).getPrice();
                }
                op.jTable1.setModel(new javax.swing.table.DefaultTableModel(
                    tbdata,
                    new String [] {
                        "Meal ID#", "Meal", "Price"
                    }
                ));

                int orderPanel = JOptionPane.showOptionDialog(null, op, "Order Meal",
                                     JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                                     null, options, options[1]);
                if(orderPanel == 1){
                    try{
                    String ordered_meal = meals.get(Integer.valueOf(op.txtMealID.getText())).getMeal();
                    double ordered_price = meals.get(Integer.valueOf(op.txtMealID.getText())).getPrice();
                    int quantity = Integer.valueOf(op.txtQuantity.getText());
                    orders.add(new Order(ordered_meal, ordered_price, quantity));
                    System.out.println(orders.get(0).getAmount());
                    }catch(Exception e){
                        JOptionPane.showMessageDialog(null, "Invalid Input");
                    }
                }
            }else if(menuChoice == 1){
                OrdersList ol = new OrdersList();
                //converting arraylist to 2d array
                Object[][] tbdata = new Object[orders.size()][5];
                double totalAmount = 0;
                for (int i = 0; i < orders.size(); i++){
                    tbdata[i][0] = i;
                    tbdata[i][1] = orders.get(i).getMeal();
                    tbdata[i][2] = orders.get(i).getPrice();
                    tbdata[i][3] = orders.get(i).getQuantity();
                    tbdata[i][4] = orders.get(i).getAmount();
                    totalAmount += orders.get(i).getAmount();
                }
                ol.jTable1.setModel(new javax.swing.table.DefaultTableModel(
                    tbdata,
                    new String [] {
                        "Order ID#", "Meal", "Price","Quantity","Amount"
                    }
                ));
                ol.lblAmount.setText(String.valueOf(totalAmount));
                ol.jButton1.addActionListener(new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton1ActionPerformed(evt);
                    }
                    private void jButton1ActionPerformed(ActionEvent evt) {
                        int id = Integer.valueOf(ol.txtOrderID.getText());
                        ((DefaultTableModel)ol.jTable1.getModel()).removeRow(id);
                        orders.remove(id);
                        double newamount = 0;
                        for (int i = 0; i < orders.size(); i++){
                            newamount+=orders.get(i).getAmount();
                        }
                        ol.lblAmount.setText(String.valueOf(newamount));
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                  });
                int ordersPanel = JOptionPane.showOptionDialog(null, ol, "Order List",
                                     JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                                     null, options, options[1]);
            }
            else if (menuChoice == 2){
                ProcessPaymentPanel pay = new ProcessPaymentPanel();
                double amount = 0;
                for (int i = 0; i < orders.size(); i++){
                    amount+=orders.get(i).getAmount();
                }
                pay.txtAmount.setText(String.valueOf(amount));
                int payPanel = JOptionPane.showOptionDialog(null, pay, "Process Payment",
                                     JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                                     null, options, options[1]);
                if (payPanel == 1){
                    double cash = Double.valueOf(pay.txtCash.getText());
                    double change = Double.valueOf(pay.txtChange.getText());
                    sales.add(new SalesReport(amount,cash,change));
                    orders.clear();
                    JOptionPane.showMessageDialog(null, "Transaction Saved!");
                }
            }else if(menuChoice == 3){
                SalesReportPanel s = new SalesReportPanel();
                Object[][] tbdata = new Object[sales.size()][4];
                double total = 0;
                for (int i = 0; i < sales.size(); i++){
                    tbdata[i][0] = i;
                    tbdata[i][1] = sales.get(i).getAmount();
                    tbdata[i][2] = sales.get(i).getCash();
                    tbdata[i][3] = sales.get(i).getChange();
                    total += sales.get(i).getAmount();
                }
                s.jTable1.setModel(new javax.swing.table.DefaultTableModel(
                    tbdata,
                    new String [] {
                        "Invoice ID", "Amount Paid for", "Cash","Change"
                    }
                ));
                s.lblTotal.setText(String.valueOf(total));
                
                JOptionPane.showOptionDialog(null, s, "Sales Report",
                                     JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                                     null, options, options[1]);
            }
            else if(menuChoice == 4){
                JOptionPane.showMessageDialog(null, "Logging out. Good Bye!");
                break;
            }
            }
        }
        
       
        
    }
    
}
