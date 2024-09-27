export const placeBetApi = async (guessedNumber, betAmount) => {
    const betData = {
        guessedNumber,
        betAmount,
    };
    try {
        const response = await fetch("http://localhost:8080/api/betting/place-bet", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(betData),
        });

        if (!response.ok) {
            const errorMessage = await response.text();
            throw new Error(errorMessage);
        }

        const data = await response.text();
        return data;
    } catch (error) {
        throw new Error("Error: " + error.message);
    }
};

