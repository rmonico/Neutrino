package org.ita.testrefactoring.junitparser;

import java.util.ArrayList;
import java.util.List;

import org.ita.testrefactoring.abstracttestparser.TestBattery;
import org.ita.testrefactoring.codeparser.Environment;
import org.ita.testrefactoring.codeparser.Type;

public class JUnitTestBattery extends TestBattery {

	private List<JUnitTestSuite> suiteList = new ArrayList<JUnitTestSuite>();
	private Environment environment;

	JUnitTestBattery() {
	}

	@Override
	public List<JUnitTestSuite> getSuiteList() {
		return suiteList;
	}
	
	public JUnitTestSuite getSuiteByName(String suiteName) {
		if (suiteName == null) {
			return null;
		}
		
		for (JUnitTestSuite suite : suiteList) {
			if (suiteName.equals(suite.getName())) {
				return suite;
			}
		}
		
		return null;
	}

	JUnitTestSuite createSuite(Type type) {
		JUnitTestSuite suite = new JUnitTestSuite();

		suite.setParent(this);
		
		suite.setCodeElement(type);
		
		suiteList.add(suite);

		return suite;
	}

	@Override
	public Environment getCodeElement() {
		return environment;
	}
	
	void setCodeElement(Environment environment) {
		this.environment = environment;
	}

}
