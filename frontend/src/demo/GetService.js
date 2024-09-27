

export const fetchTotalAmountApi = async () => {
    try {
        const response = await fetch("http://localhost:8080/api/betting/total-amount", {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
            },
        });

        if (!response.ok) {
            const errorMessage = await response.text();
            throw new Error(errorMessage);
        }

        const data = await response.json();
        return data.totalAmount;
    } catch (error) {
        throw new Error("Error: " + error.message);
    }
};