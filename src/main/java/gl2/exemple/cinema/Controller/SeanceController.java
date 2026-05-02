package gl2.exemple.cinema.Controller;

import gl2.exemple.cinema.Model.Film;
import gl2.exemple.cinema.Model.Seance;
import gl2.exemple.cinema.Repository.FilmRepository;
import gl2.exemple.cinema.Repository.SeanceRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seances")
public class SeanceController {

    private final SeanceRepository seanceRepository;
    private final FilmRepository filmRepository;

    public SeanceController(SeanceRepository seanceRepository, FilmRepository filmRepository) {
        this.seanceRepository = seanceRepository;
        this.filmRepository = filmRepository;
    }

    @GetMapping
    public List<Seance> getAll(@RequestParam(required = false) String date) {
        if (date != null) {
            return seanceRepository.findByDate(date);
        }
        return seanceRepository.findAll();
    }

    @PostMapping
    public Seance add(@RequestBody Seance seance,
                      @RequestParam Long filmId) {

        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new RuntimeException("Film non trouvé"));

        seance.setFilm(film);

        return seanceRepository.save(seance);
    }

    @GetMapping("/film/{filmId}")
    public List<Seance> getByFilm(@PathVariable Long filmId) {
        return seanceRepository.findByFilmId(filmId);
    }

    @GetMapping("/{id}")
    public Seance getById(@PathVariable Long id) {
        return seanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Séance non trouvée"));
    }

    @PutMapping("/{id}")
    public Seance update(@PathVariable Long id,
                         @RequestBody Seance nouvelleSeance,
                         @RequestParam(required = false) Long filmId) {

        Seance seance = seanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Séance non trouvée"));

        seance.setDate(nouvelleSeance.getDate());
        seance.setHeure(nouvelleSeance.getHeure());
        seance.setPlacesDisponibles(nouvelleSeance.getPlacesDisponibles());

        if (filmId != null) {
            Film film = filmRepository.findById(filmId)
                    .orElseThrow(() -> new RuntimeException("Film non trouvé"));
            seance.setFilm(film);
        }

        return seanceRepository.save(seance);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        Seance seance = seanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Séance non trouvée"));

        seanceRepository.delete(seance);
        return "Séance supprimée avec succès";
    }
    @GetMapping("/disponibles")
    public List<Seance> getSeancesDisponibles() {
        return seanceRepository.findByPlacesDisponiblesGreaterThan(0);
    }
}