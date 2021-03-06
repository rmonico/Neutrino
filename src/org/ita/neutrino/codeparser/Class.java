package org.ita.neutrino.codeparser;

import org.ita.neutrino.tparsers.abstracttestparser.TestMethod;

public interface Class extends Type {

	public Class getSuperClass();

	public NonAccessClassModifier getNonAccessModifier();

	public void removeTestMethod(TestMethod testMethod);
}
