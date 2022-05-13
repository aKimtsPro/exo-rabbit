package bstorm.akimts.exo.facture.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/health")
    public String heartbeat(){
        return "boum boum";
    }

}
