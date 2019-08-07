/* This file was generated by SableCC (http://www.sablecc.org/). */

package com.algorithmics.sablecc.node;

import com.algorithmics.sablecc.analysis.*;

@SuppressWarnings("nls")
public final class TOr extends Token
{
    public TOr(String text)
    {
        setText(text);
    }

    public TOr(String text, int line, int pos)
    {
        setText(text);
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TOr(getText(), getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTOr(this);
    }
}
