package com.algorithmics.np.preprocessor;

import com.algorithmics.np.core.NPHardProblem;
import com.algorithmics.np.core.ProblemStructure;

public interface SpecificFormatReader<T extends ProblemStructure> {
	T readFromFile(String filePath);
	String getExtension();
	NPHardProblem<?> getWithParameter(T instance, double k);
}
