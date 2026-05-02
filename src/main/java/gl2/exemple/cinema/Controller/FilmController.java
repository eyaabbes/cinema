package gl2.exemple.cinema.Controller;

import gl2.exemple.cinema.Model.Film;
import gl2.exemple.cinema.Repository.FilmRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmController {

    private final FilmRepository filmRepository;

    public FilmController(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @GetMapping
    public List<Film> getAll() {
        return filmRepository.findAll();
    }

    @PostMapping
    public Film add(@RequestBody Film film) {
        return filmRepository.save(film);
    }

    @GetMapping("/{id}")
    public Film getById(@PathVariable Long id) {
        return filmRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Film non trouvé"));
    }

    @PutMapping("/{id}")
    public Film update(@PathVariable Long id, @RequestBody Film nouveauFilm) {
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Film non trouvé"));

        film.setTitre(nouveauFilm.getTitre());
        film.setGenre(nouveauFilm.getGenre());
        film.setDuree(nouveauFilm.getDuree());

        return filmRepository.save(film);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Film non trouvé"));

        filmRepository.delete(film);
        return "Film supprimé avec succès";
    }

    @GetMapping("/genre/{genre}")
    public List<Film> getByGenre(@PathVariable String genre) {
        return filmRepository.findByGenre(genre);
    }
    @GetMapping("/search")
    public List<Film> searchByTitre(@RequestParam String titre) {
        return filmRepository.findByTitreContainingIgnoreCase(titre);
    }
}