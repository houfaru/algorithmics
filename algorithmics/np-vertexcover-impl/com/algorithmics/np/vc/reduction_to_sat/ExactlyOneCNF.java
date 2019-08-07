package com.algorithmics.np.vc.reduction_to_sat;

import java.util.ArrayList;
import java.util.List;

import com.algorithmics.np.SAT.instance.Variable;
import com.algorithmics.np.SAT.instance.CNF.Clause;
import com.algorithmics.np.SAT.instance.CNF.SentenceInCNF;

public class ExactlyOneCNF extends SentenceInCNF {	
	public ExactlyOneCNF(List <Variable>variables) {
		super(new ArrayList<Clause>());
		AtMostOneCNF atMostOne=new AtMostOneCNF(variables);
		AtLeastOneCNF atLeastOne=new AtLeastOneCNF(variables);
		super.getClauses().addAll(atMostOne.getClauses());
		super.getClauses().addAll(atLeastOne.getClauses());
	}
}
