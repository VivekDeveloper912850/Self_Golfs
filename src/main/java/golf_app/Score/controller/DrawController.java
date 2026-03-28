package golf_app.Score.controller;




import golf_app.Score.entity.Draw;
import golf_app.Score.entity.Winner;
import golf_app.Score.service.DrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/draw")
public class DrawController {

    @Autowired
    private DrawService drawService;

    // Run draw
    @PostMapping
    public Draw runDraw(){
        return drawService.runDraw();
    }

    // Get winners
    @GetMapping("/winners")
    public Map<String, Object> getWinners(){

        List<Winner> winners = drawService.getWinners();

        if(winners.isEmpty()){
            return Map.of("message", "No winners found");
        }

        return Map.of("data", winners);
    }
}