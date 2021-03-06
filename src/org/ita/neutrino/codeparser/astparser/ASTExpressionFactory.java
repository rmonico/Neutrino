package org.ita.neutrino.codeparser.astparser;

import org.ita.neutrino.codeparser.ExpressionFactory;
import org.ita.neutrino.codeparser.Type;

class ASTExpressionFactory implements ExpressionFactory {

	@Override
	public ASTLiteralExpression createLiteralExpression(Type type, String value) {
		ASTLiteralExpression expression = new ASTLiteralExpression();

		expression.setValue(value);
		
		expression.setType(type);

		return expression;
	}

}
