    import axios from "axios";

    const API_BASE_URL = "http://localhost:8083/api/orchestration";

    export const processPolynomial = async (expression) => {
    try {
        const response = await axios.post(`${API_BASE_URL}/process`, { expression });
        return response.data; // Expecting a string with the factorization result
    } catch (error) {
        console.error("Error processing polynomial:", error);
        throw new Error(
        error.response?.data?.message || "An error occurred while processing the polynomial."
        );
    }
    };

    export const getRoots = async (expression) => {
        try {
          const response = await axios.post(`${API_BASE_URL}/roots`, { expression });
          return response.data; // Expecting an array of roots
        } catch (error) {
          console.error("Error fetching roots:", error);
          throw new Error(
            error.response?.data?.message || "An error occurred while fetching the roots."
          );
        }
      };
