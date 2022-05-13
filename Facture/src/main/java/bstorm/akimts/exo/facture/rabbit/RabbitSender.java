package bstorm.akimts.exo.facture.rabbit;

import bstorm.akimts.exo.facture.model.Facture;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.FactureDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Component
public class RabbitSender {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper mapper;
    private final Logger logger = LoggerFactory.getLogger(RabbitSender.class);

    public RabbitSender(RabbitTemplate rabbitTemplate, ObjectMapper mapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.mapper = mapper;
    }

    public void sendFactureToReserv(FactureDTO f) throws JsonProcessingException {
        String fJson = mapper.writeValueAsString(f);
        Message m = MessageBuilder.withBody(fJson.getBytes(StandardCharsets.UTF_8))
                .setContentType("application/json")
                .build();
        logger.info("SENDING facture TO reserv: " + f);
        rabbitTemplate.send("direct.fact", "fact", m);
    }

}
