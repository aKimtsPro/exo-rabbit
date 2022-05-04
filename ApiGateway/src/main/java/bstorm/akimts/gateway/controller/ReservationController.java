package bstorm.akimts.gateway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/reserv")
public class ReservationController {

    private final RestTemplate template;

    public ReservationController(RestTemplate template) {
        this.template = template;
    }

    @PostMapping
    public ResponseEntity<?> askForReserv(@RequestBody Map<String, String> request){
        System.out.println(request);

        ResponseEntity<Object> response = template.postForEntity("http://localhost:8181/reserv", request, Object.class);

        return response;
    }


}
