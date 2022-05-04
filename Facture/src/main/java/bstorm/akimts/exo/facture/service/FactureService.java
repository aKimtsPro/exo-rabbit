package bstorm.akimts.exo.facture.service;

import bstorm.akimts.exo.facture.model.Facture;
import bstorm.akimts.exo.facture.rabbit.RabbitSender;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FactureService {

    private final Logger logger = LoggerFactory.getLogger(FactureService.class);
    private final RabbitSender sender;
    private final List<Facture> factures = new ArrayList<>();

    public FactureService(RabbitSender sender) {
        this.sender = sender;
    }

    public void createFacture(int nbrNuit, UUID reserv_ref){
        Facture f = new Facture(nbrNuit*50, reserv_ref);
        try {
            sender.sendFactureToReserv(f);
            factures.add(f);
        }
        catch (JsonProcessingException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Facture> getFactures(){
        return new ArrayList<>(factures);
    }
}
