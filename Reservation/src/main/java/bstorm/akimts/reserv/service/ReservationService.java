package bstorm.akimts.reserv.service;

import bstorm.akimts.reserv.models.Reservation;
import bstorm.akimts.reserv.rabbit.RabbitSender;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ReservationService {

    private final List<Reservation> list = new ArrayList<>();
    private final RabbitSender sender;
    private final Logger logger = LoggerFactory.getLogger(ReservationService.class);

    public ReservationService(RabbitSender sender) {
        this.sender = sender;
    }

    public void create(Reservation reservation){

        try {
            sender.sendReservToFacture(reservation);
            list.add(reservation);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }

    }

    public void setToFacture(UUID ref){ // Mieux qu'une liste?
        list.stream()
                .filter((e) -> e.getRef().equals(ref))
                .findFirst()
                .ifPresent((e) -> e.setStatus(Reservation.Status.FACTURE));
    }

    public List<Reservation> getReservFactures(){
        return list.stream()
                .filter((e) -> e.getStatus().equals(Reservation.Status.FACTURE))
                .toList();
    }

}
