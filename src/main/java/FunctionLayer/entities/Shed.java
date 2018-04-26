/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.entities;

/**
 * Holds the specifications of a shed
 *
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

    public void setLength(int length) {
        this.length = length;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "Shed{" + "length=" + length + ", width=" + width + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.length;
        hash = 71 * hash + this.width;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Shed other = (Shed) obj;
        if (this.length != other.length) {
            return false;
        }
        if (this.width != other.width) {
            return false;
        }
        return true;
    }

}
