package org.ita.neutrino.codeparser;

import java.util.List;

public interface MutableMethod extends Method {
	
	void addAnnotation(Type junit4BeforeAnnotation);

	void addStatements(List<Statement> codeStatements);

	void removeFirstStatements(int count);
}