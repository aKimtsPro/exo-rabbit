package bstorm.akimts.reserv.presentation;

import bstorm.akimts.reserv.models.Reservation;
import bstorm.akimts.reserv.service.ReservationService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

//@Component
public class Menu implements InitializingBean {

    private final Scanner sc = new Scanner(System.in);
    private final ReservationService service;

    public Menu(ReservationService service) {
        this.service = service;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        start();
    }

    public void start(){
        boolean continuer;
        do {
            System.out.println("Ajouter une réservation");
            System.out.println("Date arrive (j m y)");
            int days = sc.nextInt();
            int month = sc.nextInt();
            int year = sc.nextInt();
            LocalDate date = LocalDate.of(year, month, days);

            System.out.println("Date depart (j m y)");
            days = sc.nextInt();
            month = sc.nextInt();
            year = sc.nextInt();
            LocalDate dateD = LocalDate.of(year, month, days);

            System.out.println("continuer ? (Y/n)");
            continuer = sc.next().charAt(0) != 'n';

            service.create(new Reservation(date, dateD));
        }while(continuer);
    }


}
