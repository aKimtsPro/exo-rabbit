package dtos;

import dto.FactureDTO;

import java.time.LocalDate;
import java.util.UUID;

public class ReservationDTO {

    private UUID ref;
    private LocalDate arrive;
    private LocalDate depart;
    private ReservationStatus status;
    private FactureDTO factureDTO;

    public ReservationDTO() {
    }

    public ReservationDTO(UUID ref, LocalDate arrive, LocalDate depart, ReservationStatus status) {
        this.ref = ref;
        this.arrive = arrive;
        this.depart = depart;
        this.status = status;
    }

    public UUID getRef() {
        return ref;
    }

    public void setRef(UUID ref) {
        this.ref = ref;
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

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public FactureDTO getFactureDTO() {
        return factureDTO;
    }

    public void setFactureDTO(FactureDTO factureDTO) {
        this.factureDTO = factureDTO;
    }
}
