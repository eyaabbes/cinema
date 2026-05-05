package gl2.exemple.cinema.Controller;

import gl2.exemple.cinema.Model.Seance;
import gl2.exemple.cinema.Repository.ReservationRepository;
import  gl2.exemple.cinema.Model.Reservation;
import gl2.exemple.cinema.Repository.SeanceRepository;
import  gl2.exemple.cinema.Service.ReservationService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final ReservationRepository reservationRepository;
    private final SeanceRepository seanceRepository;

    public ReservationController(ReservationService reservationService, ReservationRepository reservationRepository, SeanceRepository seanceRepository) {
        this.reservationService = reservationService;
        this.reservationRepository = reservationRepository;
        this.seanceRepository = seanceRepository;
    }

    @PostMapping
    public Reservation reserver(@RequestParam Long seanceId,
                                @RequestParam String nomClient,
                                @RequestParam int nbPlaces) {

        return reservationService.reserver(seanceId, nomClient, nbPlaces);
    }
    @GetMapping
    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    @GetMapping("/{id}")
    public Reservation getById(@PathVariable Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation non trouvée"));
    }
}
