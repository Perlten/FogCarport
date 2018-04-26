/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.entities;

import DBAccess.OrderMapper;
import java.util.Objects;

/**
 * Holds the information of the customization of a carport.
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.length;
        hash = 73 * hash + this.width;
        hash = 73 * hash + this.height;
        hash = 73 * hash + (int) (Double.doubleToLongBits(this.roofangle) ^ (Double.doubleToLongBits(this.roofangle) >>> 32));
        hash = 73 * hash + Objects.hashCode(this.shed);
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
        final Customization other = (Customization) obj;
        if (this.length != other.length) {
            return false;
        }
        if (this.width != other.width) {
            return false;
        }
        if (this.height != other.height) {
            return false;
        }
        if (Double.doubleToLongBits(this.roofangle) != Double.doubleToLongBits(other.roofangle)) {
            return false;
        }
        if (!Objects.equals(this.shed, other.shed)) {
            return false;
        }
        return true;
    }
}
