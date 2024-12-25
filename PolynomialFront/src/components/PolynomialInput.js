import React, { useState } from "react";
import { processPolynomial, getRoots } from "../services/api";

const PolynomialInput = ({ onResults }) => {
  const [polynomial, setPolynomial] = useState("");
  const [factorize, setFactorize] = useState(false);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError(null);

    try {
      // Fetch roots
      const roots = await getRoots(polynomial);
      console.log("Roots:", roots);

      // Fetch factorization
      const factorization = await processPolynomial(polynomial);
      console.log("Factorization:", factorization);

      // Pass results to the parent component
      onResults({ roots, factorization });
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <div className="mb-6">
        <label className="block text-gray-700 font-medium mb-2">Enter Polynomial:</label>
        <input
          type="text"
          value={polynomial}
          onChange={(e) => setPolynomial(e.target.value)}
          className="w-full p-3 border border-gray-300 rounded-md shadow-sm"
          placeholder="e.g., x^3 - 6x^2 + 11x - 6"
        />
      </div>

      <div className="grid grid-cols-4 gap-2 mb-6">
        {["x", "^", "+", "-", "(", ")", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"].map(
          (key) => (
            <button
              type="button"
              key={key}
              onClick={() => setPolynomial((prev) => prev + key)}
              className="p-3 bg-gray-200 text-gray-700 rounded-md shadow hover:bg-gray-300 transition duration-200"
            >
              {key}
            </button>
          )
        )}
        <button
          type="button"
          onClick={() => setPolynomial("")}
          className="col-span-4 p-3 bg-red-500 text-white rounded-md shadow hover:bg-red-600 transition duration-200"
        >
          Clear
        </button>
      </div>

      {error && <p className="text-red-500 mb-4">{error}</p>}

      <div className="flex items-center justify-between">
        <label className="inline-flex items-center">
          <input
            type="checkbox"
            checked={factorize}
            onChange={(e) => setFactorize(e.target.checked)}
            className="mr-2"
          />
          Factorize Symbolically
        </label>
        <button
          type="submit"
          disabled={loading}
          className={`bg-blue-500 text-white py-2 px-6 rounded-md shadow hover:bg-blue-600 transition duration-200 ${
            loading ? "opacity-50 cursor-not-allowed" : ""
          }`}
        >
          {loading ? "Processing..." : "Calculate"}
        </button>
      </div>
    </form>
  );
};

export default PolynomialInput;
