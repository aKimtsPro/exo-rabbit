package bstorm.akimts.exo.facture.controllers;

import bstorm.akimts.exo.facture.model.Facture;
import bstorm.akimts.exo.facture.service.FactureService;
import dto.FactureDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/factures")
public class FactureController {

    private final FactureService service;

    public FactureController(FactureService service) {
        this.service = service;
    }

    @GetMapping
    public List<FactureDTO> getFactures(){
        return service.getFactures();
    }

    @GetMapping(params = "ref")
    public FactureDTO getFacture(@RequestParam UUID ref){
        return service.getByReservRef(ref);
    }


}
