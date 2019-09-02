package com.algorithmics.np.SAT.preprocessor;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;

import com.algorithmics.np.SAT.instance.tree.SentenceTree;
import com.algorithmics.sablecc.lexer.Lexer;
import com.algorithmics.sablecc.lexer.LexerException;
import com.algorithmics.sablecc.node.Start;
import com.algorithmics.sablecc.parser.Parser;
import com.algorithmics.sablecc.parser.ParserException;
import com.algorithmics.servicesupport.ExecutionException;

public class SATParser{
	
	public SentenceTree parse(String sentence) throws ExecutionException {
		Lexer l=new Lexer (new PushbackReader(new StringReader(sentence)));
		Parser p=new Parser(l);
		try {
			Start startSymbol= p.parse();
			IGenericAnalyzer<?> genericAnalysis=new GenericAnalyzer();
			SentenceTree sentenceTreeResult = (SentenceTree) genericAnalysis.caseStart(startSymbol);
			return sentenceTreeResult;
		} catch (ParserException e) {
		    throw new ExecutionException(e);
		} catch (LexerException e) {
		    throw new ExecutionException(e);
		} catch (IOException e) {
		    throw new ExecutionException(e);
		}
		
		
	}
}
