/* This file was generated by SableCC (http://www.sablecc.org/). */

package com.algorithmics.sablecc.parser;

import com.algorithmics.sablecc.analysis.*;
import com.algorithmics.sablecc.node.*;

class TokenIndex extends AnalysisAdapter
{
    int index;

    @Override
    public void caseTLpar(@SuppressWarnings("unused") TLpar node)
    {
        this.index = 0;
    }

    @Override
    public void caseTRpar(@SuppressWarnings("unused") TRpar node)
    {
        this.index = 1;
    }

    @Override
    public void caseTAnd(@SuppressWarnings("unused") TAnd node)
    {
        this.index = 2;
    }

    @Override
    public void caseTOr(@SuppressWarnings("unused") TOr node)
    {
        this.index = 3;
    }

    @Override
    public void caseTNot(@SuppressWarnings("unused") TNot node)
    {
        this.index = 4;
    }

    @Override
    public void caseTVar(@SuppressWarnings("unused") TVar node)
    {
        this.index = 5;
    }

    @Override
    public void caseEOF(@SuppressWarnings("unused") EOF node)
    {
        this.index = 6;
    }
}
