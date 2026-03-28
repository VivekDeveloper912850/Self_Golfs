package golf_app.Score.service;




import golf_app.Score.DTO.DashboardResponse;
import golf_app.Score.entity.Score;
import golf_app.Score.entity.User;
import golf_app.Score.entity.Winner;
import golf_app.Score.repository.ScoreRepository;
import golf_app.Score.repository.UserRepository;
import golf_app.Score.repository.WinnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private WinnerRepository winnerRepository;

    public DashboardResponse getDashboard(Long userId){

        User user = userRepository.findById(userId).orElseThrow();

        // Scores
        List<Score> scoreList = scoreRepository.findByUserOrderByDateAsc(user);

        List<Integer> scores = scoreList.stream()
                .map(Score::getValue)
                .toList();

        // Winnings (simple count)
        List<Winner> winners = winnerRepository.findAll();

        int totalWinnings = winners.stream()
                .filter(w -> w.getUserId().equals(userId))
                .toList()
                .size();

        // Charity (dummy for now)
        String charity = "Default Charity";

        return new DashboardResponse(
                user.isSubscriptionActive(),
                scores,
                charity,
                totalWinnings
        );
    }
}