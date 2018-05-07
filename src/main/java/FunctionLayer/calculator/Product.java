/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.calculator;

/**
 *
 * @author adamlass
 */
public class Product {
   private String title, description, unit;
   private int amount, length;
   private double price;

    public Product(String title, String description, String unit, int amount, int length, double price) {
        this.title = title;
        this.description = description;
        this.unit = unit;
        this.amount = amount;
        this.length = length;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUnit() {
        return unit;
    }

    public int getAmount() {
        return amount;
    }

    public int getLength() {
        return length;
    }

    /**
     * Use this to get the price of a single product of this type
     * @return the price of a single product of this type
     */
    public double getPrice() {
        return price;
    }
    
    /**
     * Use this method to get the total price of the products of this type.
     * @return the total price of the products of this type
     */
    public double totalPrice(){
        return price * amount;
    }
   
   
    
}
