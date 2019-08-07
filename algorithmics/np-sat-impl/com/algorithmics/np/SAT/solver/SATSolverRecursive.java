package com.algorithmics.np.SAT.solver;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import com.algorithmics.np.SAT.instance.Variable;
import com.algorithmics.np.SAT.instance.VariableAssignment;
import com.algorithmics.np.SAT.instance.CNF.Clause;
import com.algorithmics.np.SAT.instance.CNF.Literal;
import com.algorithmics.np.SAT.instance.CNF.SentenceInCNF;
import com.algorithmics.np.SAT.instance.tree.SentenceTree;
import com.algorithmics.np.SAT.preprocessor.SATParser;
import com.algorithmics.np.core.Solver;

public class SATSolverRecursive implements Solver<SentenceInCNF, VariableAssignment>{

	@Override
	public Optional<VariableAssignment> solve(SentenceInCNF sentence) {
		
		SentenceInCNF newSentence=sentence.removePureLiterals();
		
		List<Clause> unitClauses = newSentence.getUnitClauses();
		for(Clause c:unitClauses) {
			Literal literal = c.getLiterals().stream().findAny().get();
			newSentence=newSentence.assignOneVariableAndReduce(literal.getVariables().stream().findAny().get(),!literal.isNegated());
		}
		
		if(newSentence.getClauses().size()==0) {
			return Optional.of(VariableAssignment.constructEmptyAssignment());
		}
		if(newSentence.containsEmptyClause()) {
			return Optional.empty();
		}
		
		HashSet<Variable> variables = newSentence.getVariables();
		Variable oneOfTheVariable=variables.stream().findAny().get();
		
		SentenceInCNF firstReducedSentence = newSentence.assignOneVariableAndReduce(oneOfTheVariable,true);
		Optional<VariableAssignment> firstSentenceSolution = solve(firstReducedSentence);
		if(firstSentenceSolution.isPresent()) {
			firstSentenceSolution.get().assign(oneOfTheVariable, true);
			return firstSentenceSolution;
		}
		
		SentenceInCNF secondReducedSentence = newSentence.assignOneVariableAndReduce(oneOfTheVariable,false);
		Optional<VariableAssignment> secondSentenceSolution = solve(secondReducedSentence);
		if(secondSentenceSolution.isPresent()) {
			secondSentenceSolution.get().assign(oneOfTheVariable, false);
			return secondSentenceSolution;
		}
		
		return Optional.empty();
	}
	
	public Optional<VariableAssignment> solve(String sentence){
		SATParser parser=new SATParser();
		SentenceTree s=parser.parse(sentence);
		SentenceInCNF p=s.toCNF();
		return solve(p);
	}
	
	@Override
	public boolean verify(SentenceInCNF p, VariableAssignment sc) {
		return p.verify(sc);
		
	}	

}