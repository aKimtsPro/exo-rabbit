package bstorm.akimts.exo.facture.model;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Facture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double prix;
    @Column(unique = true, nullable = false, updatable = false)
    private UUID reservRef;

    public Facture(double prix, UUID reservRef) {
        this.prix = prix;
        this.reservRef = reservRef;
    }
}
