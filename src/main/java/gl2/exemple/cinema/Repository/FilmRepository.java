package gl2.exemple.cinema.Repository;

import gl2.exemple.cinema.Model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Long> {
    List<Film> findByGenre(String genre);
}