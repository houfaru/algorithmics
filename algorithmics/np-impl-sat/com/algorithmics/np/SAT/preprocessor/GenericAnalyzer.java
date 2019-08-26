package com.algorithmics.np.SAT.preprocessor;

import com.algorithmics.np.SAT.instance.Variable;
import com.algorithmics.np.SAT.instance.tree.BooleanOperationEnum;
import com.algorithmics.np.SAT.instance.tree.SentenceTree;
import com.algorithmics.sablecc.node.AAndSentence;
import com.algorithmics.sablecc.node.ANotsentenceSentence;
import com.algorithmics.sablecc.node.ANotvarSentence;
import com.algorithmics.sablecc.node.AOrSentence;
import com.algorithmics.sablecc.node.AVarSentence;
import com.algorithmics.sablecc.node.EOF;
import com.algorithmics.sablecc.node.InvalidToken;
import com.algorithmics.sablecc.node.PSentence;
import com.algorithmics.sablecc.node.Start;
import com.algorithmics.sablecc.node.TAnd;
import com.algorithmics.sablecc.node.TBlank;
import com.algorithmics.sablecc.node.TLpar;
import com.algorithmics.sablecc.node.TNot;
import com.algorithmics.sablecc.node.TOr;
import com.algorithmics.sablecc.node.TRpar;
import com.algorithmics.sablecc.node.TVar;

public class GenericAnalyzer implements IGenericAnalyzer<SentenceTree> {

    public enum NODE_CLASS {
        AAndSentence, AOrSentence, AVarSentence, ANotvarSentence, ANotsentenceSentence
    };

    public SentenceTree apply(PSentence ps) {
        NODE_CLASS nodeClass = NODE_CLASS.valueOf(ps.getClass().getSimpleName());
        switch (nodeClass) {
            case AAndSentence:
                return caseAAndSentence((AAndSentence) ps);
            case AOrSentence:
                return caseAOrSentence((AOrSentence) ps);
            case AVarSentence:
                return caseAVarSentence((AVarSentence) ps);
            case ANotvarSentence:
                return caseANotvarSentence((ANotvarSentence) ps);
            case ANotsentenceSentence:
                return caseANotsentenceSentence((ANotsentenceSentence) ps);
            default:
                throw new RuntimeException(nodeClass + " undefineable");
        }
    }

    @Override
    public SentenceTree caseStart(Start node) {
        return apply(node.getPSentence());
    }

    @Override
    public SentenceTree caseAAndSentence(AAndSentence node) {
        SentenceTree st = SentenceTree.constructNonLeafTree(BooleanOperationEnum.AND,
                apply(node.getLeft()), apply(node.getRight()));
        return st;
    }

    @Override
    public SentenceTree caseAOrSentence(AOrSentence node) {
        SentenceTree st = SentenceTree.constructNonLeafTree(BooleanOperationEnum.OR,
                apply(node.getLeft()), apply(node.getRight()));
        return st;
    }

    @Override
    public SentenceTree caseAVarSentence(AVarSentence node) {
        SentenceTree st = SentenceTree.constractLeafTree(new Variable(node.getVar().getText()));
        return st;
    }

    @Override
    public SentenceTree caseANotvarSentence(ANotvarSentence node) {
        SentenceTree st = SentenceTree.constractLeafTree(new Variable(node.getVar().getText()));
        st.setNegated(true);
        return st;
    }

    @Override
    public SentenceTree caseANotsentenceSentence(ANotsentenceSentence node) {
        SentenceTree st = apply(node.getSentence());
        st.setNegated(true);
        return st;
    }

    @Override
    public SentenceTree caseTLpar(TLpar node) {
        throw new RuntimeException(String.format(
                "Internal Error: Concrete Syntax Tree %s is supposedly ignored", node.getText()));

    }

    @Override
    public SentenceTree caseTRpar(TRpar node) {
        throw new RuntimeException(String.format(
                "Internal Error: Concrete Syntax Tree %s is supposedly ignored", node.getText()));
    }

    @Override
    public SentenceTree caseTBlank(TBlank node) {
        throw new RuntimeException(String.format(
                "Internal Error: Concrete Syntax Tree %s is supposedly ignored", node.getText()));
    }

    @Override
    public SentenceTree caseTAnd(TAnd node) {
        throw new RuntimeException(String.format(
                "Internal Error: Concrete Syntax Tree %s is supposedly ignored", node.getText()));
    }

    @Override
    public SentenceTree caseTOr(TOr node) {
        throw new RuntimeException(String.format(
                "Internal Error: Concrete Syntax Tree %s is supposedly ignored", node.getText()));
    }

    @Override
    public SentenceTree caseTNot(TNot node) {
        throw new RuntimeException(String.format(
                "Internal Error: Concrete Syntax Tree %s is supposedly ignored", node.getText()));
    }

    @Override
    public SentenceTree caseTVar(TVar node) {
        throw new RuntimeException(String.format(
                "Internal Error: Concrete Syntax Tree %s is supposedly ignored", node.getText()));
    }

    @Override
    public SentenceTree caseEOF(EOF node) {
        throw new RuntimeException(String.format(
                "Internal Error: Concrete Syntax Tree %s is supposedly ignored", node.getText()));
    }

    @Override
    public SentenceTree caseInvalidToken(InvalidToken node) {
        throw new RuntimeException(String.format(
                "Internal Error: Concrete Syntax Tree %s is supposedly ignored", node.getText()));
    }



}
