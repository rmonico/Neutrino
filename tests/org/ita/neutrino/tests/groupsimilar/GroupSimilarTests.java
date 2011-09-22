package org.ita.neutrino.tests.groupsimilar;

import org.ita.neutrino.abstracrefactoring.AbstractRefactoring;
import org.ita.neutrino.groupsimilartests.GroupSimilarTestsRefactoring;
import org.ita.neutrino.tests.RefactoringAbstractTests;

public abstract class GroupSimilarTests extends RefactoringAbstractTests {

	@Override
	protected AbstractRefactoring instantiateRefactoring() {
		// TODO Auto-generated method stub
		return new GroupSimilarTestsRefactoring();
	}

	@Override
	protected String getRefactoringName() {
		// TODO Auto-generated method stub
		return "Group Similar Tests";
	}

	@Override
	protected void setupRefactoring() {
		// informa parametros pra refatoracao.
		super.setupRefactoring();
	}

	protected StringBuilder getProductionSource() {
		StringBuilder productionClassCode = new StringBuilder();
		
		productionClassCode.append("package org.ita.neutrino.businessclasses;\n");
		productionClassCode.append("\n");
		productionClassCode.append("public class CalculadorImposto {\n");
		productionClassCode.append("	public CalculadorImposto(int valor) {\n");
		productionClassCode.append("	}\n");
		productionClassCode.append("\n");
		productionClassCode.append("	public double getPercentagem() {\n");
		productionClassCode.append("		return 0;\n");
		productionClassCode.append("	}\n");
		productionClassCode.append("}\n");

		return productionClassCode;
	}
}
