package gl2.exemple.cinema.Repository;

import gl2.exemple.cinema.Model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
