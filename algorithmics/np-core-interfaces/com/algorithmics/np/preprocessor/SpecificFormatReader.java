package com.algorithmics.np.preprocessor;

public interface SpecificFormatReader<T> {
	T readFromFile(String filePath);
	String getExtension();
}
