/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.entities;

/**
 *
 * @author adamlass
 */
public class Customization {

    private int length;
    private int width;
    private int height;
    private double roofangle;
    private Shed shed;

    public Customization(int length, int width, int height, double roofangle, Shed shed) {
        this.length = length;
        this.width = width;
        this.height = height;
        this.roofangle = roofangle;
        this.shed = shed;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getRoofangle() {
        return roofangle;
    }

    public Shed getShed() {
        return shed;
    }

    @Override
    public String toString() {
        return "Customization{" + "length=" + length + ", width=" + width + ", height=" + height + ", roofangle=" + roofangle + ", shed=" + shed + '}';
    }
    

}
