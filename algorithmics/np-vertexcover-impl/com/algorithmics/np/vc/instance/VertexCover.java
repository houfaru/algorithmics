package com.algorithmics.np.vc.instance;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.algorithmics.np.core.Certificate;

public class VertexCover extends HashSet<Integer> implements Certificate {

    private static final long serialVersionUID = 1L;

    @Override
    public boolean remove(Object o) {
        if (contains(o)) {
            return super.remove(o);
        }
        return false;
    }

    public List<Integer> getVertices() {
        return new ArrayList<Integer>(this);
    }

}
