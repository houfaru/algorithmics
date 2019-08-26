package com.algorithmics.np.vc.reduction_to_sat;

import java.util.ArrayList;
import java.util.List;

import com.algorithmics.np.SAT.instance.Variable;
import com.algorithmics.np.SAT.instance.CNF.Clause;
import com.algorithmics.np.SAT.instance.CNF.Literal;
import com.algorithmics.np.SAT.instance.CNF.SentenceInCNF;

public class AtLeastOneCNF extends SentenceInCNF {	
	public AtLeastOneCNF(List <Variable>variables) {
		super(new ArrayList<Clause>());
		Clause c=Clause.constructFalseClause();
		for(int i=0;i<variables.size();i++) {
			Variable v1=variables.get(i);
			c.getLiterals().add(new Literal(v1));
		}
		super.getClauses().add(c);
	}
}
