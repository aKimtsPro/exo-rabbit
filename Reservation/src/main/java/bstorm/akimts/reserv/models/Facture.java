package bstorm.akimts.reserv.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Facture {

    private double prix;
    private UUID reserv_ref;

}
