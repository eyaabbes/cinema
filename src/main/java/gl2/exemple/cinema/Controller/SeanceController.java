package gl2.exemple.cinema.Controller;


import gl2.exemple.cinema.Model.Film;
import gl2.exemple.cinema.Model.Seance;
import gl2.exemple.cinema.Repository.FilmRepository;
import gl2.exemple.cinema.Repository.SeanceRepository;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/seances")
public class SeanceController {

    private final SeanceRepository seanceRepository;
    private final FilmRepository FilmRepository;

    public SeanceController(SeanceRepository seanceRepository, FilmRepository filmRepository) {
        this.seanceRepository = seanceRepository;
        FilmRepository = filmRepository;
    }

    @GetMapping
    public List<Seance> getAll(@RequestParam(required = false) String date) {
        if (date != null) {
            return seanceRepository.findByDate(date);
        }
        return seanceRepository.findAll();
    }

    @PostMapping
    public Seance add(@RequestBody Seance seance, @RequestParam Long filmId) {

        Film film = FilmRepository.findById(filmId)
                .orElseThrow(() -> new RuntimeException("Film non trouvé"));

        seance.setFilm(film);

        return seanceRepository.save(seance);
    }
    @GetMapping("/film/{filmId}")
    public List<Seance> getByFilm(@PathVariable Long filmId) {
        return seanceRepository.findByFilmId(filmId);
    }
}
