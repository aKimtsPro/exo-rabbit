package bstorm.akimts.gateway2.controller;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {

    // TODO d'abord montrer ça puis actuator
    @GetMapping
    public String checkHealth(){
        return "boum boum";
    }

}
