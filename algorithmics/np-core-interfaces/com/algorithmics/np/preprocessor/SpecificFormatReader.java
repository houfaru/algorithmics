package com.algorithmics.np.preprocessor;

import com.algorithmics.np.core.NPHardProblem;
import com.algorithmics.np.core.ProblemStructure;
import com.algorithmics.servicesupport.UserExecutionException;

public interface SpecificFormatReader<T extends ProblemStructure> {
	public T readFromFile(String filePath) throws UserExecutionException;
	public String getExtension();
	public NPHardProblem<?> getWithParameter(T instance, double k);
}
