package FunctionLayer.entities;

public class StyleOption {
    
    private String name;
    private String description;
    private int price;
    private int id;

    public StyleOption(String name, String description, int price, int id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
