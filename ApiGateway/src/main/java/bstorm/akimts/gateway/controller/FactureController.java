package bstorm.akimts.gateway.controller;

import bstorm.akimts.gateway.config.ServiceProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import dto.FactureDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/factures")
public class FactureController {
    private final String BASE_URL;
    private final RestTemplate restTemplate;

    public FactureController(RestTemplate restTemplate, ServiceProperties properties) {
        this.restTemplate = restTemplate;
        this.BASE_URL = properties.getServices()
                .get("facture")
                .getUrl();
    }

    @GetMapping
    public ResponseEntity<FactureDTO[]> getFactures(){
        ResponseEntity<FactureDTO[]> response = restTemplate.getForEntity(BASE_URL, FactureDTO[].class);
        System.out.println(response);
        return response;
    }
}
