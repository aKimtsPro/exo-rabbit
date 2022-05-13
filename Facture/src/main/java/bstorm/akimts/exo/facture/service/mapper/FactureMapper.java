package bstorm.akimts.exo.facture.service.mapper;

import bstorm.akimts.exo.facture.model.Facture;
import dto.FactureDTO;
import org.springframework.stereotype.Component;

@Component
public class FactureMapper {

    public FactureDTO toDto(Facture entity){
        if(entity == null)
            return null;

        return new FactureDTO(entity.getReservRef(), entity.getPrix());
    }

}
