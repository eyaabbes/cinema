package gl2.exemple.cinema.Repository;

import gl2.exemple.cinema.Model.Seance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface SeanceRepository extends JpaRepository<Seance, Long> {
    List<Seance> findByDate(String date);

    List<Seance> findByFilmId(Long filmId);
}
