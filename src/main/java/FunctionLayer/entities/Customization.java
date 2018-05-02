package FunctionLayer.entities;

import java.util.Objects;

public class Customization {

    private int length;
    private int width;
    private int height;
    private double roofangle;
    private Shed shed;
    private StyleOption cladding;
    private StyleOption tile;
    
    public static final int padding = 30;
    public static final int beam = 5;

    public Customization(int length, int width, int height, double roofangle, Shed shed, StyleOption cladding, StyleOption tile) {
        this.length = length;
        this.width = width;
        this.height = height;
        this.roofangle = roofangle;
        this.shed = shed;
        this.cladding = cladding;
        this.tile = tile;
    }

    public StyleOption getCladding() {
        return cladding;
    }

    public StyleOption getTile() {
        return tile;
    }

    public void setCladding(StyleOption cladding) {
        this.cladding = cladding;
    }

    public void setTile(StyleOption tile) {
        this.tile = tile;
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

    public void setLength(int length) {
        this.length = length;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setRoofangle(double roofangle) {
        this.roofangle = roofangle;
    }

    public void setShed(Shed shed) {
        this.shed = shed;
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
