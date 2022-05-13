package dto;

import java.util.UUID;

public class FactureDTO {

    private UUID reservRef;
    private double price;

    public FactureDTO() {
    }

    public FactureDTO(UUID reservRef, double price) {
        this.reservRef = reservRef;
        this.price = price;
    }

    @Override
    public String toString() {
        return "FactureDTO{ reservRef=" + reservRef +
                ", price=" + price +
                '}';
    }

    public UUID getReservRef() {
        return reservRef;
    }

    public void setReservRef(UUID reservRef) {
        this.reservRef = reservRef;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
