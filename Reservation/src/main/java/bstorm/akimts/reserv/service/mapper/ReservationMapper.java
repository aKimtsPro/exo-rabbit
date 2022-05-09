package bstorm.akimts.reserv.service.mapper;

import bstorm.akimts.reserv.models.entity.Reservation;
import dtos.ReservationDTO;
import forms.ReservForm;
import org.springframework.stereotype.Service;

@Service
public class ReservationMapper {

    public ReservationDTO toDto(Reservation entity){
        if( entity == null)
            return null;

        return new ReservationDTO(
                entity.getRef(),
                entity.getArrive(),
                entity.getDepart(),
                entity.getStatus()
        );
    }

    public Reservation toEntity(ReservForm form){
        if( form == null)
            return null;

        return new Reservation(
                form.getArrive(),
                form.getDepart()
        );
    }

}
