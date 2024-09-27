package guess_numbers.assignment.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/betting")
@CrossOrigin(origins = "http://localhost:3000")
public class BettingController {

    private final BettingService bettingService;
    private double totalAmount = 5000;

    @Autowired
    public BettingController(BettingService bettingService) {
        this.bettingService = bettingService;
    }

    @PostMapping("/place-bet")
    public synchronized ResponseEntity<Map<String, Object>> placeBet(@RequestBody Bet betRequest) {
        Map<String, Object> response = new HashMap<>();
        try {

            if (betRequest.getBetAmount() == 0) {
                response.put("error", "Bet amount cannot be zero.");
                return ResponseEntity.badRequest().body(response);
            }

            if (totalAmount < betRequest.getBetAmount()) {
                response.put("error", "Insufficient funds! Your total amount is less than the bet amount.");
                return ResponseEntity.badRequest().body(response);
            }

            if (betRequest.getGuessedNumber() < 2 || betRequest.getGuessedNumber() > 98) {
                response.put("error", "Guessed number must be between 2 and 98.");
                return ResponseEntity.badRequest().body(response);
            }

            int exactNumber = bettingService.generateExactNumber();

            double winningPercentage = bettingService.calculateWinningPercentage(betRequest.getGuessedNumber());

            double payOut = bettingService.calculatePayOut(winningPercentage);
            betRequest.setPayOut(payOut);

            double winningAmount = betRequest.getBetAmount() * payOut;

            boolean userWon = exactNumber >= betRequest.getGuessedNumber() && exactNumber <= 98;

            if (userWon) {

                totalAmount += winningAmount;
            } else {

                totalAmount -= betRequest.getBetAmount();
            }

            if (totalAmount < 0) {
                totalAmount = 0;
            }

            betRequest.setTotalAmount(totalAmount);

            String resultMessage = userWon
                    ? String.format("You won! Your winning price is: %.2f", winningAmount)
                    : "You lost! Better luck next time.";

            response.put("message", String.format(
                    "You bet: %.2f, You guessed: %d, Exact number: %d, Winning percentage: %.2f%%, Payout: %.2f, Total Amount: %.2f. %s",
                    betRequest.getBetAmount(), betRequest.getGuessedNumber(), exactNumber, winningPercentage, payOut, totalAmount,
                    resultMessage
            ));

            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            response.put("error", "Internal Server Error: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    @GetMapping("/total-amount")
    public ResponseEntity<Map<String, Double>> getTotalAmount() {
        Map<String, Double> response = new HashMap<>();
        response.put("totalAmount", totalAmount);
        return ResponseEntity.ok(response);
    }
}
