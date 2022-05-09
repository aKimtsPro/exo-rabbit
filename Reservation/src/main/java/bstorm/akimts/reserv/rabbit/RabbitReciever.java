package bstorm.akimts.reserv.rabbit;

import bstorm.akimts.reserv.service.ReservationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.FactureDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitReciever {

    private final ReservationService service;
    private final ObjectMapper mapper;
    private final Logger logger = LoggerFactory.getLogger(RabbitReciever.class);

    public RabbitReciever(ReservationService service, ObjectMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @RabbitListener(queues = "facturations")
    public void consumeReserv(String message) throws JsonProcessingException {
        logger.info("RECIEVED FROM facturations: " + message);
        FactureDTO facture = mapper.readValue(message, FactureDTO.class);
        service.setToFacture(facture.getReserv_ref());
    }

}
