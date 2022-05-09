package dto;

import java.util.UUID;

public class FactureDTO {

    private UUID reserv_ref;
    private double price;

    public FactureDTO() {
    }

    public FactureDTO(UUID reserv_ref, double price) {
        this.reserv_ref = reserv_ref;
        this.price = price;
    }

    public UUID getReserv_ref() {
        return reserv_ref;
    }

    public void setReserv_ref(UUID reserv_ref) {
        this.reserv_ref = reserv_ref;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
