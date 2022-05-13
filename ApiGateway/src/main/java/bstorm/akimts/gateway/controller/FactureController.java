package bstorm.akimts.gateway.controller;

import bstorm.akimts.gateway.config.ServiceProperties;
import dto.FactureDTO;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RestController
@RequestMapping("/factures")
public class FactureController {
    private final RestTemplate restTemplate;
    private final DiscoveryClient discoveryClient;
    private final Logger logger = LoggerFactory.getLogger(FactureController.class);

    public FactureController(RestTemplate restTemplate, DiscoveryClient discoveryClient) {
        this.restTemplate = restTemplate;
        this.discoveryClient = discoveryClient;
    }

    @GetMapping
    public ResponseEntity<FactureDTO[]> getFactures(){
        URI baseUrl = getBaseURI();
        logger.info( "GET - " + baseUrl);
        return restTemplate.getForEntity(baseUrl, FactureDTO[].class);
    }

    private URI getBaseURI(){
        return discoveryClient.getInstances("facturation-service")
                .get(0)
                .getUri()
                .resolve("/factures");
    }
}
