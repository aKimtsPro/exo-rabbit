package bstorm.akimts.reserv.repository;

import bstorm.akimts.reserv.models.entity.Reservation;
import dtos.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Optional<Reservation> findByRef(UUID ref);
    List<Reservation> findByStatus(ReservationStatus status);

}
