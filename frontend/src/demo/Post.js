import React, { useState } from 'react';
import BetInput from './BetInput'; 
import { calculatePayout, calculateWinningPercentage } from './PayUtils'; 
import { placeBetApi } from './PostService'; 
const Post = () => {
    const [guessedNumber, setGuessedNumber] = useState(30); 
    const [betAmount, setBetAmount] = useState(0);
    const [totalAmount, setTotalAmount] = useState(5000); 
    const [message, setMessage] = useState('');
    const [payout, setPayout] = useState(0); 
    const [winningPercentage, setWinningPercentage] = useState(0); 

   
    const handleGuessedNumberChange = (newNumber) => {
        setGuessedNumber(newNumber);
        const newWinningPercentage = calculateWinningPercentage(newNumber); 
        setWinningPercentage(newWinningPercentage); 
        setPayout(calculatePayout(newNumber)); 
    };

    
    const handlePlaceBet = async () => {
        try {
            const responseMessage = await placeBetApi(guessedNumber, betAmount); 
            setMessage(responseMessage);

            
            const responseLines = responseMessage.split(", ");
            const totalAmountLine = responseLines.find(line => line.includes("Total Amount:"));
            if (totalAmountLine) {
                const amountString = totalAmountLine.split(": ")[1];
                const newTotalAmount = parseFloat(amountString);
                setTotalAmount(newTotalAmount); 
            }
        } catch (error) {
            setMessage(error.message);
        }
    };

    return (
        <div>
            <h1>A Game</h1>
            <p>Total Amount: {totalAmount.toFixed(2)}</p>
            <BetInput 
                guessedNumber={guessedNumber} 
                onGuessedNumberChange={handleGuessedNumberChange} 
                betAmount={betAmount} 
                setBetAmount={setBetAmount} 
                payout={payout} 
                winningPercentage={winningPercentage} 
            />

            <button 
                onClick={handlePlaceBet} 
                disabled={guessedNumber < 5}
            >
                Place Bet
            </button>
            {message && <p>{message}</p>}
        </div>
    );
};

export default Post;
