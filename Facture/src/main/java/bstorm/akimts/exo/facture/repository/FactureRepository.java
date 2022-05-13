package bstorm.akimts.exo.facture.repository;

import bstorm.akimts.exo.facture.model.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FactureRepository extends JpaRepository<Facture, Long> {

    Optional<Facture> findByReservRef(UUID ref);

}
