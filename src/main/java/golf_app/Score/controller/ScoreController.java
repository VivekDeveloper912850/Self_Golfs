package golf_app.Score.controller;



import golf_app.Score.entity.Score;
import golf_app.Score.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    // Add score
    @PostMapping("/{userId}")
    public Score addScore(@PathVariable Long userId,
                          @RequestParam int value){
        return scoreService.addScore(userId, value);
    }

    // Get scores
    @GetMapping("/{userId}")
    public List<Score> getScores(@PathVariable Long userId){
        return scoreService.getScores(userId);
    }
}