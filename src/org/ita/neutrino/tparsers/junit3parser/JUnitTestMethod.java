package org.ita.neutrino.tparsers.junit3parser;

import java.util.ArrayList;
import java.util.List;

import org.ita.neutrino.tparsers.junitgenericparser.JUnitTestStatement;

public class JUnitTestMethod extends
    org.ita.neutrino.tparsers.junitgenericparser.JUnitTestMethod {

  @Override
  protected List<JUnitTestStatement> instantiateStatementList() {
    return new ArrayList<JUnitTestStatement>();
  }

  JUnitTestMethod() {
    super();
  }

  @Override
  protected JUnitAction instantiateAction() {
    return new JUnitAction();
  }

  protected JUnitAssertion instantiateAssertion() {
    return new JUnitAssertion();
  };

  @SuppressWarnings("unchecked")
  @Override
  public List<JUnitTestStatement> getStatements() {
    return (List<JUnitTestStatement>) super.getStatements();
  }

  @Override
  public JUnitTestSuite getParent() {
    return (JUnitTestSuite) super.getParent();
  }

  void setParent(JUnitTestSuite parent) {
    super.setParent(parent);
  }

  public boolean isAfterTestMethod() {
    return this.getName().equalsIgnoreCase("tearDown");
  }

  public boolean isBeforeTestMethod() {
    return this.getName().equalsIgnoreCase("setUp");
  }

  @Override
  public int compareTo(Object o) {
    JUnitTestMethod corrente = (JUnitTestMethod) o;
    Integer size = this.getStatements().size();
    return size.compareTo(corrente.getStatements().size());
  }

}
