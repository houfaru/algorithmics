package com.algorithmics.np.vc.reduction_to_sat;

import java.util.ArrayList;
import java.util.List;

import com.algorithmics.np.SAT.instance.Variable;
import com.algorithmics.np.SAT.instance.CNF.Clause;
import com.algorithmics.np.SAT.instance.CNF.Literal;
import com.algorithmics.np.SAT.instance.CNF.SentenceInCNF;

public class AtMostOneCNF extends SentenceInCNF {

	
	
	public AtMostOneCNF(List <Variable>variables) {
		super(new ArrayList<Clause>());
		
		for(int i=0;i<variables.size()-1;i++) {
			for(int j=i+1;j<variables.size();j++) {
				Variable v1=variables.get(i);
				Variable v2=variables.get(j);
				Clause c=getNotV1OrNotV2Clause(v1, v2);
				super.getClauses().add(c);
			}	
		}
	}

	private Clause getNotV1OrNotV2Clause(Variable v1, Variable v2) {
		Literal lit1=new Literal(v1);
		Literal lit2=new Literal(v2);
		lit1.setNegated(true);
		lit2.setNegated(true);
		List<Literal>literals=new ArrayList<Literal>();
		literals.add(lit1);
		literals.add(lit2);
		Clause c=new Clause(literals);
		return c;
	}

}
