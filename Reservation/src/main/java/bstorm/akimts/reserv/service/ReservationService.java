package bstorm.akimts.reserv.service;

import bstorm.akimts.reserv.models.entity.Reservation;
import forms.ReservForm;
import bstorm.akimts.reserv.rabbit.RabbitSender;
import bstorm.akimts.reserv.service.mapper.ReservationMapper;
import dto.FactureDTO;
import dtos.ReservationDTO;
import dtos.ReservationStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ReservationService {

    private final List<Reservation> list = new ArrayList<>();
    private final RabbitSender sender;
    private final Logger logger = LoggerFactory.getLogger(ReservationService.class);
    private final ReservationMapper mapper;
    private final RestTemplate template;

    public ReservationService(RabbitSender sender, ReservationMapper mapper, RestTemplate template) {
        this.sender = sender;
        this.mapper = mapper;
        this.template = template;
    }

    public void create(ReservForm form){
        Reservation reservation = mapper.toEntity(form);
        list.add(reservation);
        try {
            sender.sendReservToFacture(mapper.toDto(reservation));
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void setToFacture(UUID ref){ // Mieux qu'une liste?
        list.stream()
                .filter((e) -> e.getRef().equals(ref))
                .findFirst()
                .ifPresent((e) -> e.setStatus(ReservationStatus.FACTUREE));
    }

    public List<ReservationDTO> getReservFactures(){
        return list.stream()
                .filter((e) -> e.getStatus().equals(ReservationStatus.FACTUREE))
                .map(mapper::toDto)
                .peek((r) -> r.setFactureDTO( template.getForObject("http://localhost:8282/factures?ref="+r.getRef(), FactureDTO.class)))
                .toList();
    }

}
