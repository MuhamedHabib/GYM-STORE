package Entity;

import java.sql.Date;

public class Product {

    private int id;
    private String title;
    private String image;
    private double price;
    private int category_id;


    public Product(int id, String title, String image, double price, int category_id) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.price = price;
        this.category_id = category_id;
    }

    @Override
    public String toString() {
        return "demande{" +
                "iddemande=" + id +
                ", nomprenom='" + title + '\'' +
                ", contact='" + image + '\'' +
                ", datedebut='" + price + '\'' +
                ", image='" + category_id + '\'' +
                '}';
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
}
