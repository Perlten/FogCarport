package FunctionLayer.entities;

public class StyleOption {
    
    private String name;
    private String description;
    private double price;
    private int id;

    public StyleOption(String name, String description, double price, int id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }
    
    public StyleOption(String name, String description, double price){
        this(name, description, price, -1);
    }

    @Override
    public String toString() {
        return "StyleOption{" + "name=" + name + ", description=" + description + ", price=" + price + '}';
    }

    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
