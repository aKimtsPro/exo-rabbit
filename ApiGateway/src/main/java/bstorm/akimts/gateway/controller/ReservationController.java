package bstorm.akimts.gateway.controller;

import dtos.ReservationDTO;
import forms.ReservForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RestController
@RequestMapping("/reserv")
public class ReservationController {

    private final RestTemplate template;
    private final DiscoveryClient discoveryClient;
    private final Logger logger = LoggerFactory.getLogger(ReservationController.class);

    public ReservationController(RestTemplate template, DiscoveryClient discoveryClient) {
        this.template = template;
        this.discoveryClient = discoveryClient;
    }

    @PostMapping
    public ResponseEntity<?> askForReserv(@RequestBody ReservForm request){
        URI baseURI = getBaseURI();
        logger.info( "GET - " + baseURI);
        return template.postForEntity(baseURI, request, Object.class);
    }

    @GetMapping
    public ResponseEntity<ReservationDTO[]> getFacturees(){
        URI baseURI = getBaseURI();
        logger.info( "GET - " + baseURI);
        return template.getForEntity(baseURI, ReservationDTO[].class);
    }

    private URI getBaseURI(){
        return discoveryClient.getInstances("reservation-service")
                .get(0)
                .getUri()
                .resolve("/reserv");
    }


}
