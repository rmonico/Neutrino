package org.ita.neutrino.tests;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.ita.neutrino.eclipseaction.ActionException;
import org.ita.neutrino.eclipseaction.IAction;
import org.ita.neutrino.tests.addexplanation.AddExplanationToAssertionJUnit3Tests;
import org.ita.neutrino.tests.addexplanation.AddExplanationToAssertionJUnit4Tests;
import org.ita.neutrino.tests.addfixture.AddFixtureJUnit3Tests;
import org.ita.neutrino.tests.astparser.TestPackageParsing;
import org.ita.neutrino.tests.astparser.TestSupportedBlockSintax;
import org.ita.neutrino.tests.extractfinalizationmethod.ExtractFinalizationMethodJUnit3Tests;
import org.ita.neutrino.tests.extractfinalizationmethod.ExtractFinalizationMethodJUnit4Tests;
import org.ita.neutrino.tests.extractinitializationmethod.ExtractInitializationMethodJUnit3Tests;
import org.ita.neutrino.tests.extractinitializationmethod.ExtractInitializationMethodJUnit4Tests;
import org.ita.neutrino.tests.junitparsers.JUnit3ParserTests;
import org.ita.neutrino.tests.junitparsers.JUnit4ParserTests;
import org.junit.runner.JUnitCore;
import org.zero.utils.tests.JUnitUtilsTests;
import org.zero.utils.tests.ListenerTests;
import org.zero.utils.tests.StringUtilsTests;

public class JUnitMain implements IAction {
	
	@Override
	public ISelection getSelection() {
		return null;
	}

	@Override
	public void setSelection(ISelection selection) {
	}

	@Override
	public void run() throws ActionException {
		JUnitCore core = new JUnitCore();
		
		ConsoleListener listener = new ConsoleListener();
		
		core.addListener(listener);
		
		Class<?>[] classesToTest = getClassesToTest();
		
		core.run(classesToTest);
		
		if (listener.isAllTestsOk()) {
			MessageDialog.openInformation(null, "JUnit Tests", listener.getResultsString());
		} else {
			MessageDialog.openWarning(null, "JUnit Tests", listener.getResultsString());
		}

	}

	private Class<?>[] getClassesToTest() {
		List<Class<?>> classesToTest = new ArrayList<Class<?>>();
		
//		Demo de como fazer JUnit para plugin no Eclipse
//		classesToTest.add(ASTRewriteExample.class);
		
//		Testes de org.zero.utils
		classesToTest.add(JUnitUtilsTests.class);
		classesToTest.add(StringUtilsTests.class);
		classesToTest.add(ListenerTests.class);
		
		classesToTest.add(TestSupportedBlockSintax.class);
		classesToTest.add(TestPackageParsing.class);
		classesToTest.add(JUnit3ParserTests.class);
		classesToTest.add(JUnit4ParserTests.class);
		classesToTest.add(AddExplanationToAssertionJUnit3Tests.class);
		classesToTest.add(AddExplanationToAssertionJUnit4Tests.class);
		classesToTest.add(ExtractInitializationMethodJUnit3Tests.class);
		classesToTest.add(ExtractInitializationMethodJUnit4Tests.class);
		classesToTest.add(ExtractFinalizationMethodJUnit3Tests.class);
		classesToTest.add(ExtractFinalizationMethodJUnit4Tests.class);
		classesToTest.add(AddFixtureJUnit3Tests.class);
//		classesToTest.add(CreateModelTestJUnit3Tests.class);
//		classesToTest.add(CreateModelTestJUnit4Tests.class);
		
		Class<?>[] result = new Class<?>[classesToTest.size()];
		
		classesToTest.toArray(result);

		return result;
	}

}
