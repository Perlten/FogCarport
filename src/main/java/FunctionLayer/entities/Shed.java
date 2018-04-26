/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.entities;

/**
 * Holds the specifications of a shed
 * @author adamlass
 */
public class Shed {
    private int length;
    private int width;

    public Shed(int length, int width) {
        this.length = length;
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public String toString() {
        return "Shed{" + "length=" + length + ", width=" + width + '}';
    }
}
