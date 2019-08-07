package com.algorithmics.np.SAT.instance;


public class Variable{
	private String varName;
		
	public Variable(String varName) {
		super();
		this.varName = varName;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Variable) {
			return ((Variable)obj).varName.equals(varName);
		}else {
			java.util.logging.Logger.getGlobal().severe("Object mismatch");
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return varName.hashCode();
	}
	
	@Override
	public String toString() {
		return varName;
	}
	
}
