package golf_app.Score.repository;


import golf_app.Score.entity.Score;
import golf_app.Score.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Long> {

    List<Score> findByUserOrderByDateAsc(User user);
}