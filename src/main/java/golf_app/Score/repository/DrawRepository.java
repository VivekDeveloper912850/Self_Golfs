package golf_app.Score.repository;


import golf_app.Score.entity.Draw;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrawRepository extends JpaRepository<Draw, Long> {
}