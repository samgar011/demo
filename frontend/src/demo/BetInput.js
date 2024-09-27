import React from 'react';

const BetInput = ({ guessedNumber, onGuessedNumberChange, betAmount, setBetAmount, payout, winningPercentage }) => {
    return (
        <div>
            <div>
                <label>
                    Guessed Number (2-98):
                    <input
                        type="range"
                        min="2"
                        max="96"
                        value={guessedNumber}
                        onChange={(e) => onGuessedNumberChange(Number(e.target.value))} 
                        step="1"
                    />
                    <span>{guessedNumber}</span>
                </label>
                <p>Winning Percentage: {winningPercentage.toFixed(2)}%</p> 
                <p>Payout: {payout.toFixed(2)}</p> 
            </div>

            <div>
                <label>
                    Bet Amount (Max 100):
                    <input
                        type="number"
                        value={betAmount}
                        onChange={(e) => setBetAmount(Number(e.target.value))}
                        min="0"
                        max="100"
                    />
                </label>
            </div>
        </div>
    );
};

export default BetInput;
