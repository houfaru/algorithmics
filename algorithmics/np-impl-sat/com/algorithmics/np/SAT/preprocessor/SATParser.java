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

public class SATParser{
	
	public SentenceTree parse(String sentence) {
		Lexer l=new Lexer (new PushbackReader(new StringReader(sentence)));
		Parser p=new Parser(l);
		try {
			Start startSymbol= p.parse();
			IGenericAnalyzer<?> genericAnalysis=new GenericAnalyzer();
			SentenceTree sentenceTreeResult = (SentenceTree) genericAnalysis.caseStart(startSymbol);
			return sentenceTreeResult;
		} catch (ParserException e) {
			e.printStackTrace();
		} catch (LexerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		throw new RuntimeException("Internal Error: Parsing error");
	}
}
