package com.algorithmics.np.SAT.instance.CNF;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.algorithmics.np.SAT.instance.Sentence;
import com.algorithmics.np.SAT.instance.Variable;
import com.algorithmics.np.SAT.instance.VariableAssignment;
import com.algorithmics.util.Symbol;

public class SentenceInCNF extends Sentence{
	private List<Clause> clauses;
	
	@Override
	public HashSet<Variable> getVariables() {
		HashSet<Variable>varSet=new HashSet<Variable>();
		clauses.forEach(c->varSet.addAll(c.getVariables()));
		return varSet;
	}
	
	public List<Clause> getClauses(){
		return clauses;
	}
	
	public SentenceInCNF(List<Clause> clauses) {
		super();
		this.clauses=clauses;
	}
	
	public static SentenceInCNF constructMinimalTrueSentence() {
		return new SentenceInCNF(new ArrayList<Clause>());
	}
	
	public static SentenceInCNF constructMinimalFalseSentence() {
		ArrayList<Clause> cl=new ArrayList<Clause>();
		cl.add(Clause.constructFalseClause());
		return new SentenceInCNF(cl);
	}
	
	public boolean containsEmptyClause() {
		return clauses.stream().anyMatch(c->c.isEmptyClause());
		
	}
	public Optional<Boolean> isTautology() {
		if(containsEmptyClause()) {
			return Optional.of(false);
		}
		if(clauses.isEmpty()) {
			return Optional.of(true);
		}
		return Optional.empty();
	}
	
	public List<Variable>getPureLiteralVars(){
		
		List<Variable> negatedVars 	  = clauses.stream().flatMap(s->s.getLiterals().stream()).distinct().filter(l->l.isNegated()).map(l->l.getVariables().stream().findAny().get()).distinct().collect(Collectors.toList());
		List<Variable> nonNegatedVars = clauses.stream().flatMap(s->s.getLiterals().stream()).distinct().filter(l->!l.isNegated()).map(l->l.getVariables().stream().findAny().get()).distinct().collect(Collectors.toList());
		
		List<Variable> pure=negatedVars.stream().filter(l->!nonNegatedVars.contains(l)).collect(Collectors.toList());
		List<Variable> pureNonNegated=nonNegatedVars.stream().filter(l->!negatedVars.contains(l)).collect(Collectors.toList());
		pure.addAll(pureNonNegated);
		
		return pure;
	}
	
	public SentenceInCNF removePureLiterals() {
		
		List<Variable> pureLiteralVars = getPureLiteralVars();
		List<Clause>newClauses=new ArrayList<>(clauses);
		for(Variable v:pureLiteralVars) {
			newClauses = newClauses.stream().filter(c->!c.getVariables().contains(v)).collect(Collectors.toList());
		}
		return new SentenceInCNF(newClauses);
	}
	
	public List<Clause>getUnitClauses() {
		return clauses.stream().filter(c->c.getLiterals().size()==1).collect(Collectors.toList());
	}
	
	public SentenceInCNF assignOneVariableAndReduce(Variable variable,boolean value) {
		SentenceInCNF scnf=SentenceInCNF.constructMinimalTrueSentence();
		for(Clause c:clauses) {
			Clause newClause=c.clone();
			newClause.assignOneVariableAndReduce(variable, value);
			if(!newClause.isTrueClause() && !newClause.isEmptyClause()) {
				scnf.getClauses().add(newClause);
			}
			if(newClause.isEmptyClause()) {
				return SentenceInCNF.constructMinimalFalseSentence(); 
			}
		}
		return scnf;
	}
	
	@Override
	public boolean verify(VariableAssignment certificate) {
		for(Clause c:clauses) {
			boolean v=c.verify(certificate);
			if(!v) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public String toString() {
		if(isTautology().isPresent() && isTautology().get()) {
			return "TRUE";
		}
		if(isTautology().isPresent() && !isTautology().get()) {
			return "FALSE";
		}
		return clauses.stream().map(c->c.toString()).reduce((a,b)->a+" "+Symbol.AND+" "+b).get(); 
	}
	public String toDimacsFile() {
		String pathString="data/tempIn.dimacs";
		Path path = Paths.get(pathString);
		try (BufferedWriter writer = Files.newBufferedWriter(path))
		{
			
			String newCNFContent="p cnf "+this.getVariables().size()+" "+this.getClauses().size()+"\n";
			for(Clause c:this.clauses) {
				String clauseString="";
				for(Literal l:c.getLiterals()) {
					String varString=l.getVariables().stream().findAny().get().toString();
					
					String negatedSymbol=l.isNegated()?"-":"";
					clauseString+=negatedSymbol;
					clauseString+=varString;
					clauseString+=" ";
				}
				clauseString+="0\n";
				newCNFContent+=clauseString;
				
			}
			writer.write(newCNFContent);
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return pathString;
	}
}
