package golf_app.Score.controller;


import golf_app.Score.DTO.DashboardResponse;
import golf_app.Score.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/{userId}")
    public DashboardResponse getDashboard(@PathVariable Long userId){
        return dashboardService.getDashboard(userId);
    }
}