package bstorm.akimts.exo.facture.service;

import bstorm.akimts.exo.facture.model.Facture;
import bstorm.akimts.exo.facture.rabbit.RabbitSender;
import bstorm.akimts.exo.facture.service.mapper.FactureMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import dto.FactureDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class FactureService {

    private final Logger logger = LoggerFactory.getLogger(FactureService.class);
    private final RabbitSender sender;
    private final List<Facture> factures = new ArrayList<>();
    private final FactureMapper mapper;

    public FactureService(RabbitSender sender, FactureMapper mapper) {
        this.sender = sender;
        this.mapper = mapper;
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

    public List<FactureDTO> getFactures(){
        return factures.stream()
                .map(mapper::toDto)
                .toList();
    }

    public FactureDTO getByReservRef(UUID ref) throws NoSuchElementException {
        return factures.stream()
                .filter(f -> f.getReserv_ref().equals(ref))
                .map(mapper::toDto)
                .findFirst()
                .orElseThrow();
    }
}
