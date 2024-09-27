package guess_numbers.assignment.demo;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Service
public class BettingService {

    private SecureRandom secureRandom = new SecureRandom();
    private final double RTP = 95.0;
    private List<Double> rtpHistory = new ArrayList<>();

    //CSPRNG random sayı generator
    public int generateExactNumber() {
        return secureRandom.nextInt(98 - 2 + 1) + 2;  // 2 ile 98 arasında güvenli bir rastgele sayı üret
    }

    //kazanma yüzdesini hesabı
    public double calculateWinningPercentage(int guessedNumber) {
        int lowerBound = 2;
        int upperBound = 98;

        if (guessedNumber >= lowerBound && guessedNumber <= upperBound) {
            return ((upperBound - guessedNumber) / (double) (upperBound - lowerBound)) * 100;
        } else {
            return 0.0;
        }
    }

    //payout hesbı
    public double calculatePayOut(double winningPercentage) {
        if (winningPercentage <= 0) {
            return 50.0;
        }

        return RTP / winningPercentage;  // Payout hesapla
    }

    //RTP hesabı
    public double calculateAndSaveRTP() {
        double totalBets = 0.0;
        double totalWinnings = 0.0;
        int iterations = 1000000;  // 1 milyon simülasyon yapılacak şekilde

        for (int i = 0; i < iterations; i++) {
            double bet = 1;  // Her bahis 1 birim olacak
            totalBets += bet;
            int result = secureRandom.nextInt(100) + 1; //rastgele sayı üret 1 ile 100 arasında
            int choice = secureRandom.nextInt(98 - 2 + 1) + 2; //tahmin edilen sayı 2 ile 98 arasında

            if (result > choice) {
                double payout = calculatePayOut(calculateWinningPercentage(choice));  //kazanç oranını hesapla
                totalWinnings += payout;  //toplam kazançları güncelle
            }
        }

        double currentRTP = (totalWinnings / totalBets) * 100;

        rtpHistory.add(currentRTP);

        return currentRTP;
    }

    public List<Double> getRtpHistory() {
        return rtpHistory;
    }
}
