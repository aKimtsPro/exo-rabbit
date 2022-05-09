package bstorm.akimts.gateway.controller;

import bstorm.akimts.gateway.config.ServiceProperties;
import dtos.ReservationDTO;
import forms.ReservForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/reserv")
public class ReservationController {

    private final RestTemplate template;
    private final String BASE_URL;

    public ReservationController(RestTemplate template, ServiceProperties properties) {
        this.template = template;
        this.BASE_URL = properties.getServices()
                .get("reservation")
                .getUrl();
    }

    @PostMapping
    public ResponseEntity<?> askForReserv(@RequestBody ReservForm request){
        return template.postForEntity(BASE_URL, request, Object.class);
    }

    @GetMapping
    public ResponseEntity<ReservationDTO[]> getFacturees(){
        ResponseEntity<ReservationDTO[]> response = template.getForEntity(BASE_URL, ReservationDTO[].class);
        System.out.println(response);
        return response;
    }


}
