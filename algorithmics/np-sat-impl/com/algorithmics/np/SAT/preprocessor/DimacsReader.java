package com.algorithmics.np.SAT.preprocessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.algorithmics.np.SAT.instance.Variable;
import com.algorithmics.np.SAT.instance.CNF.Clause;
import com.algorithmics.np.SAT.instance.CNF.Literal;
import com.algorithmics.np.SAT.instance.CNF.SentenceInCNF;
import com.algorithmics.np.preprocessor.SpecificFormatReader;

public class DimacsReader implements SpecificFormatReader<SentenceInCNF>{

	@Override
	public SentenceInCNF readFromFile(String filePath) {
		try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
			//warning !!!, we assume that the dimacs file is in correct format.
			//correct format => correct parsing
			//but correct parsing =/> correct format
			List<String> lines = stream.collect(Collectors.toList());
			Integer numOfVariables=null;
			Integer numOfClauses=null;
			List<Clause> clauses=new ArrayList<Clause>();
			for(String line:lines) {
				if(line.trim().startsWith("%")) {
					break;
				}
				if(line.trim().startsWith("c")) {
					continue;
				}
				if(line.trim().startsWith("p cnf")) {
					String[] split = line.trim().split("\\s+");
					numOfVariables=Integer.valueOf(split[2]);
					numOfClauses=Integer.valueOf(split[3]);
					continue;
				}
				
				Objects.requireNonNull(numOfClauses);
				Objects.requireNonNull(numOfVariables);
				
				String[] split = line.trim().split("\\s+");
				
				List<Literal> literals = Arrays.stream(split)
				.filter(s->!s.equals("0"))
				.map(s->Integer.valueOf(s))
				.map(i->{
					Variable v=new Variable(Math.abs(i)+"");
					Literal literal=new Literal(v);
					literal.setNegated(i<0);
					return literal;
				}).collect(Collectors.toList());
				
				Clause c=new Clause(literals);
				clauses.add(c);
			}
			
			SentenceInCNF newSentence=new SentenceInCNF(clauses);
			return newSentence;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
