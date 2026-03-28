package golf_app.Score.service;




import golf_app.Score.entity.Draw;
import golf_app.Score.entity.Score;
import golf_app.Score.entity.User;
import golf_app.Score.entity.Winner;
import golf_app.Score.repository.DrawRepository;
import golf_app.Score.repository.ScoreRepository;
import golf_app.Score.repository.UserRepository;
import golf_app.Score.repository.WinnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class DrawService {

    @Autowired
    private DrawRepository drawRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WinnerRepository winnerRepository;

    // 🔥 Run Draw
    public Draw runDraw() {

        // Step 1: Generate random 5 numbers
        Set<Integer> randomSet = new HashSet<>();
        Random random = new Random();

        while(randomSet.size() < 5){
            randomSet.add(random.nextInt(45) + 1);
        }

        List<Integer> drawNumbers = new ArrayList<>(randomSet);

        // Save draw
        Draw draw = new Draw();
        draw.setNumbers(drawNumbers);
        draw.setDrawDate(LocalDate.now());
        drawRepository.save(draw);

        // Step 2: Check all users
        List<User> users = userRepository.findAll();

        for(User user : users){

            List<Score> scores = scoreRepository.findByUserOrderByDateAsc(user);

            List<Integer> userScores = scores.stream()
                    .map(Score::getValue)
                    .toList();

            // Count matches
            int matchCount = 0;

            for(Integer num : drawNumbers){
                if(userScores.contains(num)){
                    matchCount++;
                }
            }

            // Step 3: Decide winner
            if(matchCount >= 3){
                Winner winner = new Winner();
                winner.setUserId(user.getId());
                winner.setMatchCount(matchCount);

                if(matchCount == 5){
                    winner.setPrize("Jackpot");
                } else if(matchCount == 4){
                    winner.setPrize("Second Prize");
                } else {
                    winner.setPrize("Third Prize");
                }

                winnerRepository.save(winner);
            }
        }

        return draw;
    }

    // Get all winners
    public List<Winner> getWinners(){
        return winnerRepository.findAll();
    }
}