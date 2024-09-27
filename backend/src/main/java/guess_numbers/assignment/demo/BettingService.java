package guess_numbers.assignment.demo;

import org.springframework.stereotype.Service;

@Service
public class BettingService {

    public void validateGuess(int guessedNumber) {

        if (guessedNumber < 2 || guessedNumber > 98) {
            throw new IllegalArgumentException("Guessed number must be between 2 and 98.");
        }
    }

    public double calculateWinningPercentage(int guessedNumber) {
        int lowerBound = 2;
        int upperBound = 98;

        if (guessedNumber >= lowerBound && guessedNumber <= upperBound) {

            return ((upperBound - guessedNumber) / (double) (upperBound - lowerBound)) * 100;
        } else {
            return 0.0;  // Outside the range, no win
        }
    }

    public double calculatePayOut(double winningPercentage) {
        double payOut;

        if (winningPercentage >= 96) {
            payOut = 1.02;

        } else if (winningPercentage >= 90) {
            payOut = 1.02 + ((96 - winningPercentage) / 6) * (2.80 - 1.02);

        } else if (winningPercentage >= 30) {
            payOut = 2.80 + ((90 - winningPercentage) / 60) * (4.00 - 2.80);

        } else if (winningPercentage >= 10) {
            payOut = 4.00 + ((30 - winningPercentage) / 20) * (5.80 - 4.00);

        } else if (winningPercentage >= 1) {
            payOut = 5.80 + ((10 - winningPercentage) / 9) * (50.00 - 5.80);

        } else {
            payOut = 50.00;

        }

        final double MIN_PAYOUT = 1.02;
        final double MAX_PAYOUT = 50.00;
        if (payOut < MIN_PAYOUT) {
            payOut = MIN_PAYOUT;
        } else if (payOut > MAX_PAYOUT) {
            payOut = MAX_PAYOUT;
        }

        return payOut;
    }


    public int generateExactNumber() {
        return (int) (Math.random() * (98 - 2 + 1)) + 2;  // Random number between 2 and 98
    }
}
