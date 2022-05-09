package bstorm.akimts.reserv.presentation;

import forms.ReservForm;
import bstorm.akimts.reserv.service.ReservationService;
import dtos.ReservationDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reserv")
public class ReservationController {

    private final ReservationService service;

    public ReservationController(ReservationService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void askForReserv(@RequestBody ReservForm form){
        this.service.create(form);
    }

    @GetMapping
    public List<ReservationDTO> getFacturees(){
        return this.service.getReservFactures();
    }
}
