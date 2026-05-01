package gl2.exemple.cinema.Controller;

import gl2.exemple.cinema.Repository.ReservationRepository;
import  gl2.exemple.cinema.Model.Reservation;
import  gl2.exemple.cinema.Service.ReservationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final ReservationRepository ReservationRepository;

    public ReservationController(ReservationService reservationService,ReservationRepository reservationRepository) {
        this.reservationService = reservationService;
        this.ReservationRepository = reservationRepository;
    }

    @PostMapping
    public Reservation reserver(@RequestParam Long seanceId,
                                @RequestParam String nomClient,
                                @RequestParam int nbPlaces) {

        return reservationService.reserver(seanceId, nomClient, nbPlaces);
    }

    @GetMapping("/{id}")
    public Reservation getById(@PathVariable Long id) {
        return ReservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation non trouvée"));
    }
}
