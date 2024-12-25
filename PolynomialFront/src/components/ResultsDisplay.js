import React from "react";
import { MathComponent } from "mathjax-react";

const ResultsDisplay = ({ roots, factorization }) => {
  return (
    <div className="mt-6 bg-gray-50 p-6 rounded-lg shadow">
      <h2 className="text-xl font-semibold text-gray-800 mb-4">Results</h2>
      {roots && roots.length > 0 && (
        <div>
          <strong className="block text-gray-600">Roots:</strong>
          <ul className="list-disc list-inside text-gray-700">
            {roots.map((root, index) => (
              <li key={index}>{root}</li>
            ))}
          </ul>
        </div>
      )}
      {factorization && (
        <div className="mt-4">
          <strong className="block text-gray-600">Factorization:</strong>
          <MathComponent tex={factorization} className="text-gray-700" />
        </div>
      )}
    </div>
  );
};

export default ResultsDisplay;
