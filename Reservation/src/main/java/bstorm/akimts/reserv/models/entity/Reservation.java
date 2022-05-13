package bstorm.akimts.reserv.models.entity;

import dtos.ReservationStatus;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, unique = true, updatable = false)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID ref = UUID.randomUUID();

    private LocalDate arrive;
    private LocalDate depart;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status = ReservationStatus.DEMANDEE;

    public Reservation(LocalDate arrive, LocalDate depart) {
        this.arrive = arrive;
        this.depart = depart;
    }

}
