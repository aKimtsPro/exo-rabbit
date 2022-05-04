package bstorm.akimts.reserv.models.forms;

import bstorm.akimts.reserv.models.Reservation;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservForm {

    private LocalDate arrive;
    private LocalDate depart;

    public Reservation map(){
        return new Reservation(arrive, depart);
    }

}
