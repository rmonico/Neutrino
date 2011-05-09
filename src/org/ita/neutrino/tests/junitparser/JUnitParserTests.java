package org.ita.neutrino.tests.junitparser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaModelException;
import org.ita.neutrino.abstracttestparser.TestParserException;
import org.ita.neutrino.astparser.ASTParser;
import org.ita.neutrino.codeparser.CodeElement;
import org.ita.neutrino.codeparser.ParserException;
import org.ita.neutrino.junit4parser.JUnitAction;
import org.ita.neutrino.junit4parser.JUnitAssertion;
import org.ita.neutrino.junit4parser.JUnitParser;
import org.ita.neutrino.junit4parser.JUnitTestBattery;
import org.ita.neutrino.junit4parser.JUnitTestMethod;
import org.ita.neutrino.junit4parser.JUnitTestStatement;
import org.ita.neutrino.junit4parser.JUnitTestSuite;
import org.ita.neutrino.tests.RefactoringAbstractTests;
import org.junit.Before;
import org.junit.Test;

public class JUnitParserTests extends RefactoringAbstractTests {
	
	private ASTParser codeParser;
	private JUnitTestBattery battery;
	private JUnitTestSuite suite;

	@Before
	public void setup() {
		// Não apaga o projeto de testes após rodar cada teste.
		setAlwaysDeleteTestProject(true);
	}
	
	private void prepareTests() throws JavaModelException, ParserException {
		StringBuilder mockClassCode = new StringBuilder();

		mockClassCode.append("package org.ita.neutrino.testfiles.junitparsertests;\n");
		mockClassCode.append("\n");
		mockClassCode.append("import static org.junit.Assert.assertTrue;\n");
		mockClassCode.append("\n");
		mockClassCode.append("import org.junit.After;\n");
		mockClassCode.append("import org.junit.Before;\n");
		mockClassCode.append("import org.junit.Test;\n");
		mockClassCode.append("\n");
		mockClassCode.append("public class MockClass {\n");
		mockClassCode.append("\n");
		mockClassCode.append("    private Object fixture0 = new Object();\n");
		mockClassCode.append("    private Object fixture1 = new Object();\n");
		mockClassCode.append("    \n");
		mockClassCode.append("    @Before\n");
		mockClassCode.append("    public void setup() {\n");
		mockClassCode.append("        action();\n");
		mockClassCode.append("    }\n");
		mockClassCode.append("    \n");
		mockClassCode.append("    @Test\n");
		mockClassCode.append("    public void testNothing0() {\n");
		mockClassCode.append("        action();\n");
		mockClassCode.append("        \n");
		mockClassCode.append("        assertTrue(\"Comment\", true);\n");
		mockClassCode.append("    }\n");
		mockClassCode.append("\n");
		mockClassCode.append("\n");
		mockClassCode.append("    @Test\n");
		mockClassCode.append("    public void testNothing1() {\n");
		mockClassCode.append("        action();\n");
		mockClassCode.append("        \n");
		mockClassCode.append("        assertTrue(\"Comment\", true);\n");
		mockClassCode.append("    }\n");
		mockClassCode.append("\n");
		mockClassCode.append("    \n");
		mockClassCode.append("    private void action() {\n");
		mockClassCode.append("        \n");
		mockClassCode.append("    }\n");
		mockClassCode.append("    \n");
		mockClassCode.append("    @After\n");
		mockClassCode.append("    public void teardown() {\n");
		mockClassCode.append("        \n");
		mockClassCode.append("    }\n");
		mockClassCode.append("}\n");

		ICompilationUnit mockCompilationUnit = createSourceFile("org.ita.neutrino.testfiles.junitparsertests", "MockClass.java", mockClassCode);
		
		codeParser = new ASTParser();
		
		codeParser.setActiveCompilationUnit(mockCompilationUnit);
		codeParser.setCompilationUnits(new ICompilationUnit[] {mockCompilationUnit});
		
		codeParser.parse();
	}
	
	@Test
	public void testTestParser() throws TestParserException, ParserException, JavaModelException {
		prepareTests(); 
		
		JUnitParser testParser = new JUnitParser();
		
		testParser.setEnvironment(codeParser.getEnvironment());
		
		testParser.parse();
		
		battery = testParser.getBattery();
		
		testBatteryParser();
		
		suite = battery.getSuiteByName("MockClass");
		
		testSuiteParser();

		testSuiteFixtureParser();
		
		testSuiteMethodParser();
		
		testBlockElementsParser();
		
		setTestsOk();
	}

	private void testBatteryParser() {
		assertNull("Bateria de testes: Parent", battery.getParent());
		assertEquals("Bateria de testes: Size of suite list", 1, battery.getSuiteList().size());
	}

	private void testSuiteParser() {
		assertEquals("Suite: parent", battery, suite.getParent());
		
		CodeElement expectedSuiteCodeElement = codeParser.getEnvironment().getTypeCache().get("org.ita.neutrino.testfiles.junitparsertests.MockClass");
		
		assertEquals("Suite: code element", expectedSuiteCodeElement, suite.getCodeElement());
	}

	private void testSuiteFixtureParser() {
		assertEquals("Suite: fixture list (size)", 2, suite.getFixtures().size());
		assertEquals("Suite: fixture 0", "fixture0", suite.getFixtures().get(0).getName());
		assertEquals("Suite: fixture 1", "fixture1", suite.getFixtures().get(1).getName());
	}

	private void testSuiteMethodParser() {
		assertEquals("Suite: before method list (size)", 1, suite.getBeforeMethodList().size());
		assertEquals("Suite: before method 0", "setup", suite.getBeforeMethodList().get(0).getName());
		
		
		assertEquals("Suite: after method list (size)", 1, suite.getAfterMethodList().size());
		assertEquals("Suite: after method 0", "teardown", suite.getAfterMethodList().get(0).getName());
		
		
		assertEquals("Suite: test method list (size)", 2, suite.getTestMethodList().size());
		
		assertEquals("Suite: test method 0", "testNothing0", suite.getTestMethodList().get(0).getName());
		assertEquals("Suite: test method 1", "testNothing1", suite.getTestMethodList().get(1).getName());
	}

	private void testBlockElementsParser() {
		JUnitTestMethod testNothing0 = suite.getMethodByName("testNothing0");
		
		List<JUnitTestStatement> statementList = testNothing0.getStatements();
		
		assertEquals("StatementList: size", 2, statementList.size());
		
		
		assertEquals("Action: classe", statementList.get(0).getClass(), JUnitAction.class);
		
		JUnitAction action = (JUnitAction) statementList.get(0);
		
		assertEquals("Action: valor", "action()", action.toString());
		
		
		assertEquals("Assertion: classe", statementList.get(1).getClass(), JUnitAssertion.class);
		
		JUnitAssertion assertion = (JUnitAssertion) statementList.get(1);
		
		assertEquals("Assertion: valor", "assertTrue(\"Comment\",true)", assertion.toString());
		
	}

}
