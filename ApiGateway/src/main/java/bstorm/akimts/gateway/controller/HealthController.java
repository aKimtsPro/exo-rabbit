package bstorm.akimts.gateway.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HealthController {

    @GetMapping("/health")
    public String healthCheck(
            @Value("${spring.cloud.consul.discovery.service-name}")String serviceName,
            @Value("${spring.cloud.consul.discovery.instance-id}") String id){
        return serviceName + " - " + id;
    }
}
