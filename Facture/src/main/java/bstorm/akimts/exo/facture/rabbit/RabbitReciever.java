package bstorm.akimts.exo.facture.rabbit;

import bstorm.akimts.exo.facture.model.Reservation;
import bstorm.akimts.exo.facture.service.FactureService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.temporal.ChronoUnit;

@Component
public class RabbitReciever {

    private final FactureService service;
    private final ObjectMapper mapper;
    private final Logger logger = LoggerFactory.getLogger(RabbitReciever.class);

    public RabbitReciever(FactureService service, ObjectMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @RabbitListener(queues = "reservations")
    public void consumeReserv(String message) throws JsonProcessingException {
        logger.info("RECIEVED FROM reservations : " + message);
        Reservation reservation = mapper.readValue(message, Reservation.class);
        service.createFacture(
                (int) ChronoUnit.DAYS.between(reservation.getArrive(),reservation.getDepart()),
                reservation.getRef()
            );
        service.getFactures().forEach(System.out::println);
    }

}
