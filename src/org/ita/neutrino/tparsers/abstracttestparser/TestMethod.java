package org.ita.neutrino.tparsers.abstracttestparser;

import java.util.List;

import org.ita.neutrino.codeparser.Method;
import org.ita.neutrino.codeparser.MutableMethod;

public interface TestMethod extends TestElement<Method>, Comparable<Object> {

	public abstract String getName();

	public abstract TestSuite getParent();

	public abstract List<? extends TestStatement> getStatements();

	public abstract void addStatements(List<TestStatement> statements, int index);

	public MutableMethod getCodeElement();

	public abstract void removeStatements(int index, int count);
	
	public boolean isAfterTestMethod();
	
	public boolean isBeforeTestMethod();
	
	public boolean isTestMethod();
	
	public boolean isAbstract();
	
	public void setAbstract();
}
