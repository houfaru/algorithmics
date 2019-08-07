/* This file was generated by SableCC (http://www.sablecc.org/). */

package com.algorithmics.sablecc.analysis;

import com.algorithmics.sablecc.node.AAndSentence;
import com.algorithmics.sablecc.node.ANotsentenceSentence;
import com.algorithmics.sablecc.node.ANotvarSentence;
import com.algorithmics.sablecc.node.AOrSentence;
import com.algorithmics.sablecc.node.AVarSentence;
import com.algorithmics.sablecc.node.Node;
import com.algorithmics.sablecc.node.Start;

public class ReversedDepthFirstAdapter extends AnalysisAdapter
{
    public void inStart(Start node)
    {
        defaultIn(node);
    }

    public void outStart(Start node)
    {
        defaultOut(node);
    }

    public void defaultIn(@SuppressWarnings("unused") Node node)
    {
        // Do nothing
    }

    public void defaultOut(@SuppressWarnings("unused") Node node)
    {
        // Do nothing
    }

    @Override
    public void caseStart(Start node)
    {
        inStart(node);
        node.getEOF().apply(this);
        node.getPSentence().apply(this);
        outStart(node);
    }

    public void inAAndSentence(AAndSentence node)
    {
        defaultIn(node);
    }

    public void outAAndSentence(AAndSentence node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAndSentence(AAndSentence node)
    {
        inAAndSentence(node);
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        outAAndSentence(node);
    }

    public void inAOrSentence(AOrSentence node)
    {
        defaultIn(node);
    }

    public void outAOrSentence(AOrSentence node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAOrSentence(AOrSentence node)
    {
        inAOrSentence(node);
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        outAOrSentence(node);
    }

    public void inAVarSentence(AVarSentence node)
    {
        defaultIn(node);
    }

    public void outAVarSentence(AVarSentence node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVarSentence(AVarSentence node)
    {
        inAVarSentence(node);
        if(node.getVar() != null)
        {
            node.getVar().apply(this);
        }
        outAVarSentence(node);
    }

    public void inANotvarSentence(ANotvarSentence node)
    {
        defaultIn(node);
    }

    public void outANotvarSentence(ANotvarSentence node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANotvarSentence(ANotvarSentence node)
    {
        inANotvarSentence(node);
        if(node.getVar() != null)
        {
            node.getVar().apply(this);
        }
        outANotvarSentence(node);
    }

    public void inANotsentenceSentence(ANotsentenceSentence node)
    {
        defaultIn(node);
    }

    public void outANotsentenceSentence(ANotsentenceSentence node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANotsentenceSentence(ANotsentenceSentence node)
    {
        inANotsentenceSentence(node);
        if(node.getSentence() != null)
        {
            node.getSentence().apply(this);
        }
        outANotsentenceSentence(node);
    }
}
