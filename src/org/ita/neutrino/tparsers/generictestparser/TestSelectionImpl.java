package org.ita.neutrino.tparsers.generictestparser;

import org.ita.neutrino.codeparser.CodeSelection;
import org.ita.neutrino.tparsers.abstracttestparser.TestElement;
import org.ita.neutrino.tparsers.abstracttestparser.TestSelection;

class TestSelectionImpl implements TestSelection {
	
	private final TestElement<?> testElement;
	private final CodeSelection selection;
	
	public TestSelectionImpl(TestElement<?> testElement, CodeSelection selection) {
		this.testElement = testElement;
		this.selection = selection;
	}

	@Override
	public int getSelectionStart() {
		return selection.getSelectionStart();
	}

	@Override
	public void setSelectionStart(int i) {
		selection.setSelectionStart(i);
	}

	@Override
	public int getSelectionLength() {
		return selection.getSelectionLength();
	}

	@Override
	public void setSelectionLength(int i) {
		selection.setSelectionLength(i);
	}

	@Override
	public TestElement<?> getSelectedFragment() {
		return this.testElement;
	}

	@Override
	public void setSourceFile(Object sourceFile) {
		this.selection.setSourceFile(sourceFile);
	}
	
	@Override
	public Object getSourceFile() {
		return this.selection.getSourceFile();
	}
}
