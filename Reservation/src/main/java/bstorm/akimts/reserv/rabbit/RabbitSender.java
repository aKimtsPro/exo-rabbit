package bstorm.akimts.reserv.rabbit;

import bstorm.akimts.reserv.models.Reservation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@Component
public class RabbitSender implements InitializingBean {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper mapper;
    private final Logger logger = LoggerFactory.getLogger(RabbitSender.class);

    public RabbitSender(RabbitTemplate rabbitTemplate, ObjectMapper mapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.mapper = mapper;
    }

    public void sendReservToFacture(Reservation r) throws JsonProcessingException {
        String fJson = mapper.writeValueAsString(r);
        Message m = MessageBuilder.withBody(fJson.getBytes())
                .setContentType("application/json")
                .build();
        logger.info("SENDING reserv TO facture : " + r);
        rabbitTemplate.send("direct.fact", "reserv", m);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
//        sendReservToFacture(
//                new Reservation(
//                        LocalDate.of(2021, 10,10),
//                        LocalDate.of(2021, 10,15)
//                )
//        );
    }
}
