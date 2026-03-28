package golf_app.Score.service;





import golf_app.Score.entity.Score;
import golf_app.Score.entity.User;
import golf_app.Score.repository.ScoreRepository;
import golf_app.Score.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private UserRepository userRepository;

    public Score addScore(Long userId, int value) {

        //  VALIDATION YAHI ADD KARNA HAI
        if(value < 1 || value > 45){
            throw new RuntimeException("Score must be between 1 and 45");
        }


        User user = userRepository.findById(userId).orElseThrow();

        //  Get existing scores
        List<Score> scores = scoreRepository.findByUserOrderByDateAsc(user);

        //  If already 5 scores → remove oldest
        if(scores.size() == 5){
            scoreRepository.delete(scores.get(0));
        }

        //  Add new score
        Score newScore = new Score();
        newScore.setValue(value);
        newScore.setDate(LocalDate.now());
        newScore.setUser(user);

        return scoreRepository.save(newScore);
    }

    public List<Score> getScores(Long userId){
        User user = userRepository.findById(userId).orElseThrow();
        return scoreRepository.findByUserOrderByDateAsc(user);
    }
}