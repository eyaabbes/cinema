package gl2.exemple.cinema.Service;


import gl2.exemple.cinema.Model.*;
import gl2.exemple.cinema.Repository.*;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final SeanceRepository seanceRepository;

    public ReservationService(ReservationRepository reservationRepository,
                              SeanceRepository seanceRepository) {
        this.reservationRepository = reservationRepository;
        this.seanceRepository = seanceRepository;
    }

    public Reservation reserver(Long seanceId, String nomClient, int nbPlaces) {

        Seance seance = seanceRepository.findById(seanceId)
                .orElseThrow(() -> new RuntimeException("Séance non trouvée"));

        if (seance.getPlacesDisponibles() < nbPlaces) {
            throw new RuntimeException("Pas assez de places !");
        }

        seance.setPlacesDisponibles(seance.getPlacesDisponibles() - nbPlaces);
        seanceRepository.save(seance);

        Reservation reservation = new Reservation();
        reservation.setNomClient(nomClient);
        reservation.setSeance(seance);
        reservation.setNbPlaces(nbPlaces);

        return reservationRepository.save(reservation);
    }
}
