package bstorm.akimts.reserv.presentation;

import bstorm.akimts.reserv.models.forms.ReservForm;
import bstorm.akimts.reserv.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
        this.service.create(form.map());
    }

}
