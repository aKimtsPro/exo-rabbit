package bstorm.akimts.reserv.service;

import bstorm.akimts.reserv.models.entity.Reservation;
import bstorm.akimts.reserv.repository.ReservationRepository;
import forms.ReservForm;
import bstorm.akimts.reserv.rabbit.RabbitSender;
import bstorm.akimts.reserv.service.mapper.ReservationMapper;
import dto.FactureDTO;
import dtos.ReservationDTO;
import dtos.ReservationStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ReservationService {

    private final Logger logger = LoggerFactory.getLogger(ReservationService.class);

    private final RabbitSender sender;
    private final ReservationMapper mapper;
    private final RestTemplate template;
    private final DiscoveryClient discoveryClient;
    private final ReservationRepository repository;

    public ReservationService(
            RabbitSender sender,
            ReservationMapper mapper,
            RestTemplate template,
            DiscoveryClient discoveryClient, ReservationRepository repository) {
        this.sender = sender;
        this.mapper = mapper;
        this.template = template;
        this.discoveryClient = discoveryClient;
        this.repository = repository;
    }

    public void create(ReservForm form){
        Reservation reservation = mapper.toEntity(form);
        repository.save(reservation);
        try {
            sender.sendReservToFacture(mapper.toDto(reservation));
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void setToFacture(UUID ref){
        repository.findByRef(ref)
                .ifPresent((e) -> {
                    e.setStatus(ReservationStatus.FACTUREE);
                    repository.save(e);
                });
    }

    public List<ReservationDTO> getReservFactures(){
        return repository.findByStatus(ReservationStatus.FACTUREE).stream()
                .map(mapper::toDto)
                .peek((r) -> r.setFactureDTO( template.getForObject(getReservURI()+"?ref="+r.getRef(), FactureDTO.class)))
                .toList();
    }

    private URI getReservURI(){
        return discoveryClient.getInstances("facturation-service")
                .get(0)
                .getUri()
                .resolve("/factures");
    }

}
