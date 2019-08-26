package com.algorithmics.np.SAT.preprocessor;

import com.algorithmics.sablecc.node.AAndSentence;
import com.algorithmics.sablecc.node.ANotsentenceSentence;
import com.algorithmics.sablecc.node.ANotvarSentence;
import com.algorithmics.sablecc.node.AOrSentence;
import com.algorithmics.sablecc.node.AVarSentence;
import com.algorithmics.sablecc.node.EOF;
import com.algorithmics.sablecc.node.InvalidToken;
import com.algorithmics.sablecc.node.Start;
import com.algorithmics.sablecc.node.TAnd;
import com.algorithmics.sablecc.node.TBlank;
import com.algorithmics.sablecc.node.TLpar;
import com.algorithmics.sablecc.node.TNot;
import com.algorithmics.sablecc.node.TOr;
import com.algorithmics.sablecc.node.TRpar;
import com.algorithmics.sablecc.node.TVar;

public interface IGenericAnalyzer<T> {
	T caseStart(Start node);
    T caseAAndSentence(AAndSentence node);
    T caseAOrSentence(AOrSentence node);
    T caseAVarSentence(AVarSentence node);
    T caseANotvarSentence(ANotvarSentence node);
    T caseANotsentenceSentence(ANotsentenceSentence node);

    T caseTLpar(TLpar node);
    T caseTRpar(TRpar node);
    T caseTBlank(TBlank node);
    T caseTAnd(TAnd node);
    T caseTOr(TOr node);
    T caseTNot(TNot node);
    T caseTVar(TVar node);
    T caseEOF(EOF node);
    T caseInvalidToken(InvalidToken node);
    
}
