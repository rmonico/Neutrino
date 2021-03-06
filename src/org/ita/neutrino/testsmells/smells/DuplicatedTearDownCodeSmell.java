package org.ita.neutrino.testsmells.smells;

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.ita.neutrino.refactorings.extractfinalizationmethod.ExtractFinalizationMethodHandler;
import org.ita.neutrino.refactorings.extractmethod.CommonStatementFinder;
import org.ita.neutrino.testsmells.core.MarkerManager;
import org.ita.neutrino.tparsers.abstracttestparser.TestStatement;
import org.ita.neutrino.tparsers.abstracttestparser.TestSuite;

import com.google.inject.Inject;

@NeutrinoRefactoringForEclipse(
		value=ExtractFinalizationMethodHandler.class,
		title="Extract to teardown method",
		description="Extracts the repeated initialization code into a teardown method")
public class DuplicatedTearDownCodeSmell implements TestCodeSmell<TestSuite> {

	private final CommonStatementFinder duplicatedCodeFinder;
	
	@Inject
	public DuplicatedTearDownCodeSmell(CommonStatementFinder duplicatedCodeFinder) {
		this.duplicatedCodeFinder = duplicatedCodeFinder;
	}
	
	@Override
	public void checkForPresence(TestSuite testClass, MarkerManager markerManager) throws CoreException {
		List<TestStatement> duplicatedCode =
			duplicatedCodeFinder.listCommonStatements(testClass, /* fromBegin */ false);
		
		if (!duplicatedCode.isEmpty() && testClass.getTestMethodList().size() > 1) {
			markerManager.addMarker(testClass.getCodeElement(), 
					"Duplicated finalization code", this.getClass());
		}
	}	
}
