

export const calculateWinningPercentage = (number) => {
    const lowerBound = 2;
    const upperBound = 98;

    
    if (number < lowerBound || number > upperBound) {
        return 0;
    }

    
    return ((upperBound - number) / (upperBound - lowerBound)) * 100;
};


export const calculatePayout = (number) => {
    const lowerBound = 2;
    const upperBound = 98;

    
    if (number < lowerBound || number > upperBound) {
        return 0;
    }

    const winningPercentage = calculateWinningPercentage(number);

    let payOut;

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

    return Math.min(Math.max(payOut, 1.02), 50.00);
};
