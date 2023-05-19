package pro.sky.sockswarehouse.model;



import javax.persistence.*;
import java.util.Objects;
@Entity
@Table(name = "socksInStocks")
public class SocksInStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String color;
    private int cottonPart;
    private int quantity;


    public SocksInStock(long id, String color, int cottonPart, int quantity) {
        this.id = id;
        this.color = color;
        this.cottonPart = cottonPart;
        this.quantity = quantity;
    }

    public SocksInStock() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCottonPart() {
        return cottonPart;
    }

    public void setCottonPart(int cottonPart) {
        this.cottonPart = cottonPart;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SocksInStock that = (SocksInStock) o;
        return id == that.id && cottonPart == that.cottonPart && quantity == that.quantity && Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, color, cottonPart, quantity);
    }

    @Override
    public String toString() {
        return  "id - " + id +
                ", цвет носков" + color + '\'' +
                ", процент хлопка - " + cottonPart +
                ", количество - " + quantity;
    }
}
