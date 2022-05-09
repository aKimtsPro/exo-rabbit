package bstorm.akimts.gateway.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HealthController {

    @GetMapping("/health")
    public String healthCheck(@Value("${spring.cloud.consul.discovery}")Map<String, String> props){
        return props.get("service-name") + " - " + props.get("instance-id");
    }
}
