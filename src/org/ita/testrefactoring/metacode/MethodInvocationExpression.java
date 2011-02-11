package org.ita.testrefactoring.metacode;

import java.util.ArrayList;
import java.util.List;


/**
 * Representação de uma expressão onde o parâmetro é uma invocação de método.
 * 
 * @author Rafael Monico
 * 
 */
public class MethodInvocationExpression implements Expression, MethodInvocation {

	private MethodDeclaration calledMethod;
	private List<Expression> parameterList = new ArrayList<Expression>();

	public MethodDeclaration getCalledMethod() {
		return calledMethod;
	}

	void setCalledMethod(MethodDeclaration calledMethod) {
		this.calledMethod = calledMethod;
	}
	
	@Override
	public List<Expression> getParameterList() {
		return parameterList;
	}
}