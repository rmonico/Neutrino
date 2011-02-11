package org.ita.testrefactoring.ASTParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.ICompilationUnit;
import org.ita.testrefactoring.metacode.AbstractType;
import org.ita.testrefactoring.metacode.Environment;

public class ASTEnvironment extends Environment implements ASTWrapper<List<ICompilationUnit>> {
	
	private List<ASTPackage> packageList = new ArrayList<ASTPackage>();
	private Map<String, AbstractType> typeCache = new HashMap<String, AbstractType>();
	private List<ICompilationUnit> astObject;
	
	// Construtor restrito ao pacote
	ASTEnvironment() {
		
	}

	@Override
	public List<ASTPackage> getPackageList() {
		return packageList;
	}

	protected ASTPackage createPackage() {
		ASTPackage _package = new ASTPackage();
		_package.setParent(this);
		
		return _package;
	}

	@Override
	public Map<String, AbstractType> getTypeCache() {
		return typeCache;
	}

	@Override
	public void setASTObject(List<ICompilationUnit> astObject) {
		this.astObject = astObject;
	}

	@Override
	public List<ICompilationUnit> getASTObject() {
		return astObject;
	}	
}