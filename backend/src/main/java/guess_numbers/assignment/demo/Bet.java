package guess_numbers.assignment.demo;


public class Bet {
    private int guessedNumber;
    private double betAmount;
    private double totalAmount = 5000;
    private double payOut;
    private final double maxBetAmount = 100;

    public int getGuessedNumber() {
        return guessedNumber;
    }

    public void setGuessedNumber(int guessedNumber) {
        this.guessedNumber = guessedNumber;
    }

    public double getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(double betAmount) {
        if (betAmount > maxBetAmount) {
            throw new IllegalArgumentException("Bet amount cannot exceed " + maxBetAmount);
        }
        this.betAmount = betAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getPayOut() {
        return payOut;
    }

    public void setPayOut(double payOut) {
        this.payOut = payOut;
    }

    public double getMaxBetAmount() {
        return maxBetAmount;
    }
}
