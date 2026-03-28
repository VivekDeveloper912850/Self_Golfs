package golf_app.Score.DTO;


import lombok.*;

import java.util.List;

@Data

@NoArgsConstructor
public class DashboardResponse {

    private boolean subscriptionActive;

    private List<Integer> scores;

    private String charity;

    private int totalWinnings;

    public DashboardResponse(boolean subscriptionActive, List<Integer> scores, String charity, int totalWinnings) {
        this.subscriptionActive = subscriptionActive;
        this.scores = scores;
        this.charity = charity;
        this.totalWinnings = totalWinnings;
    }


    public boolean isSubscriptionActive() {
        return subscriptionActive;
    }

    public void setSubscriptionActive(boolean subscriptionActive) {
        this.subscriptionActive = subscriptionActive;
    }

    public List<Integer> getScores() {
        return scores;
    }

    public void setScores(List<Integer> scores) {
        this.scores = scores;
    }

    public String getCharity() {
        return charity;
    }

    public void setCharity(String charity) {
        this.charity = charity;
    }

    public int getTotalWinnings() {
        return totalWinnings;
    }

    public void setTotalWinnings(int totalWinnings) {
        this.totalWinnings = totalWinnings;
    }
}