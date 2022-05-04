package bstorm.akimts.exo.facture.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    private LocalDate arrive;
    private LocalDate depart;
    private UUID ref;
    private String status;

}
