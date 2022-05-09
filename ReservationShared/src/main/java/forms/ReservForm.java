package forms;

import java.time.LocalDate;

public class ReservForm {

    private LocalDate arrive;
    private LocalDate depart;

    public ReservForm() {
    }

    public ReservForm(LocalDate arrive, LocalDate depart) {
        this.arrive = arrive;
        this.depart = depart;
    }

    public LocalDate getArrive() {
        return arrive;
    }

    public void setArrive(LocalDate arrive) {
        this.arrive = arrive;
    }

    public LocalDate getDepart() {
        return depart;
    }

    public void setDepart(LocalDate depart) {
        this.depart = depart;
    }
}
