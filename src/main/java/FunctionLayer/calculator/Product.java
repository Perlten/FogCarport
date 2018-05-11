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
    private int amount, id;
    private double price, length, lengthUsed;

    /**
     * Dummy constructer. Used for writing product lines
     * @param id of the product
     * @param amount of the product
     * @param lengthUsed of the product
     */
    public Product(int id, int amount, int lengthUsed) {
        this(null, null, null, amount, id, 0, 0, lengthUsed);
    }

    public Product(String title, String description, String unit, int amount, int id, double price, double length, double lengthUsed) {
        this.title = title;
        this.description = description;
        this.unit = unit;
        this.amount = amount;
        this.id = id;
        this.price = price;
        this.length = length;
        this.lengthUsed = lengthUsed;
    }

    public int getId() {
        return id;
    }

    public double getLengthUsed() {
        return lengthUsed;
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

    public double getLength() {
        return length;
    }

    /**
     * Use this to get the price of a single product of this type
     *
     * @return the price of a single product of this type
     */
    public double getPrice() {
        return price;
    }

    /**
     * Use this method to get the total price of the products of this type.
     *
     * @return the total price of the products of this type
     */
    public double totalPrice() {
        return price * amount;
    }

    @Override
    public String toString() {
        return "Product{" + "title=" + title + ", description=" + description + ", unit=" + unit + ", amount=" + amount + ", length=" + length + ", price=" + price + '}';
    }

}
