package bstorm.akimts.exo.facture.service;

import bstorm.akimts.exo.facture.model.Facture;
import bstorm.akimts.exo.facture.rabbit.RabbitSender;
import bstorm.akimts.exo.facture.repository.FactureRepository;
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
    private final FactureMapper mapper;
    private final FactureRepository repository;

    public FactureService(RabbitSender sender, FactureMapper mapper, FactureRepository repository) {
        this.sender = sender;
        this.mapper = mapper;
        this.repository = repository;
    }

    public void createFacture(int nbrNuit, UUID reserv_ref){
        Facture f = new Facture(nbrNuit*50, reserv_ref);
        repository.save(f);
        try {
            sender.sendFactureToReserv(mapper.toDto(f));
        }
        catch (JsonProcessingException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public List<FactureDTO> getFactures(){
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    public FactureDTO getByReservRef(UUID ref) throws NoSuchElementException {
        return repository.findByReservRef(ref)
                .map(mapper::toDto)
                .orElseThrow();
    }
}
