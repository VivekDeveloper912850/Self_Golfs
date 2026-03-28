package golf_app.Score.repository;


import golf_app.Score.entity.Winner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WinnerRepository extends JpaRepository<Winner, Long> {
}