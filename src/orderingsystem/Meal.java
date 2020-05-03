/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderingsystem;

import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class Meal {
    private String meal;
    private double price;

    public Meal(String meal, double price){
        this.meal = meal;
        this.price = price;
    }
    /**
     * @return the meal
     */
    public String getMeal() {
        return meal;
    }

    /**
     * @param meal the meal to set
     */
    public void setMeal(String meal) {
        this.meal = meal;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
}
class Password{
    private String pass;
    private int tries = 3;
    public Password(String pass){
        this.pass = pass;
    }
    public void tryAgain(){
        this.tries--;
        JOptionPane.showMessageDialog(null, "You have "+this.tries+" try left!");
    }
    
    public String getPassword(){
        return this.pass;
    }
    public int getTries(){
        return this.tries;
    }
}
class Order{
    private String meal;
    private double price;
    private int quantity;
    private double amount;
    
    public Order(String meal, double price, int qty){
        this.meal = meal;
        this.price = price;
        this.quantity = qty;
    }
    
    public String getMeal(){
        return this.meal;
    }
    public double getPrice(){
        return this.price;
    }
    public int getQuantity(){
        return this.quantity;
    }
    
    public double getAmount(){
        this.amount = this.price*this.quantity;
        return this.amount;
    }
}
class SalesReport{
    private double amount, cash, change;
    public SalesReport(double amount, double cash, double change){
        this.amount = amount;
        this.cash = cash;
        this.change = change;
    }
    
    public double getAmount(){
        return this.amount;
    }
    
    public double getCash(){
        return this.cash;
    }
    
    public double getChange(){
        return this.change;
    }
}
