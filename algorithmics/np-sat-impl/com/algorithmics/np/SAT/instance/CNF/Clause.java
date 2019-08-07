package com.algorithmics.np.SAT.instance.CNF;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import com.algorithmics.np.SAT.instance.Sentence;
import com.algorithmics.np.SAT.instance.Variable;
import com.algorithmics.np.SAT.instance.VariableAssignment;
import com.algorithmics.util.Symbol;

public class Clause extends Sentence{
	private Optional<List<Literal>>literals;
		
	private Clause() {}
	
	public Clause(List<Literal> literals) {
		super();
		this.literals = Optional.of(literals);
	}
	
	public static Clause constructTrueClause() {
		//a clause with null literals
		Clause c=new Clause();
		c.literals=Optional.empty();
		return c;
	}
	
	public static Clause constructFalseClause() {
		//a clause with non null but empty literals
		Clause c=new Clause();
		c.literals=Optional.of(new ArrayList<Literal>());
		return c;
	}

	@Override
	public HashSet<Variable> getVariables() {
		HashSet<Variable>newVars=new HashSet<Variable>();
		literals.get().forEach(v->newVars.addAll(v.getVariables()));
		return newVars;
	}
	
	public List<Literal> getLiterals(){
		return this.literals.get();
	}
	
	@Override
	public boolean isNegated() {
		return false;
	}


	@Override
	public boolean verify(VariableAssignment certificate)  {
		for(Literal l:literals.get()) {
			boolean v=l.verify(certificate);
			if(v) {
				return true;
			}
		}
		return false;
	}
	/**
	 * an empty clause is a FALSE clause=there is an empty set of literals
	 * @return
	 */
	public boolean isEmptyClause() {
		return literals.isPresent() && literals.get().size()==0;
	}
	
	/**
	 * a true clause is a clause with no literal set (i.e. null set of literals)
	 * @return
	 */
	public boolean isTrueClause() {
		return !literals.isPresent();
	}
	
	public void assignOneVariableAndReduce(Variable variable,boolean value) {
		List<Literal>newLiterals=new ArrayList<Literal>();
		boolean certificateFound=false;
		for(Literal l:this.literals.get()) {
			Variable vLiteral=l.getVariables().stream().findAny().get();
			if(vLiteral.equals(variable)) {
				if(value && !l.isNegated()|| !value && l.isNegated()) {
					certificateFound=true;
					break;
				}
			}else {
				newLiterals.add(l);
			}
		}
		if(certificateFound) {
			literals=Optional.empty();
		}else {
			literals=Optional.of(newLiterals);
		}
	}
	
	@Override
	public Clause clone() {
		Clause c=new Clause(this.literals.get());
		return c;
	}
	
	@Override
	public String toString() {
		if(isTrueClause()) {
			return "TRUE";
		}
		if(isEmptyClause()) {
			return "FALSE";
		}
		String clauseString = literals.get().stream().map(l->l.toString()).reduce((a,b)->a+" "+Symbol.OR+" "+b).get();
		return "("+clauseString+")";
		
	}

}
