import React, { useState } from "react";
import PolynomialInput from "./components/PolynomialInput";
import ResultsDisplay from "./components/ResultsDisplay";

const App = () => {
  const [results, setResults] = useState({ roots: [], factorization: "" });

  return (
    <div className="min-h-screen bg-gradient-to-b from-blue-50 to-blue-300 flex items-center justify-center p-6">
      <div className="bg-white shadow-2xl rounded-lg p-8 max-w-lg w-full">
        <h1 className="text-3xl font-bold text-center text-blue-700 mb-8">
          Polynomial Roots Calculator
        </h1>
        <PolynomialInput onResults={setResults} />
        <ResultsDisplay roots={results.roots} factorization={results.factorization} />
      </div>
    </div>
  );
};

export default App;
