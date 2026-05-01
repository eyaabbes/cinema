package gl2.exemple.cinema.Controller;

import gl2.exemple.cinema.Model.Seance;
import gl2.exemple.cinema.Repository.ReservationRepository;
import  gl2.exemple.cinema.Model.Reservation;
import gl2.exemple.cinema.Repository.SeanceRepository;
import  gl2.exemple.cinema.Service.ReservationService;
import org.antlr.v4.runtime.tree.pattern.ParseTreePattern;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final ReservationRepository ReservationRepository;
    private final SeanceRepository SeanceRepository;

    public ReservationController(ReservationService reservationService, ReservationRepository reservationRepository, SeanceRepository seanceRepository) {
        this.reservationService = reservationService;
        this.ReservationRepository = reservationRepository;
        SeanceRepository = seanceRepository;
    }

    @PostMapping
    public Reservation reserver(@RequestParam Long seanceId,
                                @RequestParam String nomClient,
                                @RequestParam int nbPlaces) {

        return reservationService.reserver(seanceId, nomClient, nbPlaces);
    }
    @GetMapping
    public List<Seance> getAll(@RequestParam(required = false) String date) {
        if (date != null) {
            return SeanceRepository.findByDate(date);
        }
        return SeanceRepository.findAll();
    }
    @GetMapping("/{id}")
    public Reservation getById(@PathVariable Long id) {
        return ReservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation non trouvée"));
    }
}
