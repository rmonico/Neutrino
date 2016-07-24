package org.ita.neutrino.tparsers.junit5parser;

import java.util.ArrayList;
import java.util.List;

import org.ita.neutrino.codeparser.Environment;
import org.ita.neutrino.codeparser.MutableMethod;
import org.ita.neutrino.codeparser.Type;
import org.ita.neutrino.eclipseaction.NotImplementedYetException;
import org.ita.neutrino.tparsers.abstracttestparser.TestMethod;

public class JUnitTestSuite extends org.ita.neutrino.tparsers.junitgenericparser.JUnitTestSuite {

	protected JUnitTestSuite() {
		super();
	}

	@Override
	protected JUnitTestMethod instantiateTestMethod() {
		return new JUnitTestMethod();
	}

	@Override
	protected List<JUnitTestMethod> instantiateMethodList() {
		return new ArrayList<JUnitTestMethod>();
	}

	protected JUnitFixture instantiateFixture() {
		return new JUnitFixture();
	};

	@Override
	protected List<JUnitFixture> instantiateFixtureList() {
		return new ArrayList<JUnitFixture>();
	}

	/**
	 * Devolve o método executado antes dos testes. Não há setter correspondente pois o createBeforeMethod já faz isso.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<JUnitTestMethod> getBeforeMethodList() {
		return (List<JUnitTestMethod>) super.getBeforeMethodList();
	}

	/**
	 * Devolve a lista de métodos de teste.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<JUnitTestMethod> getTestMethodList() {
		return (List<JUnitTestMethod>) super.getTestMethodList();
	}

	/**
	 * Devolve o método executado após os testes. Não há setter correspondente pois o createAfterMethod já faz isso.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<JUnitTestMethod> getAfterMethodList() {
		return (List<JUnitTestMethod>) super.getAfterMethodList();
	}

	@Override
	public JUnitTestBattery getParent() {
		return (JUnitTestBattery) super.getParent();
	}

	protected void setParent(JUnitTestBattery parent) {
		super.setParent(parent);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JUnitFixture> getFixtures() {
		return (List<JUnitFixture>) super.getFixtures();
	}

	@Override
	public JUnitTestMethod getMethodByName(String methodName) {
		return (JUnitTestMethod) super.getMethodByName(methodName);
	}

	@Override
	public TestMethod createNewBeforeTestsMethod() {
		String newMethodName = getNewBeforeMethodName();

		MutableMethod newMethod = getCodeElement().createNewMethod(newMethodName, 0);

		Environment environment = getCodeElement().getPackage().getParent();

		Type junit4BeforeAnnotation = (Type) environment.getTypeCache().get("org.junit.Before");

		newMethod.addAnnotation(junit4BeforeAnnotation);

		return parseBeforeMethod(newMethod);
	}

	private static final String defaultBeforeMethodName = "setUp";

	private String getNewBeforeMethodName() {
		boolean hasSetup = false;

		// Já há um método setup?
		for (TestMethod testMethod : getBeforeMethodList()) {
			if (testMethod.getName().equals(defaultBeforeMethodName)) {
				hasSetup = true;
				break;
			}
		}

		if (!hasSetup) {
			return defaultBeforeMethodName;
		}

		// TODO: colocar um número na frente do setup. Exemplo: setup1
		throw new NotImplementedYetException();
	}

	@Override
	public TestMethod createNewAfterTestsMethod() {
		String newMethodName = getNewAfterMethodName();

		MutableMethod newMethod = getCodeElement().createNewMethod(newMethodName, -1);

		Environment environment = getCodeElement().getPackage().getParent();

		Type junit4AfterAnnotation = (Type) environment.getTypeCache().get("org.junit.After");

		newMethod.addAnnotation(junit4AfterAnnotation);

		return parseAfterMethod(newMethod);
	}

	private static final String defaultAfterMethodName = "tearDown";

	private String getNewAfterMethodName() {
		boolean hasTeardown = false;

		// Já há um método teardown?
		for (TestMethod testMethod : getAfterMethodList()) {
			if (testMethod.getName().equals(defaultAfterMethodName)) {
				hasTeardown = true;
				break;
			}
		}

		if (!hasTeardown) {
			return defaultAfterMethodName;
		}

		// TODO: colocar um número na frente do setup. Exemplo: teardown1
		throw new NotImplementedYetException();
	}

	@Override
	public TestMethod createNewTestMethod(String newMethodName) {
		MutableMethod newMethod = getCodeElement().createNewMethod(newMethodName, -1);

		Environment environment = getCodeElement().getPackage().getParent();

		Type junit4TestAnnotation = (Type) environment.getTypeCache().get("org.junit.Test");

		newMethod.addAnnotation(junit4TestAnnotation);

		return parseTestMethod(newMethod);
	}

}
